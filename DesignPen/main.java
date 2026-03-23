
public class main {
    public static void main(String[] args) {
        Pen ballPen = PenFactory.createPen(PenType.BALLPOINT, "blue", false);
        Pen gelPen = PenFactory.createPen(PenType.GEL, "black", true);
        Pen fountainPen = PenFactory.createPen(PenType.FOUNTAIN, "green", true);

        try {
            ballPen.write("This should fail because pen is not started.");
        } catch (PenNotStartedException ex) {
            System.out.println("Expected error: " + ex.getMessage());
        }

        ballPen.start();
        ballPen.write("Ballpoint writing sample.");
        ballPen.close();

        gelPen.start();
        try {
            gelPen.write(
                    "Gel pens use more ink per character, so this sentence should consume ink faster.");
            gelPen.write(
                    "Trying another long sentence to force ink depletion and observe partial output behavior.");
        } catch (InkDepletedException ex) {
            System.out.println("Expected depletion: " + ex.getMessage());
            gelPen.refill();
            gelPen.write("After refill, gel pen can write again.");
        }
        gelPen.close();

        fountainPen.start();
        fountainPen.write("Fountain pen demonstration text.");
        fountainPen.refill();
        fountainPen.close();
    }
}