public class Main {
    public static void main(String[] args) {
        BreakThread breakThread = new BreakThread();

        for (int i = 1; i <= 5; i++) {
            MainThread myThread = new MainThread(i, breakThread);
            myThread.start();
        }

        new Thread(breakThread).start();
    }
}
