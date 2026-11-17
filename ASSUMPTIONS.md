# Flight Reservation System: Assumptions and Questions

Tags: [Inferred] follows from the handout or rubrics. [Choice] means the documents are silent and we pick the simplest option. Q# links to a TA question.

## Assumptions

### A. Roles and Access
1. Role chosen first. The GUI opens with Customer, Flight Agent or System Admin. [Inferred]
2. Login by email only. The handout asks for a login activity diagram but defines no credentials, so there are no passwords. [Choice] Q2
3. Staff are pre-seeded. No screen creates Agents or Admins, because "Add new customer profiles" is the only account-creation feature listed. [Inferred] Q3
4. Customer self-registration is the same operation as Add Customer Profile. After registering, the user returns to the login screen. [Choice] Q6
5. Customers can search, view flight details, make, cancel and modify bookings, and view reservations and booking history. Modifying their own bookings is a Choice. [Inferred] Q5
6. Agents add, view, update and remove customers, modify reservations, and view the schedule. They also search, book and cancel for customers. [Partly inferred, partly Choice] Q5
7. An Agent selects a customer before acting on a booking. [Choice]
8. Admins add, update and remove flights and update schedules only, with no customer or booking access. [Inferred]

### B. Flights and Search
9. Search criteria (origin, destination, date, airline) are each optional and can be combined. [Choice]
10. Results show at least seats, flight time and price. Flight time shows departure and arrival. Extra fields such as flight number and airline are our picks. [Inferred minimum]
11. The details view adds aircraft model, capacity and status. [Choice]
12. Seats are a count, with no seat map. [Choice] Q9
13. Aircraft is a small entity (model, capacity). Routes are origin and destination fields on Flight, with no standalone screens. [Choice] Q3
14. One-way flights only, with one fixed price per flight. [Choice]
15. Past flights can't be booked. [Choice]
16. A flight with active reservations can't be removed. [Choice]
17. The schedule view is a list of flights by date, for Agents and Admins. [Inferred]

### C. Bookings
18. Booking operations are make, modify, cancel and generate confirmation info. [Inferred]
19. Booking history includes cancelled reservations. [Inferred]
20. One reservation covers one flight and one or more seats or passengers. [Choice]
21. A customer profile must exist before booking. [Choice]
22. Confirmation is shown on screen and stored, with no email. [Choice]
23. Modify covers passenger details or date only. [Choice]

### D. Payment
24. Payment happens during booking but has its own entity, control class and state diagram. [Inferred]
25. Payment is simulated. It stores a masked card and the amount, and succeeds if the fields are valid. A failure leaves the reservation unconfirmed. [Choice]
26. Payment records are kept. [Inferred]
27. Cancel creates a refund record. A fare difference on modify is one extra payment. [Choice] Q12

### E. Customers
28. Customer operations are add, view, update and remove. [Inferred]
29. Email is unique and read-only after creation. [Choice]
30. Customers can view and edit their own profile. [Choice] Q6

### F. Promotions
31. Promotion news goes to Customers only. [Inferred]
32. It is a simulated message, with no email. [Inferred]
33. It appears as a popup at login, on a customer's first login of each calendar month. [Choice] Q4
34. A demo date override lets us show the popup on any date. [Choice]

### G. Data
35. SQLite database with seed data. [Choice]
36. Soft deletion only. [Choice] Q7

### H. Architecture and Implementation
37. Standalone single-user Java app with a 3-layer design, using Swing. [Inferred, except Swing which is a Choice]
38. At least three design patterns are implemented and identified. [Inferred] Q11
39. Code is written from the class diagram, so the two match. [Inferred]

### I. Documentation and Submission
40. One report as a PDF containing every design-rubric item. [Choice] Q1
41. Exactly four activity diagrams: login, browse/select, book and payment. [Inferred]
42. One scenario per use case, including include and extend ones. Objects get a single underline and operations a double underline. [Choice] Q8
43. At least four interaction diagrams (we do five), each driven by a control class. [Inferred]
44. Four state diagrams: system, payment, reservation and flight. The system diagram uses app-level states. [Inferred, with the app-level states a Choice]
45. The class diagram is in two parts. Page 1 shows relationships, multiplicities and stereotypes only. Later pages list attributes and operations alphabetically. [Inferred]
46. The package diagram shows dependencies and the classes in each package with public ones marked, plus a text table. [Inferred]
47. UML omits Java and Swing library classes, getters, setters and constructors, but keeps our own boundary classes. [Inferred]
48. Five members each demo their own part. An absent member gets zero for implementation. [Inferred, with the five from the team]
49. Submission is one zip with a jar and a source zip, due Mon Nov 30, 2 PM. The demo schedule is on D2L. [Inferred]

## Outstanding Questions for the TAs

### High Priority
1. Where is the design report submitted (D2L separately, or inside the zip), and in what format?
2. Is email-only login with no password acceptable? Is registration expected?
3. Is it fine to pre-seed Agents and Admins? Are Route and Aircraft expected as separately managed entities, or are fields on Flight acceptable?
4. Is a popup on a customer's first login each month acceptable for "first day of each month"? Or must it key off the 1st exactly?
5. Can Agents also search, book and cancel on behalf of customers, or only modify? Can Customers modify their own reservations?
6. May customers register themselves and edit their own profile?
7. Is soft deletion acceptable for "remove" customers or flights, given that they disappear from every screen?

### Medium Priority
8. Do include and extend use cases need their own scenarios?
9. Is an available-seat count enough, or is seat selection expected?
10. What JDK version will be used? Should the SQLite driver and database file be inside the jar or alongside it? Do we run on our own machine at the demo?
11. Where must design patterns be identified: the report, code comments or the demo?

### Low Priority
12. Are refund records and fare-difference payments needed on cancel and modify, or is a plain payment record enough?
