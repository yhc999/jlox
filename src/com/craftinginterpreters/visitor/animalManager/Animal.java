package com.craftinginterpreters.visitor.animalManager;

public abstract class Animal {
    String name;
    Double attack;
    Double defense;
    Double health;
    Boolean isLive;

    public Animal(String name, Double attack, Double defense, Double health) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.health = health;
        this.isLive = true;
    }

    abstract void accept(AnimalVisitor visitor);

}

class Dog extends Animal {
    public Dog(String name, Double attack, Double defense, Double health) {
        super(name, attack, defense, health);
    }

    @Override
    void accept(AnimalVisitor visitor) {
        visitor.visitDog(this);
    }

    @Override
    public String toString() {
        return "{ " + "Dog-> |"
                + "name : " + name + "|"
                + "attack : " + attack + "|"
                + "defense : " + defense + "|"
                + (isLive ? "live" : "dead")
                + "(health is: " + health + ")|"
                + " }";
    }

    public static void main(String[] args) {
        Dog dog = new Dog("tom", 10.1, 0.0, 100.0);
        dog.isLive = false;
        System.out.println(dog);
    }
}

class Cat extends Animal {
    public Cat(String name, Double attack, Double defense, Double health) {
        super(name, attack, defense, health);
    }

    @Override
    void accept(AnimalVisitor visitor) {
        visitor.visitCat(this);
    }

    @Override
    public String toString() {
        return "{ " + "Cat-> |"
                + "name : " + name + "|"
                + "attack : " + attack + "|"
                + "defense : " + defense + "|"
                + (isLive ? "live" : "dead")
                + "(health is: " + health + ")|"
                + " }";
    }
}

class Wolf extends Animal {
    public Wolf(String name, Double attack, Double defense, Double health) {
        super(name, attack, defense, health);
    }

    @Override
    void accept(AnimalVisitor visitor) {
        visitor.visitWolf(this);
    }

    @Override
    public String toString() {
        return "{ " + "Wolf-> |"
                + "name : " + name + "|"
                + "attack : " + attack + "|"
                + "defense : " + defense + "|"
                + (isLive ? "live" : "dead")
                + "(health is: " + health + ")|"
                + " }";
    }
}

class Tiger extends Animal {
    public Tiger(String name, Double attack, Double defense, Double health) {
        super(name, attack, defense, health);
    }

    @Override
    void accept(AnimalVisitor visitor) {
        visitor.visitTiger(this);
    }

    @Override
    public String toString() {
        return "{ " + "Tiger-> |"
                + "name : " + name + "|"
                + "attack : " + attack + "|"
                + "defense : " + defense + "|"
                + (isLive ? "live" : "dead")
                + "(health is: " + health + ")|"
                + " }";
    }
}