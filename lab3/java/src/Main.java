public class Main {
    public static final int BUFFER_SIZE = 3;
    public static final int REQ_COUNT = 10;
    public static final int PROD_COUNT = 4;
    public static final int CONS_COUNT = 4;

    public static void main(String[] args) {
        BufferManager manager = new BufferManager(BUFFER_SIZE);
    }
}