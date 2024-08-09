using System;
					
public class WrongSizeBO
{
	unsafe public string WrongSizeBO()
        {
		var size = Console.ReadLine();
string o = "";
        nuint t = (nuint)size * sizeof(char);

        var arg = String.Concat(Enumerable.Repeat("A", 4000));
        if (arg.Length >= (long)t) return "";
        else 
        {
           
                char c = 'c';
                char* ptr = &c;
                char* s = (char*)System.Runtime.InteropServices.NativeMemory.Alloc(t);
                int i = 0;
                while (i < arg.Length)
                {
                    s[i] = arg[i];
                    i++;
                }
                s[i++] = '$';
                s[i] = '\0';

                Console.WriteLine(o);
                System.Runtime.InteropServices.NativeMemory.Free(s);
        }
        return o;
}