package vttp2022.day06;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;

public class MyWriter {

    public static void main(String[] args) throws IOException {

        FileOutputStream fos = new FileOutputStream(args[0]);
        BufferedOutputStream bos = new BufferedOutputStream(fos)
        ObjectOutputStream oos = new ObjectOutputStream(bos);

        //Nunber of lines
        oos.writeInt(2);
        oos.writeUTF("She sells sea shell on the sea shore\n");
        oos.writeUTF("Big black bug bleeds black blood\n");

        oos.flush();
        fos.close();
    }
    
}
