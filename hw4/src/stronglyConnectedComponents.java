import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class StronglyConnectedComponents {

    private Set<Integer> seenVertex;
    private HashMap<Integer, Integer> buffer;
    private boolean isFirstPass = true;
    private int finishingPos = 0;

    /*
    * IMPORTANT !!!
    * My VM options: -Xss8m -XX:+UseSerialGC
    * It is needed for huge Graphs because of StackOverflow problem
    *
    * Kosaraju's algorithm
    *
    * STRONGLY-CONNECTED-COMPONENTS(G)
    *
    * call DFS(G) to compute finishing times u.f for each vertex u
    * compute GT
    * call DFS(GT), but in the main loop of DFS, consider the verteces in order of decreasing u.f (as computed in line 2)
    * output the vertices of each tree in the depth-first forest formed in line 4 as a separate strongly connected component
    */

    public ArrayList<Integer> runAlgorithm(HashMap<Integer, ArrayList<Integer>> G, HashMap<Integer, ArrayList<Integer>> rG, int maxVertex) {
        computeFinishingTimes(rG, maxVertex);
        return computeResultSCC(G, maxVertex);  // all SCC sizes in unsorted order
    }

    /*
    *  First DFS-loop to compute finishing times
    *
    *  instance variable HashMap<Integer, Integer> buffer = stores finishing times of each vertex
    *  instance variable int finishingPos = finishing times counter
    *
    *  @param G - directed graph
    *  @param maxVertex - highest vertex id in graph
    *
    *  after this loop instance variable buffer
    *  will map each finishing time with VertexID // buffer.put(finishingPos, v);
    */
    private void computeFinishingTimes(HashMap<Integer, ArrayList<Integer>> G, int maxVertex) {
        seenVertex = new HashSet<Integer>();
        buffer = new HashMap<Integer, Integer>(maxVertex);

        for (int i = 1; i <= maxVertex; i++)
            if (!seenVertex.contains(i))
                finishingPos = depthFirstSearch(G, i);

    }

    /*
    *  Second DFS-loop to compute result unordered SCC sizes
    *
    *  @param G In Directed Graph
    *  @param maxVertex Highest vertex id in Graph
    *
    *  @return unordered ArrayList<Integer> of SCC sizes
    */
    private ArrayList<Integer> computeResultSCC(HashMap<Integer, ArrayList<Integer>> G, int maxVertex) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        seenVertex = new HashSet<Integer>();
        isFirstPass = false;

        for (int i = maxVertex; i > 0; i--)
            if (buffer.containsKey(i) && !seenVertex.contains(buffer.get(i))) {
                finishingPos = 0;
                int sccSize = depthFirstSearch(G, buffer.get(i));
                result.add(sccSize);
            }

        return result;
    }

    /*
    *  Second DFS-loop to compute result unordered SCC sizes
    *
    *  @param G - directed graph
    *  @param v - vertex to start
    *
    *  if (isFirstPass) buffer will map each finishing time with VertexID
    *
    *  @return vertexes reached
    */
    private int depthFirstSearch(HashMap<Integer, ArrayList<Integer>> G, int v) {
        if (!seenVertex.contains(v)) {

            seenVertex.add(v);

            for (int e : adjacentEdges(G, v))
                depthFirstSearch(G, e);

            finishingPos++;
            if (isFirstPass)
                buffer.put(finishingPos, v);

        }
        return finishingPos;
    }

    /*
    * Helper function to protect from Vertexes with 0 outgoing edges
    */
    private ArrayList<Integer> adjacentEdges(HashMap<Integer, ArrayList<Integer>> G, int v) {
        if (G.containsKey(v)) return G.get(v);
        else return new ArrayList<Integer>();
    }

}
