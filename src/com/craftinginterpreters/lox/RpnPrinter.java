package com.craftinginterpreters.lox;

/**
 * 逆波兰表示法 (RPN)
 */
public class RpnPrinter implements Expr.Visitor<String> {
    String print(Expr expr) {
        return expr.accept(this);
    }

    @Override
    public String visitBinaryExpr(Expr.Binary expr) {
        return expr.left.accept(this) + " " + expr.right.accept(this) + " " + expr.operator.lexeme;
    }

    @Override
    public String visitGroupingExpr(Expr.Grouping expr) {
        return "(" + expr.expression.accept(this) + ")";
    }

    @Override
    public String visitLiteralExpr(Expr.Literal expr) {
        if (expr.value == null) {
            return "nil";
        } else {
            return expr.value.toString();
        }
    }

    @Override
    public String visitUnaryExpr(Expr.Unary expr) {
        return "(" + expr.expression.accept(this) + " " + expr.operator.lexeme + ")";
    }

    public static void main(String[] args) {
        // define {+ - * /} token for test; define some object for test
        Token plus = new Token(TokenType.PLUS, "+", null, 1);
        Token minus = new Token(TokenType.MINUS, "-", null, 2);
        Token mul = new Token(TokenType.STAR, "*", null, 3);
        Token div = new Token(TokenType.SLASH, "/", null, 4);
        Token not = new Token(TokenType.BANG, "!", null, 1);

        Expr one = new Expr.Literal(1);
        Expr two = new Expr.Literal(2);
        Expr three = new Expr.Literal(3);
        Expr four = new Expr.Literal(4);
        Expr T = new Expr.Literal("true");
        Expr F = new Expr.Literal("false");

        Expr nil = new Expr.Literal(null);

        // (+ 1 1) -> 1 1 +
        Expr expression = null;
//        expression = new Expr.Binary(
//                one,
//                plus,
//                one
//        );

        // (1 + 2) * (4 - 3) -> 1 2 + 4 3 - *
//        expression = new Expr.Binary(
//          new Expr.Binary(
//                  one,
//                  plus,
//                  two
//          ),
//          mul,
//          new Expr.Binary(
//                  four,
//                  minus,
//                  three
//          )
//        );

        // (+ (* 1 2) (nil)) -> 1 2 * (nil) +
//        expression = new Expr.Binary(
//                new Expr.Binary(
//                        one,
//                        mul,
//                        two
//                ),
//                plus,
//                new Expr.Grouping(nil)
//        );

        // (- 1) -> (1 -)
//        expression = new Expr.Unary(minus, one);

        // (! false) -> (false !)
        expression = new Expr.Unary(not, F);

        System.out.println(new RpnPrinter().print(expression));
    }
}
