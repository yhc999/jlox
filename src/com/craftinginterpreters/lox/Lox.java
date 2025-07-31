package com.craftinginterpreters.lox;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class Lox {

    private static final Interpreter interpreter = new Interpreter();
    static boolean hadError = false;
    static boolean hadRuntimeError = false;

    static void runtimeError(RuntimeError error) {
        System.err.println(error.getMessage() +
                "\n[line " + error.token.line + "]");
        hadRuntimeError = true;
    }
    static void error(Token token, String message) {
        if (token.type == TokenType.EOF) {
            report(token.line, " at end", message);
        } else {
            report(token.line, " at '" + token.lexeme + "'", message);
        }
    }
    private static void report(int line, String where, String message) {
        System.err.println(
                "[line " + line + "] Error" + where + ": " + message
        );
        hadError = true;
    }

    private static void runFile(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        run(new String(bytes, Charset.defaultCharset()));

        // Indicate an error in the exit code.
        if (hadError) System.exit(65);
        if (hadRuntimeError)   System.exit(70);
    }

    private static void runPrompt() throws IOException {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);

        for (;;) {
            System.out.print("> ");
            String line = reader.readLine();
            if (line == null) break;
            run(line);
            hadError = false;
        }
    }

    private static void run(String source) {
        Scanner scanner = new com.craftinginterpreters.lox.Scanner(source);
        List<Token> tokens = scanner.scanTokens();
        Parser parser = new Parser(tokens);

        Expr expression = parser.parse();
        if (hadError)   // Stop if there was syntax error.
            return;

        interpreter.interpret(expression);
    }

    // check arg is valid file
    private static boolean isValidArg(String filePath) {
        File file = new File(filePath);

        // 基础判断：文件是否存在，是文件（不是目录），且可读
        if (!file.exists() || !file.isFile() || !file.canRead()) {
            return false;
        }

        // 尝试按文本方式读取前几行，判断是否为文本文件
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int linesChecked = 0;
            while (linesChecked < 5) {
                String line = reader.readLine();
                if (line == null) break;  // EOF reached
                linesChecked++;
            }
            return true;  // 如果能正常读取几行，说明大概率是合法文本文件
        } catch (IOException e) {
            return false; // 无法读取，说明可能不是合法文本文件
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length > 1) {
            System.out.println("Usage: jlox [script]");
            System.exit(64);
        } else if (args.length == 1) {
            if (isValidArg(args[0])) {
                runFile(args[0]);
            } else {
                System.out.println(args[0] + " is invalid file");
                System.exit(2);
            }
        } else {
            runPrompt();
        }
    }
}