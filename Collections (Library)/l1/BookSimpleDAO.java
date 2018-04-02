package l1;

import java.util.*;

public class BookSimpleDAO implements BookDAO {

    public final List<Book> books = new ArrayList<>();
    public final List<Book> fbooks = new ArrayList<>();
    public int count = 0;

    public BookSimpleDAO() throws MyEx {
        addBook(new Book("Властелин Колец", "Толкин", "1954", "1555", "Фантастика"));
        addBook(new Book("Букварь", "Жукова", "1999", "96", "Для детей"));
        addBook(new Book("Снеговик", "Несбе", "2017", "480", "Детектив"));
        addBook(new Book("Пропись", "Жукова", "2016", "32", "Для детей"));
        addBook(new Book("Евгений Онегин", "Пушкин", "1832", "382", "Роман"));
    }

    public List<Book> findBooks() {
        return books;
    }

    public Book getBook(String BookName) {
        Book p1 = new Book();
        for(Book book : books) {
            if(book.getName().equals(BookName)) {
                p1 = book;
            }
        }
        return p1;
    }

    public List<Book> fBooks(String str) {
        String str1 = str;
        getFindingBooks(str1);
        return fbooks;
    }

    public int getCount(String str){
        for(Book book : books) {
            if(book.getAuthor().equals(str)) {
                count++;
            }
        }
        return count;
    }

    public void getFindingBooks(String str){
        for(Book book : books) {
            if(book.getAuthor().equals(str)) {
                System.out.println("udsflsdflsdbfbdsf");
                fbooks.add(book);
            }
        }
    }

    public void sortBooks(){
        Collections.sort(books,
                new Comparator<Book>() {
                    public int compare(Book o1, Book o2) {
                        return o1.getDate().compareTo(o2.getDate());
                    }
                }
        );
    }

    public Long addBook(Book book) {
        Long id = generateBookId();
        book.setBookId(id);
        books.add(book);
        return id;
    }

    private Long generateBookId() {
        Long bookId = Math.round(Math.random() * 1000 + System.currentTimeMillis());
        return bookId;
    }

    public void deleteBook(String str) {
        for(Iterator<Book> it = books.iterator(); it.hasNext();) {
            Book book = it.next();
            if(book.getName().equals(str)) {
                it.remove();
            }
        }
    }

    public void updateBook(Book book) {
        Book oldBook = getBook(book.getName());
        if(oldBook != null) {
            oldBook.setName(book.getName());
            oldBook.setAuthor(book.getAuthor());
            oldBook.setDate(book.getDate());
            oldBook.setPages(book.getPages());
            oldBook.setGenre(book.getGenre());
        }
    }
}
