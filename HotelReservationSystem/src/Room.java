public class Room {
    public enum Category { STANDARD, DELUXE, SUITE }

    private int roomNumber;
    private Category category;
    private double pricePerNight;
    private boolean available;

    public Room(int roomNumber, Category category, double pricePerNight) {
        this.roomNumber    = roomNumber;
        this.category      = category;
        this.pricePerNight = pricePerNight;
        this.available     = true;
    }

    public int getRoomNumber()       { return roomNumber; }
    public Category getCategory()    { return category; }
    public double getPricePerNight() { return pricePerNight; }
    public boolean isAvailable()     { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    @Override
    public String toString() {
        return String.format("Room %-4d | %-8s | $%.2f/night | %s",
                roomNumber, category, pricePerNight,
                available ? "Available" : "Booked");
    }
}
