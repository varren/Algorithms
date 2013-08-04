import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class Main {
    private static final String filename = "/IntegerArray.txt";
    private static final int dataSize =  100000;

    public static void main(String[]args){
        int [] input = new int[dataSize];

        parseFile(filename, input);

        //input.add(1);input.add(2);input.add(3);input.add(4);input.add(5);input.add(6);
        //input.add(1);input.add(3);input.add(5);input.add(2);input.add(4);input.add(6);
        //input.add(6);input.add(5);input.add(4);input.add(3);input.add(2);input.add(1);

        MergeSort sort = new MergeSort();
        sort.mergeSort(input, dataSize);

        System.out.print(sort.getInversionsCount());
    }
    private static void parseFile(String filename, int [] input ) {
        String dir = System.getProperty("user.dir")+ "\\hw1\\src";
        int i = 0;
        try {

            BufferedReader br = new BufferedReader(new FileReader(new File(dir+filename)));
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
