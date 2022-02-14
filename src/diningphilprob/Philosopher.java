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
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class Philosopher extends Thread implements Runnable {

    int thinking = 1000;
    int id;
    int eatingTime = 500;
    Random rand = new Random();
    Fork left;
    Fork right;
    private final Lock lock = new ReentrantLock();
    private boolean[] taking;
    private Condition condition;

    public Philosopher(int id, Fork left, Fork right) {
        this.id = id;
        this.left = left;
        this.right = right;
        this.condition = this.lock.newCondition();
        this.taking = new boolean[]{false, false, false, false, false};
    }

    private void think() {
        System.out.println("Philosopher " + id + " is thinking");
        try {
            int thinkingTime = rand.nextInt(thinking);
            Thread.sleep(thinkingTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void pickUpLeftFork() {
lock.lock();
        try {
            left.pickup();
            System.out.println("Philosopher " + id + " has left fork");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.unlock();
    }

    private void pickUpRightFork() {
lock.lock();
        try {
            right.pickup();
            System.out.println("Philosopher " + id + " has right fork");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.unlock();
    }

    private void releaseForks() {
        System.out.println("Philosopher " + id + " is putting down forks");
        left.release();
        right.release();
    }

    private void eat() {
        System.out.println("Philosopher " + id + " is eating");
        try {
            Thread.sleep(eatingTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            pickUpLeftFork();
            pickUpRightFork();
            eat();
            releaseForks();
            think();
        }

    }
}
