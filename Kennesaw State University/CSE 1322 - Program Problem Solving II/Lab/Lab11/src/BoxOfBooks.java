import java.util.ArrayList;

public class BoxOfBooks {

    private String boxName;
    private ArrayList<String> books;

    public BoxOfBooks(String boxName) {
        this.boxName = boxName;
        books = new ArrayList<>();
    }


    public void addBook(String book) {
        books.add(book);
    }


    public String retrieveBook() {
        if(books.isEmpty()) {
            return null;
        }

        return books.removeFirst();
    }


    public BoxOfBooks copy() {
        BoxOfBooks copy = new BoxOfBooks(getBoxName());

        copy.books.addAll(this.books);
        return copy;
    }


    public String getBoxName() {
        return boxName;
    }

}
