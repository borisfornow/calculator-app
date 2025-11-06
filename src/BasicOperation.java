public class BasicOperation {

    private String equation;

    public BasicOperation(String equation){
        this.equation = equation;
    }

    public double calculate() {
        // Split by spaces (e.g., "3 + 5")
        String[] parts = equation.split(" ");

        // Basic validation
        if (parts.length != 3) {
            throw new IllegalArgumentException("Equation must be in the format: number operator number");
        }

        double num1 = Double.parseDouble(parts[0]);
        String operator = parts[1];
        double num2 = Double.parseDouble(parts[2]);

        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if (num2 == 0) throw new ArithmeticException("Cannot divide by zero");
                return num1 / num2;
            case "%":
                return num1 % num2;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
}
