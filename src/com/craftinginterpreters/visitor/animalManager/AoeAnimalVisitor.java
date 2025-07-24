package com.craftinginterpreters.visitor.animalManager;

public class AoeAnimalVisitor implements AnimalVisitor {
    @Override
    public void visitDog(Dog dog) {
        dog.health -= 100;
        if (dog.health <= 0) {
            dog.isLive = false;
        }
    }

    @Override
    public void visitCat(Cat cat) {
        cat.health -= 50;
        if (cat.health <= 0) {
            cat.isLive = false;
        }
    }

    @Override
    public void visitWolf(Wolf wolf) {
        wolf.health -= 500;
        if (wolf.health <= 0) {
            wolf.isLive = false;
        }
    }

    @Override
    public void visitTiger(Tiger tiger) {
        tiger.health -= 2000;
        if (tiger.health <= 0) {
            tiger.isLive = false;
        }
    }
}
