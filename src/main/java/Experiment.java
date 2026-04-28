public class Experiment {
    private Sorter sorter = new Sorter();
    private Searcher searcher = new Searcher();
    public long measureSortTime(int[] arr, String type) {
        long start = System.nanoTime();
        if ("basic".equals(type)) {
            sorter.basicSort(arr);
        } else {
            sorter.advancedSort(arr);
        }
        long end = System.nanoTime();
        return end - start;
    }
    public long measureSearchTime(int[] arr, int target) {
        long start = System.nanoTime();
        searcher.search(arr, target);
        long end = System.nanoTime();
        return end - start;
    }
    public void runAllExperiments() {
        int[] sizes = {10, 100, 1000};
        for (int size : sizes) {
            int[] arr = sorter.generateRandomArray(size);
            int[] arrCopy1 = arr.clone();
            int[] arrCopy2 = arr.clone();
            long basicTime = measureSortTime(arrCopy1, "basic");
            long advancedTime = measureSortTime(arrCopy2, "advanced");
            sorter.advancedSort(arr);
            int target = arr[size / 2];
            long searchTime = measureSearchTime(arr, target);
            System.out.println("Size: " + size);
            System.out.println("Bubble Sort Time: " + basicTime);
            System.out.println("Quick Sort Time: " + advancedTime);
            System.out.println("Binary Search Time: " + searchTime);
            System.out.println("------------------------");
        }
    }
}
