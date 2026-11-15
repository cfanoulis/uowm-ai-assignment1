package dev.fanoulis;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public record GameAction(int cost, String direction) {
    public GameAction {
        if (!(direction.equals("LEFT") || direction.equals("RIGHT") || direction.equals("ROOT")))
            throw new IllegalArgumentException("Invalid direction, only LEFT or RIGHT allowed");
    }

    @Override
    public @NotNull String toString() {
        if (direction.equals("ROOT")) return direction;
        return String.format("Move(%s, %d)", direction, cost);
    }

    public String[] runAction(String[] state) {
        String[] work = state.clone();
        int emptyidx = -1;

        for (int i = 0; i < work.length; i++) {
            if (work[i] == "E") {
                emptyidx = i;
                break;
            }
        }

        if (emptyidx == -1) {
            return work;
        }

        if (direction.equals("RIGHT")) {
            int replacementIdx = emptyidx + cost;
            String replacementChar = work[replacementIdx];
            work[replacementIdx] = "E";
            work[emptyidx] = replacementChar;
        } else if (direction.equals("LEFT")) {
            int replacementIdx = emptyidx - cost;
            String replacementChar = work[replacementIdx];
            work[replacementIdx] = "E";
            work[emptyidx] = replacementChar;
        }

        return work;
    }

}
