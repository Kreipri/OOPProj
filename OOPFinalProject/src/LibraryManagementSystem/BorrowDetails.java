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
    private int borrowID;         // List of Borrow IDs
    private ArrayList<Books> bookList;
    private Books borrowedBook;
    private LocalDate borrowDate;       // List of borrow dates
    private LocalDate returnDate;       // List of return dates
    private ArrayList<BorrowDetails> borrowedBooks;
    
//    static DateTimeFormatter dateFormat = new DateTimeFormatter.ofPattern("MM-dd-yyyy");

    public BorrowDetails(ArrayList<Books> bookList) {
//        this.borrowID = new ArrayList<>();
//        this.borrowDate = new ArrayList<>();
//        this.returnDate = new ArrayList<>();
//        this.borrowerNames = new ArrayList<>();
        borrowedBooks = new ArrayList<>();
    }
    
    public BorrowDetails(Books book, int days){
        this.borrowID = book.getId();
        this.borrowedBook = book;
        this.borrowDate = LocalDate.now();
        this.returnDate = (LocalDate.now()).plusDays(days);
    }
    
    public BorrowDetails(Books book, LocalDate borrowDate, LocalDate returnDate){
        this.borrowID = book.getId();
        this.borrowedBook = book;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public Books matchId(int id){
        for(Books book : bookList){
                if(id == book.getId()){
                    return book;
                }
            }
        return null;
    }
    

    public Books getBorrowedBook(){
        return borrowedBook;
    }
    // getters n setters
    public int getBorrowID() {
        return borrowID;
    }

    public void addBorrowID(int borrowID) {
        this.borrowID = borrowID;
    }
    
  

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

//    public void addBorrowDate() {
//        this.borrowDate = LocalDate.now();
//    }

    public LocalDate getReturnDate() {
        return returnDate;
    }


//    public void addReturnDate(int days) {
//        this.returnDate = (LocalDate.now()).plusDays(days);
//    }

//    public String getBorrowerNames() {
//        return borrowerNames;
//    }
//    
//    public void addUserToBorrowDetails(Users user){
//        this.users.add(user);
//    }
//    
    

//    public void addBorrowerName(String borrowerName) throws IllegalArgumentException {
//        if (borrowerName == null || borrowerName.isEmpty()) {
//            throw new IllegalArgumentException("Borrower name cannot be null or empty.");
//        }
//        this.borrowerNames = borrowerName);
//    }

    // display borrow deets
    public void displayBorrowDetails() {
        System.out.println("Borrow Details:");
            System.out.printf("ID: %d, Borrow Date: %s, Return Date: %s", borrowID, borrowDate, returnDate); 
    }
/*
    // save to file
    public void saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < borrowID.size(); i++) {
                writer.write(borrowID.get(i) + "," + borrowDate.get(i) + "," +
                        returnDate.get(i) + "," + borrowerNames.get(i));
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
                        this.borrowID.add(Integer.parseInt(parts[0]));
                        this.borrowDate.add(LocalDate.parse(parts[1]));
                        this.returnDate.add(LocalDate.parse(parts[2]));
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
        if (borrowID == null) {
            throw new IllegalStateException("No borrow details found. Please add some details first.");
        }
    }
*/
//    // main method !!! PANG TESTING LANG !!!! can remove
//    public static void main(String[] args) {
//        BorrowDetails borrowDetails = new BorrowDetails();
//
//        // sample data
//        try {
//            borrowDetails.addBorrowID(101);
//            try{
//                borrowDetails.addBorrowDate();
//                borrowDetails.addReturnDate(14);
//            }catch(Exception e){
//                System.out.println("Invalid date format: "+ e.getMessage());
//            }
//            
//            borrowDetails.addBorrowerName("Darrel Tolentino");
//
//            borrowDetails.displayBorrowDetails();
//
//            borrowDetails.saveToFile("borrowDetails.txt");
//
//            borrowDetails = new BorrowDetails();
//            borrowDetails.loadFromFile("borrowDetails.txt");
//            borrowDetails.displayBorrowDetails();
//
//        } catch (IllegalArgumentException | IllegalStateException e) {
//            System.err.println("Error: " + e.getMessage());
//        }
//    }
}
