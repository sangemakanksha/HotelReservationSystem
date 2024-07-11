import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Room {
    private int roomNumber;
    private String category;
    private boolean isAvailable;

    public Room(int roomNumber, String category) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.isAvailable = true;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getCategory() {
        return category;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void bookRoom() {
        isAvailable = false;
    }

    public void releaseRoom() {
        isAvailable = true;
    }
}

class Booking {
    private int bookingId;
    private Room room;
    private String guestName;
    private String checkInDate;
    private String checkOutDate;

    public Booking(int bookingId, Room room, String guestName, String checkInDate, String checkOutDate) {
        this.bookingId = bookingId;
        this.room = room;
        this.guestName = guestName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        room.bookRoom();
    }

    public int getBookingId() {
        return bookingId;
    }

    public Room getRoom() {
        return room;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }
}

class Hotel {
    private List<Room> rooms;
    private List<Booking> bookings;
    private int nextBookingId;

    public Hotel() {
        rooms = new ArrayList<>();
        bookings = new ArrayList<>();
        nextBookingId = 1;
    }

    public void addRoom(int roomNumber, String category) {
        rooms.add(new Room(roomNumber, category));
    }

    public void displayAvailableRooms() {
        System.out.println("Available Rooms:");
        System.out.println("-----------------");
        for (Room room : rooms) {
            if (room.isAvailable()) {
                System.out.println("Room Number: " + room.getRoomNumber() + ", Category: " + room.getCategory());
            }
        }
    }

    public void makeReservation(String guestName, String checkInDate, String checkOutDate, int roomNumber) {
        Room selectedRoom = null;
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber && room.isAvailable()) {
                selectedRoom = room;
                break;
            }
        }
        if (selectedRoom != null) {
            Booking booking = new Booking(nextBookingId++, selectedRoom, guestName, checkInDate, checkOutDate);
            bookings.add(booking);
            System.out.println("Reservation successful. Booking ID: " + booking.getBookingId());
        } else {
            System.out.println("Room not available for the selected dates.");
        }
    }

    public void displayBookingDetails(int bookingId) {
        for (Booking booking : bookings) {
            if (booking.getBookingId() == bookingId) {
                System.out.println("Booking ID: " + booking.getBookingId());
                System.out.println("Guest Name: " + booking.getGuestName());
                System.out.println("Room Number: " + booking.getRoom().getRoomNumber());
                System.out.println("Check-in Date: " + booking.getCheckInDate());
                System.out.println("Check-out Date: " + booking.getCheckOutDate());
                return;
            }
        }
        System.out.println("Booking not found.");
    }
}

public class HotelReservationSystem {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        hotel.addRoom(101, "Standard");
        hotel.addRoom(102, "Standard");
        hotel.addRoom(103, "Deluxe");
        hotel.addRoom(104, "Deluxe");
        hotel.addRoom(105, "Grand Luxury ");
        hotel.addRoom(106, "Grand Luxury ");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nHotel Reservation System Menu:");
            System.out.println("--------------------------------");
            System.out.println("1. Display available rooms");
            System.out.println("2. Make a reservation");
            System.out.println("3. View booking details");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    hotel.displayAvailableRooms();
                    break;
                case 2:
                    System.out.print("Enter guest name: ");
                    String guestName = scanner.nextLine();
                    System.out.print("Enter check-in date (YYYY-MM-DD): ");
                    String checkInDate = scanner.nextLine();
                    System.out.print("Enter check-out date (YYYY-MM-DD): ");
                    String checkOutDate = scanner.nextLine();
                    System.out.print("Enter room number: ");
                    int roomNumber = scanner.nextInt();
                    hotel.makeReservation(guestName, checkInDate, checkOutDate, roomNumber);
                    break;
                case 3:
                    System.out.print("Enter booking ID: ");
                    int bookingId = scanner.nextInt();
                    hotel.displayBookingDetails(bookingId);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
