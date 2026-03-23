public class LadderFactory {
	private LadderFactory() {}

	public static java.util.List<Ladder> createLadders(int boardSize, DifficultyLevel difficulty) {
		int lastCell = boardSize * boardSize;
		java.util.List<Ladder> ladders = new java.util.ArrayList<>();

		if (difficulty == DifficultyLevel.EASY) {
			addLadder(ladders, lastCell, 3, 22);
			addLadder(ladders, lastCell, 10, 31);
			addLadder(ladders, lastCell, 27, 56);
			addLadder(ladders, lastCell, 61, 84);
			addLadder(ladders, lastCell, 72, 91);
		} else {
			addLadder(ladders, lastCell, 4, 19);
			addLadder(ladders, lastCell, 12, 34);
			addLadder(ladders, lastCell, 28, 49);
			addLadder(ladders, lastCell, 42, 68);
			addLadder(ladders, lastCell, 64, 86);
		}

		return ladders;
	}

	private static void addLadder(java.util.List<Ladder> ladders, int lastCell, int start, int end) {
		if (start > 0 && start < end && end <= lastCell) {
			ladders.add(new Ladder(start, end));
		}
	}
}
