import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class BufferManager {
    public Semaphore empty;
    public Semaphore full;
    public ArrayList<String> buffer = new ArrayList<>();

    public BufferManager(int bufferSize) {
        empty = new Semaphore(0);
        full = new Semaphore(bufferSize);
    }
}
