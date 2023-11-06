import lombok.Data;
@Data
public class Student {
    private final String name;
    private final int grade;
    private final Class className;

    public Student(
            String name,
            int grade,
            Class className) {
        this.name = name;
        this.grade = grade;
        this.className = className;
    }

    public String toString() {
        return "name: " + name + " grade: " + grade + " className: " + className;
    }
}
