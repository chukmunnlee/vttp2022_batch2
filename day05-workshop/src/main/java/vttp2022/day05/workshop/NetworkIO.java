package vttp2022.day05.workshop;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class NetworkIO {

    private InputStream is;
    private DataInputStream dis;
    private OutputStream os;
    private DataOutputStream dos;

    public NetworkIO(Socket sock) throws IOException {
        is = sock.getInputStream();
        dis = new DataInputStream(is);
        os = sock.getOutputStream();
        dos = new DataOutputStream(os);
    }

    public String read() throws IOException {
        return dis.readUTF();
    }
    public void write(String msg) throws IOException {
        dos.writeUTF(msg);
        dos.flush();
    }

    public void close() {
        try {
            dis.close();
            is.close();
            dos.close();
            os.close();
        } catch (IOException ex) { 
            // don't care if we get exception because we are closing the connection
        }
    }
    
}
