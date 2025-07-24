package com.craftinginterpreters.visitor.animalManager;

import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Prompts {
    public static void print() {
        PrintAnimalVisitor printAnimalVisitor = new PrintAnimalVisitor();
        for (Animal animal : Manager.getAnimalMap().values()) {
            animal.accept(printAnimalVisitor);
        }
    }

    public static void aoe() {
        AoeAnimalVisitor aoeAnimalVisitor = new AoeAnimalVisitor();
        Iterator<Map.Entry<String, Animal>> iterator = Manager.getAnimalMap().entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, Animal> entry = iterator.next();
            Animal animal = entry.getValue();

            animal.accept(aoeAnimalVisitor);
            if (!animal.isLive) {
                iterator.remove();
            }
        }
    }

    public static void create() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please type 1 or 2 or 3 or 4:\n" +
                "                                 1.Cat, 2.Dog, 3.Tiger, 4.Wolf");
        String type = scanner.next();
        while (type.length() != 1 || !"1234".contains(type)) {
            System.out.println("Please type 1 or 2 or 3 or 4:\n" +
                    "                                 1.Cat, 2.Dog, 3.Tiger, 4.Wolf");
            type = scanner.next();
        }

        System.out.print("input name: ");
        String name = scanner.next();

        System.out.print("input attack: ");
        Double attack = scanner.nextDouble();

        System.out.print("input defense: ");
        Double defense = scanner.nextDouble();

        System.out.print("input health: ");
        Double health = scanner.nextDouble();

        Animal animal = null;

        switch (type) {
            case "1" -> {
                animal = new Cat(name, attack, defense, health);
            }
            case "2" -> {
                animal = new Dog(name, attack, defense, health);
            }
            case "3" -> {
                animal= new Tiger(name, attack, defense, health);
            }
            case "4" -> {
                animal = new Wolf(name, attack, defense, health);
            }
        }

        Manager.getAnimalMap().put(name, animal);
        System.out.println(name + " created successfully!");
        System.out.println("Current all animals:");
        Prompts.print();
    }
}
