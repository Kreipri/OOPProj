/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LibraryManagementSystem;
import java.io.*;
import java.util.*;
import java.time.LocalDate;
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
        bookList = new ArrayList<>();
        importBooks();
        userList = new ArrayList<>();
        borrowList = new ArrayList<>();
        userBorrowMap = new HashMap<>();
        importUsers();
    }
    
    //Import books from file
    public static void importBooks(){
        //check if file exists
        File libraryFile = new File("libraryFile.txt");
        System.out.println("Looking at: "+ libraryFile.getAbsolutePath());
        if(libraryFile.exists()){
            try{
                BufferedReader reader = new BufferedReader(new FileReader("libraryFile.txt"));
                String line;
                while((line = reader.readLine())!= null){
                    String[] parts = line.split(",");
                    
                    //Fetch values from file
                    int id = Integer.parseInt(parts[0]);
                    String title = parts[1];
                    String author = parts[2];
                    int quantity = Integer.parseInt(parts[3]);
                    String type = parts[4];
                    
                    //Put values into books
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
                System.out.println("Error reading from file: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("No previous data to import.");
        }
        System.out.println("Books successfuly loaded!");
    }
    
    //Import users from file
    public void importUsers(){
        File userDatabaseFile = new File("userDataBase.txt");
        System.out.println("Looking at: "+ userDatabaseFile.getAbsolutePath());
        
        try (BufferedReader reader = new BufferedReader(new FileReader(userDatabaseFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                String[] bits = parts[0].split(",");
                //users import
                String userName = null;
                if(bits.length != 5){
                    System.err.println("Invalid user format: "+ line);
                    continue;
                }
                userName = bits[0];
                String pass = bits[1];
                String name = bits[2];
                String num = bits[3];
                String type = bits[4];
                
                //add user
                addUser(userName, pass, name, num, type);
                
                //get user to plug into userborrow map
                Users user = getUser(userName);
                userBorrowMap.putIfAbsent(user, new ArrayList<>());
                ArrayList<BorrowDetails> borrowDeets = userBorrowMap.get(user);                
              
                //Borrowed books import
                for(int i = 1; i <parts.length; i++){
                    String[] bookDetails = parts[i].split(",");
                    if(bookDetails.length == 3){
                        try{
                            int bookId = Integer.parseInt(bookDetails[0]);
                            LocalDate borrowDate = LocalDate.parse(bookDetails[1]);
                            LocalDate returnDate = LocalDate.parse(bookDetails[2]);
                  
                            //lmao find the book
                            Books bookImp = null;
                            for(Books bookz : bookList){
                                if(bookId == bookz.getId()){
                                    bookImp = bookz;
                                }
                            }
                            
                            borrowDeets.add(new BorrowDetails(bookImp, borrowDate, returnDate));
                        }catch(NumberFormatException e){
                            System.err.println("Invalid borrow detail format: " + parts[i]);
                        }catch(NullPointerException e){
                            System.err.println("Book not found.");
                        }
                    }
                }
            }
            System.out.println("Borrow details loaded successfully from: "+ userDatabaseFile);
            reader.close();
        } catch (IOException | NumberFormatException | NullPointerException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        System.out.println("Users successfuly loaded!");
    }
    
    // add users
    public static void addUser(String user, String pass, String name, String num, String type){
        if(type.equals("Regular")){
            Users newUser = new RegularUser(user, pass, name, num);
            userList.add(newUser);
            linkUser(newUser);
        }
        else if (type.equals("Student") || type.equals("PWD")|| type.equals("Senior Citizen")){
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
    
    //Get borrow details of all users
    public ArrayList<BorrowDetails> getBorrowList(Users user){
        return userBorrowMap.get(user);
        }
    
    //Get list of all users
    public ArrayList<Users> getAllUsers(){
        return userList;
    }
    
    public Users getUser(String user){
        for(Users item : userList){
            if(user.equals(item.getUser()))
                return item;
        }
        return null;
    }
    
    //Check whether ids inputted and stored matches
    public Books matchBookId (int id){
        for(Books book : bookList){
            if(id == book.getId()){
                return book;
            }
        }
        return null;
    }
    
    //Checks wheter Borrow id matches
    public BorrowDetails matchBorrowId(Users user, int id){
        for(BorrowDetails item : getBorrowList(user)){
                if(id == item.getBorrowID()){
                    return item;
                }
            }
        return null;
    }
    
    //Return book and remove from list
    public void returnBook(Users user, Books book){
        int id = book.getId();
        BorrowDetails deets = matchBorrowId(user, id);
        
        getBorrowList(user).remove(deets);
        
        System.out.println("Book returned!");
    }
    
    //Borrow book function
    public void borrowBook(Users user, Books book){
        int id = book.getId();
        int days = 0;
        String type = user.getUserType();
        if(type.equals("Regular")){
            days = 7;
        }else if(type.equals("Student") || type.equals("PWD")|| type.equals("Senior Citizen")){
            days = 14;
        }
        
        BorrowDetails deets = new BorrowDetails(book, days);
        getBorrowList(user).add(deets);
        System.out.println("Book borrowed! Please return it by " + deets.getReturnDate() + ".");
    }
    
    //Save users and books to file
    public void save(){
        System.out.println("Saving users and books...");
        saveUsers();
        saveBooks();
        System.out.println("Saved!");
        
    }
    
    //Update users list in file
    public void saveUsers(){
        File userDataBaseFile = new File("userDataBase.txt");
         try (BufferedWriter writer = new BufferedWriter(new FileWriter(userDataBaseFile))) {
             for(Map.Entry<Users, ArrayList<BorrowDetails>> entry : userBorrowMap.entrySet()){
                 Users user = entry.getKey();
                 ArrayList<BorrowDetails> borrowList = entry.getValue();
                 if(user != null){
                     //Write user details
                     writer.write(String.format("%s,%s,%s,%s,%s", user.getUser(),user.getPassword(), user.getName(), user.getNumber(), user.getUserType()));
                   
                     for(BorrowDetails item : borrowList){
                         writer.write(String.format("|%d,%s,%s", item.getBorrowID(), item.getBorrowDate(), item.getReturnDate()));
                     }
                     writer.newLine();
                 }
                 
             }
            System.out.println("User details saved successfully to " + userDataBaseFile);
        }catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    
    //Updates book list in file
    public void saveBooks(){
        File libraryFile = new File("libraryFile.txt");
         try (BufferedWriter writer = new BufferedWriter(new FileWriter(libraryFile))) {
             for(Books book : bookList){
                 if(book != null){
                     //Write user details
                     writer.write(String.format("%d,%s,%s,%d,%s", book.getId(), book.getTitle(), book.getAuthor(), book.getQuantity(), book.getType()));
                     writer.newLine();
                 }
                 
             }
            System.out.println("Book details saved successfully to " + libraryFile);
        }catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    
    //Interface
    public void libraryMenu(Users user){
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
                System.out.println("                 [1]  Borrow              [0]  Back");
                System.out.print(  " Choice: ");
                int choice = sc.nextInt();
                sc.nextLine();
                
                switch(choice){
                    case 1:
                            borrowBook(user, chosenBookObj);
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
    
    //Search for books in the archive
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
    
    //Display all books
    public static void displayBooks(){
        for(Books items : bookList){
            items.displayBook();
        }    
    }
    
    //Display user's details
    public void userDetails(Users user){
        System.out.println("+------------------------------------+");
        System.out.println("|            User Details            |");
        System.out.println("+------------------------------------+");
        System.out.printf( "| Username: %-24s |\n", user.getUser());
        System.out.printf( "| Name: %-28s |\n", user.getName());
        System.out.printf( "| Contact #: %-23s |\n", user.getNumber());
        System.out.printf( "| User type: %-23s |\n", user.getUserType());
        System.out.println("+------------------------------------+");
        return;
    }
    
    //Actual book return process
    public void returnBooks(Users user){
        System.out.println("==================================== RETURNING BOOKS ======================================");
        userBorrowedBooks(user);
        if(getBorrowList(user).isEmpty()){
            System.out.println("| No books borrowed yet.                                                                      |");
        }else{
            System.out.print("Enter the ID of the book you want to return: ");
            int choice = sc.nextInt();    
            returnBook(user, matchBookId(choice));
        } 
    }
    
    //Displays borrowed books by a user
    public void userBorrowedBooks(Users user){

        System.out.println("+---------------------------------------------------------------------------------------------+");
        System.out.println("|                                       Borrowed Books                                        |");
        System.out.println("+---------------------------------------------------------------------------------------------+");
        System.out.println("| ID | Title                                   |      Author      | Borrow Date | Return Date |");
        System.out.println("+---------------------------------------------------------------------------------------------+");
        if(getBorrowList(user).isEmpty()){
            System.out.println("| No books borrowed yet.                                                                      |");
        }
        for(BorrowDetails details : getBorrowList(user)){
            Books book = details.getBorrowedBook();
            System.out.printf("| %-2d | %-39s | %-16s | %-11s | %-11s |\n", details.getBorrowID(), book.getTitle(), book.getAuthor(), details.getBorrowDate(), details.getReturnDate());
        }
        System.out.println("+---------------------------------------------------------------------------------------------+");
        
        return;
    }
    
    //Displays all borrowed books by all users
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
        return;
    }
    
    //Book donation
    public void donateBook(){
        System.out.println("+----------------------------------------------+");
        System.out.println("|       Donate a book to the Library!          |");
        System.out.println("+----------------------------------------------+");
        System.out.print(" Book Title: ");
        String title = sc.nextLine();
        System.out.print(" Book Author: ");
        String author = sc.nextLine();
        System.out.print(" Book Type: [1] E-book [2] Physical Book");
        int type = sc.nextInt();
        System.out.print(" How many of these books will you donate? ");
        int quan = sc.nextInt();
       
        if(type == 1){
            bookList.add(new Ebook(bookList.size()+1,title, author, quan));
        }else if (type == 2){
            bookList.add(new PhysicalBook(bookList.size()+1,title, author, quan));
        }
        System.out.println("+--------------------------------------------+");
        System.out.println("| Book donated! Thank you for your kindness! |");
        System.out.println("+--------------------------------------------+");
    }
}
