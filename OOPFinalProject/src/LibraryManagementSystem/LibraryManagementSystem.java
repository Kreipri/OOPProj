package LibraryManagementSystem;

import java.util.*;
import java.io.*;


/**
 *
 * @author 202312049
 */
public class LibraryManagementSystem {
    static ArrayList<Books> books;
    static Scanner sc = new Scanner(System.in);
    
    
    public static void main(String[] args) {
        books = new ArrayList<>();
        boolean loop = true;
        //import from file
        importBooks();
        
        login();
        
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
            System.out.println("|               --- Group 7 ---                 |");
            System.out.println("+-----------------------------------------------+");
            System.out.print("  Choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            
            switch(choice){
                case 1:
                    libraryMenu();
      
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
        //check if file exists
        File libraryFile = new File("libraryFile.txt");
        //System.out.println("Looking at: "+ libraryFile.getAbsolutePath());
        if(libraryFile.exists()){
            try{
                BufferedReader reader = new BufferedReader(new FileReader("libraryFile.txt"));
                String line;
                while((line = reader.readLine())!= null){
                    String[] parts = line.split(",");
                    
                    //getting values from file
                    int id = Integer.parseInt(parts[0]);
                    String title = parts[1];
                    String author = parts[2];
                    int quantity = Integer.parseInt(parts[3]);
                    String type = parts[4];
                    
                    //put values into books
                    if(type.equalsIgnoreCase("E-book")){
                        books.add(new Ebook(id, title, author, quantity));
                    }else if(type.equalsIgnoreCase("Physical Book")){
                        books.add(new PhysicalBook(id, title, author, quantity));
                    }
                    else{
                        System.out.println("Error to import book");
                    }
                    
                }
                reader.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        } else {
            System.out.println("No previous data to import.");
        }
    }
    
    public static void libraryMenu(){
        while(true){
            System.out.println("+---------------------------------------------------------------------+");
            System.out.println("|                         Available Books                             |");
            System.out.println("+---------------------------------------------------------------------+");
            System.out.println("| ID | Title                                    |        Author       |");
            if(books != null){
                 LibraryManagementSystem.displayBooks();
            }else{
                System.out.println("No books available. Please contact your librarian.");   
            }
           
            System.out.println("+---------------------------------------------------------------------+");
            System.out.println(" Type the ID, Title or Author of the book you want to select: ");
            String chosenBook = sc.nextLine();

            Books chosenBookObj = searchForBook(chosenBook,sc);
            if(chosenBookObj != null){
                System.out.println("+---------------------------------------------------------------------+");
                System.out.println("| ID | Title                                    |        Author       |");
                chosenBookObj.displayBook();
                System.out.printf( "|   Copies available: %d                                              |\n", (chosenBookObj.getQuantity()));
                System.out.println("+---------------------------------------------------------------------+");
                System.out.println("                 [1]  Borrow              [0]  Return");
                System.out.print(  " Choice: ");
                int choice = sc.nextInt();
                sc.nextLine();
                
                switch(choice){
                    case 1:
                            System.out.println("Borrowed (TBA)");
                            return;
                    case 0:
                        return;
                    default:
                        System.out.println("+----------------------------------+");
                        System.out.println("| Invalid input. Please try again. |");
                        System.out.println("+----------------------------------+");
                }
            }
            else{
                System.out.println("Book not found!");
                return;
            }
        }
    }
    
    public static Books searchForBook(String query, Scanner sc){
        ArrayList<Integer> caughtItems = new ArrayList<>();
        int caught = 0;
        for(Books item : books){
            if(item.getTitle().toLowerCase().contains(query.toLowerCase())||item.getAuthor().toLowerCase().contains(query.toLowerCase())||(Integer.toString(item.getId())).equals(query)){
                caughtItems.add(books.indexOf(item));
                caught++; 
            }
        }
        if (caught > 1){
            System.out.println("+---------------------------------------------------------------------+");
            System.out.printf( "|                Searched matched with %d books                       |\n", caught);
            System.out.println("+---------------------------------------------------------------------+");
            System.out.println("| ID | Title                                    |        Author       |");
            for(int number : caughtItems){
                books.get(number).displayBook();
            }
            System.out.println("+---------------------------------------------------------------------+");
            System.out.println(" Type the ID of the book you want to select: ");
            int chosenIndex = sc.nextInt()-1;
            
            return books.get(chosenIndex);
            
            
        }else if (caught == 1){
            return books.get(caughtItems.get(0));
        }
        else{
            return null;
        }
        
    }
    
    public static void displayBooks(){
        for(Books items : books){
                        items.displayBook();
                    }    
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
