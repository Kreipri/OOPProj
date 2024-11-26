/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LibraryManagementSystem;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
/**
 *
 * @author ZEN
 */
public class LibrarySystem {
    static private ArrayList<Users> userList;
    static private ArrayList<BorrowDetails> borrowList;
    static private ArrayList<Books> bookList;
    
    static private Map<Users, ArrayList<BorrowDetails>> userBorrowMap;
    
    static public Scanner sc = new Scanner(System.in);
    
    public LibrarySystem(){
        userList = new ArrayList<>();
        borrowList = new ArrayList<>();
        userBorrowMap = new HashMap<>();
        bookList = new ArrayList<>();
        
        startupImport();
        
    }
    
    //IMPORTS =========================================================================================================================================
    
    public static void startupImport(){
        importBooks();
        importUsers();
        importBorrowDetails();
        importLinks();
    }
    //import books
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
                        bookList.add(new Ebook(id, title, author, quantity));
                    }else if(type.equalsIgnoreCase("Physical Book")){
                        bookList.add(new PhysicalBook(id, title, author, quantity));
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
    
    //import from file
    public static void importUsers(){
        userList.add(new RegularUser("RegUserName","RegPass","Reg", "01234567890"));
        userList.add(new SpecialUser("StudentUser","StuPass","Student","01234567890","Student"));
    }
    
    public static void importBorrowDetails(){
//        borrowList.add(new BorrowDetails())
    }
    
    public static void importLinks(){
        
    }
    
    //================================================================================================================================================
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //ADD NEW USERS =================================================================================================================================================
    // add users
    public static void addUser(String user, String pass, String name, String num, String type){
        if(type == "Regular"){
            Users newUser = new RegularUser(user, pass, name, num);
            userList.add(newUser);
            linkUser(newUser);
        }
        else if (type == "Student" || type == "PWD" || type == "Senior Citizen"){
            Users newUser = new SpecialUser(user, pass, name, num, type);
            userList.add(newUser);
            linkUser(newUser);
        }
 
    }
    
    //link NEW user to borrow details
    public static void linkUser(Users user){
        if(!userBorrowMap.containsKey(user)){
            userBorrowMap.put(user, new ArrayList<BorrowDetails>());
        }
    }
    
    //=================================================================================================================================================
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //GETTERS =========================================================================================================================================
    //get borrowDetails NA LIST of user
    public ArrayList<BorrowDetails> getBorrowList(Users user){
        return userBorrowMap.get(user);
        }
    
    // get all users NA LIST
    public ArrayList<Users> getAllUsers(){
        return userList;
    }
    
    //=================================================================================================================================================
    
    //DISPLAYS ===========================================================================================================================================
    public static void libraryMenu(){
        while(true){
            System.out.println("+---------------------------------------------------------------------+");
            System.out.println("|                         Available Books                             |");
            System.out.println("+---------------------------------------------------------------------+");
            System.out.println("| ID | Title                                    |        Author       |");
            if(bookList != null){
                 displayBooks();
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
        for(Books item : bookList){
            if(item.getTitle().toLowerCase().contains(query.toLowerCase())||item.getAuthor().toLowerCase().contains(query.toLowerCase())||(Integer.toString(item.getId())).equals(query)){
                caughtItems.add(bookList.indexOf(item));
                caught++; 
            }
        }
        if (caught > 1){
            System.out.println("+---------------------------------------------------------------------+");
            System.out.printf( "|                Searched matched with %d books                       |\n", caught);
            System.out.println("+---------------------------------------------------------------------+");
            System.out.println("| ID | Title                                    |        Author       |");
            for(int number : caughtItems){
                bookList.get(number).displayBook();
            }
            System.out.println("+---------------------------------------------------------------------+");
            System.out.println(" Type the ID of the book you want to select: ");
            int chosenIndex = sc.nextInt()-1;
            
            return bookList.get(chosenIndex);
            
            
        }else if (caught == 1){
            return bookList.get(caughtItems.get(0));
        }
        else{
            return null;
        }
        
    }
    
    public static void displayBooks(){
        for(Books items : bookList){
                        items.displayBook();
                    }    
    }
    
    public void userDetails(Users user){
        System.out.println("+------------------------------------+");
        System.out.println("|            User Details            |");
        System.out.println("+------------------------------------+");
        System.out.printf( "| Username: %-24s |\n", user.getUser());
        System.out.printf( "| Name: %-28s |\n", user.getName());
        System.out.printf( "| Contact #: %-23s |\n", user.getNumber());
        System.out.printf( "| User type: %-23s |\n", user.getUserType());
        System.out.println("+------------------------------------+");


    }
    
    public void userBorrowedBooks(Users user){
        System.out.println("+---------------------------------------------------------------------------------------+");
        System.out.println("|                                   Borrowed Books                                      |");
        System.out.println("+---------------------------------------------------------------------------------------+");
        System.out.println("|  Title                                 |      Author      | Borrow Date | Return Date |");
        System.out.println("+---------------------------------------------------------------------------------------+");
        
        for(BorrowDetails details : getBorrowList(user)){
            Books book = details.getBorrowedBook();
            System.out.printf("| %-38s | %-16s | %-11s |", book.getTitle(), book.getAuthor(), details.getBorrowDate(), details.getReturnDate());
        }
        
    }
    
    public void displayAllBorrowed(){
        System.out.println(   "+-------------------------------------------------------------------------------------------------------------------+");
        System.out.println(   "|                                             All Borrowed Books                                                    |");
        System.out.println(   "+-------------------------------------------------------------------------------------------------------------------+");
        System.out.println(   "|  Title                         |        Author        |   Borrowed by   | Borrow Date | Return Date |  Contact #  |");
        System.out.println(   "+-------------------------------------------------------------------------------------------------------------------+");
        for(Map.Entry<Users, ArrayList<BorrowDetails>> entry : userBorrowMap.entrySet()){
            Users user = entry.getKey();
            ArrayList<BorrowDetails> borrowListt = entry.getValue();
            for(BorrowDetails details : borrowListt){
                Books book = details.getBorrowedBook();
                System.out.printf("| %-30s | %-20s | %-15s | %-11s | %-11s | %-11s |", book.getTitle(), book.getAuthor(), user.getName(), details.getBorrowDate(), details.getReturnDate(), user.getNumber());
            }
        }
    }
}
