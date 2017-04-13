import java.util.ArrayList;

public class Heuristic<E> implements HeuristicOperation<E>{

	@Override
	public int HeuristicCalculation(Graph<E> g, State<E> state, City<E> to,ArrayList<Edge<E>> target) {
		City<E> from = state.frontier();
		ArrayList<Edge<E>>uncomplete = uncomplete(g,from,to,state);
		int value = 0;
		for(Edge<E> e:uncomplete){
			value = value + e.distance() + e.getStart().getTransferTime();
		}
		int min = -1;
		if(uncomplete.size()!=0){
			min = g.distance(to, uncomplete.get(0).getStart());
		}else{
			min = 0;
		}
		for(Edge<E> e:uncomplete){
			if(g.distance(to, e.getStart())<min){
				min = g.distance(to, e.getStart());
			}
		}
		return value + min;
		//return 0;
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
	
	
	
	

}
