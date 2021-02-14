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
	 * Creates and prints the adjacency matrix
	 */
	public void printAdjacencyMatrix();
	
	/**
	 * Prints the adjacency list
	 */
	public void printAdjacencyList();
	
	
	/**
	 * BFS algorithm starting from startingvertex
	 * @param startingvertex
	 * @param print
	 */
	public void BFS(int startingvertex, boolean print);
	
	/**
	 * @return The total number of vertices in the graph.
	 */
	public int getVerticesCount();
	
	/**
	 * 
	 * @param vertex
	 * @return the degree of specified vertex
	 */
	public int getDegree(int vertex);
	
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
	 * @param firstVertexID
	 * @param secondVertexID
	 * @return true if there's an edge between firstVertex and secondVertex.
	 */
	public boolean hasEdge(int firstVertexID, int secondVertexID);
	
	/**
	 * adds a new (disconnected) vertex to the graph
	 */
	public void addVertex();
	
	/** adds an edge between firstVertex and secondVertex with the specified weight
	 * @param firstVertexID
	 * @param secondVertexID
	 * @param weight
	 */
	public void addEdge(int firstVertexID, int secondVertexID, int weight);
	
	/**
	 * @return the number of edges in the graph
	 */
	public int getEdgeCount();
	
	
	/** 
	 * Removes the edge between two vertices.
	 * @param firstVertexID
	 * @param secondVertexID
	 */
	public void removeEdge(int firstVertexID, int secondVertexID);
	
	//XXX:till remove vertex
	
	
	/**
	 * @param ID The ID of vertex that is going to be deleted
	 */
	public void removeVertex(int ID);
	
	
	/**
	 * Return all the edges of the graph in a linked list.
	 * for an edge like [1,2,3] , [3,2,1] would also be included in the linked list
	 * @param adjacencyList
	 * @return
	 */
	public LinkedList<Integer[]>getEdgesList(LinkedList<LinkedList<Vertex>> adjacencyList);
	
	
	/**
	 * @return a MST (if it exists) using Kruskal algorithm.
	 */
	public WeightedGraph MSTbyKruskal();
	
	/**
	 * @return a MST (if it exists) using prim algorithm.
	 */
	public WeightedGraph MSTbyPrim();
	
	
	/**
	 * @return a MST (if it exists) using prim algorithm.
	 */
	public WeightedGraph MSTbySollin();
	
	/**
	 * @return a list of edges in order, if there's an Eulerian path on the graph.
	 */
	public LinkedList<Integer[]> eulerianPath() throws CloneNotSupportedException;
	
	
	/**
	 * @return a hamiltonian cycle (if it exists).
	 */
	public LinkedList<Integer[]> hamiltonianCycle();
	
	/**
	 * Finding and printing the shortest distance between sourceID and all other edges on the graph.
	 * By shortest distance I mean the path that has the least cost (weight) compared to
	 * all other possible paths between source vertex and the destination.
	 * Resources used: Wikipedia, 
	 * https://www.freecodecamp.org/news/dijkstras-shortest-path-algorithm-visual-introduction/
	 * https://www.codingame.com/playgrounds/1608/shortest-paths-with-dijkstras-algorithm/dijkstras-algorithm
	 * @param sourceID the pivot vertex.
	 * @return
	 */
	public LinkedList<LinkedList<Integer[]>> ShoretestPathbyDijkstra(int sourceID);
	
	
	
}
