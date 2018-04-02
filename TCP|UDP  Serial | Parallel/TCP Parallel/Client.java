package l3;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class Client{
    static final String options[]={"send","disconnect"};
    public static void main(String args[]){
        Socket sock;
        InputStream is;
        OutputStream os;
        try{
            sock=new Socket(InetAddress.getByName("localhost"),1024);//создаем сокет клиента и соединяемся с сервером который находится на порту 1024 на этом же компьютере
            is=sock.getInputStream();//получили входной поток для чтения данных
            os=sock.getOutputStream();//получили выходной поток для записи данных

            boolean cont=true; // если станет = false цикл прервется
            while(cont){//бесконечный цикл для принятия и отсылки сообщений серверу

                String stringmessage=JOptionPane.showInputDialog("Введите 1 строку");//считываем полылаемое сообщение для сервера


                if(stringmessage.compareTo("disconnect")==0 ) {
                    byte bytemessage1[]=stringmessage.getBytes();//преобразование сообщения из типа String в тип byte[]

                    os.write(bytemessage1);//запись в выходной поток преобразованного сообщения
                    JOptionPane.showMessageDialog(null, "Отключение от сервера...");
                    break;	//выход из цила while
                }
                else {
                    byte bytemessage3[]=stringmessage.getBytes();//преобразование сообщения из типа String в тип byte[]
                    os.write(bytemessage3);//запись в выходной поток преобразованного сообщения
                    byte readmessage[]=new byte[100];//создаем массив байт для чтения информации от сервера
                    int k=is.read(readmessage);//считываем сообщение посланное от сервера k - количетсво считанных символов

                    JOptionPane.showMessageDialog(null, "Полученное сообщение от сервера: \n"+new String(readmessage,0,k));//выводим полученное сообщение
                    cont=false;
                }}
            is.close();//закрываем входной поток
            os.close();//закрываем выходной поток

            sock.close();//закрываем сокет клиента
        }
        catch(Exception e){// если возникла непредвиденная ошибка
            System.out.println("Error "+ e.toString());
        }}}


