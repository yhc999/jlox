package com.craftinginterpreters.visitor.animalManager;

import java.io.IOException;

public class AnimalApp {
    public static void main(String[] args) throws IOException {
        System.out.println("======This is a Animal Manager=======");
        Manager.printUsage();
        Manager.run();
    }
}
