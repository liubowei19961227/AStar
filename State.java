import java.util.ArrayList;

public class State<E>{
	private ArrayList<Edge<E>>state;
	private int GCost;
	private int HCost;
	//private ArrayList<Edge<E>>completed;
	
	private ArrayList<Edge<E>>uncompleted;
	
	public State(){
		state = new ArrayList<Edge<E>>();
		//completed = new ArrayList<Edge<E>>();
		this.GCost = 0;
		this.HCost = 0;
		this.uncompleted = new ArrayList<Edge<E>>();
	}
	
	public int getGCost(){
		return this.GCost;
	}
	
	public int getHCost(){
		return this.HCost;
	}
	
	public boolean ifFinish(ArrayList<Edge<E>>target){
		if(uncompleted.size() == 0){
			return true;
		}
		return false;
	}
	
	
	public City<E> frontier(){
		Edge<E> step = state.get(state.size()-1);
		City<E> frontier = step.getEnd();
		return frontier;
	}
	
	
	private void setState(ArrayList<Edge<E>> state){
		this.state = state;
	}
	
	/*private void setCompleted(ArrayList<Edge<E>>completed){
		this.completed = completed;
	}*/
	
	private void setUncompleted(ArrayList<Edge<E>>uncompleted){
		this.uncompleted = uncompleted;
	}
	
	public State<E> copy(){     //change this
		State<E> now = new State<E>();
		ArrayList<Edge<E>>newState = new ArrayList<Edge<E>>();
		//ArrayList<Edge<E>>newCompleted = new ArrayList<Edge<E>>();
		ArrayList<Edge<E>>newUncompleted = new ArrayList<Edge<E>>();
		for(Edge<E> e:state){
			newState.add(e);
		}	
		/*for(Edge<E> e2: completed){
			newCompleted.add(e2);
		}*/
		for(Edge<E>e3:uncompleted){
			newUncompleted.add(e3);
		}
		now.setState(newState);
		//now.setCompleted(newCompleted);
		now.setUncompleted(newUncompleted);
		now.GCost = this.GCost;
		now.HCost = this.HCost;
		return now;
		
	}
	
	
	
	public void add(Edge<E> step,int GValue,int HValue,ArrayList<Edge<E>>target,Graph<E> g){ //every time when a new step is added, update g and h value
			/*if(!EdgeContains(completed,step) && EdgeContains(target,step)){
				this.completed.add(step);
			}*/
			this.state.add(step);
			this.uncompleted = Uncompleted(g,step);  //changed
			this.GCost = this.GCost + GValue;
			this.HCost = HValue;
	}
	
	
	

	
	
	
	
	private boolean EdgeContains(ArrayList<Edge<E>>list,Edge<E> e){
		boolean mark = false;
		for(Edge<E> edge: list){
			if(edge.edgeEquals(e)){
				mark = true;
			}
		}
		return mark;
	}
	
	public int EdgeAppearance(ArrayList<Edge<E>>list,Edge<E> e){
		int count = 0;
		for(Edge<E> edge:list){
			if(edge.edgeEquals(e)){
				count++;
			}
		}
		return count;
	}

	public ArrayList<Edge<E>> getState() {
		return this.state;
	}
	
	/*public ArrayList<Edge<E>>getCompleted(){
		return this.completed;
	}*/
	
	public void speak(){
		System.out.println("State:");
		for(Edge<E> e:state){
			e.print();
		}
		System.out.println("uncompleted");
		for(Edge<E> e:uncompleted){
			e.print();
		}
		System.out.println("the hvalue is "+ HCost);
		System.out.println("the gvalue is "+ GCost);
		System.out.println("-------------");
	}
	
	public void debug(ArrayList<Edge<E>>target){
		int count = 0;
		for(Edge<E> e:target){
			if(EdgeContains(state,e)){
				count++;
			}
		}
		if(count == 13){
			//speak();
		}
	}
	
	
	
	private ArrayList<Edge<E>>Uncompleted(Graph<E>g,Edge<E>step){
		ArrayList<Edge<E>>target = g.getTarget();
		ArrayList<Edge<E>>uncomplete = new ArrayList<Edge<E>>();
		for(Edge<E>edge:target){
			if(!EdgeContains(state,edge)){
				uncomplete.add(edge);
			}
		}
		return uncomplete;
	}
	
	public ArrayList<Edge<E>>getUncompleted(){
		return this.uncompleted;
	}
	
	
	
	
}
