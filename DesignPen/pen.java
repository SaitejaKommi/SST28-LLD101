abstract class Pen {
	private final PenType type;
	private final String color;
	private final boolean withCap;
	private final int maxInk;
	private final StartStrategy startStrategy;
	private final RefillStrategy refillStrategy;

	private int inkLevel;
	private boolean started;

	protected Pen(
			PenType type,
			String color,
			boolean withCap,
			int maxInk,
			int initialInk,
			StartStrategy startStrategy,
			RefillStrategy refillStrategy) {
		this.type = type;
		this.color = color;
		this.withCap = withCap;
		this.maxInk = maxInk;
		this.inkLevel = Math.max(0, Math.min(maxInk, initialInk));
		this.startStrategy = startStrategy;
		this.refillStrategy = refillStrategy;
		this.started = false;
	}

	public final void start() {
		startStrategy.start(this);
		started = true;
	}

	public final void close() {
		startStrategy.close(this);
		started = false;
	}

	public final void refill() {
		refillStrategy.refill(this);
	}

	public final void write(String text) {
		if (!started) {
			throw new PenNotStartedException("Cannot write. Please start the pen first.");
		}
		if (text == null || text.isEmpty()) {
			System.out.println("[" + type + "] Nothing to write.");
			return;
		}

		int inkPerChar = inkUnitsPerCharacter();
		int writableChars = inkLevel / inkPerChar;

		if (writableChars <= 0) {
			throw new InkDepletedException("Ink is empty. Refill required.");
		}

		int charsToWrite = Math.min(writableChars, text.length());
		String printableText = text.substring(0, charsToWrite);

		printWriteOutput(printableText);
		inkLevel -= charsToWrite * inkPerChar;

		if (charsToWrite < text.length()) {
			throw new InkDepletedException(
					"Ink depleted during writing. Wrote partial text: '" + printableText + "'.");
		}
	}

	protected abstract int inkUnitsPerCharacter();

	protected abstract void printWriteOutput(String text);

	public PenType getType() {
		return type;
	}

	public String getColor() {
		return color;
	}

	public boolean isWithCap() {
		return withCap;
	}

	public int getInkLevel() {
		return inkLevel;
	}

	public int getMaxInk() {
		return maxInk;
	}

	public boolean isStarted() {
		return started;
	}

	void setInkLevel(int newInkLevel) {
		inkLevel = Math.max(0, Math.min(maxInk, newInkLevel));
	}
}
