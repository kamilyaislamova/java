package queue;

import java.util.function.Predicate;

public class ArrayQueueMyTest {

    public static void fill(ArrayQueue queue, String prefix) {
        for (int i = 0; i < 15; i++) {
            queue.enqueue(prefix + "back_" +  (15 + i));
            queue.push(prefix + "front_" + (15 - i - 1));
        }
    }

    public static void dump(ArrayQueue queue, String prefix) {
        while (!queue.isEmpty()) {
            Object eq = prefix + "back_" + 15;
            Predicate<Object> pr = new Predicate<>() {
                @Override
                public boolean test(Object o) {
                    return o.equals(eq);
                }
            };
            System.out.println("number of the same objects to " + eq + ": " +
                    queue.countIf(pr));
            System.out.println(queue.size() + " from front: " + queue.dequeue() +
                    ", from back: " + queue.remove());
        }
    }

    public static void main(String[] args) {
        ArrayQueue queue1 = new ArrayQueue();
        ArrayQueue queue2 = new ArrayQueue();
        fill(queue1, "s1_");
        fill(queue2, "s2_");
        dump(queue1, "s1_");
        dump(queue2, "s2_");
    }
}
