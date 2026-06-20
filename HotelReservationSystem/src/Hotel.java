import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Hotel {
    private String hotelName;
    private List<Room> rooms       = new ArrayList<>();
    private List<Booking> bookings = new ArrayList<>();

    public Hotel(String hotelName) {
        this.hotelName = hotelName;
        initRooms();
    }

    private void initRooms() {
        // Standard rooms  101-105  $80/night
        for (int i = 101; i <= 105; i++)
            rooms.add(new Room(i, Room.Category.STANDARD, 80.00));
        // Deluxe rooms    201-204  $150/night
        for (int i = 201; i <= 204; i++)
            rooms.add(new Room(i, Room.Category.DELUXE, 150.00));
        // Suite rooms     301-302  $300/night
        for (int i = 301; i <= 302; i++)
            rooms.add(new Room(i, Room.Category.SUITE, 300.00));
    }

    public String getHotelName() { return hotelName; }

    // All rooms
    public List<Room> getAllRooms() { return rooms; }

    // Available rooms by category
    public List<Room> getAvailableRooms(Room.Category category) {
        return rooms.stream()
                .filter(r -> r.isAvailable() && r.getCategory() == category)
                .collect(Collectors.toList());
    }

    // All available rooms
    public List<Room> getAllAvailableRooms() {
        return rooms.stream().filter(Room::isAvailable).collect(Collectors.toList());
    }

    public Room findRoom(int roomNumber) {
        return rooms.stream().filter(r -> r.getRoomNumber() == roomNumber)
                .findFirst().orElse(null);
    }

    // Make a booking
    public Booking makeBooking(String guestName, String guestPhone, int roomNumber,
                               LocalDate checkIn, LocalDate checkOut) {
        Room room = findRoom(roomNumber);
        if (room == null || !room.isAvailable()) return null;
        if (!checkOut.isAfter(checkIn)) return null;

        Booking b = new Booking(guestName, guestPhone, room, checkIn, checkOut);
        room.setAvailable(false);
        bookings.add(b);
        return b;
    }

    // Cancel a booking
    public boolean cancelBooking(int bookingId) {
        Booking b = findBooking(bookingId);
        if (b == null) return false;
        b.getRoom().setAvailable(true);
        bookings.remove(b);
        return true;
    }

    public Booking findBooking(int bookingId) {
        return bookings.stream().filter(b -> b.getBookingId() == bookingId)
                .findFirst().orElse(null);
    }

    public List<Booking> getAllBookings() { return bookings; }

    // Simulate payment
    public boolean processPayment(int bookingId, String method) {
        Booking b = findBooking(bookingId);
        if (b == null || b.isPaid()) return false;
        b.setPaid(true);
        return true;
    }
}
