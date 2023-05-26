namespace Lab2;

public class Program
{
    private const int ArrSize = 10000000;
    private const int SmallestNum = -1;
    private const int SmallestNumIndex = 3;
    private readonly int[] _arr = new int[ArrSize];
    private const int ThreadNum = 8;
    private readonly object _minLocker = new();
    private readonly Thread[] _thread = new Thread[ThreadNum];
    private int _totalMinIndex;

    private Program()
    {
        for (int i = 0; i < ArrSize; i++)
        {
            if (i == SmallestNumIndex)
            {
                _arr[i] = SmallestNum;
                continue;
            }

            _arr[i] = i;
        }
    }

    private static void Main()
    {
        Program program = new();
        int totalMinIndex = program.GetTotalMinIndex();
        int totalMinValue = program._arr[totalMinIndex];
        Console.WriteLine($"Minimal array element is {totalMinValue} and it's index is {totalMinIndex}");
    }

    private int GetPartMinIndex(int startIndex, int endIndex)
    {
        int minValue = _arr[startIndex];
        int minIndex = startIndex;
        for (int i = startIndex; i <= endIndex; i++)
        {
            if (minValue > _arr[i])
            {
                minIndex = i;
            }
        }

        return minIndex;
    }

    private void SetTotalMinIndex(int startIndex, int endIndex)
    {
        int minIndex = GetPartMinIndex(startIndex, endIndex);
        lock (_minLocker)
        {
            if (_arr[_totalMinIndex] > _arr[minIndex])
            {
                _totalMinIndex = minIndex;
            }
        }
    }

    private int GetTotalMinIndex()
    {
        const int partSize = ArrSize / ThreadNum;
        for (int i = 0; i < ThreadNum; i++)
        {
            int startIndex = i * partSize;
            int endIndex = (i + 1) * partSize - 1;
            if (i == ThreadNum - 1)
            {
                endIndex = ArrSize - 1;
            }

            _thread[i] = new Thread(() => SetTotalMinIndex(startIndex, endIndex));
            _thread[i].Start();
        }

        for (int i = 0; i < ThreadNum; i++)
        {
            _thread[i].Join();
        }

        return _totalMinIndex;
    }
}