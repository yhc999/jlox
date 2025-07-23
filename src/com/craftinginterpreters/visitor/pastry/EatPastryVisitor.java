package com.craftinginterpreters.visitor.pastry;

public class EatPastryVisitor implements PastryVisitor {
    @Override
    public void visitBeignet(Beignet beignet) {
        System.out.println("eat beignet");
    }

    @Override
    public void visitCruller(Cruller cruller) {
        System.out.println("eat cruller");
    }

    @Override
    public void visitBahulu(Bahulu bahulu) {
        System.out.println("eat bahulu");
    }

    @Override
    public void visitBaklava(Baklava baklava) {
        System.out.println("eat baklava");
    }

    @Override
    public void visitFlaons(Flaons flaons) {
        System.out.println("eat flaons");
    }

    public static void main(String[] args) {
        PastryVisitor eatPastryVisitor = new EatPastryVisitor();
        Beignet beignet = new Beignet();
        beignet.accept(eatPastryVisitor);
    }
}
