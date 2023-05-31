public class Consumer extends Thread {
    private final BufferManager manager;
    private final int count;

    public Consumer(BufferManager manager, int count) {
        this.manager = manager;
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            String item;
            try {
                manager.empty.acquire();
                synchronized (this) {
                    item = manager.buffer.get(0);
                    manager.buffer.remove(0);
                    System.out.println("Item '" + item + "' consumed.");
                }
                manager.full.release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
