import java.util.ArrayList;
import java.util.List;

public class MainHW {
    public static void main(String[] args) {
        List<Item> items = new ArrayList<>();

        // Task 1 --------------------------------
        System.out.println (exp(3, 10));
        // Task 2 --------------------------------
        items.add(new Item("itemsOne", 7, 700));
        items.add(new Item("itemsTwo", 1, 100));
        items.add(new Item("itemsThree", 6, 600));
        items.add(new Item("itemsFour", 3, 300));
        items.add(new Item("itemsFive", 9, 900));

        Knapsack knapsack = new Knapsack(15);
        knapsack.calculatingBestSets(items);

        for (Item item : knapsack.getBestSet()) {
            System.out.println("Best Items: " + item.item + " " + item.cost);
        }
    }
    // Task 1 method --------------------------------
    public static int exp(int num, int power) {
        int n = 1;
        if (power < n) return n;
        else return num * exp(num, power-n);
    }
}