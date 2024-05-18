-- Run this file to initialize dummy data for DB

INSERT INTO user (id, username, email, password, is_verified, profile_picture_url, created_at)
VALUES (1, 'tomdoeslinux', 'todoescode@gmail.com', 'password', TRUE, 'profile_pic.png', NOW());

INSERT INTO creation (title, description, cover_image_url, user_id)
VALUES ('Beautiful Art', 'A beautiful pixel art creation', 'pixel_art_1.png', 1);

INSERT INTO creation (title, description, cover_image_url, user_id)
VALUES ('Pixel Piece', 'An amazing pixel art piece', 'pixel_art_2.png', 1);

INSERT INTO creation (title, description, cover_image_url, user_id)
VALUES ('Green Horizon', 'A stunning piece of pixel art', 'pixel_art_3.png', 1);

INSERT INTO creation (title, description, cover_image_url, user_id)
VALUES ('New Beginnings', 'A wonderful example of pixel art', 'pixel_art_4.png', 1);

INSERT INTO creation (title, description, cover_image_url, user_id)
VALUES ('Sunset on the Horizon', 'An extraordinary pixel art creation', 'pixel_art_5.png', 1);