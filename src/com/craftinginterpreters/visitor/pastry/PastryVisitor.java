package com.craftinginterpreters.visitor.pastry;

public interface PastryVisitor { // Visitor --> Operate set [operate1, operate2, operate3...]
    void visitBeignet(Beignet beignet);
    void visitCruller(Cruller cruller);
    void visitBahulu(Bahulu bahulu);
    void visitBaklava(Baklava baklava);
    void visitFlaons(Flaons flaons);
}
