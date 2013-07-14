import java.io.*;
import java.util.ArrayList;

public class MergeSort {
    private static final String filename = "/IntegerArray.txt";
    private static long inversionsCounter =0;

    public static void main(String[]args){
        ArrayList<Integer> input = new ArrayList<Integer>();

        parseFile(filename, input);
        //input.add(1);input.add(2);input.add(3);input.add(4);input.add(5);input.add(6);
        //input.add(1);input.add(3);input.add(5);input.add(2);input.add(4);input.add(6);
        //input.add(6);input.add(5);input.add(4);input.add(3);input.add(2);input.add(1);

        mergeSort(input);

        System.out.print(inversionsCounter);
    }

    private static ArrayList<Integer> mergeSort(ArrayList<Integer> input) {

        if(input.size() < 2)
            return input;

        ArrayList<Integer> firstPart = new ArrayList<Integer>();
        ArrayList<Integer> secondPart = new ArrayList<Integer>();

        split(firstPart, secondPart, input);

        firstPart = mergeSort(firstPart);
        secondPart = mergeSort(secondPart);

        return merge(firstPart, secondPart);
    }

    private static void split(ArrayList<Integer> one,ArrayList<Integer> two,ArrayList<Integer> input ){
         for(int i = 0;i<input.size();i++)
             if(i < input.size()/2)
                 one.add(input.get(i));
             else
                 two.add(input.get(i));

    }

    private static ArrayList<Integer> merge(ArrayList<Integer> one, ArrayList<Integer> two) {

        ArrayList<Integer> result = new ArrayList<Integer>();
        int i = 0, j = 0;
        while (result.size()!= (one.size() + two.size())) {

           if( j >= two.size() )  {

                result.add(one.get(i));
                i++;
            } else if (i >= one.size()){

                result.add(two.get(j));
                j++;
            } else if( one.get(i) <  two.get(j)){

               result.add(one.get(i));
               i++;
            } else {
               inversionsCounter += one.size() - i;
               result.add(two.get(j));
               j++;
            }
        }

        return result;
    }

    private static void parseFile(String filename, ArrayList<Integer> input ) {
        String dir = System.getProperty("user.dir")+ "\\hw1\\src";
        try {

            BufferedReader br = new BufferedReader(new FileReader(new File(dir+filename)));
            while(true){
                String line = br.readLine();
                if(line == null)
                    break;
                int nextNum = Integer.parseInt(line);

                if(nextNum == 0)
                    break;

                input.add(nextNum);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
