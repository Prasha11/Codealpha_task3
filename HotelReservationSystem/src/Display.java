import java.util.List;

public class Display {
    static final String BORDER  = "=".repeat(60);
    static final String DIVIDER = "-".repeat(60);

    public static void header(String hotelName) {
        System.out.println("\n" + BORDER);
        System.out.println("       🏨  " + hotelName.toUpperCase() + "  🏨");
        System.out.println("            Hotel Reservation System");
        System.out.println(BORDER);
    }

    public static void menu() {
        System.out.println("\n" + DIVIDER);
        System.out.println("  MAIN MENU");
        System.out.println(DIVIDER);
        System.out.println("  1. Search Available Rooms");
        System.out.println("  2. Book a Room");
        System.out.println("  3. View My Booking");
        System.out.println("  4. Cancel Booking");
        System.out.println("  5. Make Payment");
        System.out.println("  6. View All Bookings");
        System.out.println("  7. Exit");
        System.out.println(DIVIDER);
        System.out.print("  Enter choice: ");
    }

    public static void showRooms(List<Room> rooms, String title) {
        System.out.println("\n" + BORDER);
        System.out.println("  " + title);
        System.out.println(DIVIDER);
        if (rooms.isEmpty()) {
            System.out.println("  No rooms found.");
        } else {
            System.out.printf("  %-10s %-12s %-14s %s%n",
                    "ROOM NO.", "CATEGORY", "PRICE/NIGHT", "STATUS");
            System.out.println(DIVIDER);
            for (Room r : rooms) {
                System.out.printf("  %-10d %-12s $%-13.2f %s%n",
                        r.getRoomNumber(), r.getCategory(),
                        r.getPricePerNight(),
                        r.isAvailable() ? "✔ Available" : "✘ Booked");
            }
        }
        System.out.println(BORDER);
    }

    public static void showAllBookings(List<Booking> bookings) {
        System.out.println("\n" + BORDER);
        System.out.println("  ALL BOOKINGS");
        System.out.println(DIVIDER);
        if (bookings.isEmpty()) {
            System.out.println("  No bookings found.");
            System.out.println(BORDER);
            return;
        }
        System.out.printf("  %-8s %-18s %-6s %-12s %-12s %-10s %s%n",
                "ID", "GUEST", "ROOM", "CHECK-IN", "CHECK-OUT", "AMOUNT", "PAID");
        System.out.println(DIVIDER);
        for (Booking b : bookings) {
            System.out.printf("  #%-7d %-18s %-6d %-12s %-12s $%-9.2f %s%n",
                    b.getBookingId(), b.getGuestName(), b.getRoom().getRoomNumber(),
                    b.getCheckIn(), b.getCheckOut(), b.getTotalAmount(),
                    b.isPaid() ? "YES" : "NO");
        }
        System.out.println(BORDER);
    }

    public static void paymentReceipt(Booking b, String method) {
        System.out.println("\n" + BORDER);
        System.out.println("          PAYMENT RECEIPT");
        System.out.println(DIVIDER);
        System.out.printf("  Booking ID    : #%d%n", b.getBookingId());
        System.out.printf("  Guest         : %s%n", b.getGuestName());
        System.out.printf("  Room          : %d (%s)%n",
                b.getRoom().getRoomNumber(), b.getRoom().getCategory());
        System.out.printf("  Nights        : %d%n", b.getNights());
        System.out.printf("  Amount Paid   : $%.2f%n", b.getTotalAmount());
        System.out.printf("  Payment Mode  : %s%n", method);
        System.out.printf("  Status        : PAYMENT SUCCESSFUL ✔%n");
        System.out.println(BORDER);
    }
}
