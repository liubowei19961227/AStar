
public interface GraphMap<E>{
	//add vertex
	public void addVertex(City<E> c);
	
	//add edge
	public void addEdge(Edge<E> edge);
	

	public void mark(Edge<E>e);
	
	//get distance between two vertices
	public int distance(City<E> c1,City<E> c2);
	
	//num of vertices
	public int NV();
	
}
