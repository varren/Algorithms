import java.util.*;

public class RandomizedContractionAlgorithm {
   /*
    * Karger's algorithm / Contraction algorithm (minimum cut problem)
    * 1) while there are > 2 vertices
    * 2) pick the remaining edge(u,v) uniformly at random
    * 3) merge u and v in a single vertex
    * 4) remove self loop
    * 5) return cut represented by 2 final vertices
    */

    public int contractionAlgorithm(HashMap<Integer,ArrayList<Integer>> data){

        int randomVertex = 0;

        while(data.size() > 2){
            randomVertex = selectRandomVertex(data);
            
            ArrayList<Integer> randomEdges = data.get(randomVertex);

            int edgeToMergeWith = selectRandomEdge(randomEdges);

            if(edgeToMergeWith != randomVertex)
                merge(randomVertex, edgeToMergeWith,data);
        }

        return data.get(randomVertex).size();
    }

    private void merge(int oneID, int twoID, HashMap<Integer, ArrayList<Integer>> data) {
        ArrayList<Integer> oneEdges = data.get(oneID);
        ArrayList<Integer> twoEdges = data.get(twoID);

        oneEdges.removeAll(Collections.singleton(twoID));
        twoEdges.removeAll(Collections.singleton(oneID));

        for(int i: twoEdges){
            ArrayList<Integer> connectedNode = data.get(i);
            connectedNode.remove(connectedNode.indexOf(twoID));
            connectedNode.add(oneID);

            oneEdges.add(i);
        }

        data.remove(twoID);
    }

    private int selectRandomEdge(ArrayList<Integer> edges) {
        return selectRandom(edges);
    }

    private int selectRandomVertex(HashMap<Integer,ArrayList<Integer>> data){
        return selectRandom(new ArrayList<Integer>(data.keySet()));
    }

    private int selectRandom(ArrayList<Integer> edges) {
        Random random = new Random(Double.doubleToLongBits(Math.random()));
        return edges.get(random.nextInt(edges.size()));
    }

}
