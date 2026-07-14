package collegemanagement;

import java.util.Scanner;

public class MainMenu {

    static StudentDAO studentDAO = new StudentDAO();
    static FacultyDAO facultyDAO = new FacultyDAO();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("===== COLLEGE MANAGEMENT SYSTEM =====");
            System.out.println("1. Login as Admin");
            System.out.println("2. Login as Faculty");
            System.out.println("3. Login as Student");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    if (adminLogin(sc)) {
                        adminMenu(sc);
                    }
                    break;
                case 2:
                    facultyLogin(sc);
                    break;
                case 3:
                    studentLogin(sc);
                    break;
                case 4:
                    System.out.println("Exiting... Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
        sc.close();
    }

    static boolean adminLogin(Scanner sc) {
        System.out.print("Username: ");
        String user = sc.next();
        System.out.print("Password: ");
        String pass = sc.next();

        if (user.equals("admin") && pass.equals("admin123")) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Invalid credentials.");
            return false;
        }
    }

    static void adminMenu(Scanner sc) {
        boolean back = false;
        while (!back) {
            System.out.println("--- ADMIN MENU ---");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student Attendance");
            System.out.println("4. Delete Student");
            System.out.println("5. Add Faculty");
            System.out.println("6. View Faculty");
            System.out.println("7. Delete Faculty");
            System.out.println("8. Back to Main Menu");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Course: ");
                    String course = sc.nextLine();
                    System.out.print("Semester: ");
                    int sem = sc.nextInt();
                    System.out.print("Attendance %: ");
                    int att = sc.nextInt();
                    System.out.print("Marks: ");
                    int marks = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Fee Status (Paid/Pending): ");
                    String fee = sc.nextLine();
                    studentDAO.addStudent(name, course, sem, att, marks, fee);
                    break;
                case 2:
                    studentDAO.viewStudents();
                    break;
                case 3:
                    System.out.print("Student ID: ");
                    int sid = sc.nextInt();
                    System.out.print("New Attendance %: ");
                    int newAtt = sc.nextInt();
                    studentDAO.updateAttendance(sid, newAtt);
                    break;
                case 4:
                    System.out.print("Student ID to delete: ");
                    int delId = sc.nextInt();
                    studentDAO.deleteStudent(delId);
                    break;
                case 5:
                    System.out.print("Name: ");
                    String fname = sc.nextLine();
                    System.out.print("Department: ");
                    String dept = sc.nextLine();
                    System.out.print("Subject: ");
                    String subject = sc.nextLine();
                    facultyDAO.addFaculty(fname, dept, subject);
                    break;
                case 6:
                    facultyDAO.viewFaculty();
                    break;
                case 7:
                    System.out.print("Faculty ID to delete: ");
                    int fDelId = sc.nextInt();
                    facultyDAO.deleteFaculty(fDelId);
                    break;
                case 8:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    static void facultyLogin(Scanner sc) {
        System.out.print("Enter your Faculty ID: ");
        int facultyId = sc.nextInt();
        facultyMenu(sc, facultyId);
    }

    static void facultyMenu(Scanner sc, int facultyId) {
        boolean back = false;
        while (!back) {
            System.out.println("--- FACULTY MENU ---");
            System.out.println("1. View All Students");
            System.out.println("2. Mark Attendance");
            System.out.println("3. Upload Marks");
            System.out.println("4. Logout");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    studentDAO.viewStudents();
                    break;
                case 2:
                    System.out.print("Student ID: ");
                    int sid = sc.nextInt();
                    System.out.print("New Attendance %: ");
                    int newAtt = sc.nextInt();
                    studentDAO.updateAttendance(sid, newAtt);
                    break;
                case 3:
                    System.out.print("Student ID: ");
                    int mid = sc.nextInt();
                    System.out.print("New Marks: ");
                    int newMarks = sc.nextInt();
                    studentDAO.updateMarks(mid, newMarks);
                    break;
                case 4:
                    System.out.println("Logged out.");
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    static void studentLogin(Scanner sc) {
        System.out.print("Enter your Student ID: ");
        int studentId = sc.nextInt();
        Student student = studentDAO.getStudentById(studentId);

        if (student == null) {
            System.out.println("No student found with that ID.");
            return;
        }

        System.out.println("Login successful! Welcome, " + student.getName());
        studentMenu(sc, student);
    }

    static void studentMenu(Scanner sc, Student student) {
        boolean back = false;
        while (!back) {
            System.out.println("--- STUDENT MENU ---");
            System.out.println("1. View My Attendance");
            System.out.println("2. View My Marks");
            System.out.println("3. View My Fee Status");
            System.out.println("4. Logout");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Attendance: " + student.getAttendance());
                    break;
                case 2:
                    System.out.println("Marks: " + student.getMarks());
                    break;
                case 3:
                    System.out.println("Fee Status: " + student.getFeeStatus());
                    break;
                case 4:
                    System.out.println("Logged out.");
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
