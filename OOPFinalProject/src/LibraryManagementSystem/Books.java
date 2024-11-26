
package LibraryManagementSystem;

/**
 *
 * @author 202312049
 */

public class  Books {
    private String title;
    private int quantity;
    private String author;
    private int id;
    private String type;
    
    public Books(int id, String title,String author, int quantity){
        this.id = id;
        this.title = title;
        this.author = author;
        this.quantity = quantity;
        this.type = "Book";
    }
    
    public String getTitle(){
        return title;
    }
    
    public String getAuthor(){
        return author;
    }
    
    public int getId(){
        return id;
    }
    
    public int getQuantity(){
        return quantity;
    }
    
    public void displayBook(){
      //System.out.println("| ID | Title                                    |        Author       |");
        System.out.printf( "| %-2d | %-40s | %-19s |\n", id, title, author);
    }
    
    public void setType(String type){
        this.type = type;
    }
    
    public String getType(){
        return type;
    }
}
