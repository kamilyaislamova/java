package queue;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Predicate;

// Model: a[1]..a[n]
// Inv: n >= 0 && forall i=1..n: a[i] != null
// Let: immutable(k): forall i=1..k: a'[i] = a[i]

public class ArrayQueueModule {
    private static int front = 0;
    private static int back = 0;
    private static Object[] elements = new Object[5];

    // Pre: element != null
    // Post: n' = n + 1 &&
    //       a'[n'] = element &&
    //       immutable(n)
    public static void enqueue(Object element) {
        Objects.requireNonNull(element);
        ensureCapacity(size() + 1);
        elements[back] = element;
        back = (back + 1) % elements.length;
    }

    // Pre: true
    // Post: n' = n && immutable(n)
    private static void ensureCapacity(int capacity) {
        if (capacity >= elements.length) {
            if (back >= front) {
                elements = Arrays.copyOf(elements, 2 * capacity);
            } else {
                Object[] copyElements = new Object[2 * capacity];
                System.arraycopy(elements, 0, copyElements, 0, back);
                System.arraycopy(elements, front, copyElements,
                        copyElements.length - (elements.length - front), elements.length - front);
                front += capacity;
                elements = copyElements;
            }
        }
    }

    // Pre: n > 0
    // Post: R = a[1] && n' = n - 1 && immutable(n')
    public static Object dequeue() {
        assert size() > 0;

        final Object value = element();
        elements[front] = 0;
        front = (front + 1) % elements.length;
        return value;
    }

    // Pre: n > 0
    // Post: R = a[1] && n' = n && immutable(n)
    public static Object element() {
        assert size() > 0;
        return elements[front];
    }

    //Pre: n > 0
    // Post: R = a[n] && n' = n - 1 && immutable(n')
    public static Object remove() {
        assert size() > 0;

        final Object value = peek();
        back = mode(back - 1, elements.length);
        elements[back] = 0;
        return value;
    }

    // Pre: n > 0
    // Post: R = a[n] && n' = n && immutable(n)
    public static Object peek() {
        assert size() > 0;
        return elements[mode(back - 1, elements.length)];
    }

    // Pre: element != null
    // Post: n' = n + 1 &&
    //       a'[1] = element &&
    //       forall i=1..n: a'[i] = a[i - 1]
    public static void push(Object element) {
        Objects.requireNonNull(element);
        ensureCapacity(size() + 1);
        front = mode(front - 1, elements.length);
        elements[front] = element;
    }

    // Pre: true
    // Post: true
    private static int mode(int change, int mode) {
        return (change + mode) % mode;
    }

    // Pre: true
    // Post: R = n && n' = n && immutable(n)
    public static int size() {
        if (back >= front)
            return back - front;
        return elements.length - front + back;
    }

    // Pre: true
    // Post: R = (n == 0) && n' = n && immutable(n)
    public static boolean isEmpty() {
        return front == back;
    }

    // Pre: true
    // Post: n' = 0
    public static void clear() {
        int counter = back;
        while(counter != front) {
            counter = mode(counter - 1, elements.length);
            elements[counter] = null;
        }
        elements = new Object[10];
        front = 0;
        back = 0;
    }

    // Pre: true
    // Post: true
    public static int countIf(Predicate<Object> pr) {
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
}
