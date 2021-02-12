/**
 * 
 */
package graph;

import java.util.LinkedList;

import graph.WeightedGraph.Vertex;

/**
 * @author Arsalan Jafari
 * This interface shows the major algorithms that are implemented
 *
 */
public interface GraphProcessing {
	
	
	/**
	 * @return The total number of vertices in the graph.
	 */
	public int getVerticesCount();
	
	/**
	 * Recource used: GeeksForGeeks.com
	 * @return true if there's at least a cycle in the graph. false otherwise.
	 */
	public boolean isCyclic();
	
	/** 
	 * @return A deep copy of the graph
	 * @throws CloneNotSupportedException 
	 */
	public WeightedGraph getCopy() throws CloneNotSupportedException;
	
	/**
	 * @param connectedEdge
	 * @return a linkedlist of vertices that are connected to specified vertex
	 */
	public LinkedList<Vertex> getEdgesByVertex(int connectedVertexID);
	
	/**
	 * Getting the edge connected between sourceID and destinationID
	 * @param sourceID
	 * @param destinationID
	 * @return a 3 sized int[] array where [0] = source, [1] = weight, [2] = destination
	 */
	public Integer[] getEdge(int sourceID, int destinationID);
	
	/**
	 * Same as getEdgesByVertex but instead will return a linkedlist of edges as int arrays
	 * @param connectedVertex
	 * @param graph
	 * @return
	 */
	public LinkedList<Integer[]> getEdgesOfAVertex(int connectedVertex,WeightedGraph graph);
	
	/**
	 * Depth-First search or DFS
	 * @param startingVertex
	 * @param doPrint will print the vertices in order that DFS met them
	 * @return the collection of vertex indices
	 */
	public LinkedList<Integer> DFS(int startingVertex,boolean doPrint);
	
	/**
	 * @return true if the graph is connected. otherwise it will return false.
	 */
	public boolean isConnected();
	
	/**
	 * Printing the shortest path to all vertices using Dijkstra algorithm.
	 * Shortest path is the path which has the least total weight.
	 * Resources used: Wikipedia, 
	 * https://www.freecodecamp.org/news/dijkstras-shortest-path-algorithm-visual-introduction/
	 * https://www.codingame.com/playgrounds/1608/shortest-paths-with-dijkstras-algorithm/dijkstras-algorithm
	 * @param sourceID the pivot vertex.
	 */
	public void shortestPathByDijkstra(int sourceID);

}
