import cv2
import numpy as np
from ultralytics import YOLO
from scipy.spatial import distance

# Load the YOLO model
model = YOLO('best_model.pt')

# Open the video file
cap = cv2.VideoCapture("2024_Crescendo_Vid.avi")
assert cap.isOpened(), "Error reading video file"

# Video properties
w, h, fps = (int(cap.get(x)) for x in (cv2.CAP_PROP_FRAME_WIDTH, cv2.CAP_PROP_FRAME_HEIGHT, cv2.CAP_PROP_FPS))
video_writer = cv2.VideoWriter("speed_management.avi", cv2.VideoWriter_fourcc(*"mp4v"), fps, (w, h))

# Define a dictionary to store object IDs and previous coordinates for speed calculation
tracked_objects = {}
next_object_id = 0
speed_region = [(20, 400), (1080, 404), (1080, 360), (20, 360)]

while cap.isOpened():
    success, frame = cap.read()
    if not success:
        print("Video processing has been successfully completed.")
        break

    # Run YOLO inference on the frame
    results = model(frame)

    # Prepare a list to store current bounding box centers
    current_centers = []

    # Process detections
    for result in results:
        boxes = result.boxes
        for box in boxes:
            # Get bounding box coordinates
            x1, y1, x2, y2 = map(int, box.xyxy[0].cpu().numpy())
            center = ((x1 + x2) // 2, (y1 + y2) // 2)  # Calculate the center of the bounding box

            # Track the object and assign an ID if new
            matched_id = None
            for object_id, previous_center in tracked_objects.items():
                if distance.euclidean(center, previous_center) < 50:  # Match if within 50 pixels
                    matched_id = object_id
                    break

            if matched_id is None:
                matched_id = next_object_id
                next_object_id += 1

            # Calculate speed if the object has been seen before
            if matched_id in tracked_objects:
                prev_center = tracked_objects[matched_id]
                pixel_distance = distance.euclidean(center, prev_center)
                speed = pixel_distance * fps  # Speed in pixels per second
                cv2.putText(frame, f"Speed: {speed:.2f} px/s", (x1, y1 - 10), cv2.FONT_HERSHEY_SIMPLEX, 0.6, (0, 255, 0), 2)
                print(f"Object ID: {matched_id}, Speed: {speed:.2f} px/s")

            # Update the object's position
            tracked_objects[matched_id] = center

            # Draw bounding box and label
            cv2.rectangle(frame, (x1, y1), (x2, y2), (0, 255, 0), 2)
            cv2.putText(frame, f"ID: {matched_id}", (x1, y1 - 25), cv2.FONT_HERSHEY_SIMPLEX, 0.6, (255, 0, 0), 2)

    # Write the frame with bounding boxes and speed to the output video
    video_writer.write(frame)

    # Display the frame (optional for testing)
    cv2.imshow("Frame", frame)
    if cv2.waitKey(1) & 0xFF == ord("q"):
        break

# Release resources
cap.release()
video_writer.release()
cv2.destroyAllWindows()
