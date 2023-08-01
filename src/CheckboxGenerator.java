import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CheckboxGenerator extends JFrame {

    private List<JCheckBox> checkboxes;

    public CheckboxGenerator() {
        // Set up the JFrame
        setTitle("Checkbox Generator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        checkboxes = new ArrayList<>();

        // Ask the user for the number of checkboxes
        int numCheckboxes = getNumberOfCheckboxes();

        // Create and add the checkboxes
        for (int i = 1; i <= numCheckboxes; i++) {
            JCheckBox checkBox = new JCheckBox("Checkbox " + i);
            add(checkBox);
            checkboxes.add(checkBox);
        }

        JButton printButton = new JButton("Print Selected Checkboxes");
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

    private int getNumberOfCheckboxes() {
        String input = JOptionPane.showInputDialog("Enter the number of checkboxes:");
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Using default number (3).");
            return 3; // Default number of checkboxes
        }
    }

    public void printSelectedCheckboxes() {
        System.out.println("Selected Checkboxes:");
        for (JCheckBox checkBox : checkboxes) {
            if (checkBox.isSelected()) {
                System.out.println(checkBox.getText());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CheckboxGenerator());
    }
}
