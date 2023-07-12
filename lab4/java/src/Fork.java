import java.util.concurrent.Semaphore;

public class Fork {
    Semaphore semaphore = new Semaphore(1);

    public void takeFork() {
        try {
            semaphore.acquire();
        } catch (InterruptedException ignored) {}
    }

    public void putFork() {
        semaphore.release();
    }
}
