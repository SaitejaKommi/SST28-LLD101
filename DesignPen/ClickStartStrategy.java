class ClickStartStrategy implements StartStrategy {
    @Override
    public void start(Pen pen) {
        System.out.println("[" + pen.getType() + "] Clicked ON. Pen is ready to write.");
    }

    @Override
    public void close(Pen pen) {
        System.out.println("[" + pen.getType() + "] Clicked OFF. Pen is closed.");
    }
}
