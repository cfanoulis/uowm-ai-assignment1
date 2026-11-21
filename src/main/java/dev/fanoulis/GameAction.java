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
			if (work[i].equalsIgnoreCase("E")) {
				emptyidx = i;
				break;
			}
		}

		//avoid out-of-bounds errors. upstream _does_ ensure no out-of-bounds will occur, but this shall act as a last line of defense
		if (emptyidx == -1 || (Objects.equals(direction, "RIGHT") && (emptyidx + cost > work.length - 1)) || (Objects.equals(direction, "LEFT") && (emptyidx - cost < 0))) {
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
