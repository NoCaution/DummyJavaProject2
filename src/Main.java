import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static List<Student> studentList = new ArrayList<>();

    public static void main(String[] args) {
        createStudentList();
        printGroupedStudentsByClassName();
        findTopStudentsOfClasses();
        filterStudentsByGradesAboveSeventy();
        sortStudentsByGrades();
        findTopFirstStudent();
    }

    private static void printGroupedStudentsByClassName() {
        System.out.println("students grouped by class names");
        groupStudents(Comparator.comparing(Student::getClassName))
                .toList()
                .forEach(
                        student -> System.out.println(student.toString())
                );

        System.out.println("---------------");
    }

    private static void findTopStudentsOfClasses() {
        System.out.println("top students of each classes:");
        List<Student> topStudents = new ArrayList<>();

        Map<Class, List<Student>> studentsGroupedByClassNames = studentList.stream()
                .collect(Collectors.groupingBy(Student::getClassName));

        studentsGroupedByClassNames.forEach(
                (studentClass, students) -> topStudents
                        .add(students.stream().max(Comparator.comparing(Student::getGrade)).orElse(null))
        );
        for (Student student : topStudents) {
            System.out.println(student.toString());
        }


        System.out.println("---------------");
    }

    private static void filterStudentsByGradesAboveSeventy() {
        System.out.println("students whose grades are above 70:");
        studentList.stream()
                .filter(student -> student.getGrade() > 70)
                .toList()
                .forEach(
                        student -> System.out.println(student.toString())
                );

        System.out.println("---------------");
    }

    private static void sortStudentsByGrades() {
        System.out.println("students sorted by grades:");
        groupStudents(Comparator.comparing(Student::getGrade).reversed())
                .toList()
                .forEach(
                        student -> System.out.println(student.toString())
                );

        System.out.println("---------------");
    }

    private static void findTopFirstStudent() {
        System.out.println("top first student");
        Student firstStudent = groupStudents(Comparator.comparing(Student::getGrade).reversed())
                .findFirst().orElse(null);
        assert firstStudent != null;
        System.out.println(firstStudent);
        System.out.println("---------------");
    }

    private static Stream<Student> groupStudents(Comparator<Student> comparator) {
        return studentList.stream().sorted(comparator);
    }

    private static void createStudentList() {
        studentList.add(new Student("Mete", 80, Class.MATH));
        studentList.add(new Student("Mehmet", 65, Class.SCIENCE));
        studentList.add((new Student("Mikail", 100, Class.MATH)));
        studentList.add(new Student("Hakan", 55, Class.HISTORY));
        studentList.add(new Student("Nihan", 78, Class.SCIENCE));
        studentList.add(new Student("Melik", 50, Class.CHEMISTRY));
        studentList.add(new Student("Melek", 30, Class.CHEMISTRY));
        studentList.add(new Student("Sude", 75, Class.SCIENCE));
    }
}