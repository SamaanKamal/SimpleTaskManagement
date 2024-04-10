CREATE DATABASE task_management;

USE task_management;

CREATE TABLE Event (
    event_id INT PRIMARY KEY AUTO_INCREMENT,
    summary varchar(255),
    description varchar(255),
    start_datetime DATETIME NOT NULL,
    end_datetime DATETIME NOT NULL,
    location varchar(255),
    status varchar(50),
    organizer_id INT,
    creator_id INT,
    visibility varchar(50),
    creation_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
	-- FOREIGN KEY (creator_id) REFERENCES Creator(creator_id),
--     FOREIGN KEY (organizer_id) REFERENCES Organizor(organizer_id)
);

CREATE TABLE Attendee (
    attendee_id INT PRIMARY KEY AUTO_INCREMENT,
    event_id INT,
    email varchar(255),
    display_name varchar(255),
	resource boolean,
	optional boolean,
	response_Status varchar(50),
	comment varchar(255),
	additional_Guests INT,
    self boolean,
    FOREIGN KEY (event_id) REFERENCES Event(event_id)
);
CREATE TABLE Organizer (
    organizer_id INT PRIMARY KEY AUTO_INCREMENT,
    email varchar(255),
    display_name varchar(255),
    self boolean
);


CREATE TABLE Creator (
	creator_id INT PRIMARY KEY AUTO_INCREMENT,
    email varchar(255),
    display_name VARCHAR(255),
    self boolean
);

CREATE TABLE attachment(
  id INT PRIMARY KEY AUTO_INCREMENT,
  file_Url varchar(255),
  title varchar(100),
  mime_Type varchar(100),
  icon_Link varchar(255),
  file_Id varchar(100),
  event_id INT,
  FOREIGN KEY (event_id) REFERENCES Event(event_id)
);


ALTER TABLE Event ADD FOREIGN KEY (creator_id) REFERENCES Creator(creator_id);
ALTER TABLE Event ADD FOREIGN KEY (organizer_id) REFERENCES Organizer(organizer_id);


