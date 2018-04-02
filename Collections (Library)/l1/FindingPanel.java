package l1;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.List;

public class FindingPanel extends JFrame {
    private static final String[] headers = {"Название", "Автор", "Год издания", "Кол-во страниц", "Жанр"};
    private final JTable findingTable = new JTable();
    private final BookSimpleDAO secondBookManager = new BookSimpleDAO();
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
        loadBook(fname);

    }

    private void loadBook(String str) {
        str = str;
        List<Book> books2 = secondBookManager.fBooks(str);
        class FindingModel extends AbstractTableModel {
            private final List<Book> books;

            public FindingModel(List<Book> books) {
                this.books = books;
            }

            @Override
            public int getRowCount() {
                return books2.size();
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
                Book book = books.get(row);
                switch (col) {
                    case 0:
                        return book.getName();
                    case 1:
                        return book.getAuthor();
                    case 2:
                        return book.getDate();
                    case 3:
                        return book.getDate();
                    case 4:
                        return book.getGenre();
                    default:
                        return book.getBookId().toString();
                }
            }
        }
        FindingModel pm = new FindingModel(books2);
        findingTable.setModel(pm);
    }
}

