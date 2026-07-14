package collegemanagement;

public class Faculty extends Person {
    private String department;
    private String subject;

    public Faculty(int id, String name, String department, String subject) {
        super(id, name);
        this.department = department;
        this.subject = subject;
    }

    public String getDepartment() {
        return department;
    }

    public String getSubject() {
        return subject;
    }

    @Override
    public void displayDetails() {
        System.out.println(getId() + "\t" + getName() + "\t" + department + "\t" + subject);
    }
}
