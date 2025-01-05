DROP DATABASE IF EXISTS task_board;
CREATE DATABASE task_board;
USE task_board;

CREATE TABLE `user` (
    user_id INT AUTO_INCREMENT,
    user_first_name VARCHAR(20) NOT NULL,
    user_last_name VARCHAR(20) NOT NULL,
    user_name VARCHAR(10) UNIQUE NOT NULL,
    user_password VARCHAR(255) NOT NULL,
    user_email VARCHAR(60) UNIQUE NOT NULL,
    PRIMARY KEY (user_id)
);

CREATE TABLE active_task (
    task_id INT AUTO_INCREMENT,
    user_id INT,
    task_description TEXT,
    task_title VARCHAR(255) NOT NULL,
    task_category ENUM(
        'WORK',
        'PERSONAL',
        'HEALTH',
        'EDUCATION',
        'SHOPPING',
        'FINANCE',
        'HOUSEHOLD',
        'SOCIAL',
        'TRAVEL',
        'MISCELLANEOUS'
    ) DEFAULT 'MISCELLANEOUS',
    task_start_time DATETIME NOT NULL,
    task_update_time DATETIME,
    task_schedule_time DATETIME NOT NULL,
    task_priority ENUM('LOW', 'MEDIUM', 'HIGH') DEFAULT 'MEDIUM',
    task_frequency ENUM('DAILY', 'WEEKLY', 'MONTHLY', 'ONCE') DEFAULT 'ONCE',
    PRIMARY KEY (task_id),
    FOREIGN KEY (user_id) REFERENCES `user` (user_id)
);

CREATE TABLE completed_task (
    task_id INT AUTO_INCREMENT,
    user_id INT,
    task_description TEXT,
    task_title VARCHAR(255) NOT NULL,
    task_category ENUM(
        'WORK',
        'PERSONAL',
        'HEALTH',
        'EDUCATION',
        'SHOPPING',
        'FINANCE',
        'HOUSEHOLD',
        'SOCIAL',
        'TRAVEL',
        'MISCELLANEOUS'
    ) DEFAULT 'MISCELLANEOUS',
    task_start_time DATETIME NOT NULL,
    task_update_time DATETIME,
    task_schedule_time DATETIME NOT NULL,
    task_priority ENUM('LOW', 'MEDIUM', 'HIGH') DEFAULT 'MEDIUM',
    task_frequency ENUM('DAILY', 'WEEKLY', 'MONTHLY', 'ONCE') DEFAULT 'ONCE',
    completed_time DATETIME NOT NULL,
    PRIMARY KEY (task_id),
    FOREIGN KEY (user_id) REFERENCES `user` (user_id)
);

DESC `user`;
DESC active_task;
DESC completed_task;

INSERT INTO `user` (user_first_name, user_last_name, user_name, user_password, user_email)
VALUES
('John', 'Doe', 'jdoe', 'password123', 'jdoe@example.com'),
('Jane', 'Smith', 'jsmith', 'securepass', 'jsmith@example.com'),
('Alice', 'Johnson', 'ajohnson', 'alice123', 'alicej@example.com'),
('Bob', 'Brown', 'bbrown', 'bob2023', 'bobb@example.com'),
('Charlie', 'Davis', 'cdavis', 'charliepass', 'cdavis@example.com'),
('Diana', 'Evans', 'devans', 'diana321', 'dianae@example.com'),
('Evan', 'Foster', 'efoster', 'evan456', 'evanf@example.com'),
('Fiona', 'Green', 'fgreen', 'fiona789', 'fionag@example.com'),
('George', 'Harris', 'gharris', 'george2024', 'georgeh@example.com'),
('Hannah', 'Iverson', 'hiverson', 'hannah123', 'hannah@example.com'),
('Ian', 'Jackson', 'ijackson', 'iansecure', 'ianj@example.com'),
('Julia', 'Knight', 'jknight', 'juliapass', 'juliak@example.com'),
('Kevin', 'Lewis', 'klewis', 'kevin789', 'kevinl@example.com'),
('Laura', 'Moore', 'lmoore', 'laura2023', 'lauram@example.com'),
('Michael', 'Nash', 'mnash', 'michaelsecure', 'michaeln@example.com'),
('Nina', 'Owen', 'nowen', 'nina123', 'ninao@example.com'),
('Oliver', 'Perry', 'operry', 'oliver456', 'oliverp@example.com'),
('Paula', 'Quinn', 'pquinn', 'paula789', 'paulaq@example.com'),
('Quincy', 'Roberts', 'qroberts', 'quincy2024', 'quincyr@example.com'),
('Rachel', 'Stewart', 'rstewart', 'rachel123', 'rachels@example.com');

