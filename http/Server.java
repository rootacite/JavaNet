package http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import java.io.*;

public class Server {
	public void folderMethod2(String path) {
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (null != files) {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        folderMethod2(file2.getPath());
                    } else {
                        System.out.println("File:" + file2.getPath().substring(3));
						server.createContext(file2.getPath().substring(3).replace("\\","/"), new Index(file2.getPath().replace("\\","/")));
                    }
                }
            }
        } else {
			
        }
    }
	
	private HttpServer server=null;
	public Server() throws IOException
	{
	    server = HttpServer.create(new InetSocketAddress(80), 0);
		folderMethod2("WWW");
        
	}
	
	public void Start()
	{
		 server.start();
	}

    static  class Index implements HttpHandler{
		String path="";
		
		public Index(String _path)
		{
			path=_path;
		}
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            byte[] response = ServerIO.readFile(path);
            exchange.sendResponseHeaders(200, 0);
            OutputStream os = exchange.getResponseBody();
            os.write(response);
            os.close();
        }
    }
}

class ServerIO
{
    public static byte[] readFile(String strFile){
        try{
            InputStream is = new FileInputStream(strFile);
            int iAvail = is.available();
            byte[] bytes = new byte[iAvail];
            is.read(bytes);
            is.close();
			
			return bytes;
        }catch(Exception e){
            e.printStackTrace();
			return null;
        }
    }
}