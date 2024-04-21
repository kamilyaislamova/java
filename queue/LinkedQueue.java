package queue;

public class LinkedQueue extends  AbstractQueue{
    private Node front;
    private Node back;
    private int size = 0;

    protected void enqueueImpl(Object element) {
        Node previousBack = back;
        back = new Node(element, back);
        if (size == 0) {
            front = back;
        } else {
            previousBack.next = back;
        }
        size++;
    }

    public void dequeueImpl() {
        front = front.next;
        size--;
    }


    public Object elementImpl() {
        return front.value;
    }


    public int sizeImpl() {
        return size;
    }


    protected void clearImpl() {
        front = new Node(null, null);
        back = front;
        size = 0;
    }

    protected LinkedQueue makeImpl() {
        return new LinkedQueue();
    }

    private static class Node {
        private final Object value;
        private Node next;

        public Node(Object value, Node next) {

            this.value = value;
            this.next = next;
        }
    }
}
