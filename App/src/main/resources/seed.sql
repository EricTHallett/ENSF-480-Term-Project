PRAGMA foreign_keys = ON;

CREATE TABLE User (
    email TEXT PRIMARY KEY,
    role TEXT NOT NULL,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    is_active INTEGER NOT NULL DEFAULT 1,
    promotion_sent_on TEXT
);

--CREATE TABLE Customer (
--    user_email TEXT PRIMARY KEY REFERENCES User(email),
--    promotion_sent_on TEXT
--);

--CREATE TABLE FlightAgent (
--    user_email TEXT PRIMARY KEY REFERENCES User(email)
--);
--
--CREATE TABLE SystemAdministrator (
--    user_email TEXT PRIMARY KEY REFERENCES User(email)
--);

CREATE TABLE Aircraft (
    id INTEGER PRIMARY KEY,
    aircraft_model TEXT NOT NULL,
    seats INTEGER NOT NULL,
    is_active INTEGER NOT NULL DEFAULT 1
);

CREATE TABLE Flight (
    id INTEGER PRIMARY KEY,
    airline_code INTEGER,
    aircraft_id INTEGER REFERENCES Aircraft(id),
    departure_time TEXT NOT NULL,
    arrival_time TEXT NOT NULL,
    total_seats INTEGER,
    available_seats INTEGER NOT NULL,
    price_cents INTEGER NOT NULL,
    origin TEXT NOT NULL,
    destination TEXT NOT NULL, 
    status TEXT NOT NULL,
    UNIQUE(aircraft_id, departure_time)
);

CREATE TABLE Reservation (
    passenger_email INTEGER NOT NULL,
    flight_id INTEGER NOT NULL,
    num_seats INTEGER NOT NULL,
    price_cents INTEGER NOT NULL,
    status TEXT NOT NULL,
    payment_strategy TEXT,
    PRIMARY KEY (passenger_email, flight_id),
    FOREIGN KEY (passenger_email) REFERENCES User(email),
    FOREIGN KEY (flight_id) REFERENCES Flight(id)
);

INSERT INTO User (email, role, first_name, last_name, is_active, promotion_sent_on) VALUES
    ("john.doe@email.com", "CUSTOMER", "John", "Doe", 1, "2026-10-30"),
    ("jane.doe@email.com", "FLIGHT_AGENT", "Jane", "Doe", 1, "2026-11-26"),
    ("tim.ronald@email.com", "SYSTEM_ADMINISTRATOR", "Time", "Ronald", 1, "2026-11-26")
;

INSERT INTO Aircraft (id, aircraft_model, seats, is_active) VALUES 
    (1, "Max 747", 327, 1)
;

INSERT INTO Flight (id, airline_code, aircraft_id, departure_time, arrival_time, total_seats, available_seats, price_cents, origin, destination, status) VALUES
    (1, "ACA", 1, "2026-12-01T12:00:00", "2026-12-01T13:30:00", 327, 327, 10000, "Calgary", "Vancouver", "SCHEDULED")
;

INSERT INTO Reservation (passenger_email, flight_id, num_seats, price_cents, status, payment_strategy) VALUES
    ("john.doe@email.com", 1, 1, 10000, "RESERVED", NULL)
;
