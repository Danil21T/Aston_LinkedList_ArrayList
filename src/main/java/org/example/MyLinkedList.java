package org.example;

/**
 * A class that represents a linked list.
 *
 * @param <T> the type of elements in the linked list
 */
public class MyLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    /**
     * A private inner class that represents a node in the linked list.
     *
     * @param <T> the type of element in the node
     */
    private static class Node<T> {
        private T data;
        private Node<T> previous;
        private Node<T> next;

        /**
         * Constructs a node with the specified data.
         *
         * @param data the data for the node
         */
        public Node(T data) {
            this.data = data;
        }
    }

    /**
     * Constructs an empty linked list.
     */
    public MyLinkedList(){
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Constructs a new linked list by copying another linked list.
     *
     * @param copyFrom the linked list to copy from
     */
    public MyLinkedList(MyLinkedList<T> copyFrom){
        head = copyFrom.head;
        tail = copyFrom.tail;
        size = copyFrom.size;
    }

    /**
     * Adds an element to the end of the linked list.
     *
     * @param item the element to add
     */
    public void add(T item) {
        Node<T> newNode = new Node<>(item);

        if(head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.previous = tail;
            tail.next = newNode;
            tail = newNode;
        }

        size++;
    }

    /**
     * Adds an element at a specified index in the linked list.
     *
     * @param item the element to add
     * @param index the index at which to add the element
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public void add(T item, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        if (index == size) {
            add(item);
            return;
        }

        Node<T> newNode = new Node<>(item);

        if (index == 0) {
            newNode.next = head;
            head.previous = newNode;
            head = newNode;
        } else {
            Node<T> current = getNode(index);
            current.previous.next = newNode;
            newNode.previous = current.previous;
            newNode.next = current;
            current.previous = newNode;
        }

        size++;
    }

    /**
     * Removes an element from the linked list.
     *
     * @param item the element to remove
     */
    public void remove(T item) {
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(item)) {
                if (current == head) {
                    head = current.next;
                } else if (current == tail) {
                    tail = current.previous;
                } else {
                    current.previous.next = current.next;
                    current.next.previous = current.previous;
                }

                size--;
                return;
            }

            current = current.next;
        }
    }

    /**
     * Removes an element at a specified index in the linked list.
     *
     * @param index the index of the element to remove
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        if (index == 0) {
            head = head.next;
        } else if (index == size - 1) {
            tail = tail.previous;
        } else {
            Node<T> current = getNode(index);
            current.previous.next = current.next;
            current.next.previous = current.previous;
        }

        size--;
    }

    /**
     * Clears the linked list, removing all elements.
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Sorts the linked list using bubble sort.
     */
    public void sort() {
        if (size <= 1) {
            return;
        }
        boolean swapped;
        do {
            swapped = false;
            Node<T> current = head;
            while (current.next != null) {
                if (compare(current.data, current.next.data) > 0) {
                    T temp = current.data;
                    current.data = current.next.data;
                    current.next.data = temp;
                    swapped = true;
                }
                current = current.next;
            }
        } while (swapped);

    }

    /**
     * Gets the element at a specified index in the linked list.
     *
     * @param index the index of the element to get
     * @return the element at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public T get(int index){
        return getNode(index).data;
    }

    /**
     * Returns a string representation of the linked list.
     *
     * @return a string representation of the linked list
     */
    public String toString() {
        Node<T> current = head;
        StringBuilder sb = new StringBuilder();
        while (current != null) {
            sb.append(current.data).append(" ");
            current = current.next;
        }
        return sb.toString();
    }

    /**
     * Compares two elements for sorting purposes.
     *
     * @param elem1 the first element to compare
     * @param elem2 the second element to compare
     * @return a negative integer, zero, or a positive integer as the first element
     *         is less than, equal to, or greater than the second element
     */
    private int compare(T elem1, T elem2) {
        return Integer.compare(elem1.hashCode(), elem2.hashCode());
    }

    /**
     * Gets the node at a specified index in the linked list.
     *
     * @param index the index of the node to get
     * @return the node at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    private Node<T> getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current;
    }
}