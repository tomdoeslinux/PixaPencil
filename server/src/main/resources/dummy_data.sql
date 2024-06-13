-- Run this file to initialize dummy data for DB

INSERT INTO users (username, email, password, is_verified, profile_picture_url, created_at)
VALUES ('tomdoeslinux', 'todoescode@gmail.com', 'password', TRUE, 'profile_pic.png', NOW());

INSERT INTO creations (title, description, image_key, user_id)
VALUES
    ('Beautiful Art', 'A beautiful pixel art creation', 'pixel_art_1.png', 1),
    ('Pixel Piece', 'An amazing pixel art piece', 'pixel_art_2.png', 1),
    ('Green Horizon', 'A stunning piece of pixel art', 'pixel_art_3.png', 1),
    ('New Beginnings', 'A wonderful example of pixel art', 'pixel_art_4.png', 1),
    ('Sunset on the Horizon', 'An extraordinary pixel art creation', 'pixel_art_5.png', 1);

INSERT INTO comments (user_id, creation_id, text)
VALUES
    (1, 1, 'This is an amazing creation! Really loved the details.'),
    (1, 2, 'Fantastic work! The colors are so vibrant.'),
    (1, 3, 'Great job on this one. Looks really impressive!'),
    (1, 4, 'I appreciate the effort you put into this creation. Well done!'),
    (1, 5, 'Superb! This is my favorite so far.');