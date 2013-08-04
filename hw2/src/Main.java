import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    private static final int dataSize =  10000;
    private static final String path = System.getProperty("user.dir")+ "\\hw2\\src" + "\\QuickSort.txt";

    public static void main(String[]args){
        runTest(new QuickSort());
        runTest(new QuickSort1());
        runTest(new QuickSort2());
    }

    private static void runTest(QuickSort q){
        int[] input = new int[dataSize];
        parseFile(path, input);
        q.qSort(input,0,input.length);
        System.out.println(q.getComparisonsNum());
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
