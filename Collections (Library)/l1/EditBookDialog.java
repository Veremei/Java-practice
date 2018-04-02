package l1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditBookDialog extends JDialog implements ActionListener{
    private static final String SAVE = "Сохранить";
    private static final String CANCEL = "Закрыть";
    private static final int PAD = 10;
    private static final int W_L = 100;
    private static final int W_T = 300;
    private static final int W_B = 120;
    private static final int H_B = 25;
    private String str;
    private boolean save = false;

    private final JTextPane txtName = new JTextPane();
    private final JTextPane txtAuthor = new JTextPane();
    private final JTextPane txtDate = new JTextPane();
    private final JTextPane txtPages= new JTextPane();
    private final JTextPane txtBook = new JTextPane();

    public EditBookDialog(){
        this(null);
    }

    public EditBookDialog(Book book){
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
        JLabel lblName = new JLabel("Название:");
        lblName.setHorizontalAlignment(SwingConstants.RIGHT);
        lblName.setBounds(new Rectangle(PAD, 0 * H_B + PAD, W_L, H_B));
        add(lblName);
        txtName.setBounds(new Rectangle(W_L + 2 * PAD, 0 * H_B + PAD, W_T, H_B));
        txtName.setBorder(BorderFactory.createEtchedBorder());
        add(txtName);

        JLabel lblAuthor = new JLabel("Автор:");
        lblAuthor.setHorizontalAlignment(SwingConstants.RIGHT);
        lblAuthor.setBounds(new Rectangle(PAD, 1 * H_B + PAD, W_L, H_B));
        add(lblAuthor);
        txtAuthor.setBounds(new Rectangle(W_L + 2 * PAD, 1 * H_B + PAD, W_T, H_B));
        txtAuthor.setBorder(BorderFactory.createEtchedBorder());
        add(txtAuthor);

        JLabel lblDate = new JLabel("Год издания:");
        lblDate.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDate.setBounds(new Rectangle(PAD, 2 * H_B + PAD, W_L, H_B));
        add(lblDate);
        txtDate.setBounds(new Rectangle(W_L + 2 * PAD, 2 * H_B + PAD, W_T, H_B));
        txtDate.setBorder(BorderFactory.createEtchedBorder());
        add(txtDate);

        JLabel lblSize = new JLabel("Кол-во страниц:");
        lblSize.setHorizontalAlignment(SwingConstants.RIGHT);
        lblSize.setBounds(new Rectangle(PAD, 3 * H_B + PAD, W_L, H_B));
        add(lblSize);
        txtPages.setBounds(new Rectangle(W_L + 2 * PAD, 3 * H_B + PAD, W_T, H_B));
        txtPages.setBorder(BorderFactory.createEtchedBorder());
        add(txtPages);

        JLabel lblBook = new JLabel("Жанр:");
        lblBook.setHorizontalAlignment(SwingConstants.RIGHT);
        lblBook.setBounds(new Rectangle(PAD, 4 * H_B + PAD, W_L, H_B));
        add(lblBook);

        String[] items = {
                "Детектив",
                "Роман","Фантастика","Приключения","Для детей"};

        JComboBox editComboBox = new JComboBox(items);
        editComboBox.setEditable(true);
        editComboBox.setBounds(W_L + 2 * PAD, 4* H_B + PAD, W_T, H_B);
        add(editComboBox);

        editComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                str = editComboBox.getSelectedItem().toString();
                setSelection(str);
            }
        });
    }

    private void setSelection(String str) {
        this.str = str;
    }

    private String getSelection() {
        return str;
    }

    private void buildButtons() {
        JButton btnSave = new JButton("Сохранить");
        btnSave.setActionCommand(SAVE);
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

    public boolean isSave() {
        return save;
    }

    public Book getBook() {
        String str;
        str = txtAuthor.getText();
        Book book = null;
        try {
            book = new Book(txtName.getText(),str.trim(),txtDate.getText(),
                    txtPages.getText(), getSelection());
        } catch (MyEx myEx) {
            ExFrame exFrame = new ExFrame(myEx.getTxtEx());
            exFrame.setVisible(true);
        }
        System.out.println(str);
        return book;
    }

    public void actionPerformed(ActionEvent ae) {
        String action = ae.getActionCommand();
        save = SAVE.equals(action);
        setVisible(false);
    }

}
