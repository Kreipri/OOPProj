package LibraryManagementSystem;
import java.util.*;

/**
 *
 * @author 202312049
 */


public class Login {
    Scanner sc = new Scanner(System.in);
    ArrayList<String> userName = new ArrayList<>();
    ArrayList<String> passWord = new ArrayList<>();
    
    String user;
    String pass;
        //test values
        
                
    int usersTotal = 0;
    
    public Login(String user, String password){
        userName.add("RegularUser");
        passWord.add("RegPass");
    }
    public boolean signup(){
        System.out.println("+----------------------------+");
        System.out.println("|        User Signup         |");
        System.out.println("+----------------------------+");
        System.out.print(" Username: ");
        sc.nextLine();
        System.out.print(" Password: ");
        sc.nextLine();
        System.out.print(" Name: ");
        sc.nextLine();
        System.out.print(" Contact #: ");
        sc.nextLine();
        return true;
    }
    
    //test if input user and pass matches ones in the array database
    public boolean authenticate(String user, String password){
        for(String userr : userName){
            if(userName.equals(user) && passWord.equals(password)){
                System.out.println("Login Successful!");
                return true;
            }
        }
        return false;
    }
    
    
    
    
}
