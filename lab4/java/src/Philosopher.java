public class Philosopher extends Thread {
    private final int philosopherNumber;

    public Philosopher(int phNum) {
        philosopherNumber = phNum;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Philosopher " + philosopherNumber + " is thinking.");
            System.out.println("Philosopher " + philosopherNumber + " took left fork.");
            System.out.println("Philosopher " + philosopherNumber + " took right fork.");
            System.out.println("Philosopher " + philosopherNumber + " is eating.");
            System.out.println("Philosopher " + philosopherNumber + " put down left fork.");
            System.out.println("Philosopher " + philosopherNumber + " put down right fork.");
        }
    }
}
