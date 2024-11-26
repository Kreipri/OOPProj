/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LibraryManagementSystem;

/**
 *
 * @author ZEN
 */
public class SpecialUser extends Users {
    
    public SpecialUser(String username, String password, String name, String cellNo, String userType) {
    super(username, password, name, cellNo, userType);
    }

    @Override
    public void displayUser() {
        System.out.println("+---------------------------------------+");
        System.out.println("|              User Login               |");
        System.out.println("+---------------------------------------+");
        System.out.printf( "| Username: %-27s |\n", getUser());
        System.out.printf( "| Name: %-27s |\n", getName());
        System.out.printf( "| Contact #: %-11d |\n", getNumber());
        System.out.printf( "| User type: %-27s |");
        System.out.println("+---------------------------------------+");
        System.out.printf( "| Borrowed Books:                       |");

    }
}
