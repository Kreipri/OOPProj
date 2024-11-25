
package LibraryManagementSystem;

/**
 *
 * @author 202312049
 */

public class  Books {
    private String title;
    private int stocks;
    private String author;
    
    public Books(String title,String author, int stocks){
        this.title = title;
        this.author = author;
        this.stocks = stocks;
    }
    
    public String getTitle(){
        return title;
    }
    
    public String getAuthor(){
        return author;
    }
    
    public int getStocks(){
        return stocks;
    }
    
    public void displayDetails(){
      //System.out.println("| Title                                    |        Author       |");
        System.out.printf( "| %-40s | %-19s |\n", title, author);
    }
}
