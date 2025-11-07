import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class BasicOperation {

    private String equation;

    public BasicOperation(String equation){
        this.equation = equation;
    }

    public double calculate() {
        Expression expression = new ExpressionBuilder(equation).build();
        return expression.evaluate();
    }
}
