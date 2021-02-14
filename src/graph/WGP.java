/**
 * 
 */
package graph;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

import org.jdom2.JDOMException;

import graph.WeightedGraph.Vertex;

/**
 * Menu and the start of the program
 * @author Arsalan Jafari
 *
 */
public class WGP {
	
	public static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		
		/*TEMP:
		String address = "sollinTest.xml";
		try {
			WeightedGraph graph = GraphFile.loadGraph(address);
			if (graph == null)
				throw new Exception("Invalid file or an Empty graph.");
			//int a = graph.getEdge(2, 3)[1];
 			//System.out.println(graph.isConnected());
			//graph.MSTbyKruskal();
			//graph.hamiltonianCycle();
			graph.ShoretestPathbyDijkstra(0);
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			//System.exit(1);
		}
		*/
		//TEMP:
		boolean quit = false;
		while (!quit)
		{
			System.out.println("Welcome to the weighted graph processor program."
					+ "\nChoose one of the following options:\n"
					+ "Enter 1 for Loading a graph from a file.\n"
					+ "Enter 2 for Creating a new graph.\n"
					+ "Enter Q for exiting the program.");
			try {
				char choosed = input.next().charAt(0);
				switch (choosed) {
				case '1':
					loadingGraph();
					break;
				case '2':
					input.nextLine();
					System.out.print("\nEnter a name for your graph:");
					String name = input.nextLine();
					LinkedList<LinkedList<Vertex>> zeroLinkedList = 
							new LinkedList<LinkedList<Vertex>>();
					zeroLinkedList.add(new LinkedList<WeightedGraph.Vertex>());
					WeightedGraph graph = new WeightedGraph(name, zeroLinkedList);
					modifyGraphMenu(graph);
					break;
					
				case 'q':
				case 'Q':
					quittingProgram();
					break;
				default:
					System.out.println("Invalid input. please try again.");
					break;
				}
				
			} catch (Exception e) {
				
			}
			
		}
		
	}
	
	public static void loadingGraph()
	{
		System.out.println("Please enter the (relative) address of a graph XML file.\n"
				+ "You can use the pre-written documents such as \"primTest.xml\" too.");
		input.nextLine();
		String address = input.nextLine();
		try {
			WeightedGraph graph = GraphFile.loadGraph(address);
			if (graph == null)
				throw new Exception();
			graphMenu(graph);
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Invalid file or an Empty graph. Please try again.");
			//System.exit(1);
		}
	}
	
	public static void quittingProgram()
	{
		/*
		System.out.println("Do you really want to exit the program?\n"
				+ "Enter 1 for YES and 0 for No");
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		*/
		
		System.exit(0);
	}
	
	/** 
	 * Main menu of graph algorithms
	 * @param graph
	 * @throws CloneNotSupportedException 
	 */
	public static void graphMenu(WeightedGraph graph) throws CloneNotSupportedException
	{
		boolean quit = false;
		while(!quit)
		{
			input = new Scanner(System.in);
			System.out.println("Name of the graph: " + graph.getName() + 
					" Number of vertices: " + graph.getVerticesCount() + 
					" Number of edges: " + graph.getEdgeCount());
			
			System.out.println("Enter 1 for printing the adjacency matrix."
					+ "\nEnter 2 for printing the adjacency list."
					+ "\nEnter 3 for printing DFS of the graph."
					+ "\nEnter 4 for printing BFS of the graph."
					+ "\nEnter 5 for printing a MST using Kruskal algorithm."
					+ "\nEnter 6 for printing a MST using Prim algorithm."
					+ "\nEnter 7 for printing a MST using sollin (Boruvka) algorithm."
					+ "\nEnter 8 for finding and printing an Eulerian path."
					+ "\nEnter 9 for finding and printing a Hamiltonian cycle."
					+ "\nEnter D for finding and printing the shortest distance between a vertex to others"
					+ "using Dijkstra algorithm."
					+ "\nEnter C for modifying the graph."
					+ "\nEnter B for getting back to previous menu."
					+ "\nEnter Q for exiting the program.");
			
			char in = input.next().charAt(0);
			
			switch (in) {
			case '1':
				System.out.println("Adjacency matrix of the graph: ");
				graph.printAdjacencyMatrix();
				nextMenu();
				break;
				
			case '2':
				System.out.println("Adjacency list of the graph:"
						+ " (The number in parentheses refres to the weight of that edge");
			graph.printAdjacencyList();
			nextMenu();
			break;
			
			case '3':
				int startingVertex = 0;
				while (true)
				{
					System.out.println("Enter the starting vertex for DFS:");
					try {
						startingVertex = Integer.parseInt(input.next());
						break;
					} catch (InputMismatchException e) {
						System.out.println("Invalid input. Please try again.");
					}
				}
				System.out.println("DFS of the graph in order which vertices are visited,"
						+ " starting from" + startingVertex);
				graph.DFS(startingVertex, true);
				nextMenu();
				break;
				
			case '4':
				int startingVertexBFS = 0;
				while (true)
				{
					System.out.println("Enter the starting vertex for BFS:");
					try {
						startingVertexBFS = Integer.parseInt(input.next());
						break;
					} catch (InputMismatchException e) {
						System.out.println("Invalid input. Please try again.");
					}
				}
				System.out.println("BFS of the graph in order which vertices are visited,"
						+ " starting from" + startingVertexBFS);
				graph.BFS(startingVertexBFS, true);
				nextMenu();
				break;
				
			case '5':
				graph.MSTbyKruskal();
				nextMenu();
				break;
				
			case '6':
				graph.MSTbyPrim();
				nextMenu();
				break;
				
			case '7':
				graph.MSTbyKruskal();
				nextMenu();
				break;
				
			case '8':
				graph.eulerianPath();
				nextMenu();
				break;
				
			case '9':
				graph.hamiltonianCycle();
				nextMenu();
				break;
			case 'C':
			case 'c':
				modifyGraphMenu(graph);
				break;
 			case 'D':
			case 'd':
				int dijkstraStartingPoint = 0;
				while (true)
				{
					System.out.println("Enter the starting vertex for dijkstra algorithm:");
					try {
						dijkstraStartingPoint = Integer.parseInt(input.next());
						break;
					} catch (InputMismatchException e) {
						System.out.println("Invalid input. Please try again.");
					}
				}
				System.out.println("BFS of the graph in order which vertices are visited,"
						+ " starting from" + dijkstraStartingPoint);
				graph.ShoretestPathbyDijkstra(dijkstraStartingPoint);
				nextMenu();
				break;
				
			case 'B':
			case 'b':
				quit = true;
				break;
				
			case 'Q':
			case 'q':
				System.exit(0);
				break;
				
			default:
				System.out.println("Invalid input. please try again");
				
				break;
				
				
			}
			
			
			//System.out.println("Press Enter to continue");
			//input.next();
		}
		
	}
	
	private static void nextMenu()
	{
		System.out.println("Enter any key to continue.");
		input.nextLine();
		input.nextLine();
	}
	
	private static void modifyGraphMenu(WeightedGraph originalGraph) throws CloneNotSupportedException
	{
		boolean quit = false;
		//making a copy of original graph
		WeightedGraph graph = originalGraph.getCopy();
		while(!quit)
		{
			System.out.println("Name of the graph: " + graph.getName() + 
					" Number of vertices: " + graph.getVerticesCount() + 
					" Number of edges: " + graph.getEdgeCount());
			
			System.out.println("Enter 1 for adding a new vertex."
					+ "\nEnter 2 for removing a vertex."
					+ "\nEnter 3 for adding a new edge."
					+ "\nEnter 4 for removing an edge."
					+ "\nEnter S for Saving the graph."
					+ "\nEnter B for getting back to previous menu."
					+ "\nEnter Q for exiting the program.");
			
			char in = input.next().charAt(0);
			
			switch (in) {
			case '1':
				System.out.println("Vertex with the ID : " + graph.getVerticesCount() + " is added.");
				graph.addVertex();
				nextMenu();
				break;
				
			case '2':
				System.out.print("Enter the ID of the vertex you want to be removed:");
				int id;
				while (true)
				{
					try {
						id = Integer.parseInt(input.next());
						graph.removeVertex(id);
						break;
					} catch (InputMismatchException e) {
						System.out.println("Invalid input. Please try again.");
					}
				}
			nextMenu();
			break;
			
			case '3':
				while (true)
				{
					int source,weight, dest;
					try {
						System.out.print("Enter the source vertex ID of the new edge:");
						source = Integer.parseInt(input.next());
						System.out.print("\nEnter the destination vertex of the new edge:");
						dest = Integer.parseInt(input.next());
						System.out.print("\nEnter the weight of the new edge:");
						weight = Integer.parseInt(input.next());
						if (weight < 0)
							throw new IllegalArgumentException();
						graph.addEdge(source, dest, weight);
						System.out.println("Edge was added!");
						nextMenu();
						break;
					} catch (InputMismatchException | IllegalArgumentException e) {
						System.out.println("Invalid input. Please try again.");
					}
				}
				break;
				
			case '4':
				while (true)
				{
					int source,weight, dest;
					try {
						System.out.print("Enter the source vertex ID of the edge you want to be removed:");
						source = Integer.parseInt(input.next());
						System.out.print("\nEnter the destination vertex of the edge you want to be removed");
						dest = Integer.parseInt(input.next());
						graph.removeEdge(source, dest);
						System.out.println("Edge was removed!");
						nextMenu();
						break;
					} catch (InputMismatchException | IllegalArgumentException e) {
						System.out.println("Invalid input. Please try again.");
					}
				}
				break;
			case 'S':
			case 's':
				System.out.println("Enter a valid address for the modified graph:");
				input.nextLine();
				String address = input.nextLine();
				System.out.println("Enter a valid name for the modified graph:");
				input.nextLine();
				String name = input.nextLine();
				
				
				try {
					GraphFile.saveGraph(graph, address,name);
				} catch (IOException e) {
					System.out.println("I/O ERROR: PLEASE TRY ANOTHER FILE");
					System.exit(1);
				}
				System.out.println("Graph was saved! Enter any key to continue");
				input.nextLine();
				break;
				
			case 'B':
			case 'b':
				quit = true;
				break;
				
			case 'Q':
			case 'q':
				System.exit(0);
				break;
				
			default:
				System.out.println("Invalid input. please try again");
				
				break;
				
				
			}
			
		}
	}

}
