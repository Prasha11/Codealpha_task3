import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) {
        Hotel hotel = new Hotel("Grand Java Hotel");
        Display.header(hotel.getHotelName());

        boolean running = true;
        while (running) {
            Display.menu();
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1": searchRooms(hotel);    break;
                case "2": bookRoom(hotel);       break;
                case "3": viewBooking(hotel);    break;
                case "4": cancelBooking(hotel);  break;
                case "5": makePayment(hotel);    break;
                case "6": Display.showAllBookings(hotel.getAllBookings()); break;
                case "7":
                    running = false;
                    System.out.println("\n  Thank you for choosing " + hotel.getHotelName() + ". Goodbye!\n");
                    break;
                default:
                    System.out.println("  Invalid choice. Please enter 1-7.");
            }
        }
        sc.close();
    }

    // ── 1. Search Rooms ──────────────────────────────────────────
    static void searchRooms(Hotel hotel) {
        System.out.println("\n  Select category:");
        System.out.println("  1. Standard  ($80/night)");
        System.out.println("  2. Deluxe    ($150/night)");
        System.out.println("  3. Suite     ($300/night)");
        System.out.println("  4. All Available Rooms");
        System.out.print("  Enter choice: ");
        String c = sc.nextLine().trim();
        switch (c) {
            case "1":
                Display.showRooms(hotel.getAvailableRooms(Room.Category.STANDARD), "STANDARD ROOMS"); break;
            case "2":
                Display.showRooms(hotel.getAvailableRooms(Room.Category.DELUXE), "DELUXE ROOMS"); break;
            case "3":
                Display.showRooms(hotel.getAvailableRooms(Room.Category.SUITE), "SUITE ROOMS"); break;
            case "4":
                Display.showRooms(hotel.getAllAvailableRooms(), "ALL AVAILABLE ROOMS"); break;
            default:
                System.out.println("  Invalid choice.");
        }
    }

    // ── 2. Book Room ─────────────────────────────────────────────
    static void bookRoom(Hotel hotel) {
        System.out.println("\n  -- BOOK A ROOM --");

        System.out.print("  Guest Name   : ");
        String name = sc.nextLine().trim();
        if (name.isEmpty()) { System.out.println("  Name cannot be empty."); return; }

        System.out.print("  Phone Number : ");
        String phone = sc.nextLine().trim();
        if (phone.isEmpty()) { System.out.println("  Phone cannot be empty."); return; }

        // Show available rooms
        Display.showRooms(hotel.getAllAvailableRooms(), "AVAILABLE ROOMS");

        System.out.print("  Enter Room Number: ");
        int roomNo;
        try { roomNo = Integer.parseInt(sc.nextLine().trim()); }
        catch (NumberFormatException e) { System.out.println("  Invalid room number."); return; }

        LocalDate checkIn  = readDate("  Check-In  (yyyy-MM-dd): ");
        if (checkIn == null) return;
        LocalDate checkOut = readDate("  Check-Out (yyyy-MM-dd): ");
        if (checkOut == null) return;

        if (!checkOut.isAfter(checkIn)) {
            System.out.println("  Check-out must be after check-in."); return;
        }

        Booking b = hotel.makeBooking(name, phone, roomNo, checkIn, checkOut);
        if (b == null) {
            System.out.println("  Booking failed. Room may be unavailable or not found.");
            return;
        }

        System.out.println("\n  ✔ Booking Confirmed!");
        b.printDetails();
        System.out.println("  Tip: Use option 5 to make payment for booking #" + b.getBookingId());
    }

    // ── 3. View Booking ──────────────────────────────────────────
    static void viewBooking(Hotel hotel) {
        System.out.print("\n  Enter Booking ID: ");
        try {
            int id = Integer.parseInt(sc.nextLine().trim());
            Booking b = hotel.findBooking(id);
            if (b == null) { System.out.println("  Booking not found."); return; }
            b.printDetails();
        } catch (NumberFormatException e) {
            System.out.println("  Invalid ID.");
        }
    }

    // ── 4. Cancel Booking ────────────────────────────────────────
    static void cancelBooking(Hotel hotel) {
        System.out.print("\n  Enter Booking ID to cancel: ");
        try {
            int id = Integer.parseInt(sc.nextLine().trim());
            Booking b = hotel.findBooking(id);
            if (b == null) { System.out.println("  Booking not found."); return; }

            System.out.printf("  Cancel booking #%d for %s? (yes/no): ", id, b.getGuestName());
            String confirm = sc.nextLine().trim().toLowerCase();
            if (!confirm.equals("yes")) { System.out.println("  Cancellation aborted."); return; }

            hotel.cancelBooking(id);
            System.out.println("  ✔ Booking #" + id + " cancelled. Room is now available.");
        } catch (NumberFormatException e) {
            System.out.println("  Invalid ID.");
        }
    }

    // ── 5. Make Payment ──────────────────────────────────────────
    static void makePayment(Hotel hotel) {
        System.out.print("\n  Enter Booking ID: ");
        try {
            int id = Integer.parseInt(sc.nextLine().trim());
            Booking b = hotel.findBooking(id);
            if (b == null) { System.out.println("  Booking not found."); return; }
            if (b.isPaid()) { System.out.println("  This booking is already paid."); return; }

            System.out.printf("  Total Amount Due: $%.2f%n", b.getTotalAmount());
            System.out.println("  Select Payment Method:");
            System.out.println("  1. Credit Card");
            System.out.println("  2. Debit Card");
            System.out.println("  3. Cash");
            System.out.println("  4. UPI");
            System.out.print("  Enter choice: ");
            String m = sc.nextLine().trim();
            String[] methods = {"Credit Card", "Debit Card", "Cash", "UPI"};
            int mIdx;
            try { mIdx = Integer.parseInt(m) - 1; }
            catch (NumberFormatException e) { System.out.println("  Invalid choice."); return; }
            if (mIdx < 0 || mIdx >= methods.length) { System.out.println("  Invalid choice."); return; }

            hotel.processPayment(id, methods[mIdx]);
            Display.paymentReceipt(b, methods[mIdx]);
        } catch (NumberFormatException e) {
            System.out.println("  Invalid ID.");
        }
    }

    // ── Helper: read date ────────────────────────────────────────
    static LocalDate readDate(String prompt) {
        System.out.print(prompt);
        try {
            return LocalDate.parse(sc.nextLine().trim(), fmt);
        } catch (DateTimeParseException e) {
            System.out.println("  Invalid date format. Use yyyy-MM-dd.");
            return null;
        }
    }
}
