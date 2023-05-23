import java.util.HashMap;

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
        HashMap<String, Integer> min = arrClass.getMinValue(startIndex, endIndex);
        arrClass.setTotalMin(min);
    }
}
