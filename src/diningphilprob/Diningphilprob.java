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
public class Diningphilprob {



    public static void main(String[] args)  {

  
       int numberOfPhilosophers = 5;

        Philosopher[] philosopher = new Philosopher[numberOfPhilosophers];

        Fork[] forkArray=new Fork[numberOfPhilosophers];

//creating 5 forks
        for (int i = 0; i < numberOfPhilosophers; i ++) {
            forkArray[i]= new Fork(i);
        }
//creating 5 philosophers
        for (int i = 0; i < numberOfPhilosophers-1; i++) {
            philosopher[i]=new Philosopher(i, forkArray[i], forkArray[i+1]);
        }
        philosopher[numberOfPhilosophers-1]= new Philosopher(numberOfPhilosophers-1, forkArray[0], forkArray[numberOfPhilosophers-1]);

        for (Philosopher p : philosopher)
            new Thread(p).start();


    }
}