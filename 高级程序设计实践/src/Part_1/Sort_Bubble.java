package Part_1;

public class Sort_Bubble {

    public static void sort(Comparable[] a){
        int N = a.length;
        int flag = 0;
        for (int i = 0; i < N - 1; i++) {
            flag = 0;
            for (int j = 0; j < N - 1 - i; j++ ) {
                if (less(a[j + 1], a[j])){
                    exch(a,j + 1, j);
                    flag = 1;
                }
            }
            if (flag == 0){
                break;
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
        Sort_Bubble.sort(test);
        for (int i = 0; i<test.length; i++){
            System.out.println(test[i]);
        }
    }
}
