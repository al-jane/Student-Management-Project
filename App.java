import java.util.ArrayList;
import java.util.Scanner;

public class App {
     static class Student {
        String studentID;
        String name;
        int age;
        String program;

        
        public Student(String studentID, String name, int age, String program) {
            this.studentID = studentID;
            this.name = name;
            this.age = age;
            this.program = program;
        }
        @Override
        public String toString() {
            return  "Student ID: " + studentID + ", Name: " + name + ", Age: " + age + ", Program: " + program;
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();
        int choice;

        do {
            System.out.println("===== Student Management =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.println("=====================");
            System.out.print("Enter your choice (1-5): ");

            while (!scan.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number between 1 and 5.");
                System.out.print("Enter your choice (1-5): ");
                scan.next();
            }
            choice = scan.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    scan.nextLine(); // Clear buffer
                    String studentID = scan.nextLine();
                    System.out.print("Enter Student Name(Full Name): ");
                    String name = scan.nextLine();
                    System.out.print("Enter Student Age: ");
                    int age;
                    while (!scan.hasNextInt()) {
                        System.out.println("Invalid input! Age must be a number.");
                        System.out.print("Enter Student Age: ");
                        scan.next();
                    }
                    age = scan.nextInt();
                    scan.nextLine(); // Clear buffer
                    System.out.print("Enter Program: ");
                    String program = scan.nextLine();
                    students.add(new Student(studentID, name, age, program));
                    System.out.println("Student added successfully!");
                    break;
                case 2:
                    System.out.println("=== View All Students ===");
                    if (students.isEmpty()) {
                        System.out.println("No Students Available");
                    } else {
                        for (Student student : students) {
                            System.out.println(student);
                        }
                    }
                    break;
                case 3:
                    System.out.println("You selected: Update Student");
                    break;
                case 4:
                    System.out.println("You selected: Delete Student");
                    break;
                case 5:
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please select a number between 1 and 5.");
            }
            System.out.println(); // Print a blank line for better readability
        } while (choice != 5);
        scan.close();
    }
}
