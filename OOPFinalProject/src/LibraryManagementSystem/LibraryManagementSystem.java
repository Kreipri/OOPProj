package LibraryManagementSystem;

import java.util.*;


/**
 *
 * @author 202312049
 */
public class LibraryManagementSystem {
    static ArrayList<Books> books;
    
    public static void main(String[] args) {
        books = new ArrayList<>();1
        
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
        //import from file
        importBooks();
        
        
        
        while(loop){
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
            System.out.println("|              [1] Borrow Book                  |");
            System.out.println("|              [2] Return Book                  |");
            System.out.println("|              [3] Books Borrowed               |");
            System.out.println("|                                               |");
            System.out.println("|              [0] Exit                         |");
            System.out.println("|                --- Group 7 ---                |");
            System.out.println("+-----------------------------------------------+");
            System.out.print("  Choice: ");
            int choice = sc.nextInt();
            
            switch(choice){
                case 1:
                    libraryMenu(sc);
      
                    break;
                case 2:
                    //TBA
                    break;
                case 3:
                    //TBA
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
    public static void importBooks(){
       books.add(new Books("Book 1 Tile","Your Mom",3));
       books.add(new Books("Book 2 Tile","Your Dad",2));
    }
    
    public static void libraryMenu(Scanner sc){
        while(true){
            System.out.println("+----------------------------------------------------------------+");
            System.out.println("|               Available Books                                  |");
            System.out.println("+----------------------------------------------------------------+");
            System.out.println("| Title                                    |        Author       |");
            LibraryManagementSystem.displayBooks();
            System.out.println("+----------------------------------------------------------------+");
            System.out.println(" Type the title of the book you want to select: ");
            String chosenBook = sc.nextLine();

            Books chosenBookObj = searchForBook(chosenBook);
            if(chosenBookObj != null){
                System.out.println("+----------------------------------------------------------------+");
                System.out.println("| Title                                    |        Author       |");
                chosenBookObj.displayDetails();
                System.out.printf("|   Stocks available: %i                                          |");
                System.out.println("+----------------------------------------------------------------+");
                System.out.println("              [1]  Borrow              [0]  Return");
                System.out.print(  " Choice: ");
                int choice = sc.nextInt();

                switch(choice){
                    case 1:
                            System.out.println("Borrowed (TBA)");
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
    
    public static Books searchForBook(String query){
        for(Books item : books){
            if(item.getTitle().equalsIgnoreCase(query)||item.getAuthor().equalsIgnoreCase(query)){
                return item;
            }
        }
        return null;
    }
    
    public static void displayBooks(){
        for(Books items : books){
                        items.displayDetails();
                    }    
    }
    /*
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
                boolean loop = true;
                while(true){
                    System.out.println("+----------------------------+");
                    System.out.println("|         User Login         |");
                    System.out.println("+----------------------------+");
                    System.out.print("  Enter username: ");
                    String username = sc.nextLine();
                    System.out.print(" Enter password: ");
                    String password = sc.nextLine();
                    Login login = new Login(username, password);
                    if(login.authenticate(username, password)){

                    }else{
                        System.out.println("Sorry, there is no user with those details."
                                + "\n    [1] - Try Again   [2] - Sign up" );
                        switch(choice){
                             case 1:
                                 break;
                            case 2:
                                 login.signup();
                                 loop = false;
                                 break;
                            default:
                                System.out.println("+----------------------------------+");
                                System.out.println("| Invalid input. Please try again. |");
                                System.out.println("+----------------------------------+");

                            }
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
*/
}
