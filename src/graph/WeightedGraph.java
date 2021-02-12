/**
 * 
 */
package graph;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

import graph.WeightedGraph.Vertex;

/**
 * 
 * All algorithm codes and methods are written by me. 
 * Algorithms that are modeled after a psuedocode or a site
 * references that resource. 
 * @author Arsalan Jafari
 *
 */
public class WeightedGraph implements GraphProcessing {
	
	/*
	 * The core of the Graph data structure: an adjacency list
	 * Instead of using a fixed size array I used a another linked list 
	 * so i could add or remove vertices and make a more flexible program
	 */
	private LinkedList<LinkedList<Vertex>> adjacencyList =
			new LinkedList<LinkedList<Vertex>>();
	
	private String name;
	
	
	class Vertex
	{
		private int ID; //Each Vertex has a unique ID starting from 0
		private int weight; //The positive weight of the connected edge to the last Vertex
		/**
		 * Constructing a vertex
		 * @param iD
		 * @param name
		 * @param weight
		 */
		public Vertex(int iD, int weight) {
			/*
			if (iD < 0 || iD > adjacencyList.size() ||
				weight >= Integer.MAX_VALUE || weight < 0)
				throw new IllegalArgumentException("Invalid input for Vertex.\n"
						+ "The weight of a vertex should be positive and its ID should "
						+ "be between 0 and the maximum number of vertices.\n"
						+ "Also duplicate vertex IDs are not allowed. Try again.");
						*/
			ID = iD;
			this.weight = weight;
			
		}
		
		/**
		 * @return the ID of the vertex
		 */
		public int getID() {
			return ID;
		}

		/**
		 * @param iD ///XXX: should implement?
		 */
		public void setID(int iD) {
			ID = iD;
		}

		/**
		 * @return the weight of the vertex
		 */
		public int getWeight() {
			return weight;
		}

		/**
		 * @param weight the weight to set
		 */
		public void setWeight(int weight) {
			if (weight >= Integer.MAX_VALUE || weight < 0)
					throw new IllegalArgumentException("Invalid input for the weight of the"
							+ " Vertex.\n"
							+ " The weight of a vertex should be positive");
			this.weight = weight;
		}
		
		@Override
		public String toString()
		{
			return "Vertex " + this.ID +
					", weight = " + this.weight + ".";
		}
		
		/**
		 * Will return a clone of vertex
		 * Used by getCopy method of the graph
		 */
		@Override
		protected Object clone() throws CloneNotSupportedException
		{
			Vertex copyVertex = new Vertex(this.ID,this.weight);
			return copyVertex;
		}
		
		/**
		 * Will return true if the vertices are the same
		 * used mostly by prim algorithms
		 */
		@Override
		public boolean equals(Object e)
		{
			return (((Vertex)e).getID() == this.getID() &&
					((Vertex)e).getWeight() == this.weight);
		}
	}
	public WeightedGraph(String name, LinkedList<LinkedList<Vertex>> adjacencyList)
	{
		this.name = name; //XXX string equalizations lol
		this.adjacencyList = adjacencyList;
	}
	//XXX:name?
	public WeightedGraph(LinkedList<Integer[]> edges)
	{
		LinkedList<LinkedList<Vertex>> adjLinkedList = 
				new LinkedList<LinkedList<Vertex>>();
		
		//finding the maximum number of edge[0] = source
		int max = -1;
		for (Integer[] edge : edges) {
			if (edge[0] > max)
				max = edge[0];
			if (edge[2] > max) {
				max = edge[2];
			}
		}
		for (int i = 0; i <= max; i++) {
			adjLinkedList.add(new LinkedList<WeightedGraph.Vertex>());
		}
		//making the adjacency list
		for (Integer[] edge : edges) {
			int source = edge[0];
			int weight = edge[1];
			int destination = edge[2];
			Vertex addedVertex = new Vertex(destination,weight);
			adjLinkedList.get(source).add(addedVertex);
			addedVertex = new Vertex(source,weight);
			adjLinkedList.get(destination).add(addedVertex);
		}
		
		this.adjacencyList = adjLinkedList;

	}
	
	public WeightedGraph()
	{
		//dummy constructor for allowing to make new instance of vertex objects
	}
	//Start of algorithms and methods:
	
	
	public int getVerticesCount() {
		return this.adjacencyList.size();
	}
	
