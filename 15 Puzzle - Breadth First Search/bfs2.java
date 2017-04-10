import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class bfs2 {
	
	static String[] goalnode = {"1","2","3","4","5","6","7","8","9","10","11", "12", "13","14","15","0"};
	public static void main(String args[]){
		long startTime = System.currentTimeMillis();
		String[] node;
		Queue<String[]> frontier = new LinkedList<String[]>();
		List<String[]> explored = new LinkedList<String[]>();
		String[] rootnode = {"1","2","3","4","5","6","7","8","0","9","10","15","13","14","12","11"};
		frontier.add(rootnode);
		while(!frontier.isEmpty()){
			node=frontier.poll();
			explored.add(node);
			String[] checkedNode = {};
			for(int x=0;x<4;x++){
				if(x==0){
					checkedNode = Arrays.copyOf(up(node), 16);
				}
				else if(x==1){
					checkedNode = Arrays.copyOf(down(node), 16);
				}
				else if(x==2){
					checkedNode = Arrays.copyOf(left(node), 16);
				}
				else if(x==3){
					checkedNode = Arrays.copyOf(right(node), 16);
				}
				if(!frontier.contains(checkedNode) && !explored.contains(checkedNode)){
					if(Arrays.equals(checkedNode, goalnode)){
						System.out.println("Solution found.");
						System.out.println("Memory usage: " + (double) (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024 + "KB");
						long endTime   = System.currentTimeMillis();
						long totalTime = endTime - startTime;
						System.out.println("Runtime: " + totalTime + " Milliseconds");
						System.exit(0);
					}
					frontier.add(checkedNode);
				}
			}
		}
	}
   static String[] up(String[] node){
    	int a =0;
    	String[] changedNode;
    	changedNode = Arrays.copyOf(node, node.length);
    	String b;
        for(int x=0;x<16;x++){
        	if(changedNode[x]=="0"){
        		a = x;
        	}
        }
        if(a>3){
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
       for(int x=0;x<16;x++){
       	if(changedNode[x]=="0"){
       		a = x;
       	}
       }
       if(a<12){
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
	       for(int x=0;x<16;x++){
	       	if(changedNode[x]=="0"){
	       		a = x;
	       	}
	       }
	       if(a!=0 && a!=4 && a!=8 && a!=12){
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
	       for(int x=0;x<16;x++){
	       	if(changedNode[x]=="0"){
	       		a = x;
	       	}
	       }
	       if(a!=3 && a!=7 && a!=11 && a!=15){
	       	b = changedNode[a];
	       	changedNode[a]=changedNode[a+1];
	       	changedNode[a+1] = b;
	       }
	       System.out.println(" " + Arrays.toString(changedNode));
	       return changedNode;
	   }
}