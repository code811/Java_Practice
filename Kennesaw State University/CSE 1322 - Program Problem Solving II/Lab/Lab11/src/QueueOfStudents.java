import java.util.ArrayList;

public class QueueOfStudents {

    private ArrayList<Student> students;

    public QueueOfStudents() {
        students = new ArrayList<>();
    }

    public void registerForPickup(Student student) {
        students.add(student);
    }

    public Student deliverBoxOfBooks() {
        if(students.isEmpty()) return null;
        return students.removeFirst();
    }
}
