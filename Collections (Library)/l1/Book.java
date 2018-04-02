package l1;
import java.io.Serializable;

public class Book implements Serializable {

    private Long bookId;
    private String name;
    private String author;
    private String date;
    private String pages;
    private String genre;

    public Book(String name, String author, String date, String pages, String genre) throws MyEx {
        if (name.equals("")) throw new MyEx("Название");
        if (author.equals("")) throw new MyEx("Автор");
        if (Integer.parseInt(date) > 2019) throw new MyEx("");
        if (date.equals("")) throw new MyEx("Год издания");
        if (pages.equals("")) throw new MyEx("Кол-во страниц");
        if (Integer.parseInt(pages) < 1) throw new MyEx("");

        this.name = name;
        this.author = author;
        this.date = date;
        this.pages = pages;
        this.genre = genre;
    }

    public Book(){

    }

    public Book(Long id, String name, String author, String date, String pages, String genre){


        this.bookId = id;
        this.name = name;
        this.author = author;
        this.date = date;
        this.pages = pages;
        this.genre = genre;
    }

    public void setBookId(Long id){
        this.bookId = id;
    }


    public Long getBookId(){
        return bookId;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getAuthor(){
        return author;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getPages(){
        return pages;
    }

    public void setPages(String pages){
        this.pages = pages;
    }

    public String getGenre(){
        return genre;
    }

    public void setGenre(String genre){
        this.genre = genre;
    }

    public String toString() {
        return "Book{" + ", Name=" + name + ", Author=" + author + ", Date=" + date + ", Pages=" + pages + ", Genre=" + genre + '}';
    }
}
