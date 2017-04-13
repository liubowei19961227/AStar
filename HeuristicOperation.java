import java.util.ArrayList;
public interface HeuristicOperation<E> {
	public int HeuristicCalculation(Graph<E> g,State<E> state,City<E> to,ArrayList<Edge<E>>target);
}
