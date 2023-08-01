import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LIST_Student extends JFrame {
    private JTable table;
    private JButton newButton;

    public LIST_Student() {
        // Create the table with headers
        String[] columnNames = {"ID", "First Name", "Last Name", "Phone", "Email", "City"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);

        // Create a scroll pane to hold the table
        JScrollPane scrollPane = new JScrollPane(table);

        // Create a button
        newButton = new JButton("Back");
        
         newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Your action when the newButton is clicked
                   setVisible(false);
                    new Home().setVisible(true);
            }
        });
        
     

        // Create a panel for the button and set its layout to FlowLayout
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(newButton);

        // Create a panel for the table and set its layout to BorderLayout
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        tablePanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the table panel to the JFrame
        add(tablePanel);

        setTitle("Student Table");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Fetch and display student data
        displayStudentData();
    }

    private void displayStudentData() {
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

            // Step 6: Process the result set and add data to the table
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                String city = resultSet.getString("city");

                Object[] rowData = {id, firstName, lastName, phone, email, city};
                model.addRow(rowData);
            }

            // Step 7: Close the resources
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }
}
