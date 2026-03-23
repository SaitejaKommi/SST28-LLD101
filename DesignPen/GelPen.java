class GelPen extends Pen {
    GelPen(String color, boolean withCap, StartStrategy startStrategy, RefillStrategy refillStrategy) {
        super(PenType.GEL, color, withCap, 100, 100, startStrategy, refillStrategy);
    }

    @Override
    protected int inkUnitsPerCharacter() {
        return 2;
    }

    @Override
    protected void printWriteOutput(String text) {
        System.out.println("[GEL][" + getColor() + "] smooth stroke -> " + text);
    }
}
