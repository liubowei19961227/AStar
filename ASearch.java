import java.util.ArrayList;
import java.util.PriorityQueue;

public class ASearch<E> {
	
	
	
	public void pathFind(Graph<E> g){
		int expanded = 0;
		City<E> London = g.city("London");
		ArrayList<Edge<E>>target = g.getTarget();
		State<E> start = new State<E>();
		Edge<E> startEdge = new Edge<E>(London,London,0);
		Heuristic<E> h = new Heuristic<E>();
		start.add(startEdge, 0, 0,target,g);
		StateComparator<E> comparator = new StateComparator<E>();
		PriorityQueue<State<E>>openset = new PriorityQueue<State<E>>(10,comparator);
		openset.add(start);
		//int round = 0;
		
		while(!openset.isEmpty()){
			State<E> currentState = openset.poll();
			if(currentState.ifFinish(target)){
				if(currentState.getState().size()>0){
					currentState.getState().remove(0);
				}
				//System.out.println(round+" rounds");
				System.out.println(expanded+" nodes expandded");
				System.out.println("cost = "+pathCost(currentState.getState()));
				printPath(currentState.getState());
				break;
			}
			//round++;
			ArrayList<Edge<E>>expansions = currentState.getUncompleted();
			for(Edge<E> step:expansions){
				State<E> newState = currentState.copy();
				City<E> frontier = currentState.frontier();
				if(frontier.cityEquals(step.getStart())){
					int Hcost = h.HeuristicCalculation(g, newState, step.getEnd(), target);
					int Gcost = step.distance() + step.getStart().getTransferTime();
					newState.add(step, Gcost, Hcost, target, g);
				}else{
					Edge<E>extraStep = new Edge<E>(frontier,step.getStart(),g.distance(frontier, step.getStart()));
					int Gcost = extraStep.distance() + extraStep.getStart().getTransferTime();
					newState.add(extraStep, Gcost, 0, target, g);
					int Hcost = h.HeuristicCalculation(g, newState, step.getEnd(), target);
					Gcost = step.distance() + step.getStart().getTransferTime();
					newState.add(step, Gcost, Hcost, target, g);
				}
				openset.add(newState);
				expanded++;
			}
			
		}
		
		
	}
	
	
	
	
	private void printPath(ArrayList<Edge<E>>path){
		for(Edge<E> e: path){
			System.out.println("Trip "+ e.getStart().getName()+" to"+" "+e.getEnd().getName());
		}
	}
	
	private int pathCost(ArrayList<Edge<E>>path){
		int cost = 0;
		for(Edge<E> e:path){
			cost = cost+ e.getStart().getTransferTime() + e.distance();
		}
		if(path.size()>0 && path.get(0).getStart().getName().equals("London")){
			cost = cost- path.get(0).getStart().getTransferTime();
		}
		return cost;
	}
	
	
	
	
	
}
