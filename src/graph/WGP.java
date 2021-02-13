/**
 * 
 */
package graph;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.jdom2.JDOMException;

/**
 * Menu and the start of the program
 * @author Arsalan Jafari
 *
 */
public class WGP {
	
	public static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		
		//TEMP:
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
		//TEMP:
		boolean quit = false;
		while (!quit)
		{
			System.out.println("Welcome to the weighted graph processor program."
					+ "\nChoose one of the following options:\n"
					+ "Enter 1 for Loading a graph from a file\n"
					+ "Enter 2 for Creating a new graph\n"
					+ "Enter Q for exiting the program");
			try {
				char choosed = input.next().charAt(0);
				switch (choosed) {
				case '1':
					loadingGraph();
					break;
				case '2':
					//create
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
		String address = input.next();
		try {
			WeightedGraph graph = GraphFile.loadGraph(address);
			if (graph == null)
				throw new Exception("Invalid file or an Empty graph.");
			System.out.println(graph.getVerticesCount());
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
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
			System.out.println("Name of the graph: " + graph.getName() + 
					"Number of vertices: " + graph.getVerticesCount() + 
					"Number of edges: " + graph.getEdgeCount());
			
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
					+ "using Dijkstra algorithm. ");
			
			char in = input.next().charAt(0);
			
			switch (in) {
			case '1':
				System.out.println("Adjacency matrix of the graph: ");
				graph.printAdjacencyMatrix();
				break;
				
			case '2':
				System.out.println("Adjacency list of the graph:"
						+ " (The number in parentheses refres to the weight of that edge");
			graph.printAdjacencyList();
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
				break;
				
			case '5':
				graph.MSTbyKruskal();
				break;
				
			case '6':
				graph.MSTbyPrim();
				break;
				
			case '7':
				graph.MSTbyKruskal();
				break;
				
			case '8':
				graph.eulerianPath();
				break;
				
			case '9':
				graph.hamiltonianCycle();
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
				break;
				
			default:
				break;
			}
			
			//System.out.println("Press Enter to continue");
			//input.next();
		}
		
	}

}
