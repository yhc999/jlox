package com.craftinginterpreters.lox;

public class Interpreter implements Expr.Visitor<Object> {

    /**
     *  Interpreter's public API
     * @param expression AST data structure
     */
    void interpret(Expr expression) {
        try {
            Object value = evaluate(expression);
            System.out.println(stringify(value));
        } catch (RuntimeError error) {
            Lox.runtimeError(error);
        }
    }
    private String stringify(Object value) {
        if (value == null)  return "nil";

        if (value instanceof Double) {
            String text = value.toString();
            if (text.endsWith(".0")) {
                return text.substring(0, text.length()-2);
            }
            return text;
        }

        return value.toString();
    }

    @Override
    public Object visitBinaryExpr(Expr.Binary expr) {
        Object left = evaluate(expr.left);
        Object right = evaluate(expr.right);

        switch (expr.operator.type) {
            case BANG_EQUAL -> { // !=
                return !isEqual(left, right);
            }
            case EQUAL_EQUAL -> { // ==
                return isEqual(left, right);
            }
            case GREATER -> { // >
                checkNumberOperands(expr.operator, left, right);
                return (double) left > (double) right;
            }
            case GREATER_EQUAL -> { // >=
                checkNumberOperands(expr.operator, left, right);
                return (double) left >= (double) right;
            }
            case LESS -> { // <
                checkNumberOperands(expr.operator, left, right);
                return (double) left < (double) right;
            }
            case LESS_EQUAL -> { // <=
                checkNumberOperands(expr.operator, left, right);
                return (double) left <= (double) right;
            }
            case PLUS -> { // +
                if (left instanceof Double && right instanceof Double)
                    return (double) left + (double) right;
                if (left instanceof String && right instanceof Double)
                    return (double) left + (double) right;

                throw new RuntimeError(expr.operator,
                        "Operands must be two numbers or two strings.");
            }
            case MINUS -> { // -
                checkNumberOperands(expr.operator, left, right);
                return (double) left - (double) right;
            }
            case STAR -> { // *
                checkNumberOperands(expr.operator, left, right);
                return (double) left * (double) right;
            }
            case SLASH -> { // /
                checkNumberOperands(expr.operator, left, right);
                return (double) left / (double) right;
            }
        }

        // Unreachable.
        return null;
    }

    @Override
    public Object visitGroupingExpr(Expr.Grouping expr) {
        return evaluate(expr.expression);
    }

    @Override
    public Object visitLiteralExpr(Expr.Literal expr) {
        return expr.value;
    }

    @Override
    public Object visitUnaryExpr(Expr.Unary expr) {
        Object value = evaluate(expr.expression);

        switch (expr.operator.type) {
            case BANG -> {
                return !isTruthy(value);
            }
            case MINUS -> {
                checkNumberOperand(expr.operator, value);
                return -(double)value;
            }
        }

        // Unreachable.
        return null;
    }

    private Object evaluate(Expr expr) {
        return expr.accept(this);
    }

    private boolean isTruthy(Object value) {
        if (value == null) return false;
        if (value instanceof Boolean) return (Boolean) value;
        return true;
    }

    private boolean isEqual(Object left, Object right) {
        if (left == null && right == null)  return true;
        if (left == null)   return false;

        return left.equals(right);
    }

    private void checkNumberOperand(Token operator, Object operand) {
        if (operand instanceof Double) return;
        throw new RuntimeError(operator, "Operand must be a number.");
    }

    private void checkNumberOperands(Token operator, Object left, Object right) {
        if (left instanceof Double && right instanceof Double)
            return;
        throw new RuntimeError(operator, "Operands must be numbers.");
    }
}
