using System;
using System.Collections.Generic;
using System.Collections;

public class MyList
{
    private int[] data = { 1, 2, 3, 4, 5 };
    public IEnumerator GetEnumerator()
    {
        int i = 0;
        while (i < data.Length)
        {
            yield return data[i];
            i++;
        }
    }
}

namespace ConsoleApp1
{
    

    class Program
    {
        static void Main(string[] args)
        {
            var list = new MyList();

            foreach (var item in list)
            {
                Console.WriteLine(item);
            }

            IEnumerator it = list.GetEnumerator();
            it.MoveNext();
            Console.WriteLine(it.Current);      //1
            it.MoveNext();
            Console.WriteLine(it.Current);      //2
            it.MoveNext();
            Console.WriteLine(it.Current);      //3

        }
    }
}
