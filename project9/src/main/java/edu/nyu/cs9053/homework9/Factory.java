package edu.nyu.cs9053.homework9;
import java.util.concurrent.Semaphore;

/**
 * User: blangel
 */
public class Factory {
    private final static Semaphore binarySemaphore = new Semaphore(1);

    public static Customer createCustomer() {
        NormalCustomer normalCustomer = new NormalCustomer(binarySemaphore);
        return normalCustomer;
    }

    public static Barista createBarista() {

        NormalBarista normalBarista = new NormalBarista(binarySemaphore);
        return  normalBarista;
    }
}
