import java.io.*;
import http.Server;

public class Program
{
	static public void main(String []argv)
	{
		try
		{
		Server HttpSv=new Server();
		HttpSv.Start();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
