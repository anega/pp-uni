public class ArrClass {
    static final int SMALLEST_EL_INDEX = 3;
    private final int arrSize;
    private final int threadNum;
    private final int[] arr;

    public ArrClass(int arrSize, int threadNum) {
        this.arrSize = arrSize;
        this.threadNum = threadNum;
        arr = new int[arrSize];

        for (int i = 0; i < arrSize; i++) {
            if (i == SMALLEST_EL_INDEX) {
                arr[i] = -1;
                continue;
            }
            arr[i] = i;
        }
    }

    public int getMinValue(int startIndex, int endIndex) {
        int minValue = arr[startIndex];
        for (int i = startIndex; i < endIndex; i++) {
            if (minValue > arr[i]) {
                minValue = arr[i];
            }
        }

        return minValue;
    }

    public void getTotalMinValue() {
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
    }
}
