import java.util.*;

public class SingleSourceSortestPath
{
	int V;
	int INT_MAX = Integer.MAX_VALUE;
	
	class Node implements Comparable<Node>
	{
		int node, cost;
		public Node(int n, int c)
		{
			node = n;
			cost = c;
		}
		
		public int compareTo(Node a)
		{
			return this.cost - a.cost;
		}	
	}
	
	HashSet<Integer> settled;
	ArrayList<ArrayList<Node>> graph;
	PriorityQueue<Node> queue;
	int[] dist;
	
	public SingleSourceSortestPath(int v) {
		this.V = v;
		dist = new int[V];
		settled = new HashSet<>();
		queue = new PriorityQueue<>();
	}
	
	void dijkstra(ArrayList<ArrayList<Node>> graph, int src) {
	    
	    this.graph = graph;
	    
		Arrays.fill(dist, INT_MAX);
		dist[src] = 0;
		queue.add(new Node(src, 0));
		
		while(settled.size() != V)
		{
			int u = queue.poll().node;
			
			settled.add(u); 		// mark as finalized
			
			visitNeighbors(u);
		}
	}
	
	void visitNeighbors(int u){
		
		for(Node adj : graph.get(u)){
			
			int v = adj.node;
			int cost = adj.cost;
			
			if(!settled.contains(v)) {
				
				if(dist[v] > dist[u] + cost)
					dist[v] = dist[u] + cost;
					
				queue.add(new Node(v, dist[v]));
			}
		}
	}
	
	public static void main(String[] args)
	{
		int V = 4;
		int src = 0;
		
		SingleSourceSortestPath sssp = new SingleSourceSortestPath(V);
		
		ArrayList<ArrayList<Node>> list = new ArrayList<>();
		
		// Initialize list for every node 
		for(int i = 0; i < V; i++) {
			list.add(new ArrayList<Node>());
		}
		
		// Add Edges
		sssp.addEdge(list, 0, 1, 10);
		sssp.addEdge(list, 0, 2, 3);
		sssp.addEdge(list, 1, 2, 5);
		sssp.addEdge(list, 1, 3, 3);
		sssp.addEdge(list, 2, 3, 2);
		
		sssp.dijkstra(list, src);
		
		// Print distances
		for(int v = 0; v < V; v++) {
			System.out.println(v +"  -  " + sssp.dist[v]);
		}
		
	}
	
	void addEdge(ArrayList<ArrayList<Node>> list, int u, int v, int cost) {
		
		list.get(u).add(new Node(v, cost));
		
		// For directed graph comment next line
		list.get(v).add(new Node(u, cost));
	}
}