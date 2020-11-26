import java.util.Stack;
import java.util.function.Consumer;

public class TreeImpl<E extends Comparable<? super E>> implements Tree<E> {

    private Node<E> root;
    private int size;
    private final int depthLevel;

    public TreeImpl(int depthLevel) {
        this.depthLevel = depthLevel;
    }


    @Override
    public boolean add(E value) {
        Node<E> newNode = new Node<>(value); // создание нового элемента

        if (isEmpty()) {    // если дерево пустое, то корнем является добавляемый элемент
            root = new Node<>(value);
            size++;
            return true;
        }

        NodeAndParent nodeAndParent = doFind(value);
        Node<E> current = nodeAndParent.current; // если мы его нашли, то у нас есть дубликат
        if (current != null) {
            return false;
        }

        Node<E> parent = nodeAndParent.parent; // если parent = null, то не выполняем проверок
        if (parent == null) {
            return false;
        }

        int level = parent.getDepth() + 1;
        if (level > depthLevel && depthLevel != 0) { // если добавляемый уровень больше уровня глубины или != 0
            return false;                            // то элемент не добавляется
        }

        parent.addChild(newNode);
        size++;
        return true;
    }

    @Override
    public boolean contains(E value) {
        return doFind(value).current != null;
    }

    private NodeAndParent doFind(E value) {
        Node<E> previous = null;    // ссылка на предыдуший элемент
        Node<E> current = root;     // ссылка на текущий элемент

        current.setDepth(1); // задаем уровень родителю (корень на 1 уровне)

        while (current != null) {       // на каждой итерации, если
            if (previous != null) {     // previous != null, то мы ушли от корня на 2-й уровень
                current.setDepth(previous.getDepth() + 1); // дочернему элементу будет присвоен уровень + 1
            }

            previous = current;                         // обновляем ссылку на наш предудущий элемент
            if (current.isRightChild(value)) {          // если значение является большим, чем
                current = current.getRightChild();      // находится в ячейке, то двигаемся вправо
            } else {
                current = current.getLeftChild();       // иначе двигаемся влево
            }
        }
        return new NodeAndParent(null, previous);
    }

    @Override
    public boolean remove(E value) {
        NodeAndParent nodeAndParent = doFind(value);
        Node<E> removedNode = nodeAndParent.current;
        Node<E> parentNode = nodeAndParent.parent;

        if (removedNode == null) {
            return false;
        }

        if (removedNode.isLeaf()) {
            if (removedNode == root) {
                root = null;
            } else if (parentNode.isRightChild(value)) {
                parentNode.setRightChild(null);
            } else {
                parentNode.setLeftChild(null);
            }
        } else if (removedNode.hasOnlyOneChild()) {     // если удаляемый узел имеет одного потомка
            Node<E> child = removedNode.getLeftChild() != null
                    ? removedNode.getLeftChild()
                    : removedNode.getRightChild();

            if (removedNode == root) {
                root = child;
            } else if (parentNode.isRightChild(value)) {
                parentNode.setRightChild(child);
            } else {
                parentNode.setLeftChild(child);
            }
        } else {    // если удаляемый узел имеет два потомка
            Node<E> successor = getSuccessor(removedNode);
            if (removedNode == root) {
                root = successor;
            } else if (parentNode.isRightChild(value)) {
                parentNode.setRightChild(successor);
            } else {
                parentNode.setLeftChild(successor);
            }

            successor.setLeftChild(removedNode.getLeftChild());
        }

        size--;
        return true;
    }

    private Node<E> getSuccessor(Node<E> removedNode) {
        Node<E> successor = removedNode;
        Node<E> successorParent = null;
        Node<E> current = removedNode.getRightChild();

        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.getLeftChild();
        }

        if (successor != removedNode.getRightChild() && successorParent != null) {
            successorParent.setLeftChild(successor.getRightChild());
            successor.setLeftChild(removedNode.getRightChild());
        }
        return successor;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void traverse(TraverseMode mode, Consumer<E> action) {
        switch (mode) {
            case IN_ORDER -> inOrder(root, action);
            case PRE_ORDER -> preOrder(root, action);
            case POST_ORDER -> postOrder(root, action);
            default -> throw new IllegalArgumentException("This traverse mod was not found: " + mode);
        }
    }

    @Override
    public void display() {
        Stack<Node<E>> globalStack = new Stack<>();
        globalStack.push(root);
        int nBlanks = 64;

        boolean isRowEmpty = false;
        System.out.println("................................................................");

        while (!isRowEmpty) {
            Stack<Node<E>> localStack = new Stack<>();

            isRowEmpty = true;
            for (int i = 0; i < nBlanks; i++) {
                System.out.print(" ");
            }

            while (!globalStack.isEmpty()) {
                Node<E> tempNode = globalStack.pop();
                if (tempNode != null) {
                    System.out.print(tempNode.getValue());
                    localStack.push(tempNode.getLeftChild());
                    localStack.push(tempNode.getRightChild());
                    if (tempNode.getLeftChild() != null || tempNode.getRightChild() != null) {
                        isRowEmpty = false;
                    }
                } else {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < nBlanks * 2 - 2; j++) {
                    System.out.print(" ");
                }
            }

            System.out.println();

            while (!localStack.isEmpty()) {
                globalStack.push(localStack.pop());
            }

            nBlanks /= 2;
        }
        System.out.println("................................................................");
    }

    private void inOrder(Node<E> current, Consumer<E> action) {
        if (current == null) {
            return;
        }
        inOrder(current.getLeftChild(), action);
        action.accept(current.getValue());
        inOrder(current.getRightChild(), action);
    }

    private void preOrder(Node<E> current, Consumer<E> action) {
        if (current == null) {
            return;
        }
        action.accept(current.getValue());
        preOrder(current.getLeftChild(), action);
        preOrder(current.getRightChild(), action);
    }

    private void postOrder(Node<E> current, Consumer<E> action) {
        if (current == null) {
            return;
        }
        postOrder(current.getLeftChild(), action);
        postOrder(current.getRightChild(), action);
        action.accept(current.getValue());
    }

    private class NodeAndParent {
        Node<E> current;
        Node<E> parent;

        public NodeAndParent(Node<E> current, Node<E> parent) {
            this.current = current;
            this.parent = parent;
        }
    }

    @Override
    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node<E> node) {
        return (node == null) ||
                isBalanced(node.getLeftChild()) &&
                        isBalanced(node.getRightChild()) &&
                        Math.abs(height(node.getLeftChild()) - height(node.getRightChild())) <= 1;
    }

    private int height(Node<E> node) {
        return node == null ? 0 : 1 + Math.max(height(node.getLeftChild()), height(node.getRightChild()));
    }
}