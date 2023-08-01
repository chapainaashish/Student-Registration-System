import java.sql.*;
class Test{  
   
    public static void main(String[] args) {
        String inputUsername = "Aashish";
        String inputPassword = "12345";

        boolean isAuthenticated = checkCredentials(inputUsername, inputPassword);

        if (isAuthenticated) {
            System.out.println("Authentication successful.");
        } else {
            System.out.println("Authentication failed.");
        }
    }
    
    private static boolean checkCredentials(String username, String password) {
        try {
        Class.forName("com.mysql.jdbc.Driver");  
        Connection conn=DriverManager.getConnection(  
    "jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6636488","sql6636488","Wta1pUBw87");
        
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username = ?");
        stmt.setString(1, username);
        
        ResultSet resultSet = stmt.executeQuery();
        if (resultSet.next()) {
                String storedPassword = resultSet.getString("password");
                return password.equals(storedPassword);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
    }  