import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import CalculatorModel;



public class Controller {
    private final CalculatorView view;

    /**
     * Constructor for the Controller class that initializes the view object.
     *
     * @param view the CalculatorView object to control
     */

    public Controller(CalculatorView view) {
        this.view = view;
        view.addCalculateButtonListener(new CalculateButtonListener());
    }
    /**
     * Private inner class that listens for button clicks and performs calculations based on user inputs.
     */
    class CalculateButtonListener implements ActionListener {
        /**
         * Called when the user clicks the calculate button. Parses the user inputs, creates a CalculatorModel object,
         * and displays the results.
         *
         * @param e the ActionEvent object representing the button click
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double principal = view.getPrincipal();
                double annualInterestRate = view.getAnnualInterestRate();
                int numberOfPayments = view.getNumberOfPayments();
                int paymentFrequency = view.getPaymentFrequency();
                int compoundingFrequency = view.getCompoundingFrequency();

                CalculatorModel model = new CalculatorModel(principal, annualInterestRate, numberOfPayments,
                        paymentFrequency, compoundingFrequency);

                String paymentSchedule = model.paymentSchedule();

                view.displayResults(paymentSchedule);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Please enter valid inputs.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Main method that creates a new CalculatorView object, initializes a new Controller object with the view object,
     * and makes the view object visible.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculatorView view = new CalculatorView();
            new Controller(view);
            view.setVisible(true);
        });
    }
}