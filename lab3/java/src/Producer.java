public class Producer extends Thread {
    private final BufferManager manager;
    private final int count;

    public Producer(BufferManager manager, int count) {
        this.manager = manager;
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            try {
                manager.full.acquire();
                synchronized (this) {
                    manager.buffer.add("Item " + i);
                    System.out.println("Item " + i + " produced.");
                }
                manager.empty.release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
