import java.util.function.Consumer;

public interface Tree<E extends Comparable<? super E>> {
    enum TraverseMode {
        IN_ORDER,   //  в порядке позрастания
        PRE_ORDER,  //  прямой порядок
        POST_ORDER, // обратный порядок
    }

    boolean add(E value); // добавление элементов, игнорируя дубликаты

    boolean contains(E value); // метод поиска

    boolean remove(E value); // удаление определенного элемента

    boolean isEmpty();

    int size();

    void traverse(TraverseMode mode, Consumer<E> action);

    void display();

    boolean isBalanced();

}