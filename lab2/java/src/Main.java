public class Main {
    static final int ARR_SIZE = 100000000;
    static final int THREAD_NUM = 8;

    public static void main(String[] args) {
        ArrClass arrClass = new ArrClass(ARR_SIZE, THREAD_NUM);
        int minIndex = arrClass.getTotalMinValue().get("minIndex");
        int minValue = arrClass.getTotalMinValue().get("minValue");
        System.out.println("Minimal array element is " + minValue + " and it's index is " + minIndex);
    }
}