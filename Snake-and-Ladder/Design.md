## Snake and Ladder V1 LLD

### Scope
This V1 design focuses on core gameplay:
- board creation with difficulty-based snakes and ladders
- multi-player turn handling
- dice rolls (1 to 6)
- snake and ladder transitions
- win detection

### Inputs
- `boardSize` (example: 10 for a 10x10 board)
- `playerNames` (list of players)
- `difficulty` (`EASY` or `HARD`)

### Difficulty Rules
- `EASY`:
	- player keeps rolling as long as dice value is 6
- `HARD`:
	- if a player rolls three consecutive sixes in the same turn, they lose the turn

## Required Functions and Behavior

### 1) `createBoard(boardSize, difficulty)`
- creates snakes and ladders based on difficulty using factories
- returns a `Board` with transitions

### 2) `rollDice()`
- returns random integer in range 1 to 6

### 3) `makeMove()`
- picks current player from turn queue
- rolls dice according to difficulty rule
- computes target position
- applies movement if within board bounds
- calls `handleSnakeOrLadder()`
- checks winner via `checkWinningCondition()`
- pushes player back to queue if no winner

### 4) `handleSnakeOrLadder(player)`
- if player lands on snake head, move to snake tail
- else if player lands on ladder start, move to ladder end

### 5) `checkWinningCondition(player)`
- returns true if player reaches last board cell

## Class Responsibilities

- `Game`:
	- orchestrates game loop and turns
	- contains all required core functions
- `Board`:
	- stores board size and snake/ladder transitions
- `Player`:
	- stores player name and current position
- `Dice`:
	- encapsulates random roll logic
- `SnakeFactory` and `LadderFactory`:
	- build difficulty-specific snake/ladder sets
- `Snake` and `Ladder`:
	- immutable position pairs

## Class Diagram (Mermaid)

```mermaid
classDiagram
		class MakeMove {
				<<interface>>
				+makeMove()
		}

		class Game {
				-Board board
				-DifficultyLevel difficulty
				-Queue~Player~ players
				-Dice dice
				-boolean gameOver
				-Player winner
				+createBoard(int, DifficultyLevel) Board
				+rollDice() int
				+makeMove()
				+handleSnakeOrLadder(Player)
				+checkWinningCondition(Player) boolean
				+playGame()
		}

		class Board {
				-int size
				-int lastCell
				-Map~int,int~ snakes
				-Map~int,int~ ladders
				+hasSnakeAt(int) boolean
				+hasLadderAt(int) boolean
				+moveBySnake(int) int
				+moveByLadder(int) int
				+getLastCell() int
		}

		class Player {
				-String name
				-int position
				+getName() String
				+getPosition() int
				+setPosition(int)
		}

		class Dice {
				+rollDice() int
		}

		class DifficultyLevel {
				<<enum>>
				EASY
				HARD
		}

		class SnakeFactory {
				+createSnakes(int, DifficultyLevel) List~Snake~
		}

		class LadderFactory {
				+createLadders(int, DifficultyLevel) List~Ladder~
		}

		class Snake {
				-int head
				-int tail
		}

		class Ladder {
				-int start
				-int end
		}

		MakeMove <|.. Game
		Game --> Board
		Game --> Dice
		Game --> Player
		Game --> DifficultyLevel
		Game --> SnakeFactory
		Game --> LadderFactory
		SnakeFactory --> Snake
		LadderFactory --> Ladder
		Board --> Snake
		Board --> Ladder
```

## Flow Chart (V1 Turn Flow)

```mermaid
flowchart TD
		A[Start Game] --> B[createBoard(boardSize, difficulty)]
		B --> C[Initialize queue with players]
		C --> D{Game Over?}
		D -- No --> E[makeMove() for current player]
		E --> F[rollDice()]
		F --> G{Rolled 6?}
		G -- No --> H[Stop rolling]
		G -- Yes --> I{Difficulty == HARD and third consecutive 6?}
		I -- Yes --> J[Lose turn]
		I -- No --> K[Add roll to move and roll again]
		K --> F
		H --> L[Move player]
		J --> Q[Next player]
		L --> M[handleSnakeOrLadder()]
		M --> N[checkWinningCondition()]
		N -- Yes --> O[Declare winner and end]
		N -- No --> Q[Next player]
		Q --> D
		D -- Yes --> P[Stop Game]
```
