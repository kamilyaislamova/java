package queue;

import java.util.function.Predicate;

public class ArrayQueueADTMyTest {
    public static void fill(ArrayQueueADT queue, String prefix) {
        for (int i = 0; i < 15; i++) {
            ArrayQueueADT.enqueue(queue, prefix + "back_" +  (15 + i));
            ArrayQueueADT.push(queue, prefix + "front_" + (15 - i - 1));
        }
    }

    public static void dump(ArrayQueueADT queue, String prefix) {
        while (!ArrayQueueADT.isEmpty(queue)) {
            Object eq = prefix + "back_" + 15;
            Predicate<Object> pr = new Predicate<>() {
                @Override
                public boolean test(Object o) {
                    return o.equals(eq);
                }
            };
            System.out.println("number of the same objects to " + eq + ": " +
                    ArrayQueueADT.countIf(queue, pr));
            System.out.println(ArrayQueueADT.size(queue) + " from front: " + ArrayQueueADT.dequeue(queue) +
                    ", from back: " + ArrayQueueADT.remove(queue));
        }
    }

    public static void main(String[] args) {
        ArrayQueueADT queue1 = ArrayQueueADT.create();
        ArrayQueueADT queue2 = ArrayQueueADT.create();
        fill(queue1, "ADT1_");
        fill(queue2, "ADT2_");
        dump(queue1, "ADT1_");
        dump(queue2, "ADT2_");
    }
}
