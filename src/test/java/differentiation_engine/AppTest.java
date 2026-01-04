package differentiation_engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

import differentiation_engine.expressions.*;
import differentiation_engine.expressions.n_ary.*;
import differentiation_engine.expressions.binary.*;
import differentiation_engine.expressions.unary.*;
import differentiation_engine.parser.Parser;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    // @Test
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

        Expression ddp = p.differentiate(x).differentiate(x);
        System.err.println(ddp);
        ddp = ddp.Simplify();
        System.err.println(ddp);

        System.err.println("~~~~~~~~~~~~~~~~");

        Expression x_2 = new Exponent(x, c2);
        Expression x_3 = new Exponent(x, c3);
        Expression x_4 = new Exponent(x, c4);

        //(Expression)(new Multiply(c5, x_3))
        Expression s = new AddList(new ArrayList<>(java.util.List.of(x_2, new Multiply(c4, x_3), x_4)));
        System.err.println(s.toString());
        System.err.println(s.differentiate(x));
        System.err.println(" ~simplify~ " + s.differentiate(x).Simplify());
        System.err.println();
        System.err.println(s.differentiate(x).differentiate(x));
        System.err.println(" ~simplify~ " + s.differentiate(x).differentiate(x).Simplify());

        System.err.println("///////////////////////////");
        Expression m = new MultiplyList(new ArrayList<>(List.of(c2, x_3, new Logarithm(x), new Tan(x))));
        // Expression m = new MultiplyList(new ArrayList<>(List.of(new Logarithm(x), new Sin(x))));
        System.err.println(m);
        System.err.println(m.Simplify());
        System.err.println(m.differentiate(x));
        System.err.println(m.differentiate(x).Simplify());
    }

    @Test
    public void testParser() {
        System.err.println("================================");
        System.err.println("================================");

        // Scanner scanner = new Scanner(System.in);
        
        // String s = scanner.next();
        // System.err.println(s);
        
        // scanner.close();
        Parser parser = new Parser();
        Expression expr = parser.parse("x^3*(x*ln(2*x)+tan(y+12))");
        System.err.println(expr);
        System.err.println(expr.differentiate(new Variable("x")).Simplify());

        Expression expr2 = parser.parse("x^(3*x*ln(2+y))");
        System.err.println(expr2);
        System.err.println(expr2.differentiate(new Variable("x")).Simplify());
    }
}
