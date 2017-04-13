
import java.util.ArrayList;

public class City<E>{
	private E name;
	private int transferTime;
	private int lable;
	ArrayList<Edge<E>>connections;
	
	public City(E name,int transferTime,int lable){
		this.name = name;
		this.transferTime = transferTime;
		this.lable = lable;
		connections = new ArrayList<Edge<E>>();
	}
	
	
	public E getName(){
		return name;
	}
	
	public int getTransferTime(){
		return transferTime;
	}
	
	public void setLable(int test){
		this.lable=test;
	}
	
	public int getLable(){
		return lable;
	}
	
	
	
	public void AddEdge(Edge<E> e){
		if(!e.getStart().getName().equals(name)){
			System.out.println("int city class, adding the wrong edge");
		}
		this.connections.add(e);
	}
	
	public int getDistance(City<E> to){
		if(cityEquals(to)){
			return 0;
		}
		for(Edge<E> e:connections){
			if(e.getEnd().getName().equals(to.getName())){
				return e.distance();
			}
		}
		return -1;
	}
	
	
	public ArrayList<City<E>> getNeibours(){
		ArrayList<City<E>>neibours = new ArrayList<City<E>>();
		for(Edge<E> e: connections){
			neibours.add(e.getEnd());
		}
		return neibours;
	}
	
	public boolean cityEquals(City<E> c){
		if(this.name.equals(c.getName())){
			return true;
		}
		return false;
	}

	
	//helper
	public void showConnections(){
		System.out.println("this is city "+name);
		for(Edge<E> e:connections){
			e.print();
		}
	}
	
	public void cityInfo(){
		System.out.println("this is city "+name);
		System.out.println("end");
	}

	
	
}

