
import java.io.FileReader;
//import java.util.ArrayList;
import java.util.Scanner;

public class TripPlanner {
	public static void main(String[]args){
		try{
			Scanner scanner = new Scanner(new FileReader(args[0]));
			Graph<String>g = new Graph<String>();
			String divide = " ";
			int lableIndex = 0;
			while(scanner.hasNextLine()){
				String input = scanner.nextLine();
				String[]str = input.split(divide);
				if(str[0].equals("Transfer")){
					int transferTime = Integer.parseInt(str[1]);
					String name = str[2];
					City<String> city = new City<String>(name,transferTime,lableIndex);
					lableIndex++;
					g.addVertex(city);
				}else if(str[0].equals("Time")){
					int weight = Integer.parseInt(str[1]);
					City<String> v1 = g.city(str[2]);
					City<String> v2 = g.city(str[3]);
					Edge<String> e1 = new Edge<String>(v1,v2,weight);
					Edge<String> e2 = new Edge<String>(v2,v1,weight);
					g.addEdge(e1);
					g.addEdge(e2);
				}else if(str[0].equals("Trip")){
					City<String> start = g.city(str[1]);
					City<String> end = g.city(str[2]);
					int weight = g.distance(start, end);
					Edge<String> target = new Edge<String>(start,end,weight);
					g.mark(target);
				}else{
					
				}
				
			}
			//g.showGraph();
			ASearch<String> search = new ASearch<String>();
			search.pathFind(g);
			scanner.close();
			
			/*City London = new City("London",0,0);
			City Amsterdam = new City("Amsterdam",0,0);
			double result = g.ifTarget(London, Amsterdam);
			System.out.println("fuck fuck "+result);
			Edge e1 = new Edge(Taipei,Hongkong,1000);
			Edge e2 = new Edge(Taipei,Hongkong,1000);
			
			ArrayList<Edge>test = new ArrayList<Edge>();
			test.add(e1);
			test.add(e2);
			State s = new State();
			s.add(e1, 0, 0);
			State s2 = s.copy();
			s2.add(e2, 0, 0);
			int size = s2.getState().size();
			System.out.println("size is "+size);*/
			
			
		}catch(Exception e){
			System.out.println("in main the error is "+e.getMessage());
		}
	}
}

