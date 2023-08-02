import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class CheckboxGenerator extends JFrame {

    private List<JCheckBox> checkboxes;
    private List<Integer>checkboxID;

    public CheckboxGenerator() {
        setTitle("Do attendance");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(0,1));
        checkboxes = new ArrayList<>();
        checkboxID = new ArrayList<>();

        try {
            // Step 1: Load the MySQL JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6636488", "sql6636488", "Wta1pUBw87");

            // Step 3: Create the SQL query for reading data
            String selectQuery = "SELECT id, first_name, last_name, phone, email, city FROM Student";

            // Step 4: Create a prepared statement
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

            // Step 5: Execute the query and get the result set
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                
                String fullName = firstName + lastName;
                JCheckBox checkBox = new JCheckBox(fullName);
                add(checkBox);
                checkboxes.add(checkBox);
                checkboxID.add(id);
                
            }

            // Step 7: Close the resources
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        
 


        JButton printButton = new JButton("Done");
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printSelectedCheckboxes();
            }
        });
        add(printButton);

        pack();
        setVisible(true);
    }


    public void printSelectedCheckboxes() {
        System.out.println("Selected Checkboxes:");
        for (JCheckBox checkBox : checkboxes) {
            int index = checkboxes.indexOf(checkBox);
            int id = checkboxID.get(index);
            String strid = Integer.toString(id);
            
            if (checkBox.isSelected()) {
                System.out.println(checkBox.getText());
                
               try {
                Class.forName("com.mysql.jdbc.Driver");  
                Connection connection=DriverManager.getConnection(  
            "jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6636488","sql6636488","Wta1pUBw87");

                // Inserting
                String insertQuery = "INSERT INTO Attendance (StudentID) VALUES (?)";    // Step 4: Create a prepared statement
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                preparedStatement.setString(1,strid);


                // Executing and closing
                preparedStatement.executeUpdate();
                preparedStatement.close();
                connection.close();



               }
               catch (Exception e) {
                   e.printStackTrace();
               }
               
                  setVisible(false);
            new Home().setVisible(true);    }                                        

        
        
                
                
                
                
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CheckboxGenerator());
    }
}
