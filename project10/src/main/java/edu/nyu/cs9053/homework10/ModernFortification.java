package edu.nyu.cs9053.homework10;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * User: blangel
 */
public class ModernFortification extends AbstractFortification implements Fortification<ExecutorService> {
    private final ExecutorService exec;
    private final AtomicInteger running;
    private final BlockingQueue<Runnable> queue;

    public ModernFortification(final int concurrencyFactor) {
        super(concurrencyFactor);
        queue = new LinkedBlockingQueue<>();
        exec = Executors.newFixedThreadPool(concurrencyFactor);
        running = new AtomicInteger(0);
        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                if (running.get() < getConcurrencyFactor()) {
                    synchronized (ModernFortification.class) {
                        if (!queue.isEmpty()) {
                            running.incrementAndGet();
                            exec.submit(queue.poll());
                        }
                    }
                } else {
                    try {
                        Thread.sleep(100L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    public void handleAttack(AttackHandler handler) {
        if (running.get() < getConcurrencyFactor()) {
            running.incrementAndGet();
            exec.submit(() -> {
                handler.soldiersReady();
                running.decrementAndGet();
            });
        } else {
            queue.offer(() -> {
                handler.soldiersReady();
                running.decrementAndGet();
            });
        }
    }

    @Override
    public void surrender() {
        exec.shutdownNow();
    }

}
