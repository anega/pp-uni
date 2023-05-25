namespace Lab2;

public class Program
{
    private const int ArrSize = 10000000;
    private const int SmallestNum = -1;
    private const int SmallestNumIndex = 3;
    private readonly int[] _arr = new int[ArrSize];

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
}