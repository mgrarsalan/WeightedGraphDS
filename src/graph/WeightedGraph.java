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
		if (doPrint)
			System.out.println("END");
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
			System.out.print(vertex + " -> ");
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
	
	@Override
	public boolean hasEdge(int firstVertexID, int secondVertexID) {
		LinkedList<Vertex> sourceLinkedList 
		= adjacencyList.get(firstVertexID);
		for (Vertex vertex : sourceLinkedList) {
			if (vertex.getID() == secondVertexID)
				return true;
		}
		return false;
	}
	
	@Override
	public void addVertex()
	{
		this.adjacencyList.add(new LinkedList<WeightedGraph.Vertex>());
	}
	
	@Override
	public void addEdge(int firstVertexID, int secondVertexID, int weight) {
		int size = getVerticesCount();
		if (firstVertexID > size || firstVertexID < 0 ||
				secondVertexID > size || secondVertexID < 0
				|| firstVertexID == secondVertexID || weight < 0)
			throw new IllegalArgumentException("Invalid Input");
		Vertex addedVertex = new Vertex(secondVertexID, weight);
		this.adjacencyList.get(firstVertexID).add(addedVertex);
		addedVertex = new Vertex(firstVertexID, weight);
		this.adjacencyList.get(secondVertexID).add(addedVertex);
		
	}
	
	@Override
	public int getEdgeCount() {
		// 2|E| 
		int sum = 0;
		for (LinkedList<Vertex> linkedList : adjacencyList) {
			for (Vertex vertex : linkedList) {
				sum++;
			}
		}
		return sum/2;
		
	}
	
	@Override
	public void removeEdge(int firstVertexID, int secondVertexID) {
		int size = getVerticesCount();
		if (firstVertexID > size || firstVertexID < 0 ||
				secondVertexID > size || secondVertexID < 0
				|| firstVertexID == secondVertexID)
			throw new IllegalArgumentException("Invalid Input");
		for (int i = 0; i < this.adjacencyList.get(firstVertexID).size(); i++) {
			if (this.adjacencyList.get(firstVertexID).get(i).getID() == secondVertexID)
				this.adjacencyList.get(firstVertexID).remove(i);
		}
		for (int i = 0; i < this.adjacencyList.get(secondVertexID).size(); i++) {
			if (this.adjacencyList.get(secondVertexID).get(i).getID() == firstVertexID)
				this.adjacencyList.get(secondVertexID).remove(i);
		}
		///XXX IT HAS CHANGED. CHECK THE OTHER METHODS
	}
	
	//till removeVertex
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	@Override
	public void printAdjacencyMatrix() {
		
		//creating the adjacency matrix out of adjacency list
		int size = this.getVerticesCount();
		int[][] adjacencyMatrix = new int[size][size];
		for (int i = 0; i < this.adjacencyList.size(); i++) {
			int firstIndex = i;
			for (int j = 0; j < this.adjacencyList.get(i).size(); j++) {
				int secondIndex = this.adjacencyList.get(i).get(j).getID();
				if (firstIndex != secondIndex)
				{
					int weight = this.adjacencyList.get(i).get(j).getWeight();
					adjacencyMatrix[firstIndex][secondIndex] = weight;
				}
			}
		}
		
		//printing:
		for (int i = 0; i < adjacencyMatrix.length; i++) {
			System.out.print("[");
			for (int j = 0; j < adjacencyMatrix.length; j++) {
				System.out.print(adjacencyMatrix[i][j] +
						((j + 1 == adjacencyMatrix.length) ? "" : ","));
			}
			System.out.println("]");
			
		}
	}
	@Override
	public void printAdjacencyList() {
		int i = 0;
		for (LinkedList<Vertex> linkedList : adjacencyList) {
			System.out.print("Vertex " + i++ + " -"); 
			for (Vertex vertex : linkedList) {
				System.out.print("(" + vertex.getWeight() +
						")-> "+ vertex.getID() + " -");
			}
			System.out.println("-> END");
		}
	}
	@Override
	public void BFS(int startingvertex, boolean print) {
		boolean[] visited = new boolean[this.getVerticesCount()];
		
		//though it's a linkedlist, it functions as a queue data structure.
		LinkedList<Integer> queue = new LinkedList<Integer>();
		
		visited[startingvertex] = true;
		queue.add(startingvertex);
		
		while (queue.size() != 0)
		{
			startingvertex = queue.poll();
			if (print)
				System.out.print(startingvertex + " -> ");
			Iterator<WeightedGraph.Vertex> i = this.adjacencyList.get(startingvertex).listIterator();
            while (i.hasNext())
            {
                int n = i.next().getID();
                if (!visited[n])
                {
                    visited[n] = true;
                    queue.add(n);
                }
            }
		}
		
		if (print)
			System.out.println("END");
	}
	@Override
	public WeightedGraph MSTbyKruskal() {
		
		if (!this.isConnected())
		{
			System.out.println("No MST for this graph were found using Kruskal algorithm.");
			return null;
		}
		
		LinkedList<Integer[]> edges = this.getEdgesList(this.adjacencyList); //get all edges
		//removing duplicates edges for example [1,2,3] is the same as [3,2,1]
		
		for (int i = 0; i < edges.size() - 1; i++) {
			for (int j = i + 1; j < edges.size(); j++) {
				if (edges.get(i)[0] == edges.get(j)[2] &&
						edges.get(i)[2] == edges.get(j)[0])
					edges.remove(j);
			}
		}

		/*for (int i = 0; i < edges.size(); i++) {
    		System.out.println("Source: " + edges.get(i)[0]
    				+ "Weight: " + edges.get(i)[1] + "Destination: " + edges.get(i)[2]);
    	}
		*/
		//sorting the weights in increasing order
		LinkedList<Integer[]> sortedEdges = new LinkedList<Integer[]>();
		edges.sort(new Comparator<Integer[]>() {

			@Override
			public int compare(Integer[] o1, Integer[] o2) {
				return o1[1] - o2[1];
			}
			
		});
		LinkedList<Integer[]> path = new LinkedList<Integer[]>();
		
		for (int i = 0; i < edges.size(); i++) {
			path.add(edges.get(i));
			WeightedGraph connectedGraph = new WeightedGraph(path);
			if (connectedGraph.isCyclic())
			{
				path.removeLast();
				continue;
			}
			
		}
		
		WeightedGraph mst = new WeightedGraph(path);
		System.out.println("A MST was found for this graph. "
				+ "Printing the adjacency list of the MST:");
			
		mst.printAdjacencyList();
		return mst;
		
	}
	@Override
	public LinkedList<Integer[]> getEdgesList(LinkedList<LinkedList<Vertex>> sourceList) {
		
		LinkedList<Integer[]> edgesList = new LinkedList<Integer[]>();
		
		for (int source = 0; source < sourceList.size(); source++) {
			for (int destination = 0; destination < sourceList.get(source).size(); destination++) {
				Integer[] edge = new Integer[3];
				edge[0] = source;
				edge[1] = sourceList.get(source).get(destination).getWeight();
				edge[2] = sourceList.get(source).get(destination).getID();
				edgesList.add(edge);
			}
		}
		
		return edgesList;
	}
	
	@Override
	public WeightedGraph MSTbyPrim() {
		/*
		 * MST by prim algorithm.
		 * Based on https://visualgo.net/en/mst
		 */
		
		if (!this.isConnected())
		{
			System.out.println("No MST for this graph were found using Kruskal algorithm.");
			return null;
		}
		
		boolean[] addedVertices = new boolean[this.getVerticesCount()]; //T
		addedVertices[0] = true; //initialize the T
		/*
		 * [source vertex,weight,destination vertex]
		 */
		LinkedList<LinkedList<Vertex>> MST = new LinkedList<LinkedList<Vertex>>();
		for (int i = 0; i < this.adjacencyList.size(); i++) {
			MST.add((new LinkedList<WeightedGraph.Vertex>()));
		}
		
		LinkedList<Integer[]> PQ = new LinkedList<Integer[]>();
		//initializing PQ
		for (int i = 0; i < this.adjacencyList.get(0).size(); i++) {
			Integer[] edge = new Integer[3];
			edge[0] = 0;
			edge[1] = this.adjacencyList.get(0).get(i).getWeight();
			edge[2] = this.adjacencyList.get(0).get(i).getID();
			PQ.add(edge);
		}
		
		while(!PQ.isEmpty())
		{
			//finding the minimum weighted edge
			int minWeight = Integer.MAX_VALUE;
			int minIndex = -1;
			for (int i = 0; i < PQ.size(); i++) {
				if (PQ.get(i)[1] < minWeight)
				{
					minWeight = PQ.get(i)[1];
					minIndex = i;
				}
			}
			Integer edge[] = PQ.get(minIndex);
			PQ.remove(minIndex);
			if (minIndex == -1 || addedVertices[edge[2]] == true)
				continue;
			//
			int source = edge[0];
			int weight = edge[1];
			int destination = edge[2];
			addedVertices[destination] = true;
			Vertex minVertex = new Vertex(destination,weight);
			MST.get(source).add(minVertex);
			MST.get(destination).add((new Vertex(source, weight)));
			
			//updating PQ
			for (int i = 0; i < this.adjacencyList.get(destination).size(); i++) {
				Integer[] addEdge = new Integer[3];
				addEdge[0] = destination;
				addEdge[1] = this.adjacencyList.get(destination).get(i).getWeight();
				addEdge[2] = this.adjacencyList.get(destination).get(i).getID();
				PQ.add(addEdge);
			}
		}
		/*
		int i = 0;
		System.out.println("SSSSSSSSSSS");
		for (LinkedList<Vertex> linkedList : MST) {
			System.out.print("Vetex " + i++ + " -"); 
			for (Vertex vertex : linkedList) {
				System.out.print("(" + vertex.getWeight() +
						")-> "+ vertex.getID() + " -");
			}
			System.out.println("-> END");
		}
		*/
		
		//printing:
		WeightedGraph mstGraph = new WeightedGraph("mst",MST);
		System.out.println("A MST was found for this graph. "
				+ "Printing the adjacency list of the MST:");
		mstGraph.printAdjacencyList();
		return mstGraph;
		
		
	}
	@Override
	public WeightedGraph MSTbySollin() {
		
		if (!this.isConnected())
		{
			System.out.println("No MST for this graph were found using Kruskal algorithm.");
			return null;
		}
		
		
		LinkedList<LinkedList<Integer[]>> components =
				new LinkedList<LinkedList<Integer[]>>();
		for (int i = 0; i < this.getVerticesCount(); i++) {
			//initializing components with minimum edge of each vertex
			components.add(new LinkedList<Integer[]>());
			Vertex minVertex =  minimumWeight(i);
			Integer[] mInteger = new Integer[3];
			mInteger[0] = i;
			mInteger[1] = minVertex.getWeight();
			mInteger[2] = minVertex.getID();
			components.get(i).add(mInteger);
		}
		
		//removing duplicates
		for (int i = 0; i < components.size() - 1; i++) {
			Integer[] firstInteger = components.get(i).get(0);
			for (int j = i + 1; j < components.size(); j++) {
				Integer[] secondInteger = components.get(j).get(0);
				if (firstInteger[0] == secondInteger[2] &&
						firstInteger[2] == secondInteger[0] && 
						firstInteger[1] == secondInteger[1])
					components.remove(j);
				
			}
		}
		
		//connecting edges that have a shared end

		while (true)
		{
			boolean linkFound = false;
			boolean refresh = false;
			for (int firstCindex = 0; !refresh &&  firstCindex < components.size() - 1; firstCindex++) {
				
				for (int secondCIndex = firstCindex + 1; !refresh &&  secondCIndex < components.size(); secondCIndex++) {
					
					for (int firstEindex = 0; !refresh &&  firstEindex < components.get(firstCindex).size(); firstEindex++) {
						Integer[] firstEdge = components.get(firstCindex).get(firstEindex);
						for (int secondEindex = 0; !refresh &&  secondEindex <  components.get(secondCIndex).size(); secondEindex++) {
							
							Integer[] secondEdge = components.get(secondCIndex).get(secondEindex);
							
							if (firstEdge[0] == secondEdge[2] ||
									firstEdge[2] == secondEdge[0])
							{
								//making a copy of second component
								LinkedList<Integer[]> copyLinkedList = new LinkedList<Integer[]>();
								copyLinkedList.addAll(components.get(secondCIndex));
								components.get(firstCindex).addAll(copyLinkedList);
								components.remove(secondCIndex);
								linkFound = true;
								refresh = true;
								break;
							}
							
							
						}
						
					}
					
				}
				
			}
			if (linkFound == false)
				break;
		}
		/*
		System.out.println(components.size());
		for (int i = 0; i < components.size(); i++) {
			System.out.println("i : " + i);
			for (int k = 0; k < components.get(i).size(); k++) {
				System.out.println("Source: " + components.get(i).get(k)[0]
						+ "Weight: " + components.get(i).get(k)[1] + "Destination: " + components.get(i).get(k)[2]);
			}
			
		}
		*/
		
		//Finding links and connecting them
		LinkedList<Integer[]> allEdges = getEdgesList(this.adjacencyList);
		while(components.size() != 1)
		{
			boolean refresh = false;
			for (int firstCindex = 0;!refresh && firstCindex < components.size() - 1 ;firstCindex++) {
				int minWeight = Integer.MAX_VALUE;
				Integer[] minEdge = null;
				int minComponent = -1;
				for (int secondCIndex = firstCindex + 1; !refresh &&  secondCIndex < components.size(); secondCIndex++) {
					
					for (int firstEindex = 0;!refresh &&  firstEindex < components.get(firstCindex).size(); firstEindex++) {
						Integer[] firstEdge = components.get(firstCindex).get(firstEindex);
						for (int secondEindex = 0;!refresh &&  secondEindex < components.get(secondCIndex).size(); secondEindex++) {
							
							Integer[] secondEdge = components.get(secondCIndex).get(secondEindex);
							for (int i = 0; i < allEdges.size(); i++) {
								if (allEdges.get(i)[0] == firstEdge[0] &&
										allEdges.get(i)[2] == secondEdge[2])
								{
									
									//link found!
									Integer[] foundLink = new Integer[3];
									foundLink[0] = allEdges.get(i)[0];
									foundLink[1] = allEdges.get(i)[1];
									foundLink[2] = allEdges.get(i)[2];
									if (foundLink[1] < minWeight)
									{
										// new min weight edge
										minWeight = foundLink[1];
										minEdge = foundLink;
										minComponent = secondCIndex;
									}
									
								}
							}
						}
					}
				}
				//choosing the min edge
				LinkedList<Integer[]> copyLinkedList =
						new LinkedList<Integer[]>();
				copyLinkedList.addAll(components.get(minComponent));
				components.get(firstCindex).addAll(copyLinkedList);
				components.get(firstCindex).add(minEdge);
				components.remove(minComponent);
				refresh = true;
				break;
				
			}
		}
		
		//printing the mst:
		
		WeightedGraph mstGraph = new WeightedGraph(components.get(0));
		System.out.println("A MST was found for this graph. "
				+ "Printing the adjacency list of the MST:");
		mstGraph.printAdjacencyList();
		return mstGraph;
		
		
	}
	
	//helper method used by sollin algorithm:
	/**
	 * @param vertexID the ID of the vertex that is connected to the edge
	 * @return the minimum edge connected to a vertex
	 */
	private Vertex minimumWeight(int vertexID)
	{
		int minWeight = Integer.MAX_VALUE;
		int minIndex = -1;
		for (int i = 0; i < this.adjacencyList.get(vertexID).size(); i++) {
			if (this.adjacencyList.get(vertexID).get(i).getWeight()
					< minWeight)
			{
				minWeight = this.adjacencyList.get(vertexID).get(i).getWeight();
				minIndex = i;
			}
		}
		if (minIndex == -1)
			return null;
		return this.adjacencyList.get(vertexID).get(minIndex);
	}
	
	@Override
	public LinkedList<Integer[]> eulerianPath() throws CloneNotSupportedException {
		
		if (!isEulerian() || !isConnected())
		{
			System.out.println("No eulerian path on this graph is possible");
			return null;
		}
		System.out.println("Found an Eulerian path! Eulerian path in order that edges must be vistied:");
		//Choosing the starting vertex
		int startingVertex = 0;
		for (int i = 0; i < this.adjacencyList.size(); i++) {
			if (getDegree(i) % 2 != 0)
				startingVertex = i;
			//break;
		}
		//Unless it would be 0
		LinkedList<Integer[]> path = new LinkedList<Integer[]>();
		//getting a copy of graph
		WeightedGraph eulerGraph = getCopy();
		
		LinkedList<Integer[]> edges = getEdgesList(adjacencyList);
		//
		//LinkedList<Integer[]> edgesOfVertex = getEdgesOfAVertex(startingVertex, eulerGraph);
		/*
		for (int i = 0; i < edgesOfVertex.size(); i++) {
			System.out.println("Source: " + edgesOfVertex.get(i)[0]
			+ "Weight: " + edgesOfVertex.get(i)[1] + "Destination: " + edgesOfVertex.get(i)[2]);
		}
		*/
		
		while (edges.size() != 0) ///!edges.isEmpty()
		{
			//choosing the edge
			LinkedList<Integer[]> edgesOfVertex = getEdgesOfAVertex(startingVertex, eulerGraph);
			for (int i = 0; i < edgesOfVertex.size(); i++) {
				WeightedGraph connectGraph = this.getCopy();
				connectGraph.removeEdge(startingVertex, edgesOfVertex.get(i)[2]);
				if (connectGraph.isConnected() || edgesOfVertex.size() == 1) //if it DOES NOT break the connectivity
				{
					
					eulerGraph.removeEdge(startingVertex, edgesOfVertex.get(i)[2]);
					edges = eulerGraph.getEdgesList(eulerGraph.adjacencyList); //updating the edge array
					path.add(edgesOfVertex.get(i));
					System.out.println("Source: " + edgesOfVertex.get(i)[0]
							+ " Weight: " + edgesOfVertex.get(i)[1] + " Destination: " + edgesOfVertex.get(i)[2]);
					startingVertex = edgesOfVertex.get(i)[2];
					break;
				}
			}
		}
		
		
		
		return path;
	}
	/**
	 * @return True if an Eulerian path is possible in the graph
	 * An Eulerian path is possible iff 
	 * exactly zero or two vertices have odd degree,
	 * and all of its vertices with nonzero degree belong to a single connected component.
	 * (source: wikipedia)
	 */
	private boolean isEulerian()
	{
		//TODO:is connected
		int numberOfOddVertices = 0;
		for (int i = 0; i < this.adjacencyList.size(); i++) {
			if (this.getDegree(i) % 2 != 0)
				numberOfOddVertices++;
		}
		return (numberOfOddVertices == 0) || (numberOfOddVertices == 2);
		
	}
	
	@Override
	public int getDegree(int vertexID) {
		return this.adjacencyList.get(vertexID).size();
	}
	
	@Override
	public LinkedList<Integer[]> hamiltonianCycle() {
		LinkedList<Integer[]> path = new LinkedList<Integer[]>();
		LinkedList<Integer> vertices = new LinkedList<Integer>();
		for (int i = 0; i < this.getVerticesCount(); i++) {
			hamilton(path,vertices,false,i);
		}
		
		//adding the last edge to make it a cycle
		Integer[] cycleEdge = null;
		LinkedList<Integer[]> edges = this.getEdgesList(adjacencyList);
		for (int i = 0; i < edges.size(); i++) {
			if (edges.get(i)[0] == path.peekLast()[2]
					&& edges.get(i)[2] == path.peekFirst()[0]) //found the Link!
						cycleEdge = edges.get(i);
		}
		if (vertices.size() != this.getVerticesCount() ||
				this.isConnected() == false || cycleEdge == null)
		{
			System.out.println("No hamiltonian Cycle were found on this graph.");
			return null;
		}
		
		
		System.out.println("A hamiltonian cycle was found: ");
		for (int i = 0; i < path.size(); i++) {
			System.out.println("Source: " + path.get(i)[0]
					+ " Weight: " + path.get(i)[1] + " Destination: " + path.get(i)[2]);
		}
		
		return path;
	}
	
	/**
	 * Helper method for finding a Hamiltonian cycle.
	 * @param Path
	 * @param vertices
	 * @param control
	 * @param startingVertex
	 */
	private void hamilton(LinkedList<Integer[]> Path,LinkedList<Integer> vertices,boolean control,int startingVertex)
	{
		if (vertices.size() == this.getVerticesCount() || control) //then it has all the vertices
			return;
		else {
			//
			int currentVertex;
			if (Path.isEmpty())
				currentVertex = startingVertex;
			else
				currentVertex = Path.peekLast()[2];//last vertex destination
			if (vertices.contains(currentVertex)) // a duplicate vertex
			{
				control = true;
				return;
			}
			vertices.add(currentVertex);
			//choosing edge:
			LinkedList<Integer[]> edges = getEdgesOfAVertex(currentVertex, this);
			for (int i = 0; i < edges.size(); i++) {
				if (edges.get(i) != null 
						&& vertices.contains(edges.get(i)[2])) // invalid edge
					continue;
				if (edges.get(i) == null && i + 1 == edges.size()) //not a valid edge found
					return;
				Path.add(edges.get(i));
				//vertices.add(edges.get(i)[2]);
				hamilton(Path, vertices, control,startingVertex);
				if (vertices.size() == this.getVerticesCount() || control) //then it has all the vertices
					return;
				
				///XXX:
				Path.removeLast();
				vertices.removeLast();
			}
		}
		
	}
	
	@Override
	public LinkedList<LinkedList<Integer[]>> ShoretestPathbyDijkstra(int sourceID) {
		
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
					
					int minDistance = Integer.MAX_VALUE;
					int minIndex = -1;
					for (int i = 0; i < visitedVertices.length; i++) {
						if (visitedVertices[i] == false 
								&& cost[i] < minDistance)
						{
							minDistance = cost[i];
							minIndex = i;
						}
					}
					currentVertex = minIndex;
					/*
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
					*/
					
					
				}
				
				//printing
				for (int i = 0; i < pathes.size(); i++) {
					if (i == sourceID)
						continue;
					System.out.println("Shortest path from " + sourceID + " To " + i + " is:");
					for (int j = 0; j < pathes.get(i).size(); j++) {
						System.out.println("Source: " + pathes.get(i).get(j)[0]
								+ " Weight: " + pathes.get(i).get(j)[1] + " Destination: " + pathes.get(i).get(j)[2]);
					}
					System.out.println();
				}
				
				return pathes;
		
	}
}
