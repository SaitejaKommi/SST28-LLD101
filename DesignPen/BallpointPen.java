class BallpointPen extends Pen {
    BallpointPen(String color, boolean withCap, StartStrategy startStrategy, RefillStrategy refillStrategy) {
        super(PenType.BALLPOINT, color, withCap, 120, 120, startStrategy, refillStrategy);
    }

    @Override
    protected int inkUnitsPerCharacter() {
        return 1;
    }

    @Override
    protected void printWriteOutput(String text) {
        System.out.println("[BALLPOINT][" + getColor() + "] " + text);
    }
}
