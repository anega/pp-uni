public class Main {
    static final int ARR_SIZE = 20;
    static final int THREAD_NUM = 3;

    public static void main(String[] args) {
        ArrClass arrClass = new ArrClass(ARR_SIZE, THREAD_NUM);
        arrClass.getTotalMinValue();
    }
}