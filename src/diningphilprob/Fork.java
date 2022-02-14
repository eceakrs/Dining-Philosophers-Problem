/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diningphilprob;

/**
 *
 * @author eceak
 */
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class Fork {

    private int id;
    private Thread thread;
    private final Lock lock = new ReentrantLock();
    private boolean[] taking;
    private Condition condition;

    public Fork(int id) {
        this.id = id;
        thread = null;
        this.condition = this.lock.newCondition();
        this.taking = new boolean[]{false, false, false, false, false};
    }

    public int getId() {
        return id;
    }

    public synchronized void pickup() throws InterruptedException {
        
        if (thread != null) {
            this.wait();
        }
        thread = Thread.currentThread();
        
    }

    public synchronized void release() {
       
        if (thread == Thread.currentThread()) {
            thread = null;
        }
        this.notify();
        
    }

}
