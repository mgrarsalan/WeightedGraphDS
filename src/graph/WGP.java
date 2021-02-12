/**
 * 
 */
package graph;

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
		String address = "primTest.xml";
		try {
			WeightedGraph graph = GraphFile.loadGraph(address);
			if (graph == null)
				throw new Exception("Invalid file or an Empty graph.");
			//int a = graph.getEdge(2, 3)[1];
 			//System.out.println(graph.isConnected());
 			graph.shortestPathByDijkstra(0);
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

}
