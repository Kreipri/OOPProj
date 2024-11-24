package LibraryManagementSystem;

import java.util.Scanner;
import java.util.Collection;


/**
 *
 * @author 202312049
 */
public class LibraryManagementSystem {

    public static void main(String[] args) {
        //import from file
        import();
        
        
       login();
    }
    
    public static void login(){
        Scanner sc = new Scanner(System.in);
        
       System.out.println( "         __...--~~~~~-._   _.-~~~~~--...__\n" +
                            "      //               `V'               \\\\ \n" +
                            "     //                 |                 \\\\ \n" +
                            "    //__...--~~~~~~-._  |  _.-~~~~~~--...__\\\\ \n" +
                            "   //__.....----~~~~._\\ | /_.~~~~----.....__\\\\\n" +
                            "  ====================\\\\|//====================\n" +
                            "                       `---`                   ");
        System.out.println("+-----------------------------------------------+");
        System.out.println("|   Welcome to the Library Management System!   |");
        System.out.println("+-----------------------------------------------+");
        System.out.println("|   [1] Login as User      [2] Login as Staff   |");
        System.out.println("|                --- Group 7 ---                |");
        System.out.println("+-----------------------------------------------+");
        System.out.print("  Choice: ");
        
        int choice = sc.nextInt();
        sc.nextLine(); //to not skip lines
        
        switch(choice){
            case 1:
                System.out.println("+----------------------------+");
                System.out.println("|         User Login         |");
                System.out.println("+----------------------------+");
                System.out.print("  Enter username: ");
                String username = sc.nextLine();
                
                for(int = 0; i<users; i++){
                    if(u[i].usercheck(username)){
                    
                    }
                }
                
                
                break;
            case 2:
                System.out.println("");
                break;
            default:
                System.out.println("+----------------------------------+");
                System.out.println("| Invalid input. Please try again. |");
                System.out.println("+----------------------------------+");
                break;
        }
    }
}
