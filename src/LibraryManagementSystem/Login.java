package LibraryManagementSystem;
import java.util.*;

/**
 *
 * @author 202312049
 */


public class Login {
    static Scanner sc = new Scanner(System.in);
    static private ArrayList<Users> users;
    static private Users loggedInUser;
  
    //Class Constructor
    public Login(LibrarySystem lib){
        this.users = lib.getAllUsers();
        this.loggedInUser = null;
    }
    
    //log-in
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
//        System.out.println("+-------------------------------------------------+");
//        System.out.println("| Invalid username or password. Please try again. |");
//        System.out.println("+-------------------------------------------------+");
        return false;
    }
    
    //Create New User
//    public static boolean signup(String username, String password, String name, String cellNo, String userType){
//        if(!checkUniqueUser(username)){
//            System.out.println("+-----------------------------------+");
//            System.out.println("| Username taken. Please try again. |");
//            System.out.println("+-----------------------------------+");
//        }
//        
//        //Creation of new user depending on user type
//        if(userType.equals("Regular")){
//           users.add(new RegularUser(username, password, name, cellNo));
//        }else if (userType.equals("Student")|| userType.equals("PWD") || userType.equals("Senior Citizen")){
//            users.add(new SpecialUser(username, password, name, cellNo, userType));
//        }
//        System.out.println("+-----------------------------------------+");
//        System.out.println("| Sign-up successful! You can now log in. |");
//        System.out.println("+-----------------------------------------+");
//        return true;
//    }
    
    //Checks if the username is already taken
    public static boolean checkUniqueUser(String username){
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
    
    //getter
    public Users getLoggedInUser(){
        return loggedInUser;
    }
    
    //DISPLAY ===============================================================
    //actual log-in system
    public static Users login(Login login, LibrarySystem lib){
        
       System.out.println( "         __...--~~~~~-._   _.-~~~~~--...__\n" +
                            "      //               `V'               \\\\ \n" +
                            "     //                 |                 \\\\ \n" +
                            "    //__...--~~~~~~-._  |  _.-~~~~~~--...__\\\\ \n" +
                            "   //__.....----~~~~._\\ | /_.~~~~----.....__\\\\\n" +
                            "  ====================\\\\|//====================\n" +
                            "                      `---`                   ");
        System.out.println("+-----------------------------------------------+");
        System.out.println("|   Welcome to the Library Management System!   |");
        System.out.println("+-----------------------------------------------+");
        System.out.println("|   [1] Login as User      [2] Login as Staff   |");
        System.out.println("|                 [3] Sign Up                   |");
        System.out.println("|              --- Group 7 ---                  |");
        System.out.println("+-----------------------------------------------+");
        System.out.print("  Choice: ");
        
        int choice = enterChoice();
        
        switch(choice){
            case 1:
                boolean loop = true;
                boolean subloop = false;
                while(loop == true){
                    System.out.println("+----------------------------------+");
                    System.out.println("|            User Login            |");
                    System.out.println("+----------------------------------+");
                    System.out.print(" Enter username: ");
                    String username = sc.nextLine();
                    System.out.print(" Enter password: ");
                    String password = sc.nextLine();
                    if(login.login(username, password)){
                        return login.getLoggedInUser();
                    }else{ 
                        System.out.println("+-----------------------------------------------+");
                        System.out.println("|  Sorry, there is no user with those details.  |");
                        System.out.println("+-----------------------------------------------+");
                        do{
                        subloop = false;    
                        System.out.println("       [1] - Try Again   [2] - Sign up         ");
                        System.out.print(" Choice: ");
                        choice = enterChoice();

                        switch(choice){
                            case 1:
                                break;
                            case 2:
                                loop = false;
                                signup(lib);
                                break;
                            default:
                                System.out.println("+-----------------------------------------------+");
                                System.out.println("|       Invalid input. Please try again.        |");
                                System.out.println("+-----------------------------------------------+");
                                subloop = true;
                                break;
                            }
                        }while(subloop);
                    }
                }
            case 2:
                System.out.println("TBA");
                break;
            case 3:
                signup(lib);
                break;
            default:
                System.out.println("+-----------------------------------------------+");
                System.out.println("|       Invalid input. Please try again.        |");
                System.out.println("+-----------------------------------------------+");
                break;
        }
        return null;
    }
    
    //user signup input of details
    public static boolean signup(LibrarySystem lib){
        boolean loop = true;
        do{
        System.out.println("+-------------------------------------+");
        System.out.println("|             User Sign-up             |");
        System.out.println("+-------------------------------------+");
        System.out.print(  " Enter Username: ");
        String user = sc.nextLine();
        Login.checkUniqueUser(user);
        System.out.print(  " Enter Password: ");
        String pass = sc.nextLine();
        System.out.print(" Enter Name: ");
        String name = sc.nextLine();
        System.out.print(" Enter Contact Number: ");
        String cell = sc.nextLine();
        String userType = null;

        boolean subloop = true;
        do{
            System.out.print(  " Are you a: ");
            System.out.print("    \n[1] Student"
                             + "    \n[2] Person with Disability "
                             + "    \n[3] Senior Citizen"
                             + "    \n[4] None of the above"
                             + "  \nChoice: ");
            int choice = enterChoice();
            
            switch(choice){
                case 1: 
                    userType = "Student";
                    break;
                case 2: 
                    userType = "PWD";
                    break;
                case 3:
                    userType = "Senior Citizen";
                    break;
                case 4:
                    userType = "Regular";
                    break;
                default:
                    loop = true;
                    printErr();
                    break;
            }
        }while(subloop);
        lib.addUser(user,pass,name,cell,userType); 
        loop = false;
        }while(loop);
        
        return true;
    }
    
    
    
    //exception handling/error trapping for choices
    public static int enterChoice(){
        int choice = 9;
        try{
            choice = sc.nextInt();                
            }catch(Exception e){
            }
        sc.nextLine();
        return choice;
    }
    
    //error message
    public static void printErr(){
        System.out.println("+----------------------------------+");
        System.out.println("| Invalid input. Please try again. |");
        System.out.println("+----------------------------------+");
    }
}
