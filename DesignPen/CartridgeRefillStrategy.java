class CartridgeRefillStrategy implements RefillStrategy {
    @Override
    public void refill(Pen pen) {
        int before = pen.getInkLevel();
        pen.setInkLevel(pen.getMaxInk());
        System.out.println(
                "["
                        + pen.getType()
                        + "] Cartridge replaced. Ink: "
                        + before
                        + " -> "
                        + pen.getInkLevel());
    }
}
