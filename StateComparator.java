import java.util.Comparator;

public class StateComparator<E> implements Comparator<State<E>> {

	@Override
	public int compare(State<E> o1, State<E> o2) {
		if(o1.getGCost() + o1.getHCost() < o2.getGCost() + o2.getHCost()){
			return -1;
		}/*else if(o1.getGCost() + o1.getHCost() == o2.getGCost() + o2.getHCost() && o1.size()>o2.size()){
			return -1;
		}else if(o1.getGCost() + o1.getHCost() == o2.getGCost() + o2.getHCost() && o1.size()==o2.size() && o1.getHCost()<o2.getHCost()){
			return -1;
		}else if(o1.getGCost() + o1.getHCost() == o2.getGCost() + o2.getHCost() && o1.size()==o2.size() && o1.getHCost()==o2.getHCost()){
			return 0;
		}*/else{
			return 1;
		}
	}

}
