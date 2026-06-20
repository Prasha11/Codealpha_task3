# 🏨 Hotel Reservation System

A console-based Hotel Reservation System built in Java using Object-Oriented Programming (OOP) principles. This project was developed as part of a Java internship task.

---

## 📋 Features

- 🔍 Search available rooms by category (Standard, Deluxe, Suite)
- 📅 Book a room with guest details and check-in/check-out dates
- 💰 Auto-calculates total amount based on nights × price per night
- 🧾 View booking details by Booking ID
- ❌ Cancel a booking with confirmation
- 💳 Simulate payment (Credit Card, Debit Card, Cash, UPI)
- 🧾 Print payment receipt after successful payment
- 📋 View all bookings in a summary table

---

## 🏗️ Project Structure

```
HotelReservationSystem/
├── src/
│   ├── Main.java         → Entry point, full menu loop
│   ├── Hotel.java        → Core logic (rooms, bookings management)
│   ├── Room.java         → Room model (number, category, price, availability)
│   ├── Booking.java      → Booking model (guest info, dates, payment status)
│   └── Display.java      → All console output (tables, receipts, menus)
├── .gitignore
└── README.md
```

---

## 🏨 Room Categories & Pricing

| Category  | Room Numbers | Price / Night |
|-----------|-------------|---------------|
| Standard  | 101 – 105   | $80.00        |
| Deluxe    | 201 – 204   | $150.00       |
| Suite     | 301 – 302   | $300.00       |

---

## ⚙️ Requirements

- Java JDK 17 or higher
- Any terminal / VS Code / IntelliJ IDEA

---

## 🚀 How to Compile & Run

### Step 1 — Navigate to the src folder
```bash
cd HotelReservationSystem/src
```

### Step 2 — Compile all Java files
```bash
javac *.java
```

### Step 3 — Run the program
```bash
java Main
```

---

## 🖥️ Menu Options

```
1. Search Available Rooms
2. Book a Room
3. View My Booking
4. Cancel Booking
5. Make Payment
6. View All Bookings
7. Exit
```

---

## 📌 Sample Usage

```
Enter your choice: 2

-- BOOK A ROOM --
Guest Name   : Ravi Kumar
Phone Number : 9876543210
Enter Room Number: 201
Check-In  (yyyy-MM-dd): 2026-06-01
Check-Out (yyyy-MM-dd): 2026-06-04

✔ Booking Confirmed!
Booking ID    : #1001
Guest Name    : Ravi Kumar
Room          : 201 (DELUXE)
Check-In      : 2026-06-01
Check-Out     : 2026-06-04
Nights        : 3
Total Amount  : $450.00
Payment       : PENDING
```

---

## 💳 Payment Methods Supported

- Credit Card
- Debit Card
- Cash
- UPI

---

## 🧱 OOP Concepts Used

- **Encapsulation** — private fields with getters/setters in all model classes
- **Classes & Objects** — Room, Booking, Hotel, Display
- **Enums** — `Room.Category` (STANDARD, DELUXE, SUITE)
- **ArrayList** — dynamic storage of rooms and bookings
- **Stream API** — filtering available rooms by category

---

## 👨‍💻 Author

- **Name:** Your Name Here
- **Internship Task:** Task 4 — Hotel Reservation System
- **Language:** Java (Console-based)

---

## 📄 License

This project is for educational/internship purposes.
