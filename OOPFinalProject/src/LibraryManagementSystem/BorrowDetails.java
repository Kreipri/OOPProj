/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LibraryManagementSystem;

/**
 *
 * @author ZEN
 */
import java.io.*;
import java.util.*;
import java.time.LocalDate;

public class BorrowDetails {
    private List<Integer> borrowIDs;         // List of Borrow IDs
    private List<LocalDate> borrowDates;       // List of borrow dates
    private List<LocalDate> returnDates;       // List of return dates
    private List<String> borrowerNames;     // List of borrower names
    private List<Users> users;
    
//    static DateTimeFormatter dateFormat = new DateTimeFormatter.ofPattern("MM-dd-yyyy");

    public BorrowDetails() {
        this.borrowIDs = new ArrayList<>();
        this.borrowDates = new ArrayList<>();
        this.returnDates = new ArrayList<>();
        this.borrowerNames = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    // getters n setters
    public List<Integer> getBorrowIDs() {
        return borrowIDs;
    }

    public void addBorrowID(int borrowID) {
        this.borrowIDs.add(borrowID);
    }

    public List<LocalDate> getBorrowDates() {
        return borrowDates;
    }

    public void addBorrowDate() {
        this.borrowDates.add(LocalDate.now());
    }

    public List<LocalDate> getReturnDates() {
        return returnDates;
    }

    public void addReturnDate(int days) {
        this.returnDates.add((LocalDate.now()).plusDays(days));
    }

    public List<String> getBorrowerNames() {
        return borrowerNames;
    }
    
    public void addUserToBorrowDetails(Users user){
        this.users.add(user);
    }
    
    

    public void addBorrowerName(String borrowerName) throws IllegalArgumentException {
        if (borrowerName == null || borrowerName.isEmpty()) {
            throw new IllegalArgumentException("Borrower name cannot be null or empty.");
        }
        this.borrowerNames.add(borrowerName);
    }

    // display borrow deets
    public void displayBorrowDetails() {
        System.out.println("Borrow Details:");
        for (int i = 0; i < borrowIDs.size(); i++) {
            System.out.printf("ID: %d, Borrow Date: %s, Return Date: %s, Borrower: %s%n",
                    borrowIDs.get(i), borrowDates.get(i), returnDates.get(i), borrowerNames.get(i));
        }
    }

    // save to file
    public void saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < borrowIDs.size(); i++) {
                writer.write(borrowIDs.get(i) + "," + borrowDates.get(i) + "," +
                        returnDates.get(i) + "," + borrowerNames.get(i));
                writer.newLine();
            }
            System.out.println("Borrow details saved successfully to " + filename);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    // load frm file
    public void loadFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    try{
                        this.borrowIDs.add(Integer.parseInt(parts[0]));
                        this.borrowDates.add(LocalDate.parse(parts[1]));
                        this.returnDates.add(LocalDate.parse(parts[2]));
                        this.borrowerNames.add(parts[3]);
                    }catch(Exception e){
                        System.out.println("Invalid date format, could not load." + e.getMessage());
                    }
                    
                }
            }
            System.out.println("Borrow details loaded successfully from " + filename);
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }

    // check if borrow detail exists
    public void validateBorrowDetails() throws IllegalStateException {
        if (borrowIDs.isEmpty()) {
            throw new IllegalStateException("No borrow details found. Please add some details first.");
        }
    }

    // main method !!! PANG TESTING LANG !!!! can remove
    public static void main(String[] args) {
        BorrowDetails borrowDetails = new BorrowDetails();

        // sample data
        try {
            borrowDetails.addBorrowID(101);
            try{
                borrowDetails.addBorrowDate();
                borrowDetails.addReturnDate(14);
            }catch(Exception e){
                System.out.println("Invalid date format: "+ e.getMessage());
            }
            
            borrowDetails.addBorrowerName("Darrel Tolentino");

            borrowDetails.displayBorrowDetails();

            borrowDetails.saveToFile("borrowDetails.txt");

            borrowDetails = new BorrowDetails();
            borrowDetails.loadFromFile("borrowDetails.txt");
            borrowDetails.displayBorrowDetails();

        } catch (IllegalArgumentException | IllegalStateException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
