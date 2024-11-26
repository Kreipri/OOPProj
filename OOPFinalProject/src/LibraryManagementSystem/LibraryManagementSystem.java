package LibraryManagementSystem;

import java.util.*;
import java.io.*;


/**
 *
 * @author 202312049
 */
public class LibraryManagementSystem {
    static Scanner sc = new Scanner(System.in);
    static LibrarySystem lib = new LibrarySystem();
    
    
    public static void main(String[] args) {
        
        Login login = new Login(lib);
        
        Users loggedInUser = null;
        
        while(loggedInUser == null){
            loggedInUser = Login.login(login);
        }

        
        System.out.println(" Hello, "+ loggedInUser.getName() + "!");
        
        while(true){
            System.out.println( "         __...--~~~~~-._   _.-~~~~~--...__\n" +
                                "      //               `V'               \\\\ \n" +
                                "     //                 |                 \\\\ \n" +
                                "    //__...--~~~~~~-._  |  _.-~~~~~~--...__\\\\ \n" +
                                "   //__.....----~~~~._\\ | /_.~~~~----.....__\\\\\n" +
                                "  ====================\\\\|//====================\n" +
                                "                      `---`                   ");
            System.out.println("+-----------------------------------------------+");
            System.out.println("|   Welcome to the Library Management System!   |");
            System.out.println("+-----------------------------------------------+");
            System.out.println("|              [1] Borrow Book                  |");
            System.out.println("|              [2] Return Book                  |");
            System.out.println("|              [3] User Details                 |");
            System.out.println("|                                               |");
            System.out.println("|              [0] Exit                         |");
            System.out.println("|               --- Group 7 ---                 |");
            System.out.println("+-----------------------------------------------+");
            System.out.print("  Choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            
            switch(choice){
                case 1:
                    lib.libraryMenu();
                    break;
                case 2:
                    
                    break;
                case 3:
                    lib.userDetails(loggedInUser);
                    break;
                case 0:
                    return;
                default:
                System.out.println("+----------------------------------+");
                System.out.println("| Invalid input. Please try again. |");
                System.out.println("+----------------------------------+");
                        
            }
            
            
        }
        
        
        
       
    }
    
}
