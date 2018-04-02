package l4;
import java.io.*;
import java.net.*;
public  class Server
{
    public static void main(String[] args) throws IOException, InterruptedException {
        new Server();
    }
    double sum1=0,sum2=0;
    DatagramSocket st;
    byte [] buf=new byte[100];
    DatagramPacket dp=new DatagramPacket(buf,100);

    Server()throws IOException, InterruptedException{
        st=new DatagramSocket(12345);
        while (true){
            System.out.println("////////////////////");
            listen();
        }

    }

    private void listen() throws IOException, InterruptedException
    {
        int a,b,c;
        st.receive(dp);
        String str=new String(dp.getData());
        str=str.substring(0, str.indexOf('\n'));
        System.out.println("number "+str+" recieved as a");
        a=Integer.parseInt(str);
        st.receive(dp);
        str=new String(dp.getData());
        str=str.substring(0, str.indexOf('\n'));
        System.out.println("number "+str+" recieved as b");
        b=Integer.parseInt(str);
        st.receive(dp);
        str=new String(dp.getData());
        str=str.substring(0, str.indexOf('\n'));
        System.out.println("number "+str+" recieved as c");
        c=Integer.parseInt(str);
        Thread t1=new Thread(new Runnable()
        {
            public void run()
            {
                for(int i=a;i<=b;i++)
                {
                    sum1+=(i-1)*(i-1);
                }
            }
        });





        Thread t2=new Thread(new Runnable()
        {
            public void run()
            {
                for(int i=b;i<=c;i++)
                {
                   // int x,y;
                   // x = 2*i;
                   // y = (7*i)+1;
                    sum2 += (2*i)/7*i+1;

                }
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(sum1);
        System.out.println(sum2);
        sendBack();
    }
    private void sendBack() throws IOException
    {
        String str=String.valueOf(sum1-sum2);
        byte [] send=str.getBytes();
        dp=new DatagramPacket(send, send.length, InetAddress.getByName("localhost"),dp.getPort() );
        st.send(dp);
        sum1=0;
        sum2=0;
    }

}

