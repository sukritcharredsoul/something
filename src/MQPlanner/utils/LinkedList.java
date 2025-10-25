package MQPlanner.utils;


/**
 * Custom implementation of a simple singly LinkedList.
 * Provides basic operations like add, get, remove, and size.
 * @param <T> Type of elements stored in the list
 */
public class LinkedList<T> {
    private Node<T> head;
    private int size;

    public LinkedList() {
        this.head = null;
        this.size = 0;
    }


    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    /** Get element by index */
    public T get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    /** Remove element by index */
    public boolean remove(int index) {
        if (index < 0 || index >= size) return false;

        if (index == 0) {
            head = head.next;
        } else {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
        }

        size--;
        return true;
    }

    /** Get size of the list */
    public int size() {
        return size;
    }

    /** Check if list is empty */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Print elements for debugging */
    public void printList() {
        Node<T> current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    /** Get the head node (optional, for iteration or debugging) */
    public Node<T> getHead() {
        return head;
    }
}