INSERT INTO active_task (user_id, task_description, task_title, task_category, task_start_time, task_update_time, task_schedule_time, task_priority, task_frequency)
VALUES
(1, 'Complete the annual report', 'Annual Report', 'WORK', '2025-01-02 09:00:00', NULL, '2025-01-10 09:00:00', 'HIGH', 'ONCE'),
(2, 'Buy groceries for the week', 'Weekly Groceries', 'SHOPPING', '2025-01-02 17:00:00', NULL, '2025-01-03 10:00:00', 'MEDIUM', 'WEEKLY'),
(1, 'Attend yoga session', 'Yoga Class', 'HEALTH', '2025-01-02 07:00:00', NULL, '2025-01-02 07:00:00', 'LOW', 'DAILY'),
(4, 'Submit assignment for online course', 'Course Assignment', 'EDUCATION', '2025-01-01 14:00:00', NULL, '2025-01-05 23:59:00', 'HIGH', 'ONCE'),
(5, 'Plan birthday party for Alex', 'Birthday Party Planning', 'PERSONAL', '2025-01-02 10:00:00', NULL, '2025-01-08 18:00:00', 'MEDIUM', 'ONCE'),
(1, 'Pay electricity bill', 'Electricity Bill Payment', 'FINANCE', '2025-01-02 12:00:00', NULL, '2025-01-03 12:00:00',
'HIGH', 'MONTHLY'),
(7, 'Organize garage', 'Garage Cleaning', 'HOUSEHOLD', '2025-01-02 15:00:00', NULL, '2025-01-09 12:00:00', 'LOW', 'ONCE'),
(8, 'Prepare for hiking trip', 'Hiking Preparation', 'TRAVEL', '2025-01-02 18:00:00', NULL, '2025-01-10 08:00:00', 'MEDIUM', 'ONCE'),
(9, 'Call family to check in', 'Family Call', 'SOCIAL', '2025-01-02 20:00:00', NULL, '2025-01-02 20:00:00', 'LOW', 'DAILY'),
(10, 'Create budget for next month', 'Monthly Budget', 'FINANCE', '2025-01-02 10:00:00', NULL, '2025-01-31 15:00:00',
'MEDIUM', 'MONTHLY'),
(11, 'Prepare presentation for team meeting', 'Team Meeting Presentation', 'WORK', '2025-01-02 09:00:00', NULL, '2025-01-07 09:00:00', 'HIGH', 'ONCE'),
(12, 'Water the garden', 'Gardening', 'HOUSEHOLD', '2025-01-02 06:00:00', NULL, '2025-01-02 06:00:00', 'LOW', 'DAILY'),
(13, 'Read a new book on programming', 'Read Programming Book', 'EDUCATION', '2025-01-01 20:00:00', NULL, '2025-01-31 23:59:00', 'MEDIUM', 'ONCE'),
(1, 'Buy new shoes for gym', 'Gym Shoes Shopping', 'SHOPPING', '2025-01-02 16:00:00', NULL, '2025-01-06 12:00:00',
'LOW', 'ONCE'),
(15, 'Prepare for annual medical checkup', 'Medical Checkup Preparation', 'HEALTH', '2025-01-01 11:00:00', NULL, '2025-01-07 09:00:00', 'MEDIUM', 'ONCE'),
(1, 'Set up a home workout routine', 'Home Workout Setup', 'HEALTH', '2025-01-02 07:30:00', NULL, '2025-01-05 08:00:00',
 'MEDIUM', 'ONCE'),
(17, 'Schedule car maintenance', 'Car Maintenance', 'HOUSEHOLD', '2025-01-02 13:00:00', NULL, '2025-01-10 10:00:00', 'HIGH', 'ONCE'),
(18, 'Plan weekend outing with friends', 'Weekend Outing', 'SOCIAL', '2025-01-02 19:00:00', NULL, '2025-01-06 19:00:00', 'LOW', 'ONCE'),
(19, 'Prepare tax documents', 'Tax Preparation', 'FINANCE', '2025-01-02 10:30:00', NULL, '2025-01-30 10:00:00', 'HIGH', 'ONCE'),
(20, 'Write blog post on tech trends', 'Tech Blog Writing', 'WORK', '2025-01-02 14:00:00', NULL, '2025-01-04 18:00:00', 'MEDIUM', 'ONCE');

