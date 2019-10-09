import java.util.Scanner;


public class Supertables
{
	public static void main(String[] args)
	{
		int A,B,tmp;
		int k=0;
		int table[]={};
		int a,b;
		int n,i=1,j;
		
		Scanner scan=new Scanner(System.in);
		a=scan.nextInt();
		b=scan.nextInt();
		n=scan.nextInt();
		while(i<=n)
		{
			A=a*i;
			B=b*i;
			if(A==B)
				table[k++]=A;
			else
			{
				table[k++]=A;
				table[k++]=B;
			}
			i++;
		}
        for (i = 1; i < 6; i++) 
        {
            tmp = table[i];
            for (j = i; j >= 1 && tmp < table[j-1]; j--)
                    table[j] = table[j-1];
            table[j] = tmp;
        }
		System.out.println(table[(int) n]);
		scan.close();
	}
	
}
