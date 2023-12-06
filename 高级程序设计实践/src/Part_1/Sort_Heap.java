package Part_1;

public class Sort_Heap {

    public static void sort(Comparable[] pq){
        int N = pq.length;
        for (int k = N / 2; k >= 1; k--){
            sink(pq, k, N);
        }
        int k = N;
        while (k > 1) {
            exch(pq, 1, k--);
            sink(pq, 1, k);
        }
    }

    private static void sink(Comparable[] pq, int k, int n) {
        while (2*k <= n) {
            int j = 2*k;
            if (j < n && less(pq, j, j+1)) j++;
            if (!less(pq, k, j)) break;
            exch(pq, k, j);
            k = j;
        }
    }

    private static boolean less(Comparable[] pq, int i, int j) {
        return pq[i-1].compareTo(pq[j-1]) < 0;
    }


    private static void exch(Object[] pq, int i, int j) {
        Object swap = pq[i-1];
        pq[i-1] = pq[j-1];
        pq[j-1] = swap;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

    public static void main(String[] args) {
        Comparable[] test = {4, 3, 5, 7, 8, 1, 2, 9};
        Sort_Heap.sort(test);
        Sort_Heap.show(test);
    }
}
