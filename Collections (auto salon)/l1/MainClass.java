package l1;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;


public class MainClass {
    public static void main(String[] args) throws MyEx {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);//видимость
        mainFrame.setLocationRelativeTo(null);//по центру
    }
}

class MainFrame extends JFrame implements ActionListener{
    private static final String[] headers = {"Название", "Художник", "Дата", "Размер", "Краска"};
    private static final String LOAD = "LOAD";
    private static final String ADD = "ADD";
    private static final String EDIT = "EDIT";
    private static final String DELETE = "DELETE";
    private static final String FIND = "FIND";
    private static final String SORT = "SORT";
    private final CarSimpleDAO firstCarManager = new CarSimpleDAO();//создаем объект все параметры заданы в карсимпл методе
    private final CarSimpleDAO secondCarManager = new CarSimpleDAO();
    private final JTable carTable = new JTable();
    public boolean flag = false;


    public MainFrame() throws MyEx {
        carTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);    //выделение 1-ой строки в таблице
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();//определяет параметры размещения отдельных компонентов
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;     //элемент на весь размер ячейки
        gbc.insets = new Insets(5, 5, 0, 5); //Поле insets позволяет задать для компонента отступы от краев выделенной ему области. По умолчанию такие отступы отсутствуют.
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(gridbag);
        buttonPanel.add(createButton(gridbag, gbc, "Обновить", LOAD));
        buttonPanel.add(createButton(gridbag, gbc, "Добавить", ADD));
        buttonPanel.add(createButton(gridbag, gbc, "Исправить", EDIT));
        buttonPanel.add(createButton(gridbag, gbc, "Удалить", DELETE));
        buttonPanel.add(createButton(gridbag, gbc, "Поиск", FIND));
        buttonPanel.add(createButton(gridbag, gbc, "Сортировать", SORT));
        JPanel top = new JPanel();
        top.setLayout(new BorderLayout());
        top.add(buttonPanel, BorderLayout.SOUTH);
        add(top, BorderLayout.SOUTH);
        add(new JScrollPane(carTable), BorderLayout.CENTER);
        setBounds(100, 200, 900, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loadCar();
    }

    private JButton createButton(GridBagLayout gridbag, GridBagConstraints gbc, String title, String action) {
        JButton button = new JButton(title);
        button.setActionCommand(action);
        button.addActionListener(this);
        gridbag.setConstraints(button, gbc);
        return button;
    }

    public void actionPerformed(ActionEvent ae){
        String action = ae.getActionCommand();
        switch (action){
            case LOAD: loadCar(); break;
            case ADD: try{addCar();}catch (NullPointerException e){

            }break;
            case EDIT: editCar(); break;
            case DELETE: deleteCar(); break;
            case FIND:
                try {
                    findCar();
                } catch (MyEx myEx) {

                }
                break;
            case SORT: sortCar(); break;
        }
    }

    private void loadCar(){
        List<Car> cars = firstCarManager.findCars();
        class CarModel extends AbstractTableModel{
            private final List<Car> cars;

            public CarModel(List<Car> cars) {
                this.cars = cars;
            }

            @Override
            public int getRowCount() {
                return cars.size();
            }

            @Override
            public int getColumnCount() {
                return 5;
            }

            @Override
            public String getColumnName(int col) {
                return headers[col];
            }

            @Override
            public Object getValueAt(int row, int col){
                Car car = cars.get(row);
                switch (col){
                    case 0: return car.getName();
                    case 1: return car.getModel();
                    case 2: return car.getDate();
                    case 3: return car.getColor();
                    case 4: return car.getVIN();
                    default: return car.getCarId().toString();
                }
            }
        }
        CarModel pm = new CarModel(cars);
        carTable.setModel(pm);
    }


    private void sortCar(){
        firstCarManager.sortCars();
        loadCar();
    }

    private void editCar() {
        if (deleteCar()){
            addCar();
        }
        loadCar();
        sortCar();
    }

    private void addCar() {
        EditCarDialog dialog = new EditCarDialog();
        saveCar(dialog);
        loadCar();
    }

    private void saveCar(EditCarDialog dialog) {
        if (dialog.isSave()) {
            Car car = dialog.getCar();
            if (car.getName() != null) {
                firstCarManager.addCar(car);
            } else {
                firstCarManager.addCar(car);
            }
        }
    }

    private boolean deleteCar() {
        int sr = carTable.getSelectedRow();
        if (sr != -1) {
            String str = carTable.getModel().getValueAt(sr, 0).toString();
            firstCarManager.deleteCar(str);
            flag = true;
        } else {
            JOptionPane.showMessageDialog(this, "Выделите строку!");
        }
        return flag;
    }

    private void findCar() throws MyEx {
        loadCar();
        FindCarDialog ec = new FindCarDialog();
        forFindCar(ec);
        loadCar();
    }

    private void forFindCar(FindCarDialog dialog) throws MyEx {
        if (dialog.isSave()) {
            String str = dialog.getFindName();
            FindingPanel fp = new FindingPanel(str);
            fp.setVisible(true);
        }
    }
}
