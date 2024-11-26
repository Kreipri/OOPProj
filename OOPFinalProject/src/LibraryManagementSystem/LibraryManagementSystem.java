package LibraryManagementSystem;

import java.util.*;
import java.io.*;


/**
 *
 * @author 202312049
 */
public class LibraryManagementSystem {
    static ArrayList<Books> books;
    static ArrayList<Users> users;
    static Scanner sc = new Scanner(System.in);
    
    
    public static void main(String[] args) {
        books = new ArrayList<>();
        users = new ArrayList<>();
       
        //import from file
        importUsers();
        importBooks();
        
        Login login = new Login(users);
        
        boolean loop = true;
        Users loggedInUser = null;
        
        while(loggedInUser == null){
            loggedInUser = login(login);
        }
        
        ;
        
        loop = true;
        
        System.out.println(" Hello, "+ loggedInUser.getName() + "!");
        
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
                    libraryMenu();
      
                    break;
                case 2:
                    
                    break;
                case 3:
                    userDetails(loggedInUser);
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
    
    public static void userDetails(Users user){
        System.out.println("+------------------------------------+");
        System.out.println("|            User Details            |");
        System.out.println("+------------------------------------+");
        System.out.printf( "| Username: %24s |", user.getUser());
        System.out.printf( "| Name: %28s |", user.getName());
        System.out.printf( "| Contact #: %23s |", user.getNumber());
        System.out.printf( "| User type: %23s |", user.getUserType());
        System.out.println("+------------------------------------+");
        
        System.out.println("+-------------------------------------------------------------------------+");
        System.out.println("|                            Borrowed Books                               |");
        System.out.println("+-------------------------------------------------------------------------+");
        System.out.println("|  Title                                 |      Author      | Return Date |");
        System.out.println("+-------------------------------------------------------------------------+");
        
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
                System.out.printf( "|   Copies available: %3d                                             |\n", (chosenBookObj.getQuantity()));
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
    
    public static void importUsers(){
        users.add(new RegularUser("RegUserName","RegPass","Reg", "01234567890"));
        users.add(new SpecialUser("StudentUser","StuPass","Student","01234567890","Student"));
    }
    
    public static Users login(Login login){
        
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
        System.out.println("|              --- Group 7 ---                  |");
        System.out.println("+-----------------------------------------------+");
        System.out.print("  Choice: ");
        int choice = 9;
        
        try{
            choice = sc.nextInt();
            sc.nextLine();
        }catch(IllegalArgumentException e){
            System.out.println("+----------------------------------+");
            System.out.println("| Invalid input. Please try again. |");
            System.out.println("+----------------------------------+");
        }
        
        switch(choice){
            case 1:
                boolean loop = true;
                while(true){
                    System.out.println("+----------------------------------+");
                    System.out.println("|            User Login            |");
                    System.out.println("+----------------------------------+");
                    System.out.print(" Enter username: ");
                    String username = sc.nextLine();
                    System.out.print(" Enter password: ");
                    String password = sc.nextLine();
                    if(login.login(username, password)){
                        return login.getLoggedInUser();
                    }else{
                        while(loop){
                            System.out.println("+-----------------------------------------------+");
                        System.out.println("|  Sorry, there is no user with those details.  |\n"
                                        +  "|       [1] - Try Again   [2] - Sign up         |" );
                        System.out.println("+-----------------------------------------------+");
                        System.out.print(" Choice: ");
                        try{
                                choice = sc.nextInt();
                                sc.nextLine();
                            }catch(IllegalArgumentException e){
                                System.out.println("+----------------------------------+");
                                System.out.println("| Invalid input. Please try again. |");
                                System.out.println("+----------------------------------+");
                                continue;
                            }
                        
                        switch(choice){
                             case 1:
                                 break;
                            case 2:
                                
                                System.out.println("+-------------------------------------+");
                                System.out.println("|             User Signup             |");
                                System.out.println("+-------------------------------------+");
                                System.out.print(  "  Enter Username: ");
                                String user = sc.nextLine();
                                login.checkUniqueUser(username);
                                System.out.print(  "  Enter Password: ");
                                String pass = sc.nextLine();
                                System.out.print(" Enter Name:");
                                String name = sc.nextLine();
                                System.out.print(" Enter Contact Number:");
                                String cell = sc.nextLine();
                                String userType = null;
                                
                                loop = true;
                                while(loop){
                                    System.out.print(" Are you a: ");
                                    System.out.println(" [1] Student"
                                                     + " [2] Person with Disability "
                                                     + " [3] Senior Citizen"
                                                     + " [4] None of the above");
                                    try{
                                        choice = sc.nextInt();
                                    }catch(IllegalArgumentException e){
                                        System.out.println("+----------------------------------+");
                                        System.out.println("| Invalid input. Please try again. |");
                                        System.out.println("+----------------------------------+");
                                    }
                                    
                                    

                                    switch(choice){
                                        case 1: 
                                            userType = "Student";
                                            loop = false;
                                            break;
                                        case 2: 
                                            userType = "PWD";
                                            loop = false;
                                            break;
                                        case 3:
                                            userType = "Senior Citizen";
                                            loop = false;
                                            break;
                                        case 4:
                                            userType = "Regular";
                                            loop = false;
                                            break;
                                        default:
                                            System.out.println("+----------------------------------+");
                                            System.out.println("| Invalid input. Please try again. |");
                                            System.out.println("+----------------------------------+");
                                    }
                                }
                                login.signup(user, pass, name, cell, userType);
                                loop = false;
                                break;

                            default:
                                System.out.println("+----------------------------------+");
                                System.out.println("| Invalid input. Please try again. |");
                                System.out.println("+----------------------------------+");

                            }
                        }
                        
                    }
                }
            case 2:
                break;
            default:
                System.out.println("+----------------------------------+");
                System.out.println("| Invalid input. Please try again. |");
                System.out.println("+----------------------------------+");
                break;
        }
        return null;
    }
}
