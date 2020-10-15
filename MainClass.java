public class Main {
    public static void main(String[] args) {

        Integer[] arr = {1, 2, 4, 4, 2, 3, 4, 1, 7};
        Set<Integer> set = new HashSet<>(Arrays.asList(arr));

        if (!set.contains(4)) throw new RuntimeException("there is no at least one four");


        System.out.println(Arrays.toString(returnNewArray(arr)));
        System.out.println(checkArray(arr));
    }
    //Method 2
    private static Object[] returnNewArray(Integer[] arr) {
        return Arrays.stream(arr).skip(7).toArray();
    }
    //Method 3
    private static boolean checkArray(Integer[] arr) {
        Set<Integer> set = new HashSet<>(Arrays.asList(arr));
        return set.contains(1) && set.contains(4);
    }
}