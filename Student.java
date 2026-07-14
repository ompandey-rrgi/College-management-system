package collegemanagement;

public class Student extends Person {
    private String course;
    private int semester;
    private int attendance;
    private int marks;
    private String feeStatus;

    public Student(int id, String name, String course, int semester, int attendance, int marks, String feeStatus) {
        super(id, name);
        this.course = course;
        this.semester = semester;
        this.attendance = attendance;
        this.marks = marks;
        this.feeStatus = feeStatus;
    }

    public String getCourse() {
        return course;
    }

    public int getSemester() {
        return semester;
    }

    public int getAttendance() {
        return attendance;
    }

    public int getMarks() {
        return marks;
    }

    public String getFeeStatus() {
        return feeStatus;
    }

    @Override
    public void displayDetails() {
        System.out.println(getId() + "\t" + getName() + "\t" + course + "\t" + semester + "\t" + attendance + "\t" + marks + "\t" + feeStatus);
    }
}
