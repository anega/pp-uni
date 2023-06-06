namespace c_sharp;

public class Program
{
    private const int BufferSize = 3;
    private const int ReqCount = 10;
    private const int ProdCount = 4;
    private const int ConsCount = 4;
    private Semaphore _access = new Semaphore(1, 1);
    private Semaphore _empty = new Semaphore(0, BufferSize);
    private Semaphore _full = new Semaphore(BufferSize, BufferSize);
    private readonly List<string> _buffer = new();

    private static void Main()
    {
        Program program = new();
    }

    private void BufferManager()
    {
    }

    private void Producer()
    {
    }

    private void Consumer()
    {
    }
}