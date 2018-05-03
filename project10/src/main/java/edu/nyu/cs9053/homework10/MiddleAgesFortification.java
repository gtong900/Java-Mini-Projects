package edu.nyu.cs9053.homework10;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * User: blangel
 */
public class MiddleAgesFortification extends AbstractFortification implements Fortification<Thread> {
    private final Thread[] threads;
    private final AtomicInteger running;

    public MiddleAgesFortification(int concurrencyFactor) {
        super(concurrencyFactor);
        threads = new Thread[concurrencyFactor];
        this.running = new AtomicInteger(0);
        for (int i = 0; i < getConcurrencyFactor(); i++) {
            threads[i] = new Thread();
        }
    }


    @Override
    public void handleAttack(AttackHandler handler) {

        if (running.get() < getConcurrencyFactor()) {
            for (int i = 0; i < getConcurrencyFactor(); i++) {
                if (!threads[i].isAlive() ) {
                    running.incrementAndGet();
                    threads[i] = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            handler.soldiersReady();
                            running.decrementAndGet();
                        }
                    });
                    threads[i].start();
                    return;
                }
            }
        }


    }

    @Override
    public void surrender() {
        for (int i = 0; i < getConcurrencyFactor(); i++) {
            if (threads[i] != null) {
                threads[i].interrupt();
            }
        }
    }

}
