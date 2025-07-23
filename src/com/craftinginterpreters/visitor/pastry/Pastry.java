package com.craftinginterpreters.visitor.pastry;

public abstract class Pastry {
    abstract void accept(PastryVisitor visitor);
}

class Beignet extends Pastry {
    @Override
    void accept(PastryVisitor visitor) {
        visitor.visitBeignet(this);
    }
}

class Cruller extends Pastry {
    @Override
    void accept(PastryVisitor visitor) {
        visitor.visitCruller(this);
    }
}

class Bahulu extends Pastry {
    @Override
    void accept(PastryVisitor visitor) {
        visitor.visitBahulu(this);
    }
}

class Baklava extends Pastry {
    @Override
    void accept(PastryVisitor visitor) {
        visitor.visitBaklava(this);
    }
}

class Flaons extends Pastry {
    @Override
    void accept(PastryVisitor visitor) {
        visitor.visitFlaons(this);
    }
}

