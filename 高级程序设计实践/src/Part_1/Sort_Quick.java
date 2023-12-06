package Part_1;

public class Sort_Quick {

    private static int partition(Comparable[] a,int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        while(true){
            while(less(a[++i],a[lo]))
                if (i == hi) break;

            while(less(a[lo],a[--j]))
                if (j == lo) break;

            if (i >= j)
                break;
            exch(a,i,j);
        }
        exch(a,lo,j);
        return j;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void sort(Comparable[] a) {
        sort(a,0,a.length-1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int j = partition(a, lo, hi);
        sort(a,lo,j-1);
        sort(a,j+1,hi);
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static void main(String[] args) {
        Comparable[] test = {4, 3, 5, 7, 8, 1, 2, 9};
        Sort_Quick.sort(test);
        for (int i = 0; i<test.length; i++){
            System.out.println(test[i]);
        }
    }
}
