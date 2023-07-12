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
            if (philosopherNumber % 2 == 0) {
                leftFork.takeFork();
                System.out.println("Philosopher " + philosopherNumber + " took left fork(" + leftFork.id + ").");
                rightFork.takeFork();
                System.out.println("Philosopher " + philosopherNumber + " took right fork(" + rightFork.id + ").");
            } else {
                rightFork.takeFork();
                System.out.println("Philosopher " + philosopherNumber + " took right fork(" + rightFork.id + ").");
                leftFork.takeFork();
                System.out.println("Philosopher " + philosopherNumber + " took left fork(" + leftFork.id + ").");
            }
            System.out.println("Philosopher " + philosopherNumber + " is eating.");
            leftFork.putFork();
            System.out.println("Philosopher " + philosopherNumber + " put down left fork(" + leftFork.id + ").");
            rightFork.putFork();
            System.out.println("Philosopher " + philosopherNumber + " put down right fork(" + rightFork.id + ").");
        }
    }
}
