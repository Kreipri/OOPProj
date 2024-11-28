package LibraryManagementSystem;

import java.util.*;

/**
 *
 * @author 202312049
 */
public class LibraryManagementSystem {
    static Scanner sc = new Scanner(System.in);
    static LibrarySystem lib;
    
    public static void main(String[] args) {
        lib = new LibrarySystem(); 
        Login login = new Login(lib);
        
        Users loggedInUser = null;
        
        while(loggedInUser == null){
            loggedInUser = Login.login(login, lib);
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
            System.out.println("|              [1] View Books                   |");
            System.out.println("|              [2] Borrow Book                  |");
            System.out.println("|              [3] Return Book                  |");
            System.out.println("|              [4] User Details                 |");
            System.out.println("|              [5] View Borrowed Books          |");
            System.out.println("|              [6] Donate Books                 |");
            System.out.println("|                                               |");
            System.out.println("|              [0] Exit                         |");
            System.out.println("|               --- Group 7 ---                 |");
            System.out.println("+-----------------------------------------------+");
            System.out.print("  Choice: ");
            int choice = 9;
            try{
                choice = sc.nextInt();
            }catch(Exception e){
            }
            sc.nextLine();
            
            switch(choice){
                case 1:
                    lib.displayAllBooks();
                    break;
                case 2:
                    lib.libraryMenu(loggedInUser);
                    break;
                case 3:
                    lib.returnBooks(loggedInUser);
                    break;
                case 4:
                    lib.userDetails(loggedInUser);
                    break;
                case 5:
                    lib.userBorrowedBooks(loggedInUser);
                    break;
                case 6:
                    lib.donateBook();
                    break;
                case 0:
                    lib.save();
                    return;
                default:
                System.out.println("+-----------------------------------------------+");
                System.out.println("|        Invalid input. Please try again.       |");
                System.out.println("+-----------------------------------------------+"); 
                break;
            }
        }
        
        
        
       
    }
    
}
