import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private final int size;
    private final int lastCell;
    private final Map<Integer, Integer> snakes;
    private final Map<Integer, Integer> ladders;

    public Board(int size, List<Snake> snakes, List<Ladder> ladders) {
        this.size = size;
        this.lastCell = size * size;
        this.snakes = new HashMap<>();
        this.ladders = new HashMap<>();

        for (Snake snake : snakes) {
            this.snakes.put(snake.getHead(), snake.getTail());
        }
        for (Ladder ladder : ladders) {
            this.ladders.put(ladder.getStart(), ladder.getEnd());
        }
    }

    public int getSize() {
        return size;
    }

    public int getLastCell() {
        return lastCell;
    }

    public boolean hasSnakeAt(int cell) {
        return snakes.containsKey(cell);
    }

    public boolean hasLadderAt(int cell) {
        return ladders.containsKey(cell);
    }

    public int moveBySnake(int cell) {
        return snakes.get(cell);
    }

    public int moveByLadder(int cell) {
        return ladders.get(cell);
    }
}
