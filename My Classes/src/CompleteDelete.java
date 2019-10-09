import java.io.File;
import java.util.Scanner;


public class CompleteDelete 
{
	public static void main(String[] args) 
	{
		try
		{
			Scanner sc=new Scanner(System.in);
			System.out.print(" Enter the path of the file you want to delete = ");
			String inp=sc.nextLine();
			File f=new File(inp);
			if(f.isFile())f.delete();
			else 
			{
				if(f.list().length==0)
				{
					System.out.println(f.getName()+" is deleted.");
					f.delete();
				}
				else 
				{
					del(f.getAbsolutePath());
					System.out.println(f.getName()+" is deleted.");
					f.delete();
				}
			}
			f=null;
			sc.close();
		}
		catch(Exception e)
		{
			System.out.println(e+" has occurred.");
			System.out.println("-----try again-----");
		}
	}
	static void del(String pn)
	{
		File f1=new File(pn);
		String lis[]=f1.list();
		for(String z:lis)
		{
			File f2=new File(pn+"\\"+z);
			if(f2.isFile())
			{
				System.out.println(f2.getName()+" is deleted.");
				f2.delete();
			}
			else 
			{
				del(f2.getAbsolutePath());
				System.out.println(f2.getName()+" is deleted.");
				f2.delete();
			}
		}
	}
}


