public class Main {
    private static final int PHILOSOPHERS_QUANTITY = 5;
    private static final Fork [] forks = new Fork[PHILOSOPHERS_QUANTITY];

    public static void main(String[] args) {
        for (int i = 0; i < PHILOSOPHERS_QUANTITY; i++) {
            forks[i] = new Fork();
        }

        for (int i = 0; i < PHILOSOPHERS_QUANTITY; i++) {
            Philosopher philosopher = new Philosopher(i, forks[i], forks[(i + 1) % PHILOSOPHERS_QUANTITY]);
            philosopher.start();
        }
    }
}