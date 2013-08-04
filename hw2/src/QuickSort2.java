/*
 * This implementation takes the pivot by "median-of-three" rule
 *
 */

public class QuickSort2 extends QuickSort{

    @Override
    protected int selectPivot(int[] A, int low, int high){
    int mid = low + (high - low) / 2;

    if ((A[low] < A[mid] && A[mid] < A[high])  // low < mid < high
            || (A[high] < A[mid] && A[mid] < A[low])) // high < mid < low
            return mid;
    else if ((A[low] < A[high] && A[high] < A[mid])  // low < high < mid
            || (A[mid] < A[high] && A[high] < A[low])) // mid < high < low
            return high;

    return low;
    }
}
