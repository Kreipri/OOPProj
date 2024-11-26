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

    public People(String username, String password, String name, String cellNo){
        this.username = username;
        this.password = password;
        this.name = name;
        this.cellNo = cellNo;
        
//        System.out.println("+----------------------+");
//        System.out.println("|     User created!    |");
//        System.out.println("+----------------------+");
//        System.out.println(" Username: "+ this.username);
//        System.out.println(" Password: "+ this.password.replaceAll(".*","*") + " ");
//        System.out.println(" CellNo: "+ this.cellNo);
    }
    
        //encapsulation
    public String getUser(){
        return username;
    }

    public void setUser(String user){
        username = user;
    } 
    
    public String getName(){
        return name;
    }

    public boolean checkUser(String user){
        return user.equalsIgnoreCase(username);
    }

    public boolean checkPassword(String pass){
        return password.equals(password);
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
}



