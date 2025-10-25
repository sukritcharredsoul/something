package MQPlanner.utils;

/**
 * Represents a single node in a singly linked list.
 * @param <T> Type of data stored in the node
 */
public class Node<T> {
    T data;
    Node<T> next;

    public Node(T data) {
        this.data = data;
        this.next = null;
    }

    public T getData() {
        return data;
    }
}