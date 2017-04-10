import java.util.*;

public class bfs {
	
	static String[] goalnode = {"1","2","3","4","5","6","7","8","9","10","11", "12", "13","14","15","0"}; //Goal State
	public static void main(String args[]){
		long startTime = System.currentTimeMillis(); //To measure time
		String[] node;
		Queue<String[]> frontier = new LinkedList<String[]>(); //frontier declaration
		List<String[]> explored = new LinkedList<String[]>(); //explored declaration
		String[] rootnode = {"1","2","3","4","5","6","7","8","9","0","10","11","13","14","15","12"}; //Initial State 1
		frontier.add(rootnode);  //Add initial state to frontier
		while(!frontier.isEmpty()){  //Check if frontier is empty
			node=frontier.poll();  //Pop from frontier and insert to node
			explored.add(node); //Add the node to exlpored
			String[] checkedNode = {};
			for(int x=0;x<4;x++){ //Perform Actions
				if(x==0){
					checkedNode = Arrays.copyOf(up(node), 16); //Call up() function
				}
				else if(x==1){
					checkedNode = Arrays.copyOf(down(node), 16); // Call down() function
				}
				else if(x==2){
					checkedNode = Arrays.copyOf(left(node), 16); //Call left() function
				}
				else if(x==3){
					checkedNode = Arrays.copyOf(right(node), 16); // Call right() function
				}
				if(!frontier.contains(checkedNode) && !explored.contains(checkedNode)){
					if(Arrays.equals(checkedNode, goalnode)){ // Check for solution
						System.out.println("Solution found.");
						System.out.println("Memory usage: " + (double) (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024 + "KB"); //Memory usage
						long endTime   = System.currentTimeMillis();
						long totalTime = endTime - startTime;
						System.out.println("Runtime: " + totalTime + " Milliseconds"); // Runtime
						System.exit(0); //exit if solution found
					}
					frontier.add(checkedNode); //If no solution, add to frontier
				}
			}
		}
	}
   static String[] up(String[] node){
    	int a =0;
    	String[] changedNode;
    	changedNode = Arrays.copyOf(node, node.length);
    	String b;
        for(int x=0;x<16;x++){ // Calculate the position of 0
        	if(changedNode[x]=="0"){
        		a = x;
        	}
        }
        if(a>3){ // Condition for up
        	b = changedNode[a];
        	changedNode[a]=changedNode[a-4];
        	changedNode[a-4] = b;
        }
        System.out.println(" " + Arrays.toString(changedNode));
        return changedNode;
    }
   static String[] down(String[] node){
   	int a =0;
   	String[] changedNode;
   	changedNode = Arrays.copyOf(node, node.length);
   	String b;
       for(int x=0;x<16;x++){ // Calculate the position of 0
       	if(changedNode[x]=="0"){
       		a = x;
       	}
       }
       if(a<12){ // Condition for down
       	b = changedNode[a];
       	changedNode[a]=changedNode[a+4];
       	changedNode[a+4] = b;
       }
       System.out.println(" " + Arrays.toString(changedNode));
       return changedNode;
   }
   static String[] left(String[] node){
	   	int a =0;
	   	String[] changedNode;
	   	changedNode = Arrays.copyOf(node, node.length);
	   	String b;
	       for(int x=0;x<16;x++){ // Calculate the position of 0
	       	if(changedNode[x]=="0"){
	       		a = x;
	       	}
	       }
	       if(a!=0 && a!=4 && a!=8 && a!=12){ // Condition for left
	       	b = changedNode[a];
	       	changedNode[a]=changedNode[a-1];
	       	changedNode[a-1] = b;
	       }
	       System.out.println(" " + Arrays.toString(changedNode));
	       return changedNode;
	   }
   
   static String[] right(String[] node){
	   	int a =0;
	   	String[] changedNode;
	   	changedNode = Arrays.copyOf(node, node.length);
	   	String b;
	       for(int x=0;x<16;x++){ // Calculate the position of 0
	       	if(changedNode[x]=="0"){
	       		a = x;
	       	}
	       }
	       if(a!=3 && a!=7 && a!=11 && a!=15){ // Condition for right
	       	b = changedNode[a];
	       	changedNode[a]=changedNode[a+1];
	       	changedNode[a+1] = b;
	       }
	       System.out.println(" " + Arrays.toString(changedNode));
	       return changedNode;
	   }
}