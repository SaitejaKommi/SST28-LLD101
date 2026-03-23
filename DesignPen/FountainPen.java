class FountainPen extends Pen {
    FountainPen(String color, boolean withCap, StartStrategy startStrategy, RefillStrategy refillStrategy) {
        super(PenType.FOUNTAIN, color, withCap, 150, 150, startStrategy, refillStrategy);
    }

    @Override
    protected int inkUnitsPerCharacter() {
        return 1;
    }

    @Override
    protected void printWriteOutput(String text) {
        System.out.println("[FOUNTAIN][" + getColor() + "] elegant flow -> " + text);
    }
}
