package differentiation_engine;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        Expression c1 = new Const(1);
        Expression c2 = new Const(2);
        Expression c3 = new Const(3);
        Expression c4 = new Const(4);
        Expression c5 = new Const(5);
        Expression cm1 = new Const(-1);
        Expression c10 = new Const(10);
        // assertTrue(true);
        // Variable x = new Variable("x");
        // Variable y = new Variable("y");
        // Expression expr1 = new Add(x, y);
        // Expression diff1 = expr1.differentiate(x);

        // System.out.println(expr1.toString());
        // System.out.println(diff1.toString());

        Variable x = new Variable("x");
        Expression expr2 = new Multiply(x, new Sin(new Multiply(new Const(10), x)));
        Expression diff2 = expr2.differentiate(x);
        Expression diff22 = diff2.differentiate(x);

        System.err.println(expr2.toString());
        System.err.println(diff2.toString());
        System.err.println(diff22.toString());
        
        diff2 = diff2.Simplify();
        diff22 = diff22.Simplify();
        
        System.err.println("-------------");
        System.err.println(diff2.toString());
        System.err.println(diff22.toString());

        System.err.println("=============");
        
        Expression p = new Exponent(x, new Logarithm(new Multiply(c2, x)));
        Expression dp = p.differentiate(x).Simplify();
        System.err.println(p);
        System.err.println(dp);
    }
}
