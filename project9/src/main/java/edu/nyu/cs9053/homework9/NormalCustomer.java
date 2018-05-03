package edu.nyu.cs9053.homework9;
import java.util.concurrent.Semaphore;
import java.util.ArrayList;
import java.util.Random;

public class NormalCustomer implements Customer {

    private final Semaphore binarySemaphore;
    private final static Random random = new Random();


    public NormalCustomer(Semaphore binarySemaphore) {
        this.binarySemaphore = binarySemaphore;
    }

    @Override
    public OrderNumber order(Queue queue) {
        if (queue == null) {
            throw new IllegalArgumentException();
        }

        try {
            binarySemaphore.acquire();
            try {
                if (!queue.full()) {
                    return queue.addOrder(RandomCoffeeDrink());
                }
                return null;
            } finally {
                binarySemaphore.release();
            }
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(ie);
        }

    }

    private CoffeeDrink RandomCoffeeDrink() {
        ArrayList<CoffeeDrink> randomCoffeeDrink = new ArrayList<>(2){{add(new Latte(true, true)); add(new Espresso(true,true));}};
        return randomCoffeeDrink.get(random.nextInt(2));
    }

}
