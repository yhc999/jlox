package com.craftinginterpreters.tool;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class GenerateAst {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage: generate_ast <output directory>");
            System.exit(64);
        }
        String outputDir = args[0];
        defineAst(outputDir, "Expr", Arrays.asList(
           "Binary      : Expr left, Token operator, Expr right",
           "Grouping    : Expr expression",
           "Literal     : Object value",
           "Unary       : Token operator, Expr expression"
        ));
    }

    private static void defineAst(String outputDir, String baseName, List<String> types) throws IOException {
        String path = outputDir + "/" + baseName + ".java";
        PrintWriter writer = new PrintWriter(path, StandardCharsets.UTF_8);

        writer.println("package com.craftinginterpreters.lox;");
        writer.println();
        writer.println("import java.util.List;");
        writer.println();
        writer.println("abstract class " + baseName +  "{");

        // The AST classes.
        for (String type : types) {
            String className = type.split(":")[0].trim();
            String fields = type.split(":")[1].trim();
            defineType(writer, baseName, className, fields);
        }
        writer.println("}");
        writer.close();
    }
    private static void defineType(PrintWriter writer, String baseName, String className, String fields) {
        writer.println();

        writer.println("    static class " + className + " extends " + baseName + " {");

        // Fields.
        String[] fieldArray = fields.split(", ");
        for (String field : fieldArray) {
            writer.println("        final " + field + ";");
        }

        writer.println();

        // Constructor.
        writer.println("        " + className + "(" + fields + ") {");
        for (String field : fieldArray) {
            String fieldVale = field.split(" ")[1];
            writer.println("            " + "this." + fieldVale + " = " + fieldVale + ";");
        }

        writer.println("        }");
        writer.println("    }");
    }
}
