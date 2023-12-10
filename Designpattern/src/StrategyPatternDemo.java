import java.util.Arrays;

interface SortingStrategy {
    void sort(int[] array);
}

class BubbleSortStrategy implements SortingStrategy {
    @Override
    public void sort(int[] array) {
        // Implement Bubble Sort
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] > array[i + 1]) {
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    swapped = true;
                }
            }
        } while (swapped);
    }
}

class SelectionSortStrategy implements SortingStrategy {
    @Override
    public void sort(int[] array) {
        // Implement Selection Sort
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }
}

class Sorter {
    private SortingStrategy strategy;

    public Sorter(SortingStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(SortingStrategy strategy) {
        this.strategy = strategy;
    }

    public void sortArray(int[] array) {
        strategy.sort(array);
    }
}

public class StrategyPatternDemo {
    public static void main(String[] args) {
        int[] array = { 3, 2, 5, 4, 1, 10, 9, 242, 59 };

        Sorter sorter = new Sorter(new BubbleSortStrategy());
        sorter.sortArray(array);
        System.out.println("Sorted using Bubble Sort: " + Arrays.toString(array));

        sorter.setStrategy(new SelectionSortStrategy());
        sorter.sortArray(array);
        System.out.println("Sorted using Selection Sort: " + Arrays.toString(array));
    }
}


