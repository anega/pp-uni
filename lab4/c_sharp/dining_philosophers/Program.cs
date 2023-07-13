namespace dining_philosophers;

public class DiningPhilosophers
{
    private const int PhilosophersQuantity = 5;
    private static readonly Thread[] Philosophers = new Thread[PhilosophersQuantity];

    private static void Main()
    {
        for (int i = 0; i < PhilosophersQuantity; i++)
        {
            int i1 = i;
            Philosophers[i1] = new Thread(() => StartPhilosopher(i1));
            Philosophers[i1].Start();
        }
    }

    private static void StartPhilosopher(int philosopherId)
    {
        while (true)
        {
            Console.WriteLine($"Philosopher {philosopherId} is thinking.");
            Console.WriteLine($"Philosopher {philosopherId} takes left fork.");
            Console.WriteLine($"Philosopher {philosopherId} takes right fork.");
            Console.WriteLine($"Philosopher {philosopherId} is eating.");
            Console.WriteLine($"Philosopher {philosopherId} puts down left fork.");
            Console.WriteLine($"Philosopher {philosopherId} puts down right fork.");
        }
    }
}