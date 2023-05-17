public class MainThread extends Thread {
    private final int id;
    private final int sumStep;
    private final BreakThread breakThread;

    public MainThread(int id, int sumStep, BreakThread breakThread) {
        this.id = id;
        this.sumStep = sumStep;
        this.breakThread = breakThread;
    }

    @Override
    public void run() {
        long sum = 0;
        long elementsCount = 0;
        boolean isStop = false;

        do {
            sum += sumStep;
            isStop = breakThread.isCanBreak();
            elementsCount++;
        } while (!isStop);

        System.out.printf("Thread %d: sum = %s, elements count: %d%n", id, sum, elementsCount);
    }
}
