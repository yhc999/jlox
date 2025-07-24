package com.craftinginterpreters.visitor.animalManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Manager {

    private static Map<String, Animal> animalMap = new HashMap<>();

    public static void printUsage() {
        String usage = """
                All Prompt:
                    list | l : list all animals in current system
                    help | h : print this help doc
                    quit | q : quit this animal Manager
                    create | c : create new animal,
                                 current supported animal types:
                                 1.Cat, 2.Dog, 3.Tiger, 4.Wolf
                    aoe : aoe all animals, make these reduce some health
                """;
        System.out.print(usage);
    }

    public static void run() throws IOException {
        for (;;) {
            System.out.print("===> ");

            InputStreamReader input = new InputStreamReader(System.in);
            BufferedReader reader = new BufferedReader(input);
            String userInput = reader.readLine();

            if (userInput.equals("quit") || userInput.equals("q")) {
                break;
            } else {
                switch (userInput) {
                    case "help", "h" -> printUsage();
                    case "list", "l" -> Prompts.print();
                    case "create", "c" -> Prompts.create();
                    case "aoe" -> Prompts.aoe();
                    default -> {
                        System.out.println("Sorry, your prompt is not correct, usage is:");
                        printUsage();
                    }
                }
            }
        }
    }

    public static Map<String, Animal> getAnimalMap() {
        return animalMap;
    }
}
