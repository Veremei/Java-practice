package l1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FindBookDialog extends JDialog implements ActionListener {
    private static final String FIND = "Найти";
    private static final String CANCEL = "Закрыть";
    private static final int PAD = 10;
    private static final int W_L = 100;
    private static final int W_T = 300;
    private static final int W_B = 120;
    private static final int H_B = 25;
    private String str;
    private boolean save = false;

    private final JTextPane txtAuthor = new JTextPane();

    public FindBookDialog(){
        this(null);
    }

    public FindBookDialog(Book book){
        setLayout(null);
        buildFields();
        initFields();
        buildButtons();
        setModal(true);
        setResizable(false);
        setBounds(300, 300, 450, 300);
        setVisible(true);
    }

    private void buildFields() {

        JLabel lblAuthor = new JLabel("Автор:");
        lblAuthor.setHorizontalAlignment(SwingConstants.RIGHT);
        lblAuthor.setBounds(new Rectangle(PAD, 1 * H_B + PAD, W_L, H_B));
        add(lblAuthor);
        txtAuthor.setBounds(new Rectangle(W_L + 2 * PAD, 1 * H_B + PAD, W_T, H_B));
        txtAuthor.setBorder(BorderFactory.createEtchedBorder());
        add(txtAuthor);
    }

    private void buildButtons() {
        JButton btnSave = new JButton("Найти");
        btnSave.setActionCommand(FIND);
        btnSave.addActionListener(this);
        btnSave.setBounds(new Rectangle(W_B + 3 * PAD - 20, 6 * H_B + PAD, W_B, H_B));
        add(btnSave);
        JButton btnCancel = new JButton("Закрыть");
        btnCancel.setActionCommand(CANCEL);
        btnCancel.addActionListener(this);
        btnCancel.setBounds(new Rectangle(2 * W_B + 3 * PAD - 20, 6 * H_B + PAD, W_B, H_B));
        add(btnCancel);
    }

    private void initFields() {

    }

    public String getFindName(){
        String str = txtAuthor.getText();
        return str;
    }

    public void actionPerformed(ActionEvent ae) {
        String action = ae.getActionCommand();
        save = FIND.equals(action);
        setVisible(false);
    }

    public boolean isSave() {
        return save;
    }


}
