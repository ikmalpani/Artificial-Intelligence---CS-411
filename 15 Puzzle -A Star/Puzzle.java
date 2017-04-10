package astar;

import java.util.Arrays;

public class Puzzle {

    int state[];
    int parent_action;
    int cost;
    Puzzle parent;
    int f;

    public Puzzle() {
        state = new int[16];
        parent_action = 0;
        cost = 0;
    }

    public Puzzle(Puzzle parent, int action) {
        state = new int[16];
        cost = 0;
        System.out.println(Arrays.toString(parent.getState()));
        System.arraycopy(parent.state, 0, state, 0, 16);
        parent_action = action;
        cost = parent.cost + 1;
        this.parent = parent;
    }

    public int getF() {
        return f;
    }

    public void setF(int h) {
        int i;
        if (h == 1) {
            this.f = this.cost;
            for (i = 0; i < 16; i++) {
                if (this.state[i] == 0) {
                    continue;
                }
                if (this.state[i] != i ) {
                    this.f++;
                }
            }
        } else if (h == 2) {
            int x, y;
            int manhattan = 0;
            for (i = 0; i < 16; i++) {
                if (i < 4) {
                    x = 0;
                    y = i;
                } else if (i < 8) {
                    x = 1;
                    y = i - 4;
                } else if (i < 12) {
                    x = 2;
                    y = i - 8;
                } else {
                    x = 3;
                    y = i - 12;
                }
                int value = this.state[i];
                if (value != 0) { // we don't compute Manhattan Distance for element 0
                    int targetX = (value) / 4; // expected x-coordinate (row)
                    int targetY = (value) % 4; // expected y-coordinate (col)
                    int dx = x - targetX; // x-distance to expected coordinate
                    int dy = y - targetY; // y-distance to expected coordinate
                    manhattan += Math.abs(dx) + Math.abs(dy);
                }
            }
            this.f = this.cost + manhattan;
        }
    }

    public int[] getState() {
        return state;
    }

    public void setState(int[] state) {
        this.state = state;
    }

    public int getParent_action() {
        return parent_action;
    }

    public void setParent_action(int parent_action) {
        this.parent_action = parent_action;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Puzzle getParent() {
        return parent;
    }

    public void setParent(Puzzle parent) {
        this.parent = parent;
    }

    public void printState() {
        int i;
        for (i = 0; i < 16; i++) {
            System.out.print(state[i] + " ");
        }
        System.out.println();
    }

    public void copyState(int source[]) {
        System.arraycopy(source, 0, state, 0, 16);
    }

    public boolean stateCompare(int comp[]) {
        return Arrays.equals(state, comp);
    }

    public int blankPosition() {
        int i;
        for (i = 0; i < 16; i++) {
            if (state[i] == 0) {
                return i;
            }
        }
        return i;//will never be executed 
    }

    public void performAction(int position, int action) {
        if (action == 2) {//down
            int temp;
            temp = state[position + 4];
            state[position + 4] = state[position];
            state[position] = temp;
        }
        if (action == 8) {//up
            int temp;
            temp = state[position - 4];
            state[position - 4] = state[position];
            state[position] = temp;
        }
        if (action == 4) {//left
            int temp;
            temp = state[position - 1];
            state[position - 1] = state[position];
            state[position] = temp;
        }
        if (action == 6) {//right
            int temp;
            temp = state[position + 1];
            state[position + 1] = state[position];
            state[position] = temp;
        }
    }
}
