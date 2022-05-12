import java.lang.System;
import java.util.ArrayList;

public class Task8 {
    public static void main(String[] args) {
        Integer array[] = { 1, 4, 3, 2, 7, 4, 7, 3, 7, 4, 65, 54, 32, 34, 5, 2 };
        MergeSort test = new MergeSort(array);
        test.threaded_sort();

    }
}

class MergeSort {
    private int cores = Runtime.getRuntime().availableProcessors();
    private Integer[] array;

    public MergeSort(Integer[] array) {
        this.array = array;
    }

    private class SortThreads extends Thread {
        SortThreads(Integer[] array, int begin, int end) {
            super(() -> {
                MergeSort.merge_sort(array, begin, end);
            });
            this.start();
        }
    }

    public void threaded_sort() {
        final int length = array.length;
        boolean exact = length % cores == 0;
        int maxlim = exact ? length / cores : length / (cores - 1);
        final ArrayList<SortThreads> threads = new ArrayList<>();
        for (int i = 0; i < length; i += maxlim) {
            int beg = i;
            int remain = (length) - i;
            int end = remain < maxlim ? i + (remain - 1) : i + (maxlim - 1);
            final SortThreads t = new SortThreads(array, beg, end);
            threads.add(t);
        }
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException ignored) {
            }
        }
        for (int i = 0; i < length; i += maxlim) {
            int mid = i == 0 ? 0 : i - 1;
            int remain = (length) - i;
            int end = remain < maxlim ? i + (remain - 1) : i + (maxlim - 1);
            merge(array, 0, mid, end);
        }
        print_sorted();
    }

    public static void merge_sort(Integer[] array, int begin, int end) {
        if (begin < end) {
            int mid = (begin + end) / 2;
            merge_sort(array, begin, mid);
            merge_sort(array, mid + 1, end);
            merge(array, begin, mid, end);
        }
    }

    public static void merge(Integer[] array, int begin, int mid, int end) {
        Integer[] temp = new Integer[(end - begin) + 1];
        int i = begin, j = mid + 1;
        int k = 0;
        while (i <= mid && j <= end) {
            if (array[i] <= array[j]) {
                temp[k] = array[i];
                i += 1;
            } else {
                temp[k] = array[j];
                j += 1;
            }
            k += 1;
        }
        while (i <= mid) {
            temp[k] = array[i];
            i += 1;
            k += 1;
        }
        while (j <= end) {
            temp[k] = array[j];
            j += 1;
            k += 1;
        }
        for (i = begin, k = 0; i <= end; i++, k++) {
            array[i] = temp[k];
        }
    }

    public void print_sorted() {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ",");
        }
    }
}
