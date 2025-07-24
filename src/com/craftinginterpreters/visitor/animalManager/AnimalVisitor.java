package com.craftinginterpreters.visitor.animalManager;

public interface AnimalVisitor {
    void visitDog(Dog dog);
    void visitCat(Cat cat);
    void visitWolf(Wolf wolf);
    void visitTiger(Tiger tiger);
}
