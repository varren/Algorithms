/*
 * This implementation always takes last element as a pivot
 *
 */
public class QuickSort1 extends QuickSort{
    @Override
    protected int selectPivot(int[] A, int low, int high){
            return high;
    }

}
