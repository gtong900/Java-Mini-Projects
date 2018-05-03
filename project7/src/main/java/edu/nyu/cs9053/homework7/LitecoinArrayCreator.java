package edu.nyu.cs9053.homework7;

public class LitecoinArrayCreator implements ArrayCreator<Litecoin> {
    
    @Override
    public Litecoin[] createCurrency(int size) {
        return new Litecoin[size];
    }
}
