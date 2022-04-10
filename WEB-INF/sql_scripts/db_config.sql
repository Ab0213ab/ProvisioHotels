-- Andrew Schaefer
-- Parrish Ward
-- Mitchell Kwon
-- Caleb Mastromonaco
-- Cameron Frison
-- Jacob Breault

--- 3/20/22

-- CSD460

-- Module 5.1 Provisio Database 

-- Drop database if exists:
DROP DATABASE IF EXISTS provisio;

-- Create database
CREATE DATABASE provisio;

-- Create tables
CREATE TABLE provisio.users (
	id int NOT NULL AUTO_INCREMENT,
	first_name nvarchar(255) NOT NULL,
	last_name nvarchar(255) NOT NULL,
	email nvarchar(255) NOT NULL,
	password nvarchar(64) NOT NULL,
	salt nvarchar(32) NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE provisio.user_provisio_points (
	id int NOT NULL AUTO_INCREMENT,
	user_id int NOT NULL,
	points int NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(user_id) REFERENCES provisio.users(id)
);

CREATE TABLE provisio.reservation_locations(
	id int NOT NULL AUTO_INCREMENT,
	location_name nvarchar(255),
	PRIMARY KEY(id)
);

CREATE TABLE provisio.reservation_amenities(
	id int NOT NULL AUTO_INCREMENT,
	amenity_name nvarchar(255),
	amenity_cost decimal(6,2),
	PRIMARY KEY(id)
);

CREATE TABLE provisio.reservation_guest_costs(
	id int NOT NULL AUTO_INCREMENT,
	number_of_guests nvarchar(255),
	cost double NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE provisio.user_reservations(
    id int NOT NULL AUTO_INCREMENT,
    user_id int NOT NULL,
    provisio_location_id int NOT NULL,
    amenity_id int NOT NULL,
    bedding_id int NOT NULL,
    guest_cost_id int NOT NULL,
    check_in_date date,
    check_out_date date,
    reservation_date date,
    ip nvarchar(45) NOT NULL,
    number_of_nights int NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(user_id) REFERENCES provisio.users(id),
    FOREIGN KEY(provisio_location_id) REFERENCES provisio.reservation_locations(id),
    FOREIGN KEY(amenity_id) REFERENCES provisio.reservation_amenities(id),
    FOREIGN KEY(guest_cost_id) REFERENCES provisio.reservation_guest_costs(id)
);

-- Populate the database

-- Create three users:
INSERT INTO provisio.users (
	first_name,
	last_name,
	email,
	password,
	salt
) VALUES (
	"Bob",
	"McRib",
	"bobmcrib@gmail.com",
	"eb5a242ab73622ff9c6820dccf6099dbf78efacc350a4b362637ec6e25a83cfe",
	"1gSlwc96ppwrqe1SBiE_bAw8i6-V13c4"
);

INSERT INTO provisio.users (
	first_name,
	last_name,
	email,
	password,
	salt
) VALUES (
	"John",
	"Doe",
	"johndoe@gmail.com",
	"7abc37cd192a1151a2d3dbcef2c1d6f750daf199e699b72c7061ce967a9d4518",
	"ir0pSBl22fxdyN-pq1ySQzN61HrBQDGB"
);

INSERT INTO provisio.users (
	first_name,
	last_name,
	email,
	password,
	salt
) VALUES (
	"Bill",
	"Goat",
	"billgoat@gmail.com",
	"03f2ea674c4f3164f4bde4ac94c423e8e7601ae7ed42fa7e8ad50943648f0987",
	"q_NeOqp1nvvzHgwuNZTnHvOqwAOw49fw"
);

-- Populate user points:
INSERT INTO provisio.user_provisio_points (
	user_id,
	points
) VALUES (
	1,
	3
);

INSERT INTO provisio.user_provisio_points (
	user_id,
	points
) VALUES (
	2,
	4
);

INSERT INTO provisio.user_provisio_points (
	user_id,
	points
) VALUES (
	3,
	5
);

-- Reservation locations:
INSERT INTO provisio.reservation_locations (
	location_name
) VALUES (
	"Lake Garda, Italy"
);

INSERT INTO provisio.reservation_locations (
	location_name
) VALUES (
	"Marrakesh, Morocco"
);

INSERT INTO provisio.reservation_locations (
	location_name
) VALUES (
	"Rio de Janeiro, Brazil"
);

-- Reservation amenities
INSERT INTO provisio.reservation_amenities (
	amenity_name,
	amenity_cost
) VALUES (
	"Wi-Fi",
	12.99
);

INSERT INTO provisio.reservation_amenities (
	amenity_name,
	amenity_cost
) VALUES (
	"Breakfast",
	8.99
);

INSERT INTO provisio.reservation_amenities (
	amenity_name,
	amenity_cost
) VALUES (
	"Parking",
	19.99
);

-- Reservation Guest Costs
INSERT INTO provisio.reservation_guest_costs (
	number_of_guests,
	cost
) VALUES (
	"1-2",
	115.0
);

INSERT INTO provisio.reservation_guest_costs (
	number_of_guests,
	cost
) VALUES (
	"3-4",
	130.0
);

INSERT INTO provisio.reservation_guest_costs (
	number_of_guests,
	cost
) VALUES (
	"5",
	150.0
);

-- User reservations:
INSERT INTO provisio.user_reservations (
	user_id,
	provisio_location_id,
	amenity_id,
	guest_cost_id,
	check_in_date,
	check_out_date,
	reservation_date,
	ip
) VALUES (
	1,
	1,
	1,
	1,
	STR_TO_DATE('2022-31-03 00:00:00', '%Y-%d-%m %H:%i:%s'),
	STR_TO_DATE('2022-03-04 00:00:00', '%Y-%d-%m %H:%i:%s'),
	CURRENT_DATE(),
	'127.0.0.1' -- I'm not going to put a real IP
);

INSERT INTO provisio.user_reservations (
	user_id,
	provisio_location_id,
	amenity_id,
	guest_cost_id,
	check_in_date,
	check_out_date,
	reservation_date,
	ip
) VALUES (
	2,
	2,
	2,
	2,
	STR_TO_DATE('2022-31-03 00:00:00', '%Y-%d-%m %H:%i:%s'),
	STR_TO_DATE('2022-03-04 00:00:00', '%Y-%d-%m %H:%i:%s'),
	CURRENT_DATE(),
	'127.0.0.1' -- I'm not going to put a real IP
);

INSERT INTO provisio.user_reservations (
	user_id,
	provisio_location_id,
	amenity_id,
	guest_cost_id,
	check_in_date,
	check_out_date,
	reservation_date,
	ip
) VALUES (
	3,
	3,
	3,
	3,
	STR_TO_DATE('2022-31-03 00:00:00', '%Y-%d-%m %H:%i:%s'),
	STR_TO_DATE('2022-03-04 00:00:00', '%Y-%d-%m %H:%i:%s'),
	CURRENT_DATE(),
	'127.0.0.1' -- I'm not going to put a real IP
);