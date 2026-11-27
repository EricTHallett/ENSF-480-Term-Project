PRAGMA foreign_keys = ON;

CREATE TABLE User (
    email TEXT PRIMARY KEY,
    role TEXT NOT NULL,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    is_active INTEGER NOT NULL DEFAULT 1,
    promotion_sent_on TEXT,
    payment_strategy TEXT
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

CREATE TABLE Airline (
    airline_code TEXT PRIMARY KEY,
    name TEXT NOT NULL,
    is_active INTEGER NOT NULL DEFAULT 1
);

CREATE TABLE Route (
    id INTEGER PRIMARY KEY,
    origin TEXT NOT NULL,
    destination TEXT NOT NULL,
    is_active INTEGER NOT NULL DEFAULT 1,
    UNIQUE(origin, destination)
);

CREATE TABLE Aircraft (
    id INTEGER PRIMARY KEY,
    aircraft_model TEXT NOT NULL,
    seats INTEGER NOT NULL,
    is_active INTEGER NOT NULL DEFAULT 1
);

CREATE TABLE Flight (
    id INTEGER PRIMARY KEY,
    airline_code INTEGER REFERENCES Airline(airline_code),
    route_id INTEGER REFERENCES Route(id),
    aircraft_id INTEGER REFERENCES Aircraft(id),
    departure_time TEXT NOT NULL,
    arrival_time TEXT NOT NULL,
    total_seats INTEGER REFERENCES Aircraft(seats),
    available_seats INTEGER NOT NULL,
    price_cents INTEGER NOT NULL,
    status TEXT NOT NULL,
    UNIQUE(aircraft_id, departure_time)
);

CREATE TABLE Reservation (
    passenger_email INTEGER NOT NULL,
    flight_id INTEGER NOT NULL,
    num_seats INTEGER NOT NULL,
    price_cents INTEGER NOT NULL,
    status TEXT,
    PRIMARY KEY (passenger_email, flight_id),
    FOREIGN KEY (passenger_email) REFERENCES User(passenger_email),
    FOREIGN KEY (flight_id) REFERENCES Flight(flight_id)
);

INSERT INTO User (email, role, first_name, last_name, is_active, promotion_sent_on, payment_strategy) VALUES
    ("john.doe@email.com", "CUSTOMER", "John", "Doe", 1, "2026-10-30", NULL),
    ("jane.doe@email.com", "FLIGHT_AGENT", "Jane", "Doe", 1, "2026-11-26", NULL)
;
