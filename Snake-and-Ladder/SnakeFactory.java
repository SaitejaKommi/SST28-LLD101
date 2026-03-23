import java.util.ArrayList;
import java.util.List;

public class SnakeFactory {
	private SnakeFactory() {}

	public static List<Snake> createSnakes(int boardSize, DifficultyLevel difficulty) {
		int lastCell = boardSize * boardSize;
		List<Snake> snakes = new ArrayList<>();

		if (difficulty == DifficultyLevel.EASY) {
			addSnake(snakes, lastCell, 17, 7);
			addSnake(snakes, lastCell, 32, 14);
			addSnake(snakes, lastCell, 48, 29);
			addSnake(snakes, lastCell, 66, 46);
			addSnake(snakes, lastCell, 88, 57);
		} else {
			addSnake(snakes, lastCell, 21, 8);
			addSnake(snakes, lastCell, 36, 15);
			addSnake(snakes, lastCell, 55, 23);
			addSnake(snakes, lastCell, 73, 41);
			addSnake(snakes, lastCell, 90, 52);
			addSnake(snakes, lastCell, 97, 63);
			addSnake(snakes, lastCell, 99, 5);
		}
		return snakes;
	}

	private static void addSnake(List<Snake> snakes, int lastCell, int head, int tail) {
		if (head <= lastCell && tail > 0 && tail < head) {
			snakes.add(new Snake(head, tail));
		}
	}
}
