package queue;

import java.util.function.Predicate;

public class ArrayQueueModuleMyTest {

    public static void fill(String prefix) {
        for (int i = 0; i < 15; i++) {
            ArrayQueueModule.enqueue(prefix + "back_" +  (15 + i));
            ArrayQueueModule.push(prefix + "front_" + (15 - i - 1));
        }
    }

    public static void dump(String prefix) {
        while (!ArrayQueueModule.isEmpty()) {
            Object eq = prefix + "back_" + 15;
            Predicate<Object> pr = new Predicate<>() {
                @Override
                public boolean test(Object o) {
                    return o.equals(eq);
                }
            };
            System.out.println("number of the same objects to " + eq + ": " +
                    ArrayQueueModule.countIf(pr));
            System.out.println(ArrayQueueModule.size() + " from front: " + ArrayQueueModule.dequeue() +
                    ", from back: " + ArrayQueueModule.remove());
        }
    }

    public static void main(String[] args) {
        ArrayQueue queue1 = new ArrayQueue();
        ArrayQueue queue2 = new ArrayQueue();
        fill("module_");
        dump("module_");
    }
}
