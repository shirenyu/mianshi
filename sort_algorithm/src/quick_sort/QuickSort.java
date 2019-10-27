package quick_sort;

public class QuickSort {
    static int partition(int[] A, int p, int r){
        int x = A[r];
        int i = p - 1;
        for(int j=p;j<=r-1;j++){
            if (A[j]<=x) {
                i = i + 1;
                int temp = A[i];
                A[i] = A[j];
                A[j] = temp;
            }
        }
        i = i + 1;
        int temp = A[i];
        A[i] = A[r];
        A[r] = temp;
        return i;
    }
/*public static int partition(int[] array, int start, int end) {
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
}*/
    static void quick_sort(int[] A, int p, int r){
        if(p<r){//解决边界问题
            int k = partition(A,p,r);
            quick_sort(A,p,k-1);
            quick_sort(A,k+1,r);
        }
    }

    public static void main(String[] args) {
        int[] A = {2,8,7,1,3,5,6,4};
        quick_sort(A,0,A.length-1);

        for(int i = 0;i<A.length;i++){
            System.out.print(A[i]+"===");
        }
    }
}
