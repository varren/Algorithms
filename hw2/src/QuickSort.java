/*
 * This implementation always takes first element as a pivot
 *
 */
public class QuickSort {

    private long comparisonsNum;

    public void qSort(int[] A, int low, int high) {

        if(high - low < 1) return;

        int pivot = selectPivot(A,low,high - 1);
        swap(A, low, pivot);

        int pivotPos = partition(A, low + 1 ,high, low);

        qSort(A, low, pivotPos);
        qSort(A, pivotPos + 1, high);
    }

    protected int selectPivot(int[] A, int low, int high){
        return low;
    }

    private int partition(int[] A, int low, int high,int pivotID){
        comparisonsNum += high - low;
        int i = low;
        for(int j = i; j < high;j++)
            if(A[j] < A[pivotID]){
                swap(A, j, i);
                i++;
            }

        swap(A, pivotID, i - 1);
        return i - 1;

    }

    private void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    public long getComparisonsNum(){
        return comparisonsNum;
    }
}
