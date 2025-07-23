package com.craftinginterpreters.visitor.pastry;

public class PrintPastryVisitor implements PastryVisitor {
    @Override
    public void visitBeignet(Beignet beignet) {
        System.out.println("Beignet > sweet and soft.");
    }

    @Override
    public void visitCruller(Cruller cruller) {
        System.out.println("Cruller > crunchy and delicious.");
    }

    @Override
    public void visitBahulu(Bahulu bahulu) {
        System.out.println("Bahulu >>>>>----<<<<");
    }

    @Override
    public void visitBaklava(Baklava baklava) {
        System.out.println("Baklava =====> ok");
    }

    @Override
    public void visitFlaons(Flaons flaons) {
        System.out.println("Flaons ----+---+---- Very good.");
    }

    public static void main(String[] args) {
        PastryVisitor printPastryVisitor = new PrintPastryVisitor();
        Flaons flaons = new Flaons();
        flaons.accept(printPastryVisitor);

        Bahulu bahulu = new Bahulu();
        bahulu.accept(printPastryVisitor);
    }
}
