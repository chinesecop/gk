public class SimpleLinkedListImpl<E> implements LinkedList<E> {

    private Integer[] linkedList;
    private int currentSize;

    public SimpleLinkedListImpl(Integer[] newArray) {
        this.linkedList = newArray;
        this.currentSize = linkedList.length;
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < currentSize && linkedList[currentIndex] != null;
            }

            @Override
            public E next() {
                return (E) linkedList[currentIndex++];
            }
        };
        return it;
    }
}