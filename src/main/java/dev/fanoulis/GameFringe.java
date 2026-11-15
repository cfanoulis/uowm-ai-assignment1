package dev.fanoulis;

import java.util.ArrayList;
import java.util.Vector;

public class GameFringe {

    private ArrayList<GameStateNode> queue;

    public GameStateNode pop() {
        return queue.removeFirst();
    }

    public static boolean CheckGoalState(char[] state) {
        for (int i = 0; i < state.length-1; i++) {
            if(state[i] == state[i+1]) return false;
        }
        return true;
    }
}
