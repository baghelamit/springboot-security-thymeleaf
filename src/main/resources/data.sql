insert into role (role_name) values ("ADMIN")
insert into role (role_name) values ("USER")
insert into user (confirm_password, date_of_birth, email, first_name, gender, image, last_name, password, user_name) values ("$2a$10$7lv7PzqN0dlxmmXiKZFzFO9pN0HDApRDAtrwpgt2Af7mxRuCpvbn.", "1977-09-09", "admin@yahoo.com", "Jhon", "MALE", null, "Doe", "$2a$10$7lv7PzqN0dlxmmXiKZFzFO9pN0HDApRDAtrwpgt2Af7mxRuCpvbn.", "admin")
insert into user_role (user_id, role_id) values (1, 1)