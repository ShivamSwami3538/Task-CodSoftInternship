import java.sql.*;
import java.util.Scanner;

public class App {
    public static void main(String[] args){
        
        int choice = 0;

        while(choice!=8008)
        {
        System.out.println("\nWelcome to SMS (STUDENT MANAGEMENT SYSTEM)");
        System.out.println("Please Authenticate System:");

        System.out.print("Enter Choice : \n 1: Login \n 2: SignUp \n 3: EXIT \n\nSelect your choice: ");
        int menu = 0;
        Scanner sc = new Scanner(System.in);
        menu = sc.nextInt();
        switch(menu)
        {
            case 1:
                     boolean result = login(); 
                     if(result)
                     {
                        System.out.println("Login success.");
                     }
                     else
                     {
                        System.out.println("Wrong creditials or not record found.");
                     }
                     break;
            case 2:
                    SignUp();
                    break; 
            case 3:
                    choice = 8008;
                    break;
        }
      }
    }

    // login method

    static boolean login()
    {
        boolean loginSuccess = false;
        String id1="",pass="";
        String id="",password="";
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter ID:");
        id = sc.nextLine();
        System.out.print("Enter Password:");
        password = sc.nextLine();

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rohit", "root", "Shivam2003ss");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from login");   // Result set is actually rows and column format

            while(rs.next())
            {
                id1 = rs.getString(1);
                pass = rs.getString(2);
            }
            if(id.equals(id1) && password.equals(pass))
            loginSuccess = true;
            else
            loginSuccess = false;
        }catch(Exception e)
        {
            System.out.println(e);
        }

        return loginSuccess;
    }

    // SignUp method

    static void SignUp()
    {
        int loop=0;
        int code=0;
        String id ="",password="",name="";
        while(loop!=8008)
        {
            Scanner sc = new Scanner(System.in);
            System.out.print("\nSignUp: Two-Step Verification: \n");
            code = sc.nextInt();
            if(code==8008)
            {
                Scanner data = new Scanner(System.in);
                System.out.print("Enter ID:");
                id = data.nextLine();
                System.out.print("Enter password:");
                password = data.nextLine();
                System.out.print("Enter Name:");
                name = data.nextLine();

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rohit", "root", "Shivam2003ss");
                    String query = "insert into signup (id,password,name)"+ "values (?,?,?)";
                    String query1 = "insert into login (id,password)"+"values (?,?)";
                    PreparedStatement ps = con.prepareStatement(query);        
                    PreparedStatement ps1 = con.prepareStatement(query1);        
                    ps.setString(1, id);
                    ps.setString(2, password);
                    ps.setString(3, name);
                    ps1.setString(1, id);
                    ps1.setString(2, password);
                    int i =ps.executeUpdate();
                    ps1.executeUpdate();

                    if(i>0)
                    {
                        System.out.print("\n\nData inserted...\n\n");
                    }
                    else
                    {
                        System.out.print("\n\nData not inserted...");
                    }
                }catch(Exception e) 
                {
                    System.out.print(e);                    
                }
            }else
            {
                System.out.print("\nWrong pin_code.\n");
                return;
            }
        }  
    }
}
