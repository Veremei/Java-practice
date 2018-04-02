package l3;
import java.net.*;
import java.io.*;

class Server{

    private static class ClientHandler implements Runnable
    {
        char[] b1 = new char[255];

        int i;

        // локальные используемые переменные
        boolean choose;
        private int clientid=0;
        private InputStream is;
        private OutputStream os;
        private Socket client;
        public ClientHandler(int id,Socket s)


        {
            clientid=id;
            client=s;
        }
        public void run()
        {
            try
            {
                os=client.getOutputStream();//получили выходной поток для записи данных
                is=client.getInputStream();//получили входной поток для чтения данных

                boolean flag=true;
                while(flag==true){//цикл для работы с одним клиентом
                    int k;
                    byte clientMessage[]=new byte[100];//байтовый массив, который будет использоваться при чтении из входного потока
                    try
                    {

                        k=is.read(clientMessage);//чтение иформации, посланной клиентом, из вхоного потока в массив clientMessage[] k - колличество считанных байт

                    }
                    catch(Exception e) //если клиент просто остановил свою работу не отправив disconnect
                    {
                        break;//завершение цикла обработки данного клиента
                    }
                    String tempString=new String(clientMessage,0,k);// формирование строки из того что считанно

                    tempString=tempString.trim();// убираем пробелы в конце и в начале

                    if(tempString.compareTo("disconnect")==0 ) {
                        System.out.println("message \"disconnect\" recieved from client");
                        flag=false;// приведет к прекращению повторения цикла
                    }

                    else {
                        //подразумеваем что строка передана в виде a знак b
                        try
                        {
                            System.out.println("Строка " + tempString );
                            b1=tempString.toCharArray();
                            if (b1.length==0){
                                System.out.println("END");

                                flag=false;}
                            for(i=0;i<=b1.length;i++)
                                if(i%4==0 && i!=0){
                                    b1[i-1] = '%' ;}


                            String s=String.valueOf(b1);
                            System.out.println("-----> "+s);
                            os.write(s.getBytes());

                        } catch(Exception e)// если не получилось что то преобразовать или посчитать
                        {
                            os.write(("Wrong message. "+e.toString()).getBytes());// отправляем клиенту сообщение об ошибке состоящее из wrong message  и описания ошибки
                        }}}
                is.close();//закрытие входного потока

                os.close();//закрытие выходного потока
                client.close();//закрытие сокета, выделенного для работы с подключившимся клиентом
            }catch(Exception e)
            {

            }
            System.out.println("Client "+clientid+" disconnected");
        }}
    static int countclients=0;//счетчик подключившихся клиентов



    public static void main(String args[]){
        ServerSocket sock;

        try{
            sock=new ServerSocket(1024);//создаем серверный сокет работающий локально по порту 1024

            while(true){//бесконечный цикл для возможности подключения последовательно нескольних клиентов
                Socket client=sock.accept();//сработает, когда клиент подключится, для него выделится отдельный сокет client
                countclients++;//количество подключившихся клиентов увеличивается на 1

                System.out.println("Client  "+countclients+"  connected");//вывод сообщения
                new Thread(new ClientHandler(countclients++,client)).start();// стартуем новый поток обработки сообщений нового клиента
// сама обработка находится в методе run класса ClientHandler
            }}
        catch(Exception e){// в случае возникновения других не предвиденных ошибок
            System.out.println("Error "+ e.toString());
        }}}
