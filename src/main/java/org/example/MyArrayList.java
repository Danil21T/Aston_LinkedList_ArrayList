package org.example;

import java.util.Arrays;

/**
 * Represents a resizable array list.
 *
 * @param <T> the type of elements in the list
 */
public class MyArrayList<T> {

    private final int DEFAULT_SIZE = 16;
    private Object[] mas;
    private int point = 0;
    private int size;

    /**
     * Constructs an empty MyArrayList with the default initial capacity of 16.
     */
    public MyArrayList() {
        this.size = DEFAULT_SIZE;
        initDefaultMassive();
    }

    /**
     * Constructs an empty MyArrayList with the specified initial capacity.
     *
     * @param size the initial capacity
     */
    public MyArrayList(int size) {
        this.size = size;
        initDefaultMassive();
    }

    /**
     * Constructs a new MyArrayList that contains the elements of the specified MyArrayList.
     *й
     * @param copyFrom the MyArrayList to copy elements from
     */
    public MyArrayList(MyArrayList<T> copyFrom){
        this(copyFrom.size);
        System.arraycopy(copyFrom.mas, 0, this.mas, 0, size);
    }

    /**
     * Returns the element at the specified index in this list.
     *
     * @param index the index of the element to return
     * @return the element at the specified index, or null if the index is out of range
     */
    public Object get(int index) {
        if (index >= 0 && index < point) {
            return mas[index];
        } else return null;
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param item the element to be added
     */
    public void add(T item) {
        if (point == size - 1)
            resizeMassive();
        mas[point++] = item;
    }

    /**
     * Inserts the specified element at the specified position in this list.
     *
     * @param item the element to be added
     * @param index  the index at which to add the element
     */
    public void add(T item, int index) {
        if (index >= 0 && index < point) {
            if (point == size - 1)
                resizeMassive();
            int end_ind = index;
            while (mas[end_ind] != null) {
                end_ind++;
            }
            for (int j = end_ind; j > index; j--) {
                mas[j] = mas[j - 1];
            }
            mas[index] = item;
            point++;
        } else {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    /**
     * Removes the element at the specified index in this list.
     *
     * @param index the index of the element to be removed
     */
    public void remove(int index) {
        if (index >= 0 && index < point) {
            for (int i = index; i < size - 1; i++) {
                mas[i] = mas[i + 1];
            }
            mas[point - 1] = null;
            point--;
        }
        else{
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    /**
     * Removes the first occurrence of the specified element from this list.
     *
     * @param o the element to be removed
     * @return true if the element is successfully removed, false otherwise
     */
    public boolean remove(Object o) {
        int index = 0;
        for (Object elem : mas) {
            if(elem == null)
                break;
            if (elem.equals(o)) {
                remove(index);
                return true;
            }
            index++;
        }
        return false;
    }

    /**
     * Removes all of the elements from this list.
     */
    public void clear() {
        Arrays.fill(mas, null);
        point = 0;
    }

    /**
     * Sorts the elements in this list in ascending order.
     */
    public void sort(){
        if(point <= 1)
            return;
        for (int out = point - 1; out >= 1; out--){
            for (int in = 0; in < out; in++){
                if(compare(in, in + 1) > 0){
                    swap(in, in+1);
                }
            }
        }
    }

    /**
     * Returns a string representation of this list.
     *
     * @return a string representation of this list
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Object o : mas){
            if(o == null) break;
            sb.append(o).append(" ");
        }
        return sb.toString();
    }

    /**
     * Swapping array elements.
     *
     */
    private void swap(int index1, int index2){
        Object mem = mas[index1];
        mas[index1] = mas[index2];
        mas[index2] = mem;
    }

    /**
     * Comparison of two elements.
     *
     * @return less than 0 - if the 2nd is greater than the 1st, zero - if they are equal, more than 0 - if the 1st is greater than 2-uj
     */
    private int compare(int index1, int index2){
        return Integer.compare(mas[index1].hashCode(), mas[index2].hashCode());
    }

    /**
     * Resize of the array.
     *
     */
    private void resizeMassive() {
        int old_size = size;
        size = size + DEFAULT_SIZE >> 1;
        Object[] copy = new Object[size];
        System.arraycopy(mas, 0, copy, 0, old_size);
        mas = copy;
    }

    /**
     * Initializing the initial array and filling with null values.
     *
     */
    private void initDefaultMassive() {
        mas = new Object[size];
        Arrays.fill(mas, null);
    }

}
