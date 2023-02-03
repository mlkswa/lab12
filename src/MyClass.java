import java.util.Arrays;

public class MyClass{
    static int SIZE = 10000000;
    static int HALF = SIZE/2;

    public static void firstMethod(){
        float[] arr = new float[SIZE];
        Arrays.fill(arr, 1);
        long a = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++){
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i /5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("MethodOne " + (System.currentTimeMillis() - a));
    }
    public static void secondMethod() throws InterruptedException {
        float[] arr = new float[SIZE];
        Arrays.fill(arr, 1);
        long a = System.currentTimeMillis();
        float[]arrOne = new float[HALF];
        float[]arrTwo = new float[HALF];
        System.arraycopy(arr, 0, arrOne,0, HALF);
        System.arraycopy(arr, HALF, arrTwo, 0,HALF);

        Thread mOne = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < HALF; i++){
                    arr[i] = (float) (arr[i]* Math.sin(0.2f + i /5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });
        Thread mTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < HALF; i++){
                    arr[i] = (float) (arr[i]* Math.sin(0.2f + (i+HALF) /5) * Math.cos(0.2f + (i+HALF) / 5) * Math.cos(0.4f + (i+HALF) / 2));
                }
            }
        });
        mOne.start();
        mTwo.start();
        mOne.join();
        mTwo.join();
        System.arraycopy(arrOne,0,arr,0,HALF);
        System.arraycopy(arrTwo,0,arr,HALF,HALF);
        System.out.println("MethodTwo = " + (System.currentTimeMillis() - a));
    }
    public static void main(String[] args) throws InterruptedException{
firstMethod();
secondMethod();
    }
}
