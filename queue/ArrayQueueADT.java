package queue;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Predicate;

public class ArrayQueueADT {
    private int front = 0;
    private int back = 0;
    private Object[] elements = new Object[5];

    public static ArrayQueueADT create() {
        return new ArrayQueueADT();
    }

    // Pre: element != null
    // Post: n' = n + 1 &&
    //       a'[n'] = element &&
    //       immutable(n)
    public static void enqueue(ArrayQueueADT queue, Object element) {
        assert queue != null;

        Objects.requireNonNull(element);
        ensureCapacity(queue, size(queue) + 1);
        queue.elements[queue.back] = element;
        queue.back = (queue.back + 1) % queue.elements.length;
    }

    // Pre: true
    // Post: n' = n && immutable(n)
    private static void ensureCapacity(ArrayQueueADT queue, int capacity) {
        assert queue != null;
        if (capacity >= queue.elements.length) {
            if (queue.back >= queue.front) {
                queue.elements = Arrays.copyOf(queue.elements, 2 * capacity);
            } else {
                Object[] copyElements = new Object[2 * capacity];
                System.arraycopy(queue.elements, 0, copyElements, 0, queue.back);
                System.arraycopy(queue.elements, queue.front, copyElements,
                        copyElements.length - (queue.elements.length - queue.front),
                        queue.elements.length - queue.front);
                queue.front += capacity;
                queue.elements = copyElements;
            }
        }
    }

    // Pre: n > 0
    // Post: R = a[1] && n' = n - 1 && immutable(n')
    public static Object dequeue(ArrayQueueADT queue) {
        assert queue != null && size(queue) > 0;

        final Object value = element(queue);
        queue.elements[queue.front] = 0;
        queue.front = (queue.front + 1) % queue.elements.length;
        return value;
    }

    // Pre: n > 0
    // Post: R = a[1] && n' = n && immutable(n)
    public static Object element(ArrayQueueADT queue) {
        assert queue != null && size(queue) > 0;
        return queue.elements[queue.front];
    }

    //Pre: n > 0
    // Post: R = a[n] && n' = n - 1 && immutable(n')
    public static Object remove(ArrayQueueADT queue) {
        assert queue != null && size(queue) > 0;

        final Object value = peek(queue);
        queue.back = mode(queue.back - 1, queue.elements.length);
        queue.elements[queue.back] = 0;
        return value;
    }

    // Pre: n > 0
    // Post: R = a[n] && n' = n && immutable(n)
    public static Object peek(ArrayQueueADT queue) {
        assert queue != null && size(queue) > 0;
        return queue.elements[mode(queue.back - 1, queue.elements.length)];
    }

    // Pre: element != null
    // Post: n' = n + 1 &&
    //       a'[1] = element &&
    //       forall i=1..n: a'[i] = a[i - 1]
    public static void push(ArrayQueueADT queue, Object element) {
        assert queue != null;
        Objects.requireNonNull(element);
        ensureCapacity(queue, size(queue) + 1);
        queue.front = mode(queue.front - 1, queue.elements.length);
        queue.elements[queue.front] = element;
    }

    // Pre: true
    // Post: true
    private static int mode(int change, int mode) {
        return (change + mode) % mode;
    }

    // Pre: true
    // Post: R = n && n' = n && immutable(n)
    public static int size(ArrayQueueADT queue) {
        assert queue != null;
        if (queue.back >= queue.front)
            return queue.back - queue.front;
        return queue.elements.length - queue.front + queue.back;
    }

    // Pre: true
    // Post: R = (n == 0) && n' = n && immutable(n)
    public static boolean isEmpty(ArrayQueueADT queue) {
        assert queue != null;

        return queue.front == queue.back;
    }

    // Pre: true
    // Post: n' = 0
    public static void clear(ArrayQueueADT queue) {
        assert queue != null;
        int counter = queue.back;
        while(counter != queue.front) {
            counter = mode(counter - 1, queue.elements.length);
            queue.elements[counter] = null;
        }
        queue.elements = new Object[10];
        queue.front = 0;
        queue.back = 0;
    }

    // Pre: true
    // Post: true
    public static int countIf(ArrayQueueADT queue, Predicate<Object> pr) {
        assert queue != null && (pr != null);
        int count = 0;
        int counter = queue.back;
        while(counter != queue.front) {
            counter = mode(counter - 1, queue.elements.length);
            if (pr.test(queue.elements[counter]))
                count++;
        }
        return count;
    }
}
