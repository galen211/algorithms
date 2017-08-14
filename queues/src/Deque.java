import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private final Node pre;     // sentinel before first item
    private final Node post;    // sentinel after last item
    private int n;      // number of items on the queue

    /**
     * constructor to initialize a doubly-linked deque with pre and post sentinel nodes
     */
    public Deque() {
        pre = new Node();
        post = new Node();
        pre.next = post;
        post.prev = pre;
    }

    /**
     * Private node class for storing items
     */
    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }

    /**
     * is the deque empty?
     *
     * @return true if empty
     */
    public boolean isEmpty() {
        return n == 0;
    }    // or n==0             // is the deque empty?

    /**
     * gets the number of items in the deque
     *
     * @return the size of the deque
     */
    public int size() {
        return n;
    }

    /**
     * adds an item to the front of the deque
     *
     * @param item the item to be added
     */
    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException();

        Node first = new Node(); // new first node
        first.item = item;

        Node hold = pre.next;
        pre.next = first;
        hold.prev = first;
        first.next = hold;
        first.prev = pre;

        n++;
    }          // add the item to the front

    /**
     * adds an item to the rear of the deque
     *
     * @param item the item to be added
     */
    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException();

        Node last = new Node(); // new last node
        last.item = item;

        Node hold = post.prev;
        post.prev = last;
        hold.next = last;
        last.next = post;
        last.prev = hold;

        n++;
    }           // add the item to the end

    /**
     * removes an item to the front of the deque
     */
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Node hold = pre.next;
        Item item = hold.item;

        Node first = hold.next; // might not have a next

        pre.next = first;
        first.prev = pre;

        n--;

        return item;
    }                // remove and return the item from the front

    /**
     * removes an item to the rear of the deque
     */
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Node hold = post.prev; // existing last node
        Item item = hold.item;

        Node last = hold.prev; // new last node

        post.prev = last;
        last.next = post;

        n--;

        return item;
    }                 // remove and return the item from the end

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }         // return an iterator over items in order from front to end

    private class DequeIterator implements Iterator<Item> {

        private Node current = pre.next;

        @Override
        public boolean hasNext() {
            return current.next != null;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();

            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
