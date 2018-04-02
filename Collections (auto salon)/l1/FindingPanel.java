package l1;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class FindingPanel extends JFrame {
    private static final String[] headers = {"Марка", "Модель", "Год выпуска", "VIN номер", "Цвет"};
    private final JTable findingTable = new JTable();
    private final CarSimpleDAO secondCarManager = new CarSimpleDAO();
    String fname;

    public FindingPanel(String str) throws MyEx {
        fname = str;
        findingTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        add(new JScrollPane(findingTable), BorderLayout.CENTER);
        setBounds(100, 200, 900, 400);
        loadCar(fname);

    }

    private void loadCar(String str) {
        str = str;
        List<Car> cars2 = secondCarManager.fCars(str);
        class FindingModel extends AbstractTableModel {
            private final List<Car> cars;

            public FindingModel(List<Car> cars) {
                this.cars = cars;
            }

            @Override
            public int getRowCount() {
                return cars2.size();
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
            public Object getValueAt(int row, int col) {
                Car car = cars.get(row);
                switch (col) {
                    case 0:
                        return car.getName();
                    case 1:
                        return car.getModel();
                    case 2:
                        return car.getDate();
                    case 3:
                        return car.getColor();
                    case 4:
                        return car.getVIN();
                    default:
                        return car.getCarId().toString();
                }
            }
        }
        FindingModel pm = new FindingModel(cars2);
        findingTable.setModel(pm);
    }
}

