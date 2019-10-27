package insert_sort;

public class InsertSort {

    static void insert_sort(int[] A,int n){
        for(int i=1;i<n;i++){
            int tmp = A[i];//tmp为要插入的元素
            int j = i-1; //有序列表右边界
            while(j>=0&&tmp<A[j]) {//从右向左，当没有找到第一个可插入的位置时，有序区逐个右移
                A[j+1] = A[j];
                j--;
            }
            A[j+1] = tmp;
        }
    }

    public static void main(String[] args) {
        int arr[] = {5,7,8,3,1,2,4,6};
        insert_sort(arr,arr.length);
        for (int i=0;i<arr.length;i++){
            System.out.print(arr[i]+"---");
        }
    }

}
