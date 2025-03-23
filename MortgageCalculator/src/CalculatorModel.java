import java.util.Collections;

public class CalculatorModel {

    private final double principal;                   /** The principal amount of the mortgage*/
    private final double annualInterestRate;          /** The annual interest rate  */
    private final int numberOfPayments;               /** The number of payments  */
    private final int paymentFrequency;               /** The payment frequency */
    private final int compoundingFrequency;           /** The compounding frequency */

    /**
     * Constructor for the CalculatorModel class.
     * @param principal the principal amount
     * @param annualInterestRate the annual interest rate
     * @param numberOfPayments the number of payments
     * @param paymentFrequency the payment frequency
     * @param compoundingFrequency the compounding frequency
     */

    public CalculatorModel(double principal, double annualInterestRate, int numberOfPayments, int paymentFrequency, int compoundingFrequency) {
        this.principal = principal;
        this.annualInterestRate = annualInterestRate;
        this.numberOfPayments = numberOfPayments;
        this.paymentFrequency = paymentFrequency;
        this.compoundingFrequency = compoundingFrequency;
    }

    /**
     * Method to calculate the interest factor.
     * @return the interest factor
     */
    public double calculateInterestFactor() {
        return Math.pow((annualInterestRate / compoundingFrequency) + 1, compoundingFrequency / (double) paymentFrequency) - 1;
    }

    /**
     * Method to calculate the blended payment.
     * @return the blended payment
     */
    public double calculateBlendedPayment() {
        double interestFactor = calculateInterestFactor();
        return (principal * interestFactor) / (1 - Math.pow(1 + interestFactor, -numberOfPayments));
    }

    /**
     * Method to get the total interest paid.
     * @return the total interest paid
     */
    public double getTotalInterestPaid() {
        return (calculateBlendedPayment() * numberOfPayments) - principal;
    }
    /**
     * Method to get the total interest and principal.
     * @return the total interest and principal
     */
    public double getTotalInterestAndPrincipal() {
        return principal + getTotalInterestPaid();
    }

    /**
     * Method to get the interest/principal ratio.
     * @return the interest/principal ratio
     */
    public double getInterestPrincipalRatio() {
        return getTotalInterestPaid() / principal;
    }

    /**
     * Method to get the average interest paid per year.
     * @return the average interest paid per year
     */
    public double getAverageInterestPerYear() {
        return getTotalInterestPaid() / (numberOfPayments / (double) paymentFrequency);
    }

     /* Method to get the average interest paid per month.
     * @return the average interest paid per month
     */
    public double getAverageInterestPerMonth() {
        return (getAverageInterestPerYear() / 12);
    }

    /**
     * Method to get the amortization in years.
     * @return the amortization in years
     */
    public double getAmortizationInYears() {
        return numberOfPayments / (double) paymentFrequency;
    }

    /**
     * Method to get the payment schedule.
     * @return the payment schedule
     */
    public String paymentSchedule() {
        double interestFactor = calculateInterestFactor();
        double blendedPayment = calculateBlendedPayment();
        double remainingBalance = principal;
        StringBuilder paymentSchedule = new StringBuilder();

        /** Formatting the header row */
        paymentSchedule.append(String.format("%-10s | %-16s | %-16s | %-16s | %-16s%n",
                "Payment ", "Blended Payment", "Interest", "Principal Amount", "Balance"));

        /** Adding a separator line */
        paymentSchedule.append(String.join("", Collections.nCopies(76, "-"))).append("\n");

        for (int paymentNumber = 1; paymentNumber <= numberOfPayments; paymentNumber++) {
            double interestComponent = remainingBalance * interestFactor;
            double principalComponent = blendedPayment - interestComponent;
            remainingBalance -= principalComponent;

            /** Setting the remaining balance to 0 if it's negligible */

            if (Math.abs(remainingBalance) < 1e-2) {
                remainingBalance = 0.0;
            }
            /** Format each row with fixed width columns */
            paymentSchedule.append(String.format("%-16d | %-16f | %-16f | %-16f | %-16f%n",
                    paymentNumber, blendedPayment, interestComponent, principalComponent, remainingBalance));
        }

        /* Adding the new calculated values */

        paymentSchedule.append("\nAdditional Information :\n");
        paymentSchedule.append(String.format("\nTotal Interest Paid: %.2f%n", getTotalInterestPaid()));
        paymentSchedule.append(String.format("\nTotal Interest and Principal: %.2f%n", getTotalInterestAndPrincipal()));
        paymentSchedule.append(String.format("\nInterest/Principal Ratio: %.2f%n", getInterestPrincipalRatio()));
        paymentSchedule.append(String.format("\nAverage Interest Paid per Month: %.2f%n", getAverageInterestPerMonth()));
        paymentSchedule.append(String.format("\nAverage Interest Paid per Year: %.2f%n", getAverageInterestPerYear()));
        paymentSchedule.append(String.format("\nAmortization in Years: %.2f%n", getAmortizationInYears()));

        return paymentSchedule.toString();
    }
}
