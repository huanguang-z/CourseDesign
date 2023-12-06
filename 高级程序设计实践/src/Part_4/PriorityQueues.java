package Part_4;

public class PriorityQueues<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N;

    public PriorityQueues() {
        pq = (Key[]) new Comparable[2];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void insert(Key key) {
        if (N == pq.length - 1) resize(pq.length * 2);
        pq[++N] = key;
        swim(N);
    }

    public Key delMin() {
        Key min = pq[1];
        exch(1, N--);
        sink(1);
        pq[N + 1] = null;
        return min;
    }

    private void swim(int k) {
        while (k > 1 && less(k, k / 2)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j + 1, j)) j = j + 1;
            if (less(k, j)) return;
            exch(k, j);
            k = j;
        }
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key key = pq[i];
        pq[i] = pq[j];
        pq[j] = key;
    }

    public void show() {
        for(int i = 1; i < N+1; i++) {
            System.out.println(pq[i]);
        }
    }

    private void resize(int size){
        Key[] newKey = (Key[]) new Comparable[size];
        for (int i = 1; i < pq.length; i++){
            newKey[i] = pq[i];

        }
        pq = newKey;

    }

    public int size() {
        return N;
    }
}
