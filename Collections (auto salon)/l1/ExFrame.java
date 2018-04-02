package l1;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExFrame extends JFrame {
    String err;
    ExFrame(String err){
        this.setLayout(null);
        this.err = err;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 200);
        JButton button = new JButton("OK");
        button.setLocation(90,100);
        JLabel lbl = new JLabel(err);
        lbl.setLocation(120, 20);
        lbl.setSize(300, 50);
        ActionListener actionListener = new TestActionListener();
        button.addActionListener(actionListener);
        add(button);
        add(lbl);
        button.setSize(300, 50);
    }

    public class TestActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
        }
    }
}
