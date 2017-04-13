
import java.util.ArrayList;

public class Graph<E>implements GraphMap<E> {
	private ArrayList<City<E>>graphList;
	private ArrayList<Edge<E>>target;
	
	/**
	 *graph constructor 
	 */
	public Graph(){
		graphList = new ArrayList<City<E>>();
		target = new ArrayList<Edge<E>>();
	}
	@Override
	public void addVertex(City<E> c) {
		if(!graphList.contains(c)){
			graphList.add(c);
		}
	}

	@Override
	public void addEdge(Edge<E> edge) {
		City<E> start = edge.getStart();
		int index = findCity(start);
		graphList.get(index).AddEdge(edge);
		
	}

	
	@Override
	public int distance(City<E> c1, City<E> c2) {
		int index = findCity(c1);
		int distance = graphList.get(index).getDistance(c2);
		return distance;
	}
	


	@Override
	public int NV() {
		return graphList.size();
	}
	
	
	
	/**
	 * 
	 * @return the completed status in double number for the whole trip to be finished
	 */

	/**
	 * 
	 * @return the arraylist of all target edges
	 */
	public ArrayList<Edge<E>> getTarget(){
		return target;
	}
	
	
	//given a city and find it from the list
	private int findCity(City<E> obj){
		for(int i = 0; i < graphList.size(); i++){
			if(graphList.get(i).cityEquals(obj)){
				return i;
			}
		}
		System.out.println("city not found, in graph findCity method");
		return -1;
	}
	
	public void showGraph(){
		System.out.println("show graph");
		int i;
		int size = graphList.size();
		System.out.println("there are "+size+" cities in the graph");
		for(i=0;i<size;i++){
			graphList.get(i).showConnections();
		}
		
		System.out.println("target edges are :");
		for(Edge<E> e:target){
			e.print();
		}
		System.out.println("finished--");
	}
	
	
	/**
	 * this method will return a city based on the name given
	 * @param name
	 * @return
	 */
	public City<E> city(String name){
		int index = -1;
		int i;
		int size = graphList.size();
		for(i=0;i<size;i++){
			if(graphList.get(i).getName().equals(name)){
				index = i;
			}
		}
		if(index == -1){
			System.out.println("Edge can't be built because city cant be found, in Graph city method");
			return null;
		}
		return graphList.get(index);
	}
	
	//debug
	public boolean checkLable(){
		int time = 0;
		int i;
		int lable = -1;
		int size = graphList.size();
		for(City<E> city:graphList){
			time = 0;
			lable = city.getLable();
			for(i=0;i<size;i++){
				if(graphList.get(i).getLable() == lable){
					time++;
				}
			}
			if(time > 1){
				System.out.println("lables are repeated");
				return false;
			}
		}
		System.out.println("lables are all fine");
		return true;
	}
	@Override
	public void mark(Edge<E> e) {
		target.add(e);
		
	}
	
	

}
