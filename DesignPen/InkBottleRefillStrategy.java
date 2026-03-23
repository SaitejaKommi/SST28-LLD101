class InkBottleRefillStrategy implements RefillStrategy {
    private final int refillUnits;

    InkBottleRefillStrategy(int refillUnits) {
        this.refillUnits = refillUnits;
    }

    @Override
    public void refill(Pen pen) {
        int before = pen.getInkLevel();
        pen.setInkLevel(before + refillUnits);
        System.out.println(
                "["
                        + pen.getType()
                        + "] Refilled from ink bottle. Ink: "
                        + before
                        + " -> "
                        + pen.getInkLevel());
    }
}
