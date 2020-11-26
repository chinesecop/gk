import java.util.Random;

public class BinaryTree {

    @Test
    public void homework6() {
        Random random = new Random();
        int numbTrees = 20; // количество деревьев
        int depthLevel = 4; // уровень глубины
        int numbNode = (int) (Math.pow(2, depthLevel) - 1); // количество узлов
        int maxRandom = 25; // от -25 до 25

        int balancedTreeCount = 0; // подсчет сбалансированных деревьев
        boolean treeView = false; // для вывода первого сбалансированного дерева

        for (int i = 0; i < numbTrees; i++) {
            Tree<Integer> tree = new TreeImpl<>(depthLevel);
            fillTree(random, numbNode, maxRandom, tree);
            if (tree.isBalanced()) {
                balancedTreeCount++;
                if (!treeView) {        // как только убедились в том, что первое дерево сбалансировано
                    treeView = true;    // возвращаем true и выводим в консоль
                    tree.display();
                }
            }
        }
        System.out.println("Balanced Tree Count = " + ((balancedTreeCount / (numbTrees * 1.0)) * 100) + "%");
    }

    private static void fillTree(Random random, int numbNode, int maxRandom, Tree<Integer> tree) {
        for (int j = 0; j < numbNode; j++) {
            tree.add(random.nextInt(maxRandom * 2 + 1) - maxRandom);
        }
    }
}