	@Override
	public boolean isCyclic() {
	          
	        // Mark all the vertices as not visited and 
	        // not part of recursion stack 
		  
        // Mark all the vertices as  
        // not visited and not part of 
        // recursion stack 
        boolean visited[] = new boolean[this.getVerticesCount()]; 
        // Call the recursive helper  
        // function to detect cycle in 
        // different DFS trees 
        for (int i = 0; i < this.getVerticesCount(); i++) 
        {   
          
            // Don't recur for u if already visited 
            if (!visited[i])  
                if (hasCycle(i, visited, -1))
                    return true; 
        } 
  
        return false; 
	  
	}
	
	/**
	 * helper method for isCyclic method. Inspired by: GeeksForGeeks.com
	 * @param v
	 * @param visited
	 * @param parent
	 * @return
	 */
	private boolean hasCycle(int v, boolean visited[], int parent)
	{
		 visited[v] = true; 
	        Integer i; 
	  
	        // Recur for all the vertices  
	        // adjacent to this vertex 
	        Iterator<Vertex> it =  this.adjacencyList.get(v).iterator(); 
	        while (it.hasNext()) 
	        { 
	            i = it.next().getID(); 
	  
	            // If an adjacent is not  
	            // visited, then recur for that 
	            // adjacent 
	            if (!visited[i]) 
	            { 
	                if (hasCycle(i, visited, v)) 
	                    return true; 
	            } 
	  
	            // If an adjacent is visited  
	            // and not parent of current 
	            // vertex, then there is a cycle. 
	            else if (i != parent) 
	                return true; 
	        } 
	        return false; 
	}
	
	@Override
	public WeightedGraph getCopy() throws CloneNotSupportedException {
		String name = this.name;
		//A copy of adjacency list of the graph
		LinkedList<LinkedList<Vertex>> adjacencyCopy =
				new LinkedList<LinkedList<Vertex>>();
		for (LinkedList<Vertex> vertices : adjacencyList) {
			LinkedList<Vertex> edgesList = 
					new LinkedList<WeightedGraph.Vertex>();
			for (Vertex vertex : vertices) {
				//A new copy of vertex
				Vertex copyVertex = (Vertex) vertex.clone();
				edgesList.add(copyVertex);
				
			}
			adjacencyCopy.add(edgesList);
		}
		WeightedGraph copyGraph = new WeightedGraph(name, adjacencyCopy);
		return copyGraph;
	}
	
	@Override
	public LinkedList<Vertex> getEdgesByVertex(int connectedVertexID) {
		return this.adjacencyList.get(connectedVertexID);
	}
	@Override
	public Integer[] getEdge(int sourceID, int destinationID) {
		if (sourceID < 0 || sourceID > this.getVerticesCount()
			|| destinationID < 0 || destinationID > this.getVerticesCount())
			throw new IllegalArgumentException();
		//XXX: might throw illeal access bound error
		Integer[] edge = new Integer[3];
		edge[0] = sourceID;
		edge[2] = destinationID;
		for (Vertex vertex : this.adjacencyList.get(sourceID)) {
			if (vertex.getID() == destinationID)
				edge[1] = vertex.getWeight();
		}
		return edge;
	}
	
	@Override
	public boolean isConnected() {
		
		//if it's connected then all DFSs must have all the vertices
		LinkedList<Integer> visitedVertices = DFS(0, false);
		for (int i = 0; i < this.adjacencyList.size(); i++) {
			if (!visitedVertices.contains(i))
				return false;
		}
		return true;
	}
	
	@Override
	public LinkedList<Integer> DFS(int vertex, boolean doPrint) {
		boolean[] visited = new boolean[this.getVerticesCount()];
		//Visited vertices in the order of being visited
		//why linkedlist? to check the connectivity
		LinkedList<Integer> visitedVertices = new LinkedList<Integer>();
		DFSHelperMethod(vertex, visited, doPrint,visitedVertices);
		return visitedVertices;
		
	}
	
	/**
	 * The core of DFS algorithm.
	 * @param vertex
	 * @param visited
	 * @param doPrint if true then during traversal, visited vertices are printed.
	 */
	private void DFSHelperMethod(int vertex,boolean[] visited,boolean doPrint,LinkedList<Integer> visitedVertices)
	{
		visited[vertex] = true;
		if (doPrint)
			System.out.println("Visited: " + vertex);
		visitedVertices.add(vertex);
		for (int i = 0; i < this.adjacencyList.get(vertex).size(); i++) {
			int next = this.adjacencyList.get(vertex).get(i).getID();
			if (!visited[next])
				DFSHelperMethod(next, visited, doPrint,visitedVertices);
		}
	}
	
