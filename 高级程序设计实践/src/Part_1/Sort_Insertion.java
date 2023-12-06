package Part_1;


public class Sort_Insertion {
        public static void sort(Comparable[] a) {
            int N = a.length;
            for (int i = 0; i < N; i++) {
                for (int j = i; j > 0; j--) {
                    if (less(a[j], a[j - 1])) {
                        exch(a, j, j - 1);
                    }
                    else {
                        break;
                    }
                }
            }
        }

        private static boolean less(Comparable v, Comparable w) {
            return v.compareTo(w) < 0;
        }

        private static void exch(Comparable[] a, int i, int j) {
            Comparable swap = a[i];
            a[i] = a[j];
            a[j] = swap;
        }

    public static void main(String[] args) {
        Comparable[] test = {4, 3, 5, 7, 8, 1, 2, 9};
        Sort_Insertion.sort(test);
        for (int i = 0; i<test.length; i++){
            System.out.println(test[i]);
        }


    }
}
