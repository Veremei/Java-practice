package l4;
import java.net.*;
import java.io.*;
import java.util.*;
public class Client {
    public static void main(String[] args) throws IOException {
        DatagramSocket st=new DatagramSocket();
        DatagramPacket dp = null;
        InetAddress loc=InetAddress.getByName("localhost");
        byte [] buf=new byte[100];
        Scanner in = new Scanner(System.in);
        int arr[] = new int [3];
        for (int i = 0; i < arr.length; i++)
        {
            System.out.print("Введите элемент arr["+ i +"]:");

            int k=System.in.read(buf);

            dp=new DatagramPacket(buf, k, loc,12345 );
            st.send(dp);
        }
        dp=new DatagramPacket(buf,100);
        st.receive(dp);
        String str=new String(dp.getData());
        str=str.trim();
        System.out.println("the answer is "+str);
        st.close();
    }
}
