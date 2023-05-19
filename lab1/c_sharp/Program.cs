namespace c_sharp;

public class Program
{
    private static void Main(string[] args)
    {
        new Program().Start();
    }

    private void Start()
    {
        for (int i = 1; i <= 3; i++)
        {
            int threadId = i;
            Thread myThread = new Thread(() => Calculator(threadId, 2));
            myThread.Start();
        }

        new Thread(Stopper).Start();
    }

    private void Calculator(int threadId, int sumStep)
    {
        long sum = 0;
        long elementsCount = 0;
        do
        {
            sum += sumStep;
            elementsCount++;
        } while (!CanStop);

        Console.WriteLine($"Thread {threadId}: sum = {sum}, elements count: {elementsCount}");
    }

    private bool CanStop { get; set; }

    private void Stopper()
    {
        Thread.Sleep(30 * 1000);
        CanStop = true;
    }
}