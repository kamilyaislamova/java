package queue;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Predicate;



public class ArrayQueue extends AbstractQueue{
    private int front = 0;
    private int back = 0;
    private Object[] elements = new Object[5];

    protected void enqueueImpl(Object element) {
        this.ensureCapacity(this.size() + 1);
        this.elements[back] = element;
        back = (back + 1) % elements.length;
    }

    private void ensureCapacity(int capacity) {
        if (capacity >= this.elements.length) {
            if (back >= front) {
                this.elements = Arrays.copyOf(this.elements, 2 * capacity);
            } else {
                Object[] copyElements = new Object[2 * capacity];
                System.arraycopy(elements, 0, copyElements, 0, back);
                System.arraycopy(elements, front, copyElements,
                        copyElements.length - (elements.length - front), elements.length - front);
                front += capacity;
                this.elements = copyElements;
            }
        }
    }

    protected void dequeueImpl() {
        elements[front] = 0;
        front = (front + 1) % elements.length;
    }

    protected Object elementImpl() {
        return elements[front];
    }

    protected int sizeImpl() {
        if (back >= front)
            return back - front;
        return elements.length - front + back;
    }

    protected void clearImpl() {
        elements = new Object[10];
        front = 0;
        back = 0;
    }

    protected ArrayQueue makeImpl() {
        return new ArrayQueue();
    }

    public int countIf(Predicate <Object> pr) {
        assert (pr != null);
        int count = 0;
        int counter = back;
        while(counter != front) {
            counter = mode(counter - 1, elements.length);
            if (pr.test(elements[counter]))
                count++;
        }
        return count;
    }

    public Object remove() {
        assert this.size() > 0;

        final Object value = peek();
        back = mode(back - 1, elements.length);
        elements[back] = 0;
        return value;
    }

    public Object peek() {
        assert this.size() > 0;
        return elements[mode(back - 1, elements.length)];
    }

    public void push(Object element) {
        Objects.requireNonNull(element);
        this.ensureCapacity(this.size() + 1);
        front = mode(front - 1, elements.length);
        this.elements[front] = element;
    }

    private int mode(int change, int mode) {
        return (change + mode) % mode;
    }

}

