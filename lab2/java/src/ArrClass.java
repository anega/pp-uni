public class ArrClass {
    static final int SMALLEST_EL_INDEX = 3;
    private final int arrSize;
    private final int[] arr;

    public ArrClass(int arrSize) {
        this.arrSize = arrSize;
        arr = new int[arrSize];

        for (int i = 0; i < arrSize; i++) {
            if (i == SMALLEST_EL_INDEX) {
                arr[i] = -1;
                continue;
            }
            arr[i] = i;
        }
    }
}
