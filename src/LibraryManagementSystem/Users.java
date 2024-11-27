/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LibraryManagementSystem;
import java.util.*;

/**
 *
 * @author 202312049
 */


public abstract class Users extends People{
    private String userType; //Regular or Special
    private BorrowDetails borrowDetails;
    private List<String> borrowedBooks;
   
    //Class Constructor
    public Users(String username, String password, String name, String cellNo, String userType){
        super(username, password, name, cellNo);
        this.userType = userType;
        this.borrowedBooks = new ArrayList<>();
    }
    
    //Setters and Getters
    public String getUserType(){
        return userType;
    }
    public abstract void displayUser();
    
    public void displayBorrowed(){
        
    }
}
