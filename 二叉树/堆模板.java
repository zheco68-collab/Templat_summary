import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class 堆模板{
    public static StreamTokenizer sz = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static int sc() throws IOException {
        sz.nextToken();
        return (int) sz.nval;
    }

    public static class MinHeap {
        public int[] heap;
        public int sz;

        public MinHeap(int cap) {
            heap = new int[-~cap];
            sz = 0;
        }

        public void push(int val) {
            heap[sz = -~sz] = val;
            up(sz);
        }

        public int poll() {
            int top = heap[1];
            heap[1] = heap[sz];
            sz = ~-sz;
            down(1);
            return top;
        }

        public int peek() {
            return heap[1];
        }

        public boolean isEmpty() {
            return sz == 0;
        }

        public void up(int u) {
            while (u >> 1 > 0 && heap[u] < heap[u >> 1]) {
                swap(u, u >> 1);
                u >>= 1;
            }
        }

        public void down(int u) {
            int t = u;
            if (u << 1 <= sz && heap[u << 1] < heap[t]) t = u << 1;
            if ((u << 1 | 1) <= sz && heap[u << 1 | 1] < heap[t]) t = u << 1 | 1;
            if (t != u) {
                swap(u, t);
                down(t);
            }
        }

        public void swap(int i, int j) {
            int tmp = heap[i];
            heap[i] = heap[j];
            heap[j] = tmp;
        }
    }

    public static void main(String... args) throws IOException {
        int n = sc();
        MinHeap hp = new MinHeap(n);

        for (int i = 1; i <= n; i = -~i) {
            int op = sc();
            if (op == 1) {
                hp.push(sc());
            } else if (op == 2) {
                System.out.println(hp.peek());
            } else {
                hp.poll();
            }
        }
    }
}