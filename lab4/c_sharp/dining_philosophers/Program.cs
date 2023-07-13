namespace dining_philosophers;

public class DiningPhilosophers
{
    private const int PhilosophersQuantity = 5;
    private static readonly Thread[] Philosophers = new Thread[PhilosophersQuantity];
    private static readonly List<Semaphore> Forks = new(PhilosophersQuantity);

    private static void Main()
    {
        for (int i = 0; i < PhilosophersQuantity; i++)
        {
            int i1 = i;
            Forks.Add(new Semaphore(1, 1));
            Philosophers[i1] = new Thread(() =>
                StartPhilosopher(i1, Forks[i1], Forks[(i1 + 1) % PhilosophersQuantity]));
            Philosophers[i1].Start();
        }
    }

    private static void StartPhilosopher(int philosopherId, Semaphore leftFork, Semaphore rightFork)
    {
        while (true)
        {
            Console.WriteLine($"Philosopher {philosopherId} is thinking.");
            if (philosopherId % 2 == 0)
            {
                leftFork.WaitOne();
                Console.WriteLine($"Philosopher {philosopherId} takes left fork.");
                rightFork.WaitOne();
                Console.WriteLine($"Philosopher {philosopherId} takes right fork.");
            }
            else
            {
                rightFork.WaitOne();
                Console.WriteLine($"Philosopher {philosopherId} takes right fork.");
                leftFork.WaitOne();
                Console.WriteLine($"Philosopher {philosopherId} takes left fork.");
            }

            Console.WriteLine($"Philosopher {philosopherId} is eating.");
            leftFork.Release();
            Console.WriteLine($"Philosopher {philosopherId} puts down left fork.");
            rightFork.Release();
            Console.WriteLine($"Philosopher {philosopherId} puts down right fork.");
        }
    }
}