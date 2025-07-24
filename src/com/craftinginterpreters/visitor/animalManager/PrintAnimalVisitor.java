package com.craftinginterpreters.visitor.animalManager;

public class PrintAnimalVisitor implements AnimalVisitor {
    @Override
    public void visitDog(Dog dog) {
        System.out.println(dog);
    }

    @Override
    public void visitCat(Cat cat) {
        System.out.println(cat);
    }

    @Override
    public void visitWolf(Wolf wolf) {
        System.out.println(wolf);
    }

    @Override
    public void visitTiger(Tiger tiger) {
        System.out.println(tiger);
    }
}
