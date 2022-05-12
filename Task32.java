public class Task32 {
    public static void main(String[] args) {
        int a[] = { 90, 87, 65, 11, 23, 45, 21 };
        HeapSortClass h = new HeapSortClass(a, a.length);
        h.heapSort();
        ;
    }
}

class HeapSortClass {
    int array[];
    int array_length;

    public HeapSortClass(int a[], int al) {
        this.array = a;
        this.array_length = al;
    }

    public void heapify(int size, int i) {
        int largest = i; // Initialize largest as root
        int left = 2 * i + 1; // left child
        int right = 2 * i + 2; // right child
        // If left child is larger than root
        if (left < size && array[left] > array[largest])
            largest = left;
        // If right child is larger than root
        if (right < size && array[right] > array[largest])
            largest = right;
        // If root is not largest
        if (largest != i) {
            // swap a[i] with a[largest]
            int temp = array[i];
            array[i] = array[largest];
            array[largest] = temp;
            heapify(size, largest);
        }
    }

    public void heapSort() {
        for (int i = array_length / 2 - 1; i >= 0; i--) {
            heapify(array_length, i);
        }
        for (int i = array_length - 1; i >= 0; i--) {
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            heapify(i, 0);
        }
        printSorted();
    }

    public void printSorted() {
        for (int i = 0; i < array_length; i++) {
            System.out.print(array[i] + ",");
        }
    }
}
