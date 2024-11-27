package LibraryManagementSystem;




/**
 *
 * @author 202312049
 */


public class People{
    private String username;
    private String password;
    private String name;
    private String cellNo;

    //Class Constructor
    public People(String username, String password, String name, String cellNo){
        this.username = username;
        this.password = password;
        this.name = name;
        this.cellNo = cellNo;
    }
    
    //Getters and Setters
    public String getUser(){
        return username;
    }
    public void setUser(String user){
        username = user;
    } 
    public String getName(){
        return name;
    }
    public void setPassword(String pass){
        password = pass;
    } 
    public String getPassword(){
        return password;
    }
    public String getNumber(){
        return cellNo;
    }
    
    //username verifier
    public boolean checkUser(String user){
        return user.equalsIgnoreCase(username);
    }
    //password verifier
    public boolean checkPassword(String pass){
        return password.equals(password);
    }
}



