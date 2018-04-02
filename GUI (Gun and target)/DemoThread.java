package l2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.*;

public class DemoThread extends JFrame {
    static Thread firstBombThread;
    static Thread GunThread;
    static Thread AimThread;
    static Thread BoomThread;
    static boolean bool1 = false;


    public DemoThread() {
        setTitle("GUN");
        setSize(new Dimension(750, 500));
        setLocationRelativeTo(null);
        setVisible(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

       JPanel content = new JPanel(new BorderLayout());
        setContentPane(content);

        Gun g = new Gun();
        content.add(g,BorderLayout.LINE_START);
        Aim a = new Aim();
        Bomb1 b = new Bomb1();
        content.add(b,BorderLayout.CENTER);
       // Boom boom = new Boom();
       // content.add(boom,BorderLayout.LINE_END);

        final JButton button = new JButton("Start");
        button.setSize(100, 50);
        content.add(button,BorderLayout.NORTH);
        final JButton fire = new JButton("Fire");
        fire.setSize(30, 50);
        content.add(fire,BorderLayout.SOUTH);




        button.addActionListener(new ActionListener() {
            private boolean pulsing = false;
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pulsing) {
                    AimThread.suspend();
                    firstBombThread.suspend();
                    content.remove(a);
                    pulsing = false;
                    button.setText("Start");


                } else {
                    pulsing = true;
                    content.add(a,BorderLayout.LINE_END);

                    AimThread.resume();
                    button.setText("Stop");

                }
            }
        });

        fire.addActionListener(new ActionListener() {
            private boolean pulsing2 = true;
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pulsing2) {
                    firstBombThread.resume();

                }
            }
        });






        setBackground(Color.WHITE);
        firstBombThread.suspend();
        AimThread.suspend();

    }



    public class Gun extends JPanel implements Runnable{
        boolean bool = true;

        public Gun() {
            setPreferredSize(new Dimension(100, 100));
            try {
                buffImg1 = ImageIO.read(new File("/Users/apple/Downloads/Gun.png"));
            }
            catch (IOException exc) {
            };
            GunThread = new Thread(this);
            GunThread.start();}

        public void run() {
            while (bool){

                repaint();
                try {
                    Thread.sleep(150);
                } catch (Exception exc) {
                };
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D graphics2D = (Graphics2D)g;
            graphics2D.drawImage(buffImg1, 0, 160, buffImg1Width, buffImg1Height, this);
        }
    }

    public class Bomb1 extends JPanel implements Runnable{
        public Bomb1() {
            setPreferredSize(new Dimension(100, 100));
            try {
                buffImg3 = ImageIO.read(new File("/Users/apple/Downloads/Ball.png"));
            }
            catch (IOException exc) {};
            firstBombThread = new Thread(this);
            firstBombThread.start();
        }



        public void run() {
            while (buffImg1Width != 0) {


                if(x<510) {
                    x+=30;
                    repaint();

                    try {
                        Thread.sleep(100);
                    }
                    catch (Exception exc) {};
                }

               /* else {
                    x=-60 ;
                    repaint();
                    try {
                        Thread.sleep(100);
                    }
                    catch (Exception exc) {};
                }*/
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D graphics2D = (Graphics2D)g;

                graphics2D.drawImage(buffImg3, x , 160,buffImg3Width, buffImg3Height, this);
        }
    }







    public class Aim extends JPanel implements Runnable{
        public Aim() {
            setPreferredSize(new Dimension(200, 200));
            try {
                buffImg2 = ImageIO.read(new File("/Users/apple/Downloads/target.png"));
                buffImg4 = ImageIO.read(new File("/Users/apple/Downloads/boom.png"));

            }
            catch (IOException exc) {};
            AimThread = new Thread(this);
            AimThread.start();}



        public void run() {
            while (buffImg1Width!=0) {
               if(x<460){ repaint();}
               else {

                    if(buffImg4Height<130){
                   buffImg4Width +=1;
                   buffImg4Height +=1;
                   repaint();}
               }



                try {
                    Thread.sleep(150);
                }
                catch (Exception exc) {};
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D graphics2D = (Graphics2D)g;
            if(x<460)
            graphics2D.drawImage(buffImg2, 0, 130, buffImg2Width, buffImg2Height, this);
            else graphics2D.drawImage(buffImg4, 0, 130, buffImg4Width, buffImg4Height, this);

        }
    }
   /* public class Boom extends JPanel implements Runnable{
        public Boom() {
            setPreferredSize(new Dimension(200, 200));
            try {
                buffImg4 = ImageIO.read(new File("/Users/apple/Downloads/boom.png"));

            }
            catch (IOException exc) {};
            AimThread = new Thread(this);
            AimThread.start();}



        public void run() {
            while (buffImg1Height!=0) {
                repaint();
                try {
                    Thread.sleep(150);
                }
                catch (Exception exc) {};
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D graphics2D = (Graphics2D)g;
            graphics2D.drawImage(buffImg4, 0, 130, buffImg4Width, buffImg4Height, this);
        }
    }
*/

    private BufferedImage buffImg1, buffImg2,buffImg3,buffImg4;
    public int x=-80,buffImg1Width = 100, buffImg1Height = 80, buffImg2Width = 80, buffImg2Height = 80,
            buffImg3Width = 25, buffImg3Height=25,buffImg4Width = 100, buffImg4Height=100;





    public static void main(String[] args) {
        new DemoThread().setVisible(true);
    }
}
