package differentiation_engine;

import java.util.Scanner;

import differentiation_engine.expressions.Expression;
import differentiation_engine.expressions.Variable;
import differentiation_engine.parser.Parser;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser();
        
        System.out.println("Enter expression for differentiation:");
        String input = scanner.next();
        Expression expr = parser.parse(input).Simplify();
        System.out.println("Parsed expression: " + expr);

        System.out.println("Enter variable for differentiation:");
        Variable var = new Variable(scanner.next());
        
        System.out.println(expr.differentiate(var).Simplify());

        scanner.close();
    }
}
