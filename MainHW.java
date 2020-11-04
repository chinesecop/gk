public class MainHW {

    private static final int ARRAY_CAPACITY = 10_000;
    private static final int MAX_VALUE = 10_000;

    public static void main(String[] args) {

        int[] arr1 = new int[ARRAY_CAPACITY];

        fillingTheArray(arr1);      // random fill method

        getIndexOf(arr1, 1); // search method
        add(arr1, 2);         // add method
        remove(arr1, 1);   // remove method

        long startTime = System.nanoTime();
        bubbleSort(arr1);
        long finishTime = System.nanoTime();
        long duration = finishTime - startTime;
        System.out.printf("%s took time: %d micros.%n", "Bubble sort", TimeUnit.NANOSECONDS.toMicros(duration));

        startTime = System.nanoTime();
        selectionSort(arr1);
        finishTime = System.nanoTime();
        duration = finishTime - startTime;
        System.out.printf("%s took time: %d micros.%n", "Selection sort", TimeUnit.NANOSECONDS.toMicros(duration));

        startTime = System.nanoTime();
        insertionSort(arr1);
        finishTime = System.nanoTime();
        duration = finishTime - startTime;
        System.out.printf("%s took time: %d micros.%n", "Insertion sort", TimeUnit.NANOSECONDS.toMicros(duration));
    }

    private static void fillingTheArray(int[]... arr) {
        Random randomNumbers = new Random();
        for (int i = 0; i < MAX_VALUE; i++) {
            int value = randomNumbers.nextInt(MAX_VALUE);
            for (int[] array : arr) {
                array[i] = value;
            }
        }
    }

    private static void bubbleSort(int[] arr) {
        int[] copy = new int[ARRAY_CAPACITY];
        System.arraycopy(arr, 0, copy, 0, arr.length);
        int n = copy.length;

        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (copy[j] > copy[j + 1]) {
                    int temp = copy[j];
                    copy[j] = copy[j + 1];
                    copy[j + 1] = temp;
                }
    }

    private static void selectionSort(int[] arr) {
        int[] copy = new int[ARRAY_CAPACITY];
        System.arraycopy(arr, 0, copy, 0, arr.length);
        int n = copy.length;

        for (int i = 0; i < n; i++) {
            int min = copy[i];
            int min_i = i;
            for (int j = i + 1; j < n; j++) { //Если находим, запоминаем его индекс
                if (copy[j] < min) {
                    min = copy[j];
                    min_i = j;
                }
            }
            if (i != min_i) { //Если нашелся элемент, меньший, чем на текущей позиции, меняем их местами
                int tmp = copy[i];
                copy[i] = copy[min_i];
                copy[min_i] = tmp;
            }
        }
    }

    private static void insertionSort(int[] arr) {
        int[] copy = new int[ARRAY_CAPACITY];
        System.arraycopy(arr, 0, copy, 0, arr.length);

        for (int i = 1; i < copy.length; i++) {
            int key = copy[i];
            int j = i - 1;
            while (j >= 0 && copy[j] > key) {
                copy[j + 1] = copy[j];
                j = j - 1;
            }
            copy[j + 1] = key;
        }
    }

    private static int getIndexOf(int[] arr, int search) {
        for (int i = 0; i < arr.length; i++)
            if (arr[i] == search)
                return i;

        return -1;
    }

    private static int[] add(int[] arr, int add) {
        int[] result = Arrays.copyOf(arr, arr.length + 1);
        result[arr.length] = add;
        return result;
    }

    private static int[] remove(int[] arr, int remove) {
        int[] result = new int[ARRAY_CAPACITY];
        System.arraycopy(arr, 0, result, 0, arr.length);
        System.arraycopy(arr, remove + 1, arr, remove, arr.length - 1 - remove);
        return result;
    }
}