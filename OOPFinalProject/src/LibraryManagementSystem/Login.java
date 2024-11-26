package LibraryManagementSystem;
import java.util.*;

/**
 *
 * @author 202312049
 */


public class Login {
    Scanner sc = new Scanner(System.in);
    private ArrayList<Users> users;
    private Users loggedInUser;
  
    public Login(ArrayList<Users> user){
        this.users = user;
        this.loggedInUser = null;
    }
    
    public boolean login(String username, String password){
        for (Users user : users){
            if(user.checkUser(username) && user.checkPassword(password)){
                System.out.println("+-----------------------+");
                System.out.println("|   Login successful!   |");
                System.out.println("+-----------------------+");
                loggedInUser = user;
                return true;
            }
        }
        System.out.println("+-------------------------------------------------+");
        System.out.println("| Invalid username or password. Please try again. |");
        System.out.println("+-------------------------------------------------+");
        return false;
    }
    

    public boolean signup(String username, String password, String name, String cellNo, String userType){
        if(!checkUniqueUser(username)){
            System.out.println("+-----------------------------------+");
            System.out.println("| Username taken. Please try again. |");
            System.out.println("+-----------------------------------+");
        }
        
        if(userType == "Regular"){
           users.add(new RegularUser(username, password, name, cellNo));
        }else if (userType == "Student" || userType == "PWD" || userType == "Senior Citizen"){
            users.add(new SpecialUser(username, password, name, cellNo, userType));
        }
        System.out.println("+-----------------------------------------+");
        System.out.println("| Sign-up successful! You can now log in. |");
        System.out.println("+-----------------------------------------+");
        return true;
    }
    
    public boolean checkUniqueUser(String username){
            for(Users user: users){
                if (user.getUser().equals(username)){
                    System.out.println("+----------------------------------------------------------+");
                    System.out.println("| Username already exists. Please choose another username. |");
                    System.out.println("+----------------------------------------------------------+");
                    return false;
                }
        }
        return true;
    }
    
    public Users getLoggedInUser(){
        return loggedInUser;
    }
    
//    public Login(String user, String password){
//        userName.add("RegularUser");
//        passWord.add("RegPass");
//    }
//    
    
    
//    //test if input user and pass matches ones in the array database
//    public boolean authenticate(String user, String password){
//        for(String userr : userName){
//            if(userName.equals(user) && passWord.equals(password)){
//                System.out.println("Login Successful!");
//                return true;
//            }
//        }
//        return false;
//    }
    
    
    
    
}
