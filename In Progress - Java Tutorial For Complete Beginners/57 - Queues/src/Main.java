
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Wojciech Orzechowski
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // In Java, a queue is a First-In-First-Out (FIFO) structures
        // The front of the queue is called head.
        // The end ot the queue is called tail.
        // LinkedList implements Interface Queue.

        // ArrayBlockingQueue
        // it has a finite size.
        Queue<Integer> q1 = new ArrayBlockingQueue<Integer>(4);
        q1.add(10);
        q1.add(20);

        // If adding more than the number of allowed objects.
        // it will throw an exception
        try {
            q1.add(30);
        } catch (IllegalStateException e) {
            System.out.println("Adding to many items to the queue.");
        }

        // Printing the queue
        System.out.println("q1: " + q1);

        // Removing values
        // It will return the head of the queue.
        Integer value = q1.remove();
        System.out.println("Head of the queue: " + value);

        // Removing to many
        q1.remove();
        try {
            q1.remove();
            q1.remove();
        } catch (NoSuchElementException e) {
            System.out.println("The queue is empty. You cannot delete any more value.");
        }
        // =====================================================================
        Queue<Integer> q2 = new ArrayBlockingQueue<Integer>(3);

        q2.offer(7);
        q2.offer(27);
        q2.offer(127);
        q2.offer(1127);
        // Showing the head
        System.out.println("Peek: " + q2.peek());

        // The last integer was not added - the queue is full.
        System.out.println(q2);

        // .offer() will return False if the queue is full.
        if (q2.offer(7) == false) {
            System.out.println("Offer failed to add. The queue is full.");
        }

        // You can use .poll same as remove.
        System.out.println(q2.poll());
        System.out.println(q2.poll());
        System.out.println(q2.poll());
        // Finally it returns 'null' as the queue is empty.
        System.out.println(q2.poll());
        
        // Look up 'BlockingQueue'!
    }

}
