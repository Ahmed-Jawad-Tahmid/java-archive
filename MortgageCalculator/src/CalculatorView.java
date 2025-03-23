import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CalculatorView extends JFrame {

    private JTextField principalTextField;                  /** TextField for entering the principal amount */
    private JTextField numberOfPaymentsTextField;           /** TextField for entering the number of payments */
    private JTextField annualInterestRateTextField;         /** TextField for entering the annual interest rate */
    private JTextArea resultingTextArea;                    /** TextArea for displaying the calculated mortgage payment */
    private JButton calculateButton;                        /** Button for calculating the mortgage payment */
    private JComboBox<String> paymentFrequencyComboBox;     /** ComboBox for selecting the payment frequency */
    private JComboBox<Integer> compoundingFrequencyComboBox; /** ComboBox for selecting the compounding frequency */

    /**
     * Constructor that sets the title, layout, and components for the calculator GUI.
     */
    public CalculatorView() {
        setTitle("Mortgage Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        initComponents();
        addComponentsToLayout();

        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Initializes the GUI components and sets their font and color.
     */
    private void initComponents() {
        principalTextField = new JTextField(10);
        annualInterestRateTextField = new JTextField(10);
        numberOfPaymentsTextField = new JTextField(10);

        paymentFrequencyComboBox = new JComboBox<>(new String[]{"Monthly", "Bi-Weekly", "Weekly"});
        compoundingFrequencyComboBox = new JComboBox<>(new Integer[]{1, 2, 4, 12, 365});

        calculateButton = new JButton("Calculate");

        resultingTextArea = new JTextArea(10, 40);
        resultingTextArea.setEditable(false);

        // Changing font and color
        Font customFont = new Font("Times Roman", Font.PLAIN, 16);
        Color customColor = new Color(0, 0, 181);

        principalTextField.setFont(customFont);
        principalTextField.setForeground(customColor);
        annualInterestRateTextField.setFont(customFont);
        annualInterestRateTextField.setForeground(customColor);
        numberOfPaymentsTextField.setFont(customFont);
        numberOfPaymentsTextField.setForeground(customColor);

        paymentFrequencyComboBox.setFont(customFont);
        paymentFrequencyComboBox.setForeground(customColor);
        compoundingFrequencyComboBox.setFont(customFont);
        compoundingFrequencyComboBox.setForeground(customColor);

        calculateButton.setFont(customFont);
        calculateButton.setForeground(customColor);

        resultingTextArea.setFont(customFont);
        resultingTextArea.setForeground(customColor);
    }

    /**
     * Adds the GUI components to the layout using GridBagLayout.
     */

    private void addComponentsToLayout() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5);

        add(new JLabel("Principal:"), constraints);
        constraints.gridx++;
        add(principalTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel("Annual Interest Rate (%):"), constraints);
        constraints.gridx++;
        add(annualInterestRateTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel("Number of Payments:"), constraints);
        constraints.gridx++;
        add(numberOfPaymentsTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel("Payment Frequency:"), constraints);
        constraints.gridx++;
        add(paymentFrequencyComboBox, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        add(new JLabel("Compounding Frequency:"), constraints);
        constraints.gridx++;
        add(compoundingFrequencyComboBox, constraints);

        constraints.gridx = 1;
        constraints.gridy++;
        add(calculateButton, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 2;
        add(new JScrollPane(resultingTextArea), constraints);
    }
    /**Returns the principal entered by the user.
    @return the principal entered by the user as a double.
     */
    public double getPrincipal() {
        return Double.parseDouble(principalTextField.getText());
    }

    /** Returns the annual interest rate
     @return a double value representing the annual interest rate
     */
    public double getAnnualInterestRate() {
        return Double.parseDouble(annualInterestRateTextField.getText()) / 100;
    }

    /** Returns the number of payments
     @return an integer value representing the number of payments
     */
    public int getNumberOfPayments() {
        return Integer.parseInt(numberOfPaymentsTextField.getText());
    }
    /** Returns the payment frequency selected in the Payment Frequency combo box.
    @return an integer value representing the payment frequency selected in the Payment Frequency combo box
    */
    public int getPaymentFrequency() {
        String selectedItem = (String) paymentFrequencyComboBox.getSelectedItem();
        if (selectedItem == null) {
            return 12;
        }

        return switch (selectedItem) {
            case "Bi-Weekly" -> 26;
            case "Weekly" -> 52;
            default -> 12;
        };
    }

    /**Returns the compounding frequency selected in the Compounding Frequency combo box.
    @return an integer value representing the compounding frequency selected in the Compounding Frequency combo box
    */
    public int getCompoundingFrequency() {
        Integer selectedItem = (Integer) compoundingFrequencyComboBox.getSelectedItem();
        if (selectedItem == null) {
            return 2;
        }
        return selectedItem;
    }
    /** Adds an action listener to the Calculate button.
     @param listener the ActionListener object to be added to the Calculate button
     */
    public void addCalculateButtonListener(ActionListener listener) {
        calculateButton.addActionListener(listener);
    }

    /** Displays the mortgage calculation results in the Resulting Text Area.
     @param results a String object representing the mortgage calculation results
     */
    public void displayResults(String results) {
        resultingTextArea.setText(results);
    }
}