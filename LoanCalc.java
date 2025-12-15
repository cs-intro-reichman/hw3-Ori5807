public class LoanCalc {

    static double epsilon = 0.001;   // Approximation accuracy
    static int iterationCounter;     // Number of iterations

    public static void main(String[] args) {

        // Safety check (optional but recommended)
        if (args.length != 3) {
            System.out.println("Usage: java LoanCalc <loan> <rate> <periods>");
            return;
        }

        // Gets the loan data
        double loan = Double.parseDouble(args[0]);
        double rate = Double.parseDouble(args[1]);
        int n = Integer.parseInt(args[2]);

        System.out.println("Loan = " + loan + ", interest rate = " + rate + "%, periods = " + n);

        // -------- Brute force --------
        System.out.print("\nPeriodical payment, using brute force: ");
        System.out.println((int) bruteForceSolver(loan, rate, n, epsilon));
        System.out.println("number of iterations: " + iterationCounter);

        // -------- Bisection --------
        System.out.print("\nPeriodical payment, using bi-section search: ");
        System.out.println((int) bisectionSolver(loan, rate, n, epsilon));
        System.out.println("number of iterations: " + iterationCounter);
    }

    // ------------------ Brute Force Solver ------------------
    public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {

        iterationCounter = 0;

        double prevBalance = remainingBalance(loan, rate, n, 0);

        for (double payment = epsilon; payment <= loan; payment += epsilon) {
            iterationCounter++;
            double balance = remainingBalance(loan, rate, n, payment);

            // Found sign change → crossed zero
            if (prevBalance >= 0 && balance <= 0) {
                return payment;
            }

            prevBalance = balance;
        }

        return 0; // Should not happen for valid input
    }

    // ------------------ Bisection Solver ------------------
    public static double bisectionSolver(double loan, double rate, int n, double epsilon) {

        iterationCounter = 0;

        double low = 0;
        double high = loan;
        double mid;

        while (high - low > epsilon) {
            mid = (low + high) / 2;
            iterationCounter++;

            double balance = remainingBalance(loan, rate, n, mid);

            if (balance > 0) {
                low = mid;
            } else {
                high = mid;
            }
        }

        return (low + high) / 2;
    }

    // ------------------ Remaining Balance ------------------
    public static double remainingBalance(double loan, double rate, int n, double payment) {

        double monthlyRate = rate / 1200.0;
        double balance = loan;

        for (int i = 0; i < n; i++) {
            balance = balance * (1 + monthlyRate) - payment;
        }

        return balance;
    }
}