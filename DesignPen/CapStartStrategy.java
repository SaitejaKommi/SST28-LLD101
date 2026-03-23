class CapStartStrategy implements StartStrategy {
    @Override
    public void start(Pen pen) {
        System.out.println("[" + pen.getType() + "] Cap removed. Pen is ready to write.");
    }

    @Override
    public void close(Pen pen) {
        System.out.println("[" + pen.getType() + "] Cap placed back. Pen is closed.");
    }
}
