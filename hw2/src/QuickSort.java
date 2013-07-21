import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class QuickSort {
    private static final String path = System.getProperty("user.dir")+ "\\hw2\\src" + "\\QuickSort.txt";
    private static long comparisonsNum;
    private static final int dataSize =  10000;

    private static final int PROBLEM_A = 0;
    private static final int PROBLEM_B = 1;
    private static final int PROBLEM_C = 2;
    private static int currentProblem = 0;

    public static void main(String[]args){
        currentProblem = PROBLEM_A;
        runTest();
        currentProblem = PROBLEM_B;
        runTest();
        currentProblem = PROBLEM_C;
        runTest();
    }

    private static void runTest(){
        int[] input = new int[dataSize];
        comparisonsNum =0;
        parseFile(path, input);
        qSort(input,0,input.length);
        System.out.println(comparisonsNum);
    }


    public static void qSort(int[] A, int low, int high) {

        if(high - low < 1) return;

        int pivot = selectPivot(A,low,high - 1);
        swap(A, low, pivot);

        int pivotPos = partition(A, low + 1 ,high, low);

        qSort(A, low, pivotPos);
        qSort(A, pivotPos + 1, high);
    }

    private static int selectPivot(int[] A, int low, int high){

        if (currentProblem == PROBLEM_A)
            return low;
        else if(currentProblem == PROBLEM_B)
            return high;

        int mid = low + (high - low) / 2;

        if ((A[low] < A[mid] && A[mid] < A[high])  //low < mid < high
         || (A[high] < A[mid] && A[mid] < A[low])) //high < mid < low
            return mid;
        else if ((A[low] < A[high] && A[high] < A[mid])  //low < high < mid
              || (A[mid] < A[high] && A[high] < A[low])) //mid < high < low
            return high;

        return low;
    }

    private static int partition(int[] A, int low, int high,int pivotID){
        comparisonsNum+= high - low;
        int i = low;
        for(int j = i; j < high;j++)
            if(A[j] < A[pivotID]){
                swap(A, j, i);
                i++;
            }

        swap(A, pivotID, i - 1);
        return i - 1;

    }
    private static void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    private static void parseFile(String filePath, int [] input ) {

        try {
            int i=0;
            BufferedReader br = new BufferedReader(new FileReader(new File(filePath)));
            while(true){
                String line = br.readLine();
                if(line == null)
                    break;
                int nextNum = Integer.parseInt(line);

                if(nextNum == 0)
                    break;

                input[i++]=nextNum;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