INSERT INTO completed_task (user_id, task_description, task_title, task_category, task_start_time, task_update_time, task_schedule_time, completed_time, task_priority, task_frequency)
VALUES
(1, 'Finish quarterly review report', 'Quarterly Review', 'WORK', '2024-12-01 09:00:00', NULL, '2024-12-10 17:00:00', '2024-12-10 16:45:00', 'HIGH', 'ONCE'),
(2, 'Shop for holiday gifts', 'Holiday Shopping', 'SHOPPING', '2024-12-15 12:00:00', NULL, '2024-12-20 18:00:00', '2024-12-20 17:50:00', 'MEDIUM', 'ONCE'),
(3, 'Complete daily workout', 'Morning Workout', 'HEALTH', '2024-12-10 07:00:00', NULL, '2024-12-10 07:30:00', '2024-12-10 07:25:00', 'LOW', 'DAILY'),
(4, 'Submit application for certification exam', 'Certification Application', 'EDUCATION', '2024-12-01 14:00:00', NULL, '2024-12-05 23:59:00', '2024-12-05 23:40:00', 'HIGH', 'ONCE'),
(5, 'Book tickets for concert', 'Concert Tickets', 'PERSONAL', '2024-12-02 10:00:00', NULL, '2024-12-03 12:00:00', '2024-12-03 11:50:00', 'MEDIUM', 'ONCE'),
(6, 'Pay credit card bill', 'Credit Card Payment', 'FINANCE', '2024-12-01 12:00:00', NULL, '2024-12-02 12:00:00', '2024-12-02 11:45:00', 'HIGH', 'MONTHLY'),
(7, 'Clean refrigerator', 'Fridge Cleaning', 'HOUSEHOLD', '2024-12-10 15:00:00', NULL, '2024-12-15 12:00:00', '2024-12-15 11:50:00', 'LOW', 'ONCE'),
(8, 'Confirm hotel reservation for vacation', 'Vacation Hotel Booking', 'TRAVEL', '2024-12-01 18:00:00', NULL, '2024-12-10 08:00:00', '2024-12-10 07:50:00', 'MEDIUM', 'ONCE'),
(9, 'Send holiday greetings to family', 'Holiday Greetings', 'SOCIAL', '2024-12-25 08:00:00', NULL, '2024-12-25 08:30:00', '2024-12-25 08:20:00', 'LOW', 'ONCE'),
(10, 'Review monthly budget', 'Budget Review', 'FINANCE', '2024-12-01 10:00:00', NULL, '2024-12-31 15:00:00', '2024-12-31 14:50:00', 'MEDIUM', 'MONTHLY'),
(11, 'Prepare slides for client presentation', 'Client Presentation', 'WORK', '2024-12-05 09:00:00', NULL, '2024-12-07 09:00:00', '2024-12-07 08:50:00', 'HIGH', 'ONCE'),
(12, 'Mow the lawn', 'Lawn Mowing', 'HOUSEHOLD', '2024-12-01 06:00:00', NULL, '2024-12-01 08:00:00', '2024-12-01 07:50:00', 'LOW', 'ONCE'),
(13, 'Complete online coding challenge', 'Coding Challenge', 'EDUCATION', '2024-12-01 20:00:00', NULL, '2024-12-31 23:59:00', '2024-12-31 23:45:00', 'MEDIUM', 'ONCE'),
(14, 'Purchase holiday decorations', 'Holiday Decorations', 'SHOPPING', '2024-12-10 16:00:00', NULL, '2024-12-15 12:00:00', '2024-12-15 11:45:00', 'LOW', 'ONCE'),
(15, 'Complete yearly health checkup', 'Health Checkup', 'HEALTH', '2024-12-01 11:00:00', NULL, '2024-12-07 09:00:00', '2024-12-07 08:45:00', 'MEDIUM', 'ONCE'),
(16, 'Setup new home gym equipment', 'Home Gym Setup', 'HEALTH', '2024-12-05 07:30:00', NULL, '2024-12-10 08:00:00', '2024-12-10 07:50:00', 'MEDIUM', 'ONCE'),
(17, 'Wash the car', 'Car Washing', 'HOUSEHOLD', '2024-12-10 13:00:00', NULL, '2024-12-15 10:00:00', '2024-12-15 09:50:00', 'LOW', 'ONCE'),
(18, 'Host dinner party for friends', 'Dinner Party', 'SOCIAL', '2024-12-20 19:00:00', NULL, '2024-12-21 22:00:00', '2024-12-21 21:50:00', 'LOW', 'ONCE'),
(19, 'Submit financial documents for loan', 'Loan Application', 'FINANCE', '2024-12-01 10:30:00', NULL, '2024-12-10 10:00:00', '2024-12-10 09:50:00', 'HIGH', 'ONCE'),
(20, 'Write report on market trends', 'Market Trends Report', 'WORK', '2024-12-01 14:00:00', NULL, '2024-12-04 18:00:00', '2024-12-04 17:50:00', 'MEDIUM', 'ONCE');

SELECT * FROM `user`;
SELECT * FROM active_task;
SELECT * FROM completed_task;
