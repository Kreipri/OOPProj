/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LibraryManagementSystem;

/**
 *
 * @author ZEN
 */

//Inheritance
public class Ebook extends Books{
    //Class Costructor
    public Ebook(int id, String title, String author, int quantity){
        //Calls super class
        super(id, title, author, quantity);
        setType("E-Book");
    }
}
