package differentiation_engine.expressions.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import differentiation_engine.expressions.Const;
import differentiation_engine.expressions.Expression;
import differentiation_engine.expressions.Variable;
import differentiation_engine.expressions.binary.Exponent;
import differentiation_engine.expressions.n_ary.AddList;
import differentiation_engine.expressions.n_ary.MultiplyList;
import differentiation_engine.expressions.unary.Cos;
import differentiation_engine.expressions.unary.Logarithm;
import differentiation_engine.expressions.unary.Sin;
import differentiation_engine.expressions.unary.Tan;

public class Parser {
    // Get monomial expression of the form (1234xyz) from given index in input
    private Expression getMonomial(String input, MutableInteger index, Double constant) {
        int currentIndex = index.getValue();
        if (currentIndex >= input.length()) {
            // end of the monomial(and expression)
            return new Const(constant == null ? 1.0 : constant);
        }
        char c = input.charAt(currentIndex);
        if (Character.isDigit(c)) {
            // we have base-10 system, so we have value -> value*10 + c
            return getMonomial(input, index.inc(), (constant == null ? 0.0 : constant) * 10 + Character.getNumericValue(c));
        } else if (Character.isAlphabetic(c)) {
            // multiply by a variable (assume all variables have names length one)
            return new MultiplyList(new ArrayList<>(List.of(new Variable(Character.toString(c)), getMonomial(input, index.inc(), constant))));
        } else {
            // encountered a sign or a bracket - end of the monomial
            return new Const(constant == null ? 1.0 : constant);
        }
    }

    // Parse input string into a vector of items of type either SignItem or ExpressionItem
    private Vector<Item> parseString(String input, MutableInteger currentIndex) {
        Vector<Item> list = new Vector<Item>();

        while (true) {
            int index = currentIndex.getValue();
            if (index >= input.length()) {
                break; // TODO: throw Exception?
            }
            char c = input.charAt(index);

            if (c == ')') {
                // end of current expression
                currentIndex.inc();
                break;
            } else if (c == '(') {
                // begin of nested expression
                currentIndex.inc();
                Expression inside = getExpression(input, currentIndex);
                list.add(new ExpressionItem(inside));
            } else if (c == '+' || c == '-' || c == '*' || c == '^') {
                // sign operation
                list.add(new SignItem(c));
                currentIndex.inc();
            } else if (index + 4 < input.length() && input.substring(index, index + 3).equals("sin")) {
                // sin
                currentIndex.inc(4);
                Expression argument = getExpression(input, currentIndex);
                list.add(new ExpressionItem(new Sin(argument)));
            } else if (index + 4 < input.length() && input.substring(index, index + 3).equals("cos")) {
                // cos
                currentIndex.inc(4);
                Expression argument = getExpression(input, currentIndex);
                list.add(new ExpressionItem(new Cos(argument)));
            } else if (index + 4 < input.length() && input.substring(index, index + 3).equals("tan")) {
                // tan
                currentIndex.inc(4);
                Expression argument = getExpression(input, currentIndex);
                list.add(new ExpressionItem(new Tan(argument)));
            } else if (index + 3 < input.length() && input.substring(index, index + 2).equals("ln")) {
                // ln
                currentIndex.inc(3);
                Expression argument = getExpression(input, currentIndex);
                list.add(new ExpressionItem(new Logarithm(argument)));
            } else {
                // monomial
                Expression monomial = getMonomial(input, currentIndex, null).Simplify();
                list.add(new ExpressionItem(monomial));
            }

            // if we added second expression in a row, assume multiplication in between
            if (list.size() >= 2 && list.lastElement() instanceof ExpressionItem && list.get(list.size() - 2) instanceof ExpressionItem) {
                list.add(list.size() - 1, new SignItem('*'));
            }
        }

        return list;
    }

    // Decompose a list of {SignItem or ExperssionItem} into a single Expression
    private Expression decomposeList(Vector<Item> list) throws IllegalArgumentException {
        // validity checking
        if (list.isEmpty()) {
            return new AddList(new ArrayList<>());
        }
        if (list.get(0) instanceof SignItem || list.get(list.size() - 1) instanceof SignItem) {
            throw new IllegalArgumentException("list can't start/end with SignItem");
        }
        for (int i = 0; i + 1 < list.size(); i ++) {
            if (list.get(i) instanceof SignItem && list.get(i + 1) instanceof SignItem) {
                throw new IllegalArgumentException("list can't have two SignItems in a row");
            }
            if (list.get(i) instanceof ExpressionItem && list.get(i + 1) instanceof ExpressionItem) {
                throw new IllegalArgumentException("list can't have two ExpressionItems in a row");
            }
        }

        // decomposing exponents (^)
        Vector<Item> nextList = new Vector<>();
        nextList.add(list.lastElement());
        for (int i = list.size() - 2; i >= 1; i -= 2) {
            if (((SignItem)list.get(i)).getSign() == '^') {
                Expression expr = ((ExpressionItem)list.get(i - 1)).getExpression();
                nextList.set(nextList.size() - 1, new ExpressionItem(new Exponent(expr, ((ExpressionItem)nextList.lastElement()).getExpression())));
            } else {
                nextList.add(list.get(i));
                nextList.add(list.get(i - 1));
            }
        }
        Collections.reverse(nextList);

        // decomposing multiplication and addition
        // of the form (***) + (***) + (***)
        ArrayList<Expression> addList = new ArrayList<>();
        ArrayList<Expression> currentMulList = new ArrayList<>();
        currentMulList.add(((ExpressionItem)nextList.get(0)).getExpression());

        for (int i = 1; i < nextList.size(); i += 2) {
            if (((SignItem)list.get(i)).getSign() == '+') {
                addList.add(new MultiplyList(currentMulList).Simplify());
                currentMulList.clear();
            } else if (((SignItem)list.get(i)).getSign() == '-') {
                addList.add(new MultiplyList(currentMulList).Simplify());
                currentMulList.clear();
                currentMulList.add(new Const(-1));
            }
            currentMulList.add(((ExpressionItem)nextList.get(i + 1)).getExpression());
        }
        addList.add(new MultiplyList(currentMulList).Simplify());

        return new AddList(addList).Simplify();
    }

    private void _debugItemList(Vector<Item> list) {
        for (Item item : list) {
            if (item instanceof ExpressionItem e) {
                System.err.println("~~~~<expr>~~~~ " + e.getExpression());
            } else if (item instanceof SignItem s) {
                System.err.println("~~~~<sign>~~~~ " + s.getSign());
            } else {
                throw new RuntimeException("Invalid type of Item: neither Sign nor Expression");
            }
        }
        System.err.println("////");
    }

    // Recursive function getExpression from a given string input and currentIndex
    private Expression getExpression(String input, MutableInteger currentIndex) {
        Vector<Item> list = parseString(input, currentIndex);

        // *** for debug causes: ***
        // _debugItemList(list);
        // **********************

        Expression expr = decomposeList(list);
        // TODO: catch exceptions!!!

        return expr.Simplify();
    }

    // public parse String -> Expression function
    public Expression parse(String input) {
        // delete all zeroes, make lowercase, add outer brackets
        input = "(" + input.toLowerCase().chars().filter(c -> c != ' ').mapToObj(c -> String.valueOf((char)c)).collect(Collectors.joining()) + ")";
        // initialize current index used by helper parser functions
        MutableInteger currentIndex = new MutableInteger(1);
        return getExpression(input, currentIndex);
    }
}