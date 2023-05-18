namespace c_sharp;

public class Program
{
    private static void Main(string[] args)
    {
        new Program().Start();
    }

    private void Start()
    {
        new Thread(Calculator).Start();
        new Thread(Calculator).Start();
        new Thread(Calculator).Start();

        Thread thread1 = new Thread(Calculator);
        thread1.Start();

        new Thread(Stopper).Start();
    }

    private void Calculator()
    {
        long sum = 0;
        do
        {
            sum++;
        } while (!CanStop);

        Console.WriteLine(sum);
    }

    private bool CanStop { get; set; }

    private void Stopper()
    {
        Thread.Sleep(3 * 1000);
        CanStop = true;
    }
}