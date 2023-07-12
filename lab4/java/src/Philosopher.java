public class Philosopher extends Thread {
    private final int philosopherNumber;
    private final Fork leftFork;
    private final Fork rightFork;

    public Philosopher(int phNum, Fork leftFork, Fork rightFork) {
        philosopherNumber = phNum;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Philosopher " + philosopherNumber + " is thinking.");
            leftFork.takeFork();
            System.out.println("Philosopher " + philosopherNumber + " took left fork.");
            rightFork.takeFork();
            System.out.println("Philosopher " + philosopherNumber + " took right fork.");
            System.out.println("Philosopher " + philosopherNumber + " is eating.");
            leftFork.putFork();
            System.out.println("Philosopher " + philosopherNumber + " put down left fork.");
            rightFork.putFork();
            System.out.println("Philosopher " + philosopherNumber + " put down right fork.");
        }
    }
}
