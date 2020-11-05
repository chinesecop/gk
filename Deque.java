public interface Deque<E> extends Queue<E> {
    boolean insertFirst(E value);
    boolean insertLast(E value);

    E removeFirst(E value);
    E removeLast(E value);

    boolean isFull();
}