package Gunjungps;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class javasocketclient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
try {
	Socket socket=new Socket("localhost", 5001);
	System.out.println("client started:localhost, 5001");
			
	ObjectOutputStream outstream=new ObjectOutputStream(socket.getOutputStream());
	outstream.writeUTF("Hello");
	outstream.flush();
	
	System.out.println("Hello sent.");
	
	
		ObjectInputStream instream=new ObjectInputStream(socket.getInputStream());
	String inStr=instream.readUTF();
	
	System.out.println("inStr from client:"+inStr);
	
	socket.close();
	

}
catch(IOException e) {
	e.printStackTrace();
}
	}

}
