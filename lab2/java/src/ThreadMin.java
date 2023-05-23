public class ThreadMin extends Thread {
    private final int startIndex;
    private final int endIndex;
    private final ArrClass arrClass;

    public ThreadMin(int startIndex, int endIndex, ArrClass arrClass) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.arrClass = arrClass;
    }

    @Override
    public void run() {
        int min = arrClass.getMinValue(startIndex, endIndex);
        System.out.println("Thread " + Thread.currentThread().threadId() + ": min value is " + min);
    }
}
