/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LibraryManagementSystem;

/**
 *
 * @author ZEN
 */
public class RegularUser extends Users{
    
    //Class Constructor
    public RegularUser(String username, String password, String name, String cellNo) {
        super(username, password, name, cellNo, "Regular");
    }

    //Displays Books Borrowed
    @Override
    public void displayUser() {
        System.out.println("+---------------------------------------+");
        System.out.println("|              User Login               |");
        System.out.println("+---------------------------------------+");
        System.out.printf( "| Username: %-27s |\n", getUser());
        System.out.printf( "| Name: %-27s |\n", getName());
        System.out.printf( "| Contact #: %-11d |\n", getNumber());
        System.out.println("| User Type: Regular User                |");
        System.out.println("+---------------------------------------+");
        System.out.println("| Borrowed Books:                        |");

    }

    
}
