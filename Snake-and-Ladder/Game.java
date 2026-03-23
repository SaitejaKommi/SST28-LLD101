import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class Game implements MakeMove {
    private final Board board;
    private final DifficultyLevel difficulty;
    private final Queue<Player> players;
    private final Dice dice;
    private boolean gameOver;
    private Player winner;

    public Game(int boardSize, List<String> playerNames, DifficultyLevel difficulty) {
        this.difficulty = difficulty;
        this.players = new ArrayDeque<>();
        for (String playerName : playerNames) {
            players.offer(new Player(playerName));
        }
        this.dice = new Dice();
        this.board = createBoard(boardSize, difficulty);
        this.gameOver = false;
        this.winner = null;
    }

    public Board createBoard(int boardSize, DifficultyLevel difficulty) {
        List<Snake> snakes = SnakeFactory.createSnakes(boardSize, difficulty);
        List<Ladder> ladders = LadderFactory.createLadders(boardSize, difficulty);
        return new Board(boardSize, snakes, ladders);
    }

    public int rollDice() {
        return dice.rollDice();
    }

    @Override
    public void makeMove() {
        Player currentPlayer = players.poll();
        if (currentPlayer == null) {
            return;
        }

        int totalMove = 0;
        int consecutiveSixes = 0;
        boolean loseTurn = false;

        while (true) {
            int rolledValue = rollDice();
            System.out.println(currentPlayer.getName() + " rolled: " + rolledValue);

            if (rolledValue == 6) {
                consecutiveSixes++;
            } else {
                consecutiveSixes = 0;
            }

            if (difficulty == DifficultyLevel.HARD && consecutiveSixes == 3) {
                loseTurn = true;
                totalMove = 0;
                System.out.println(currentPlayer.getName() + " rolled three consecutive sixes and loses turn.");
                break;
            }

            totalMove += rolledValue;

            if (rolledValue != 6) {
                break;
            }
        }

        if (!loseTurn) {
            int target = currentPlayer.getPosition() + totalMove;
            if (target <= board.getLastCell()) {
                currentPlayer.setPosition(target);
                System.out.println(currentPlayer.getName() + " moved to: " + currentPlayer.getPosition());
                handleSnakeOrLadder(currentPlayer);
            } else {
                System.out.println(currentPlayer.getName() + " needs exact roll to reach " + board.getLastCell());
            }
        }

        if (checkWinningCondition(currentPlayer)) {
            System.out.println("Winner is: " + currentPlayer.getName());
            winner = currentPlayer;
            gameOver = true;
            return;
        }

        players.offer(currentPlayer);
    }

    public void handleSnakeOrLadder(Player player) {
        int currentCell = player.getPosition();

        if (board.hasSnakeAt(currentCell)) {
            int newCell = board.moveBySnake(currentCell);
            System.out.println(player.getName() + " got bitten by a snake: " + currentCell + " -> " + newCell);
            player.setPosition(newCell);
            return;
        }

        if (board.hasLadderAt(currentCell)) {
            int newCell = board.moveByLadder(currentCell);
            System.out.println(player.getName() + " climbed a ladder: " + currentCell + " -> " + newCell);
            player.setPosition(newCell);
        }
    }

    public boolean checkWinningCondition(Player player) {
        return player.getPosition() == board.getLastCell();
    }

    public void playGame() {
        while (!players.isEmpty() && !gameOver) {
            makeMove();
        }

        if (winner != null) {
            System.out.println("Game finished. Winner: " + winner.getName());
        }
    }

    public static void main(String[] args) {
        List<String> playerNames = java.util.Arrays.asList("Aman", "Priya", "Rohit");
        Game game = new Game(10, playerNames, DifficultyLevel.HARD);
        game.playGame();
    }
}
