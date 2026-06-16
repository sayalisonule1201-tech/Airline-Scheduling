import java.util.*;
class Flight {
    int flightNo;
    String source, destination, departureTime, arrivalTime, status;
    Flight(int flightNo, String source, String destination, String departureTime, String arrivalTime) {
        this.flightNo = flightNo;
        this.source = source;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.status = "Scheduled";
    }
    void display() {
        System.out.printf("| %-8d | %-10s | %-12s | %-14s | %-12s | %-10s |\n",
            flightNo, source, destination, departureTime, arrivalTime, status);
    }
}
public class AirlineScheduling {
    static List<Flight> flights = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static void addFlight() {
        System.out.print("Enter Flight No: ");
        int no = sc.nextInt(); sc.nextLine();
        System.out.print("Source: ");
        String src = sc.nextLine();
        System.out.print("Destination: ");
        String dest = sc.nextLine();
        System.out.print("Departure Time (HH:MM): ");
        String dep = sc.nextLine();
        System.out.print("Arrival Time (HH:MM): ");
        String arr = sc.nextLine();
        flights.add(new Flight(no, src, dest, dep, arr));
        System.out.println("Flight added successfully.");
    }
    static void viewFlights() {
        if (flights.isEmpty()) { System.out.println("No flights scheduled."); return; }
        System.out.println("+----------+------------+--------------+----------------+--------------+------------+");
        System.out.println("| Flight No | Source     | Destination  | Departure Time | Arrival Time | Status     |");
        System.out.println("+----------+------------+--------------+----------------+--------------+------------+");
        for (Flight f : flights) f.display();
        System.out.println("+----------+------------+--------------+----------------+--------------+------------+");
    }
    static void updateStatus() {
        System.out.print("Enter Flight No to update status: ");
        int no = sc.nextInt(); sc.nextLine();
        for (Flight f : flights) {
            if (f.flightNo == no) {
                System.out.print("New Status (Scheduled/Delayed/Cancelled/Departed): ");
                f.status = sc.nextLine();
                System.out.println("Status updated.");
                return;
            }
        }
        System.out.println("Flight not found.");
    }
    static void searchFlight() {
        System.out.print("Enter Source: ");
        String src = sc.nextLine();
        System.out.print("Enter Destination: ");
        String dest = sc.nextLine();
        boolean found = false;
        for (Flight f : flights) {
            if (f.source.equalsIgnoreCase(src) && f.destination.equalsIgnoreCase(dest)) {
                f.display();
                found = true;
            }
        }
        if (!found) System.out.println("No flights found for this route.");
    }
    static void cancelFlight() {
        System.out.print("Enter Flight No to cancel: ");
        int no = sc.nextInt();
        for (Flight f : flights) {
            if (f.flightNo == no) {
                f.status = "Cancelled";
                System.out.println("Flight " + no + " has been cancelled.");
                return;
            }
        }
        System.out.println("Flight not found.");
    }
    public static void main(String[] args) {
        flights.add(new Flight(101, "Mumbai", "Delhi", "06:00", "08:30"));
        flights.add(new Flight(102, "Delhi", "Bangalore", "10:00", "12:45"));
        flights.add(new Flight(103, "Chennai", "Kolkata", "14:00", "16:20"));
        int choice;
        do {
            System.out.println("\n===== AIRLINE SCHEDULING SYSTEM =====");
            System.out.println("1. Add Flight");
            System.out.println("2. View All Flights");
            System.out.println("3. Search Flight by Route");
            System.out.println("4. Update Flight Status");
            System.out.println("5. Cancel Flight");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt(); sc.nextLine();
            switch (choice) {
                case 1: addFlight(); break;
                case 2: viewFlights(); break;
                case 3: searchFlight(); break;
                case 4: updateStatus(); break;
                case 5: cancelFlight(); break;
                case 0: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }
}