public abstract class DequeImpl<E> implements Deque<E> {

    protected static final int DEFAULT_HEAD = 0;
    protected static final int DEFAULT_END = -1;

    protected int head;
    protected int end;

    protected E[] data;
    protected int size;

    @Override
    public boolean insertFirst(E value){
        if (isFull()) {
            return false;
        }
        if (head == DEFAULT_HEAD) {
            head = data.length;
        }

        data[--head] = value;
        size++;

        return true;
    }

    @Override
    public boolean insertLast(E value){
        if (isFull()) {
            return false;
        }

        if (end == data.length - 1) {
            end = DEFAULT_END;
        }

        data[++end] = value;
        size++;
        return true;
    }

    @Override
    public E removeFirst(E value) {
        if (isEmpty()) {
            return null;
        }

        if (head == data.length) {
            head = DEFAULT_HEAD;
        }

        E removedValue = data[head++];
        size--;
        return removedValue;
    }

    @Override
    public E removeLast(E value) {
        if (isEmpty()) {
            return  null;
        }
        if (end == DEFAULT_END) {
            end = data.length - 1;
        }

        size--;
        return data[end--];
    }

    @Override
    public boolean isFull() {
        return data.length == size;
    }
}