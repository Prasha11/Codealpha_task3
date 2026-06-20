import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Booking {
    private static int counter = 1000;

    private int bookingId;
    private String guestName;
    private String guestPhone;
    private Room room;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private double totalAmount;
    private boolean paid;

    public Booking(String guestName, String guestPhone, Room room,
                   LocalDate checkIn, LocalDate checkOut) {
        this.bookingId   = ++counter;
        this.guestName   = guestName;
        this.guestPhone  = guestPhone;
        this.room        = room;
        this.checkIn     = checkIn;
        this.checkOut    = checkOut;
        long nights      = ChronoUnit.DAYS.between(checkIn, checkOut);
        this.totalAmount = nights * room.getPricePerNight();
        this.paid        = false;
    }

    public int getBookingId()      { return bookingId; }
    public String getGuestName()   { return guestName; }
    public String getGuestPhone()  { return guestPhone; }
    public Room getRoom()          { return room; }
    public LocalDate getCheckIn()  { return checkIn; }
    public LocalDate getCheckOut() { return checkOut; }
    public double getTotalAmount() { return totalAmount; }
    public boolean isPaid()        { return paid; }
    public void setPaid(boolean paid) { this.paid = paid; }

    public long getNights() {
        return ChronoUnit.DAYS.between(checkIn, checkOut);
    }

    public void printDetails() {
        String line = "-".repeat(55);
        System.out.println(line);
        System.out.printf("  Booking ID    : #%d%n", bookingId);
        System.out.printf("  Guest Name    : %s%n", guestName);
        System.out.printf("  Phone         : %s%n", guestPhone);
        System.out.printf("  Room          : %d (%s)%n", room.getRoomNumber(), room.getCategory());
        System.out.printf("  Check-In      : %s%n", checkIn);
        System.out.printf("  Check-Out     : %s%n", checkOut);
        System.out.printf("  Nights        : %d%n", getNights());
        System.out.printf("  Total Amount  : $%.2f%n", totalAmount);
        System.out.printf("  Payment       : %s%n", paid ? "PAID" : "PENDING");
        System.out.println(line);
    }
}
