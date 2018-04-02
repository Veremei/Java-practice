package l1;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class EditCarDialog extends JDialog implements ActionListener{
    private static final String SAVE = "Сохранить";
    private static final String CANCEL = "Закрыть";
    private static final int PAD = 10;
    private static final int W_L = 100;
    private static final int W_T = 300;
    private static final int W_B = 120;
    private static final int H_B = 25;
    private String str;
    private boolean save = false;

    private final JTextPane txtMark = new JTextPane();
    private final JTextPane txtModel = new JTextPane();
    private final JTextPane txtDate = new JTextPane();
    private final JTextPane txtColor= new JTextPane();
    private final JTextPane txtCar = new JTextPane();

    public EditCarDialog(){
        this(null);
    }

    public EditCarDialog(Car car){
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
        JLabel lblName = new JLabel("Марка:");
        lblName.setHorizontalAlignment(SwingConstants.RIGHT);
        lblName.setBounds(new Rectangle(PAD, 0 * H_B + PAD, W_L, H_B));
        add(lblName);
        txtMark.setBounds(new Rectangle(W_L + 2 * PAD, 0 * H_B + PAD, W_T, H_B));
        txtMark.setBorder(BorderFactory.createEtchedBorder());
        add(txtMark);

        JLabel lblModel = new JLabel("Модель:");
        lblModel.setHorizontalAlignment(SwingConstants.RIGHT);
        lblModel.setBounds(new Rectangle(PAD, 1 * H_B + PAD, W_L, H_B));
        add(lblModel);
        txtModel.setBounds(new Rectangle(W_L + 2 * PAD, 1 * H_B + PAD, W_T, H_B));
        txtModel.setBorder(BorderFactory.createEtchedBorder());
        add(txtModel);

        JLabel lblDate = new JLabel("Год выпуска:");
        lblDate.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDate.setBounds(new Rectangle(PAD, 2 * H_B + PAD, W_L, H_B));
        add(lblDate);
        txtDate.setBounds(new Rectangle(W_L + 2 * PAD, 2 * H_B + PAD, W_T, H_B));
        txtDate.setBorder(BorderFactory.createEtchedBorder());
        add(txtDate);

        JLabel lblSize = new JLabel("VIN номер:");
        lblSize.setHorizontalAlignment(SwingConstants.RIGHT);
        lblSize.setBounds(new Rectangle(PAD, 3 * H_B + PAD, W_L, H_B));
        add(lblSize);
        txtColor.setBounds(new Rectangle(W_L + 2 * PAD, 3 * H_B + PAD, W_T, H_B));
        txtColor.setBorder(BorderFactory.createEtchedBorder());
        add(txtColor);

        JLabel lblCar = new JLabel("Цвет:");
        lblCar.setHorizontalAlignment(SwingConstants.RIGHT);
        lblCar.setBounds(new Rectangle(PAD, 4 * H_B + PAD, W_L, H_B));
        add(lblCar);

        String[] items = {
                "Черный",
                "Белый","Синий","Красный","Желтый","Зеленый","Серый"};

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

    public Car getCar() {
        String str;
        str = txtModel.getText();
        Car car = null;
        try {
            car = new Car(txtMark.getText(),str.trim(),txtDate.getText(),
                    txtColor.getText(), getSelection());
        } catch (MyEx myEx) {
            ExFrame exFrame = new ExFrame(myEx.getTxtEx());
            exFrame.setVisible(true);
        }
        System.out.println(str);
        return car;
    }

    public void actionPerformed(ActionEvent ae) {
        String action = ae.getActionCommand();
        save = SAVE.equals(action);
        setVisible(false);
    }

}
