package hxr190001;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import hxr190001.BFSOO;
import hxr190001.Graph;
import hxr190001.Graph.Edge;
import hxr190001.Graph.Vertex;

public class BFSApplications {
	
	/* Finds the lowest common ancestor between vertex u & v
	 * @param bfsoo
	 * @param vertex u
	 * @param vertex v
	 * @return from_v (list)
	 */
	
	public static List<Vertex> getCycle(BFSOO bfsoo, Vertex u, Vertex v) {
		List<Vertex> from_u = new ArrayList<>();
        List<Vertex> from_v = new ArrayList<>();
        while(u != v){
        	from_u.add(u);
        	from_v.add(0,v);
            u = bfsoo.getParent(u);
            v = bfsoo.getParent(v);
        }
        from_u.add(u);
        from_v.add(0,v);
        from_v.addAll(from_u);
        return from_v;
	}
	
	/*Helper method to detect odd cycle in graph.
	 * Performs BFS, checks if distance between vertex u & v is equal
	 * if cycle exists;
	 * @param graph g
	 * @return result (list)
	 * else;
	 * @return
	 */
	public List<Vertex> oddCycle(Graph g){
		Vertex[] vertexArray = g.getVertexArray();
		List<Vertex> result = new ArrayList<Graph.Vertex>();
		BFSOO bfs = null;
		 
		for(Vertex src : vertexArray ){
			if(bfs == null || !bfs.getSeen(src)){
	            bfs = BFSOO.breadthFirstSearch(g,src);
	            for(Vertex u : vertexArray){
	                if(bfs.getSeen(u)){
	                    for(Edge e : g.outEdges(u)){
	                        Vertex v = e.otherEnd(u);
	                        if(bfs.getDistance(u) == bfs.getDistance(v)){
	                        	result = getCycle(bfs, u, v);
				    			return result;
	                            }
	                    	}
	                	}
	            	}
				}
			}
		return null;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		
		//Graph with cycle
		String string = "8 8   1 2 2   1 3 3   3 4 4   4 5 1   4 1 -7   6 7 1   6 8 1   7 8 1 1";
		
		//Graph with no cycle
		//String string = "7 8   1 2 2   1 3 3   2 4 5   3 4 4   4 5 1   5 1 -7   6 7 -1   7 6 -1 1";
		Scanner in;
		// If there is a command line argument, use it as file from which
		// input is read, otherwise use input from string.
		in = args.length > 0 ? new Scanner(new File(args[0])) : new Scanner(string);
		
		// Read graph from input
	    Graph g = Graph.readGraph(in);
	    
	    //Checking if the graph has cycle or not
	    BFSApplications b = new BFSApplications();
        List<Vertex> ans = b.oddCycle(g);
        if (ans == null) {
        	System.out.println("No odd length cycle in graph.");
        }
        else {
        	System.out.println("The odd length cycle is: ");
        	System.out.println(ans);
        }
	
}
}
