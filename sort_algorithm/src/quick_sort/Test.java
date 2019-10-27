package quick_sort;

public class Test {
    public static void main(String[] args) {
        int[] array = {2,8,7,1,3,5,6,4};
        quickSort(array, 0, array.length - 1);
        printArray(array);
    }

    /**
     * 快速排序
     *
     * @param array
     *            待排序数组
     * @param start
     *            待排序子数组的起始索引
     * @param end
     *            待排序子数组的结束索引
     */
    public static void quickSort(int[] array, int start, int end) {
        if (start < end) {
            int position = partition(array, start, end);
            quickSort(array, start, position - 1);
            quickSort(array, position + 1, end);
        }
    }

    /**
     * 重排array，并找出“临界”位置的索引
     *
     * @param array
     *            待重排数组
     * @param start
     *            待重排子数组的起始索引
     * @param end
     *            待重排子数组的结束索引
     * @return
     */
    public static int partition(int[] array, int start, int end) {
        int position = start - 1;
        int base = array[end];
        for (int i = start; i < end; i++) {
            if (array[i] <= base) {
                position++;
                int temp = array[position];
                array[position] = array[i];
                array[i] = temp;
            }
        }
        int temp = array[position + 1];
        array[position + 1] = array[end];
        array[end] = temp;
        return position + 1;
    }

    /**
     * 打印数组
     *
     * @param array
     */
    public static void printArray(int[] array) {
        for (int i : array) {
            System.out.print(i + "");
        }
        System.out.println();
    }
}

