import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class 对顶堆模板{
    public static StreamTokenizer sz = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static int sc() throws IOException {
        sz.nextToken();
        return (int) sz.nval;
    }

    public static class Heap {
        public int[] heap;
        public int sz;
        public boolean max;

        public Heap(int cap, boolean max) {
            heap = new int[-~cap];
            sz = 0;
            this.max = max;
        }

        public boolean cmp(int a, int b) {
            return max ? heap[a] > heap[b] : heap[a] < heap[b];
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

        public int size() {
            return sz;
        }

        public boolean isEmpty() {
            return sz == 0;
        }

        public void up(int u) {
            while (u >> 1 > 0 && cmp(u, u >> 1)) {
                swap(u, u >> 1);
                u >>= 1;
            }
        }

        public void down(int u) {
            int t = u;
            if (u << 1 <= sz && cmp(u << 1, t)) t = u << 1;
            if ((u << 1 | 1) <= sz && cmp(u << 1 | 1, t)) t = u << 1 | 1;
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
        Heap max = new Heap(n, true);
        Heap min = new Heap(n, false);
        StringBuilder out = new StringBuilder();

        for (int i = 1; i <= n; i = -~i) {
            int x = sc();
            if (max.isEmpty() || x <= max.peek()) max.push(x);
            else min.push(x);

            if (max.size() > min.size() + 1) min.push(max.poll());
            else if (min.size() > max.size()) max.push(min.poll());

            out.append(max.peek()).append("\n");
        }

        System.out.print(out);
    }
}
