import java.util.Comparator;

public class EdgeComparator<E> implements Comparator<Edge<E>> {

	@Override
	public int compare(Edge<E> o1, Edge<E> o2) {
		if(o1.distance() + o1.getStart().getTransferTime() < o2.distance() + o2.getStart().getTransferTime()){
			return -1;
		}
		return 1;
	}

}
