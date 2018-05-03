package edu.nyu.cs9053.homework9;

public class Espresso implements CoffeeDrink {

    private final boolean ifDecaf;
    private final boolean containsMilk;

    public Espresso(boolean ifDecaf, boolean containsMilk) {
        this.ifDecaf = ifDecaf;
        this.containsMilk = containsMilk;
    }

    @Override
    public boolean isDecaf() {
        return ifDecaf;
    }

    @Override
    public boolean containsMilk() {
        return containsMilk;
    }

}
