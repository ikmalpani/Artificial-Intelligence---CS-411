package astar;

import java.util.Arrays;

public class Explored {

    int explored_state[] = new int[16];
    int f;

    public int[] getExplored_state() {
        return explored_state;
    }

    public void setExplored_state(int[] explored_state) {
        this.explored_state = explored_state;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public Explored(Puzzle node) {
        System.arraycopy(node.getState(), 0, explored_state, 0, 16);
        f = node.f;
    }

    public void stateCopy(int source[]) {
        System.arraycopy(source, 0, explored_state, 0, 16);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(explored_state);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Explored other = (Explored) obj;
        boolean res;
        res = Arrays.equals(this.explored_state, other.explored_state);
        return res;
    }

}
