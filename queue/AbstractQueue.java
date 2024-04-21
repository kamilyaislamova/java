package queue;

import java.util.List;
import java.util.function.Function;

public abstract class AbstractQueue implements Queue{

    protected abstract void enqueueImpl(Object element);
    @Override
    public void enqueue(Object element) {
        assert element != null;

        enqueueImpl(element);
    }

    protected abstract void dequeueImpl();
    @Override
    public Object dequeue() {
        assert this.size() > 0;

        final Object value = element();
        dequeueImpl();
        return value;
    }

    protected abstract Object elementImpl();
    @Override
    public Object element() {
        assert this.size() > 0;

        return elementImpl();
    }

    protected abstract int sizeImpl();
    @Override
    public int size() {
        return sizeImpl();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    protected abstract void clearImpl();
    @Override
    public void clear() {
        clearImpl();
    }

    protected abstract Queue makeImpl();

    public Queue flatMap(Function <Object, List<Object>> func) {
        assert func != null;
        Queue ans = makeImpl();
        int len = size();
        for (int i = 0; i < len; i++) {
            Object current = this.dequeue();
            List<Object> cur = func.apply(current);
            for (Object j: cur) {
                ans.enqueue(j);
            }
            this.enqueue(current);
        }
        return ans;
    }
}
