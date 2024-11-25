/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LibraryManagementSystem;

/**
 *
 * @author ZEN
 */
public class PhysicalBook extends Books{
    String type;
    
    public PhysicalBook(int id, String title, String author, int quantity){
        super(id, title, author, quantity);
        this.type = "Physical Book";
        
    }
}
