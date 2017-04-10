package astar;

import java.lang.reflect.Array;
import java.util.*;

public class AStar {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        System.out.println("Select the initial state:");
        System.out.println("1 for first initial state");
		System.out.println("2 for second initial state");
		System.out.println("3 for third initial state");
		int input[] = {}; 
		int input1[] = {1,2,3,4,5,6,7,8,9,0,10,11,13,14,15,12}; //Initial State 1
		int input2[] = {1,2,3,4,5,6,7,8,0,9,10,15,13,14,12,11}; //Initial State 2
		int input3[] = {1,2,3,4,5,0,12,10,9,8,7,11,13,14,15,6}; //Initial State 3
		Scanner reader = new Scanner(System.in);
		int k= reader.nextInt();
		if(k==1){
			input = Arrays.copyOf(input1, 16);
		}
		else if(k==2){
			input = Arrays.copyOf(input2, 16);
		}
		else if(k==3){
			input = Arrays.copyOf(input3, 16);
		}
		else {
			System.out.println("Invalid input.");
			System.exit(0);
		}
        System.out.println("Initial State sectected is: "+Arrays.toString(input));
        int i;
        int goal[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0};
        
        System.out.println("Select the heuristic function:");
        System.out.println("Select 1 for Number of Misplaced Tiles");
        System.out.println("Select 2 for Manhattan Distance");
        int h=0;
        int h1=1;
        int h2=1;
        Scanner reader1 = new Scanner(System.in);
		int l= reader1.nextInt();
		if(l==1){
			h=h1;
		}
		else if(l==2){
			h=h2;
		}
		else {
			System.out.println("Invalid input.");
			System.exit(0);
		}
		reader.close();
		reader1.close();
        Puzzle start_state = new Puzzle(); //this is the root object of the search tree

        start_state.copyState(input);
        start_state.setF(h);

        if (start_state.stateCompare(goal)) {
            System.out.println("Initial State is the goal!");
            return;
        }
        PriorityQueue<Puzzle> frontier = new PriorityQueue<>((Puzzle puz1, Puzzle puz2) -> Integer.valueOf(puz1.getF()).compareTo(puz2.getF()));
        frontier.add(start_state);
        HashSet explored = new HashSet(); //the Hash set for the explored states (closed list)
        HashSet frontierHash = new HashSet(); //a Hash set where are stored the states of current frontier objects
        Explored init = new Explored(start_state);
        frontierHash.add(init);
        try {
            while (true) {
                Puzzle current;
                Explored exp; //the two Hash sets contain objects of this class, Explored. this class only contains an int[9] array
                if (frontier.isEmpty()) {
                    System.out.println("NO SOLUTION!\n");
                    break;
                }
                current = (Puzzle) frontier.poll();
                if (current.stateCompare(goal)) {
                    System.out.println("Found solution with cost: " + current.getCost());
                    System.out.println("");
                    int finalpath[] = new int[current.getCost()];
                    for (i = Array.getLength(finalpath) - 1; current.getParent_action() != 0; i--) {
                        finalpath[i] = current.getParent_action();
                        current = current.getParent();
                    }
                    System.out.println("The path to the solution is below: ");
                    start_state.printState();

                    for (i = 0; i < Array.getLength(finalpath); i++) {
                        if (finalpath[i] == 4) {
                            System.out.println("Step " + (i + 1) + ": " + "left");
                        } else if (finalpath[i] == 6) {
                            System.out.println("Step " + (i + 1) + ": " + "right");
                        } else if (finalpath[i] == 2) {
                            System.out.println("Step " + (i + 1) + ": " + "down");
                        } else {
                            System.out.println("Step " + (i + 1) + ": " + "up");
                        }
                        start_state.performAction(start_state.blankPosition(), finalpath[i]);
                        start_state.printState();
                    }
                    System.out.println("Goal state reached.");
                    break;
                }
                exp = new Explored(current);
                explored.add(exp);//each time I add a state to the explored Hash set 
                frontierHash.remove(exp);//I remove it from the frontier Hash set
                int position = current.blankPosition(); //find blank's position
                int actions_count, j;
                int action_arr[];//in this array the possible actions are stored.
                //this is tha action format: 8 for up, 2 for down, 4 for left and 6 for right
                if (position == 0) { //it can only move right and down
                    actions_count = 2;
                    action_arr = new int[2];
                    action_arr[0] = 2;
                    action_arr[1] = 6;
                } else if (position == 1 || position == 2) {//it can move left, down and right
                    actions_count = 3;
                    action_arr = new int[3];
                    action_arr[0] = 2;
                    action_arr[1] = 4;
                    action_arr[2] = 6;
                } else if (position == 3) { //it can move left and down
                    actions_count = 2;
                    action_arr = new int[2];
                    action_arr[0] = 2;
                    action_arr[1] = 4;
                } else if (position == 4 || position == 8) {//it can move up, right and down
                    actions_count = 3;
                    action_arr = new int[3];
                    action_arr[0] = 2;
                    action_arr[1] = 6;
                    action_arr[2] = 8;
                } else if (position == 5 || position == 6 || position == 9 || position == 10) {//it can move towards ALL directions
                    actions_count = 4;
                    action_arr = new int[4];
                    action_arr[0] = 2;
                    action_arr[1] = 4;
                    action_arr[2] = 6;
                    action_arr[3] = 8;
                } else if (position == 7 || position == 11) {//it can move left, up and down
                    actions_count = 3;
                    action_arr = new int[3];
                    action_arr[0] = 2;
                    action_arr[1] = 4;
                    action_arr[2] = 8;
                } else if (position == 12) {//it can move up and right
                    actions_count = 2;
                    action_arr = new int[2];
                    action_arr[0] = 6;
                    action_arr[1] = 8;
                } else if (position == 13 || position == 14) {//it can move up, left and right
                    actions_count = 3;
                    action_arr = new int[3];
                    action_arr[0] = 4;
                    action_arr[1] = 6;
                    action_arr[2] = 8;
                } else {//then position is 15 -- it can move left and up 
                    actions_count = 2;
                    action_arr = new int[2];
                    action_arr[0] = 4;
                    action_arr[1] = 8;
                }
                for (j = 0; j < actions_count; j++) {//for each possible action
                    Puzzle child;
                    child = new Puzzle(current, action_arr[j]);
                    child.performAction(position, action_arr[j]); //change the state of the Puzzle according to the position of the blank and the action
                    child.setF(h);
                    Explored childexp = new Explored(child); //this will be used for searching the two Hashsets as well as for adding the state of the newly created object to the frontier Hashset
                    if (!explored.contains(childexp) && !frontierHash.contains(childexp)) {
                        frontier.add(child); //if goal is not reached then add the newly created child to the frontier
                        frontierHash.add(childexp);//and add his state to the frontier HashSet
                    }
                }
            }

        } 
        catch (OutOfMemoryError e) {
            System.out.println("No solution found!");
        }

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total Runtime: " + totalTime + " milliseconds");
        System.out.println("Memory usage: " + (double) (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024 + "KB");
    }
}