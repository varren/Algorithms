
    /*
     * Your task is to compute the number of inversions in the file given,
     * where the ith row of the file indicates the ith entry of an array.
     * Because of the large size of this array,
     * you should implement the fast divide-and-conquer algorithm covered in the video lectures.
     *
    */
public class MergeSort {
    private long inversions = 0;

    public int [] mergeSort(int [] input, int size) {

        if(size < 2) return input;

        int [] firstPart = new int[size/2];
        int [] secondPart = new int[size - size/2];

        split(firstPart, secondPart, input, size);

        firstPart = mergeSort(firstPart, size/2);
        secondPart = mergeSort(secondPart, size - size/2);

        return merge(firstPart, secondPart, size);
    }

    private void split(int [] one, int [] two, int [] input ,int size){
        System.arraycopy(input, 0     , one, 0, size/2);
        System.arraycopy(input, size/2, two, 0, size - size/2);
    }

    private int [] merge(int [] one, int [] two, int size) {

        int i = 0, j = 0;
        int [] result = new int [size];

        while (size != (j + i))
            if      (j >= size - size/2)     result[i+j] = one[i++];
            else if (i >= size/2)            result[i+j] = two[j++];
            else if (one[i] < two[j])        result[i+j] = one[i++];
            else { inversions += size/2 - i; result[i+j] = two[j++];}

        return result;
    }

    public long getInversionsCount(){
        return inversions;
    }

}
