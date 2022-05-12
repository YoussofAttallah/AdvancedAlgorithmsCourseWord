public class Task3 {
    public static void main(String[] arg) {
        System.out.println("The Min Heap is ");
        MinHeap minHeap = new MinHeap(15);
        minHeap.insert(5);
        minHeap.insert(3);
        minHeap.insert(17);
        minHeap.insert(10);
        minHeap.insert(84);
        minHeap.insert(19);
        minHeap.insert(6);
        minHeap.insert(22);
        minHeap.insert(9);
        minHeap.print();
        System.out.println("The Min val is "
                + minHeap.remove());
    }
}

class MinHeap {
    private int[] Heap;
    private int size;
    private int maxsize;
    private static final int FRONT = 1;
    public MinHeap(int maxsize) {
        this.maxsize = maxsize;
        this.size = 0;
        Heap = new int[this.maxsize + 1];
        Heap[0] = Integer.MIN_VALUE;
    }
    private boolean isLeaf(int pos) {
        if (pos > (size / 2) && pos <= size) {
            return true;
        
        }
        return false;
    }
    private void swap(int fpos, int spos) {
        int tmp;
        tmp = Heap[fpos];
        Heap[fpos] = Heap[spos];
        Heap[spos] = tmp;
    }
    private void minHeapify(int pos) {
        if (!isLeaf(pos)) {
            if (Heap[pos] > Heap[2 * (pos)]
                    || Heap[pos] > Heap[(2 * pos) + 1]) {
                if (Heap[2 * (pos)] < Heap[(2 * pos) + 1]) {
                    swap(pos, 2 * (pos));
                    minHeapify(2 * pos);
                }
                else {
                    swap(pos, (2 * pos) + 1);
                    minHeapify((2 * pos) + 1);
                }
            }
        }
    }
    public void insert(int element) {
        if (size >= maxsize) {
            return;
        }
        Heap[++size] = element;
        int current = size;
        while (Heap[current] < Heap[(current) / 2]) {
            swap(current, current / 2);
            current = current / 2;
        }
    }

    // Method 8
    // To print the contents of the heap
    public void print() {
        for (int i = 1; i <= size / 2; i++) {
            // Printing the parent and both childrens
            System.out.print(
                    " PARENT : " + Heap[i]
                            + " LEFT CHILD : " + Heap[2 * i]
                            + " RIGHT CHILD :" + Heap[2 * i + 1]);
            // By here new line is required
            System.out.println();
        }
    }

    // Method 9
    // To remove and return the minimum
    // element from the heap
    public int remove() {
        int popped = Heap[FRONT];
        Heap[FRONT] = Heap[size--];
        minHeapify(FRONT);
        return popped;
    }
}
