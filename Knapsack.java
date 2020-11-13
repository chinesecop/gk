import java.util.ArrayList;
import java.util.List;

public class Knapsack {
    private final int maxWeight;
    private List<Item> bestItems = null;
    private int bestCost;

    public Knapsack(int maxW) {
        this.maxWeight = maxW;
    }

    private int calculateWeight(List<Item> items) { // общий вес набора предметов
        int sum = 0;

        for (Item i : items) {
            sum += i.weight();
        } return sum;
    }

    private int calculateCost(List<Item> items) { // общая стоимость набора предметов
        int sum = 0;

        for (Item i : items) {
            sum += i.cost();
        } return sum;
    }

    private void checkSet(List<Item> items) { // является ли набор лучшим
        if (bestItems == null && calculateWeight(items) <= maxWeight){
                bestItems = items;
                bestCost = calculateCost(items);
        } else if (calculateWeight(items) <= maxWeight && calculateCost(items) > bestCost) {
            bestItems = items;
            bestCost = calculateCost(items);
        }
    }

    public void calculatingBestSets(List<Item> items) { // общее колличество лучших предметов
        if (items.size() > 0) {
            checkSet(items);
        }
        for (int i = items.size()-1; i >= 0; i--) {
            List<Item> newItems = new ArrayList<>(items);
            newItems.remove(i);
            calculatingBestSets(newItems);
        }
    }

    public List<Item> getBestSet() { // возвращает набор предметов
        return bestItems;
    }
}