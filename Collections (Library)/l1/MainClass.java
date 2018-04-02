package l1;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class MainClass {
    public static void main(String[] args) throws MyEx {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);
    }
}

class MainFrame extends JFrame implements ActionListener{
    private static final String[] headers = {"Название", "Автор", "Год издания", "Жанр", "Кол-во страниц"};
    private static final String LOAD = "LOAD";
    private static final String ADD = "ADD";
    private static final String EDIT = "EDIT";
    private static final String DELETE = "DELETE";
    private static final String FIND = "FIND";
    private static final String SORT = "SORT";
    private final BookSimpleDAO firstBookManager = new BookSimpleDAO();
    private final BookSimpleDAO secondBookManager = new BookSimpleDAO();
    private final JTable bookTable = new JTable();
    public boolean flag = false;


    public MainFrame() throws MyEx {
        bookTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);    //выделение 1-ой строки в таблице
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;     //элемент на весь размер ячейки
        gbc.insets = new Insets(5, 5, 0, 5);    //границы в ячейке
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
        top.add(buttonPanel, BorderLayout.EAST);
        add(top, BorderLayout.EAST);
        add(new JScrollPane(bookTable), BorderLayout.CENTER);
        setBounds(100, 200, 900, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loadBook();
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
            case LOAD: loadBook(); break;
            case ADD: try{addBook();}catch (NullPointerException e){

            }break;
            case EDIT: editBook(); break;
            case DELETE: deleteBook(); break;
            case FIND:
                try {
                    findBook();
                } catch (MyEx myEx) {

                }
                break;
            case SORT: sortBook(); break;
        }
    }

    private void loadBook(){
        List<Book> books = firstBookManager.findBooks();
        class BookModel extends AbstractTableModel{
            private final List<Book> books;

            public BookModel(List<Book> books) {
                this.books = books;
            }

            @Override
            public int getRowCount() {
                return books.size();
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
                Book book = books.get(row);
                switch (col){
                    case 0: return book.getName();
                    case 1: return book.getAuthor();
                    case 2: return book.getDate();
                    case 3: return book.getPages();
                    case 4: return book.getGenre();
                    default: return book.getBookId().toString();
                }
            }
        }
        BookModel pm = new BookModel(books);
        bookTable.setModel(pm);
    }


    private void sortBook(){
        firstBookManager.sortBooks();
        loadBook();
    }

    private void editBook() {
        if (deleteBook()){
            addBook();
        }
        loadBook();
        sortBook();
    }

    private void addBook() {
        EditBookDialog dialog = new EditBookDialog();
        saveBook(dialog);
        loadBook();
    }

    private void saveBook(EditBookDialog dialog) {
        if (dialog.isSave()) {
            Book book = dialog.getBook();
            if (book.getName() != null) {
                firstBookManager.addBook(book);
            } else {
                firstBookManager.addBook(book);
            }
        }
    }

    private boolean deleteBook() {
        int sr = bookTable.getSelectedRow();
        if (sr != -1) {
            String str = bookTable.getModel().getValueAt(sr, 0).toString();
            firstBookManager.deleteBook(str);
            flag = true;
        } else {
            JOptionPane.showMessageDialog(this, "Выделите строку!");
        }
        return flag;
    }

    private void findBook() throws MyEx {
        loadBook();
        FindBookDialog ec = new FindBookDialog();
        forFindBook(ec);
        loadBook();
    }

    private void forFindBook(FindBookDialog dialog) throws MyEx {
        if (dialog.isSave()) {
            String str = dialog.getFindName();
            FindingPanel fp = new FindingPanel(str);
            fp.setVisible(true);
        }
    }
}