	private boolean allVisited(boolean visited[])
	{
		for (boolean b : visited) {
			if (b == false)
				return false;
		}
		return true;
	}
	
	private void sortEdges(LinkedList<Integer[]> edges)
	{
		//sorting the weights in increasing order
				edges.sort(new Comparator<Integer[]>() {

					@Override
					public int compare(Integer[] o1, Integer[] o2) {
						return o1[1] - o2[1];
					}
					
				});
		
	}
	@Override
	public void shortestPathByDijkstra(int sourceID) {
		
		//Step 1: initializing the sets
		boolean[] visitedVertices = new boolean[this.getVerticesCount()];
		int[] cost = new int[this.getVerticesCount()];
		for (int i = 0; i < cost.length; i++) {
			if (i != sourceID)
				cost[i] = Integer.MAX_VALUE;
		}
		cost[sourceID] = 0; //start from here
		
		LinkedList<LinkedList<Integer[]>> pathes =
				new LinkedList<LinkedList<Integer[]>>();
		for (int i = 0; i < this.getVerticesCount(); i++) {
			pathes.add(new LinkedList<Integer[]>());
		}
		int currentVertex = sourceID;
		//getting adjacent neighbours
		while (!allVisited(visitedVertices))
		{
			LinkedList<Integer[]> neighbourEdges = this.getEdgesOfAVertex(currentVertex, this);
			for (int i = 0; i < neighbourEdges.size(); i++) {
				Integer[] endPoint = neighbourEdges.get(i);
				if (visitedVertices[endPoint[2]])
					continue;
				int newCost = cost[endPoint[0]] + endPoint[1]; //previous cost + weight
				if (newCost < cost[endPoint[2]]) // new optimized path discovered
				{
					
					cost[endPoint[2]] = newCost;
					//setting the new path 
					LinkedList<Integer[]> aLinkedList = new LinkedList<Integer[]>(); //a copy of this
					//if i didn't make this copy the code below would not run correctly
					aLinkedList.addAll(pathes.get(endPoint[0]));
					pathes.set(endPoint[2], aLinkedList);
					pathes.get(endPoint[2]).add(endPoint);
					/*
					for (int k = 0; k < pathes.size(); k++) {
						System.out.println( k + " is:");
						for (int j = 0; j < pathes.get(k).size(); j++) {
							System.out.println("Source: " + pathes.get(k).get(j)[0]
									+ "Weight: " + pathes.get(k).get(j)[1] + "Destination: " + pathes.get(k).get(j)[2]);
						}
					}
					*/
					
				}
			}
			visitedVertices[currentVertex] = true;
			//choosing the new current vertex:
			sortEdges(neighbourEdges); //minimum weight
			for (int i = 0; i < neighbourEdges.size(); i++) {
				if (visitedVertices[neighbourEdges.get(i)[2]] == false)
				{
					currentVertex = neighbourEdges.get(i)[2];
					break;
				}
				if (i + 1 == neighbourEdges.size()
						&& allVisited(visitedVertices) == false)
				{
					for (int j = 0; j < visitedVertices.length; j++) {
						if (visitedVertices[j] == false)
							currentVertex = j;
					}
				}
			}
			
			
		}
		
		//printing
		for (int i = 0; i < pathes.size(); i++) {
			if (i == sourceID)
				continue;
			System.out.println("Shortest path from " + sourceID + " To " + i + " is:");
			for (int j = 0; j < pathes.get(i).size(); j++) {
				System.out.println("Source: " + pathes.get(i).get(j)[0]
						+ "Weight: " + pathes.get(i).get(j)[1] + "Destination: " + pathes.get(i).get(j)[2]);
			}
		}
		
		
	}
	@Override
	public LinkedList<Integer[]> getEdgesOfAVertex(int connectedVertexID, WeightedGraph graph) {

		LinkedList<Integer[]> edgesIntegers = new LinkedList<Integer[]>();
		for (int i = 0; i < graph.adjacencyList.get(connectedVertexID).size(); i++) {
			Integer[] edgeInteger = new Integer[3];
			edgeInteger[0] = connectedVertexID;
			edgeInteger[1] = graph.adjacencyList.get(connectedVertexID).get(i).getWeight();
			edgeInteger[2] = graph.adjacencyList.get(connectedVertexID).get(i).getID();
			edgesIntegers.add(edgeInteger);
		}
		return edgesIntegers;
	}
	

}
