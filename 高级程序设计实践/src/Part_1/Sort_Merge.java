package Part_1;

public class Sort_Merge {

        private static Comparable[] aux;

        private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
            assert isSorted(a, lo, mid);
            assert isSorted(a, mid + 1, hi);

            for (int k = lo; k <= hi; k++) {
                aux[k] = a[k];
            }
            int i = lo;
            int j = mid + 1;
            for (int k = lo; k <= hi; k++) {
                if (i > mid) a[k] = aux[j++];
                else if (j > hi) a[k] = aux[i++];
                else if (less(aux[j], aux[i])) a[k] = aux[j++];
                else a[k] = aux[i++];
            }
            assert isSorted(a, lo, hi);
        }

        private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
            if (hi <= lo) {
                return;
            }

            int mid = lo + (hi - lo) / 2;
            sort(a, aux, lo, mid);
            sort(a, aux, mid + 1, hi);
            if (!less(a[mid + 1], a[mid])) {
                return;
            }
            merge(a, aux, lo, mid, hi);
        }

        public static void sort(Comparable[] a) {
            aux = new Comparable[a.length];
            sort(a, aux, 0, a.length - 1);
        }

        private static boolean isSorted(Comparable[] a, int lo, int mid) {
            for (int i = lo; i < mid - 1; i++) {
                if (!less(a[i], a[i + 1])) {
                    return false;
                }
            }
            return true;
        }
        private static boolean less(Comparable v, Comparable w) {
            return v.compareTo(w) < 0;
        }

    public static void main(String[] args) {
        Comparable[] test = {4, 3, 5, 7, 8, 1, 2, 9};
        Sort_Merge.sort(test);
        for (int i = 0; i<test.length; i++){
            System.out.println(test[i]);
        }
    }
}
