public class Student {

    private String name;
    private BoxOfBooks boxOfBooks;

    public Student(String name, BoxOfBooks boxOfBooks) {
        this.name = name;
        this.boxOfBooks = boxOfBooks;
    }


    public String unpackBoxOfBooks() {
        String listOfBooks = "";
        String book;

        while((book = boxOfBooks.retrieveBook()) != null) {
            listOfBooks += book + '\n';
        }

        return listOfBooks;
    }


    @Override
    public String toString() {
        return "Student: " + name + '\n' +
                "Books: \n" + unpackBoxOfBooks();
    }

}
