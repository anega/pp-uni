﻿namespace c_sharp;

public class Program
{
    private const int BufferSize = 3;
    private const int ReqCount = 10;
    private const int ProdCount = 4;
    private const int ConsCount = 4;
    private readonly Semaphore _access = new(1, 1);
    private readonly Semaphore _empty = new(0, BufferSize);
    private readonly Semaphore _full = new(BufferSize, BufferSize);
    private readonly List<string> _buffer = new();

    private static void Main()
    {
        Program program = new();
    }

    private void BufferManager()
    {
    }

    private void Producer(int count)
    {
        for (int i = 0; i < count; i++)
        {
            _full.WaitOne();
            _access.WaitOne();
            _buffer.Add("Item " + i);
            Console.WriteLine($"Item {i} was added to the buffer.");
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