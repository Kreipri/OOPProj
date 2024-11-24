package LibraryManagementSystem;




/**
 *
 * @author 202312049
 */


public class People{
    private String username;
    private String password;
    private int cellNo;

    People(String username, String password, int cellNo){
        this.username = username;
        this.password = password;
        this.cellNo = cellNo;
        System.out.println("+----------------------+");
        System.out.println("|     User created!    |");
        System.out.println("+----------------------+");
        System.out.println(" Username: "+ this.username);
        System.out.println(" Password: "+ this.password.replaceAll(".*","*") + " ");
        System.out.println(" CellNo: "+ this.cellNo);
    }
    
        //encapsulation
    public String getUser(){
        return this.username;
    }

    public void setUser(String user){
        this.username = user;
    } 

    public boolean checkUser(String user){
        return user.equalsIgnoreCase(this.username);
    }

    public boolean checkPassword(String pass){
        return password.equals(this.password);
    }

    public void setPassword(String pass){
        this.password = pass;
    } 
}



