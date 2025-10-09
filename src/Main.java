import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.print("Enter a number: ");
            String num1Str = scanner.nextLine();

            System.out.print("Enter an operation (- + / % *): ");
            String op = scanner.nextLine();

            System.out.print("Enter another number: ");
            String num2Str = scanner.nextLine();

            // Validate numbers
            if (!num1Str.matches("-?\\d+") || !num2Str.matches("-?\\d+")) {
                System.out.println("Invalid numbers! Please enter digits only.");
                continue; // Restart loop
            }

            // Validate operator
            if (!op.equals("-") && !op.equals("+") && !op.equals("/") && !op.equals("%") && !op.equals("*")) {
                System.out.println("Invalid operator! Use - + / % *");
                continue; // Restart loop
            }

            int number1 = Integer.parseInt(num1Str);
            int number2 = Integer.parseInt(num2Str);
            int ans = 0;

            // Handle operations
            switch (op) {
                case "-" -> ans = number1 - number2;
                case "+" -> ans = number1 + number2;
                case "/" -> {
                    if (number2 == 0) {
                        System.out.println("Cannot divide by zero!");
                        continue;
                    }
                    ans = number1 / number2;
                }
                case "%" -> {
                    if (number2 == 0) {
                        System.out.println("Cannot modulo by zero!");
                        continue;
                    }
                    ans = number1 % number2;
                }
                case "*" -> ans = number1 * number2;
            }

            System.out.println("Your answer is: " + ans);

            // Ask if user wants to continue
            System.out.print("Do you want to calculate again? (yes/no): ");
            String again = scanner.nextLine().trim().toLowerCase();
            if (!again.equals("yes")) {
                running = false;
            }
        }

        System.out.println("Calculator closed.");
    }
}
