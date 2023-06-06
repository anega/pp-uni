namespace c_sharp;

public class Program
{
    private const int BufferSize = 3;
    private const int ReqCount = 4;
    private const int ProdCount = 1;
    private const int ConsCount = 2;
    private readonly Semaphore _access = new(1, 1);
    private readonly Semaphore _empty = new(0, BufferSize);
    private readonly Semaphore _full = new(BufferSize, BufferSize);
    private readonly List<string> _buffer = new();

    private static void Main()
    {
        Program program = new();
        program.BufferManager(ReqCount, ProdCount, ConsCount);
    }

    private void BufferManager(int reqCount, int prodThreadCount, int consThreadCount)
    {
        for (int i = 0; i < prodThreadCount; i++)
        {
            Thread pThread = new(() => Producer(reqCount));
            pThread.Start();
        }

        for (int i = 0; i < consThreadCount; i++)
        {
            Thread cThread = new(() => Consumer(reqCount));
            cThread.Start();
        }
    }

    private void Producer(int count)
    {
        for (int i = 0; i < count; i++)
        {
            _full.WaitOne();
            _access.WaitOne();
            _buffer.Add("Item " + i);
            Console.WriteLine($"Item {i} produced.");
            _access.Release();
            _empty.Release();
        }
    }

    private void Consumer(int count)
    {
        for (int i = 0; i < count; i++)
        {
            _empty.WaitOne();
            _access.WaitOne();
            string item = _buffer.ElementAt(0);
            _buffer.RemoveAt(0);
            Console.WriteLine($"Item {item} consumed.");
            _access.Release();
            _full.Release();
        }
    }
}