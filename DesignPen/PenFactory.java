class PenFactory {
    private PenFactory() {}

    public static Pen createPen(PenType type, String color, boolean withCap) {
        validate(color);

        StartStrategy startStrategy = withCap ? new CapStartStrategy() : new ClickStartStrategy();

        switch (type) {
            case BALLPOINT:
                return new BallpointPen(color, withCap, startStrategy, new CartridgeRefillStrategy());
            case GEL:
                return new GelPen(color, withCap, startStrategy, new CartridgeRefillStrategy());
            case FOUNTAIN:
                return new FountainPen(color, withCap, startStrategy, new InkBottleRefillStrategy(70));
            default:
                throw new IllegalArgumentException("Unsupported pen type: " + type);
        }
    }

    private static void validate(String color) {
        if (color == null || color.trim().isEmpty()) {
            throw new IllegalArgumentException("Color cannot be null or empty.");
        }
    }
}
