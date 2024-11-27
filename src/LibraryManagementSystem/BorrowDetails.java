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

//Initialization of variables
public class BorrowDetails {
    private int borrowID;         // List of Borrow IDs
    private ArrayList<Books> bookList;
    private Books borrowedBook;
    private LocalDate borrowDate;       // List of borrow dates
    private LocalDate returnDate;       // List of return dates
    private ArrayList<BorrowDetails> borrowedBooks;
    
    //Class Constructor
    public BorrowDetails(ArrayList<Books> bookList) {
        borrowedBooks = new ArrayList<>();
    }
    //Class Constructor
    public BorrowDetails(Books book, int days){
        this.borrowID = book.getId();
        this.borrowedBook = book;
        this.borrowDate = LocalDate.now();
        this.returnDate = (LocalDate.now()).plusDays(days);
    }
    
    //Class Constructor
    public BorrowDetails(Books book, LocalDate borrowDate, LocalDate returnDate){
        this.borrowID = book.getId();
        this.borrowedBook = book;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }
    
    //bookID verification
    public Books matchId(int id){
        for(Books book : bookList){
                if(id == book.getId()){
                    return book;
                }
            }
        return null;
    }
    
    //Getters and Setters
    public Books getBorrowedBook(){
        return borrowedBook;
    }
    public int getBorrowID() {
        return borrowID;
    }
    public LocalDate getBorrowDate() {
        return borrowDate;
    }
    public LocalDate getReturnDate() {
        return returnDate;
    }
    public void addBorrowID(int borrowID) {
        this.borrowID = borrowID;
    }
} 
