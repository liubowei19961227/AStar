
public class Edge<E>{
	private City<E> v1;
	private City<E> v2;
	private int distance;
	
	public Edge(City<E> v1,City<E> v2,int distance){
		this.v1 = v1;
		this.v2 = v2;
		this.distance = distance;
	}
	
	public City<E> getStart(){
		return v1;
	}
	
	public City<E> getEnd(){
		return v2;
	}
	
	public int distance(){
		return distance;
	}
	
	
	

	
	public boolean edgeEquals(Edge<E> o){
		if(this.v1.cityEquals(o.v1) && this.v2.cityEquals(o.v2)){
			return true;
		}
		return false;
	}
	
	public void print(){
		System.out.println("from " + v1.getName() +" "+ v1.getLable() +" to" + " "+v2.getName() +" "+ v2.getLable()+ " distance is "+distance );
	}
	
}

