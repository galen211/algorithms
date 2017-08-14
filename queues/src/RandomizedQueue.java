import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * A queue where elements are retrieved in FIFO order.  This implementation uses q resizing array, which doubles the
 * size of the underlying array when the queue is full and halves the size of the array when the queue is one-quarter
 * full.
 *
 * @param <Item>
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] q; // the array
    private int n; // number of elements in the queue

    /**
     * Initializes an empty queue.
     */
    public RandomizedQueue() {
        q = (Item[]) new Object[1]; // initialize to size of 2
        n = 0;
    }

    /**
     * Determines if the queue is empty.
     *
     * @return true if empty
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Determines the number of items in the queue.
     *
     * @return the number of items
     */
    public int size() {
        return n;
    }

    /**
     * Add an item to the queue
     *
     * @param item the item to be added
     */
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Added null item");
        }

        if (n == q.length) {
            resize(2 * q.length);
        }

        q[n++] = item;
    }

    /**
     * Retrieves and removes the least recently added item from the queue.
     *
     * @return the item at the last of the queue
     */
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }

        int r = StdRandom.uniform(0, n);
        exchange(r, n - 1);

        Item hold = q[--n];
        q[n] = null;

        if (n > 0 && n == (q.length / 4)) {
            resize(q.length / 2);
        }

        return hold;
    }

    private void exchange(int a, int b) {
        Item temp = q[a];
        q[a] = q[b];
        q[b] = temp;
    }

    /**
     * Return but do not remove q random item from the queue.
     *
     * @return an Item selected uniformly at random
     */
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }

        int r = StdRandom.uniform(n);
        return q[r];
    }

    /**
     * Private method that resizes the capacity of the queue.
     *
     * @param capacity the desired capacity of the queue
     */
    private void resize(int capacity) {
        assert capacity >= n;

        Item[] temp = (Item[]) new Object[capacity];


        for (int i = 0; i < n; i++) {
            temp[i] = q[i];
        }
        q = temp;
    }

    /**
     * Returns an iterator that iterates over the items in this queue in FIFO order.
     *
     * @return an iterator that iterates over the items in this queue in FIFO order.
     */
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<Item> {

        private final Item[] copy = (Item[]) new Object[n]; // only need the items
        private int k = n; // number of items in copy



        public QueueIterator() {
            for (int j = 0; j < n; j++) {
                copy[j] = q[j];
            }
            StdRandom.shuffle(copy);
        }

        public boolean hasNext() {
            return k > 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item hold = copy[--k];
            return hold;
        }

    }

    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue();
        rq.enqueue("A");
        rq.enqueue("B");
        rq.enqueue("C");
        rq.enqueue("D");
        rq.enqueue("E");

        for (String s : rq) {
            StdOut.println(s);
        }
        StdOut.println();
        rq.dequeue();

        for (String s : rq) {
            StdOut.println(s);
        }
        StdOut.println();
        rq.dequeue();

        for (String s : rq) {
            StdOut.println(s);
        }
        StdOut.println();
    }

}