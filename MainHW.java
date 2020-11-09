public class MainHW {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();

        LinkIterator itr = new LinkIterator(list);

        itr.insertBefore("Bugatti Chiron", 443);
        itr.insertAfter("Koenigsegg Agera RS", 458);
        itr.getCurrent();
        itr.reset();
        itr.atEnd();
        itr.nextLink();
        itr.deleteCurrent();

        list.display();

        forEach();
    }

    private static void forEach() {

        Integer[] numbers = new Integer[]{1, 2, 3, 4, 5};
        SimpleLinkedListImpl<Integer> linkedList = new SimpleLinkedListImpl<>(numbers);

        for (Integer value : linkedList) {
            System.out.println(value);
        }
    }
}