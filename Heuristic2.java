import java.util.ArrayList;
import java.util.PriorityQueue;

public class Heuristic2 <E> implements HeuristicOperation<E>{

	@Override
	public int HeuristicCalculation(Graph<E> g, State<E> state, City<E> to,ArrayList<Edge<E>> target) {
		City<E> from = state.frontier();
		ArrayList<Edge<E>>uncomplete = uncomplete(g,from,to,state);
		if(uncomplete.size() == 0){
			return 0;
		}
		ArrayList<City<E>>nodes = MSTnodes(uncomplete);
		ArrayList<Edge<E>>mst = MST(nodes,g,state);
		int dist = 0;
		for(Edge<E>e:mst){
			dist += e.distance() + e.getStart().getTransferTime();
		}
		return dist;
	}
	
	public ArrayList<Edge<E>>uncomplete(Graph<E> g,City<E> from,City<E> to,State<E>state){
		ArrayList<Edge<E>>uncomplete = new ArrayList<Edge<E>>();
		for(Edge<E> e: state.getUncompleted()){
				uncomplete.add(e);
		}
		Edge<E> e = new Edge<E>(from,to,g.distance(from, to));
		int flag = -1;
		for(int i = 0;i<uncomplete.size();i++){
			if(e.edgeEquals(uncomplete.get(i))){
				flag = i;
			}
		}
		if(flag != -1){
			uncomplete.remove(flag);
		}
		return uncomplete;
	}
	
	private ArrayList<City<E>> MSTnodes(ArrayList<Edge<E>>uncompleted){
		ArrayList<City<E>>nodes = new ArrayList<City<E>>();
		for(Edge<E>e : uncompleted){
			if(!appearance(nodes,e.getStart())){
				nodes.add(e.getStart());
			}
			if(!appearance(nodes,e.getEnd())){
				nodes.add(e.getEnd());
			}
		}
		return nodes;
	}
	
	private ArrayList<Edge<E>> MST (ArrayList<City<E>>list,Graph<E>g,State<E>s){
		if(list.size() < 2){
			System.out.println("MST cracked off");
			return null;
		}
		EdgeComparator<E>comparator = new EdgeComparator<E>();
		PriorityQueue<Edge<E>>pq = new PriorityQueue<Edge<E>>(10,comparator);
		ArrayList<City<E>> unvisited = list;
		ArrayList<Edge<E>>path = new ArrayList<Edge<E>>();
		/*for(Edge<E>e: s.getUncompleted()){
			if(appearance(unvisited,e.getStart()) && appearance(unvisited,e.getEnd())){
				path.add(e);
				delete(unvisited,e.getStart());
				delete(unvisited,e.getEnd());
			}
		}
		if(unvisited.size() < 1){
			return path;
		}*/
		City<E>start = unvisited.get(0);
		Edge<E>startEdge = new Edge<E>(start,start,0);
		pq.add(startEdge);
		while(!pq.isEmpty()){
			Edge<E>current = pq.poll();
			City<E>frontier = current.getEnd();
			if(current.getStart().cityEquals(current.getEnd())==false){
				path.add(current);
			}
			delete(unvisited,frontier);
			if(unvisited.size() == 0){
				break;
			}
			for(City<E>neibours : unvisited){
				Edge<E> edge = new Edge<E>(frontier,neibours,g.distance(frontier, neibours));
				pq.add(edge);
			}
		}
		return path;
	}
	
	
	private void delete(ArrayList<City<E>>list,City<E>city){
		int index = -1;
		for(int i = 0; i < list.size(); i++){
			if(list.get(i).cityEquals(city)){
				index = i;
				break;
			}
		}
		if(index != -1){
			list.remove(index);
		}
	}
	
	private boolean appearance(ArrayList<City<E>>list,City<E>city){
		for(City<E> c: list){
			if(c.cityEquals(city)){
				return true;
			}
		}
		return false;
	}
	

}
