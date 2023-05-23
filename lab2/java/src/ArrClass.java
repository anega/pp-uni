import java.util.HashMap;

public class ArrClass {
    static final int SMALLEST_EL_INDEX = 3;
    private final int arrSize;
    private final int threadNum;
    private final int[] arr;
    private final HashMap<String, Integer> totalMin = new HashMap<>();

    public ArrClass(int arrSize, int threadNum) {
        this.arrSize = arrSize;
        this.threadNum = threadNum;
        arr = new int[arrSize];
        totalMin.put("minIndex", 0);
        totalMin.put("minValue", 0);

        for (int i = 0; i < arrSize; i++) {
            if (i == SMALLEST_EL_INDEX) {
                arr[i] = -1;
                continue;
            }
            arr[i] = i;
        }
    }

    synchronized public void setTotalMin(HashMap<String, Integer> min) {
        int minValue = min.get("minValue");
        int minIndex = min.get("minIndex");
        if (minValue < totalMin.get("minValue")) {
            totalMin.put("minIndex", minIndex);
            totalMin.put("minValue", minValue);
        }
    }

    public HashMap<String, Integer> getMinValue(int startIndex, int endIndex) {
        HashMap<String, Integer> minElement = new HashMap<>();
        minElement.put("minIndex", startIndex);
        minElement.put("minValue", arr[startIndex]);
        for (int i = startIndex; i < endIndex; i++) {
            if (minElement.get("minValue") > arr[i]) {
                minElement.put("minIndex", i);
                minElement.put("minValue", arr[i]);
            }
        }

        return minElement;
    }

    public HashMap<String, Integer> getTotalMinValue() {
        ThreadMin[] threadMin = new ThreadMin[threadNum];
        int partSize = arrSize / threadNum;

        for (int i = 0; i < threadNum; i++) {
            int startIndex = i * partSize;
            int endIndex = (i + 1) * partSize - 1;
            if (i == threadNum - 1) {
                endIndex = arrSize - 1;
            }
            threadMin[i] = new ThreadMin(startIndex, endIndex, this);
            threadMin[i].start();
        }
        for (int i = 0; i < threadNum; i++) {
            try {
                threadMin[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return totalMin;
    }
}
