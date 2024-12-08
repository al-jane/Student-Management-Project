import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    static class Student {
        String studentID;
        String name;
        int age;
        String program;
        String year;
        String section;
        List<String> subjects; // list to store all subj

        public Student(String studentID, String name, int age, String program, String year, String section) {
            this.studentID = studentID;
            this.name = name;
            this.age = age;
            this.program = program;
            this.year = year;
            this.section = section;
            this.subjects = new ArrayList<>();
        }

        @Override
        public String toString() {
            StringBuilder subjectList = new StringBuilder();
            for (int i = 0; i < subjects.size(); i++) {
                subjectList.append(i + 1).append(". ").append(subjects.get(i)).append("\n");
            }
            return "Student ID: " + studentID + "\n" +
                    "Name: " + name + "\n" +
                    "Age: " + age + "\n" +
                    "Program: " + program + "\n" +
                    "Year: " + year + "\n" +
                    "Section: " + section + "\n" +
                    "Subjects:\n" + subjectList.toString();
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();
        int choice;

        do {
            System.out.println("===== Student Management =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Search a Student");
            System.out.println("6. Exit");
            System.out.print("Enter your choice (1-6): ");

            while (!scan.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number between 1 and 6.");
                scan.next();
            }
            choice = scan.nextInt();
            scan.nextLine(); 

            switch (choice) {
                case 1: // Add Student
                    System.out.print("Enter Student ID: ");
                    String studentID = scan.nextLine();
                    System.out.print("Enter Student Name (Full Name): ");
                    String name = scan.nextLine();
                    System.out.print("Enter Student Age: ");
                    int age = scan.nextInt();
                    scan.nextLine(); 
                    System.out.print("Enter Program: ");
                    String program = scan.nextLine();
                    System.out.print("Enter Year: ");
                    String year = scan.nextLine();
                    System.out.print("Enter Section: ");
                    String section = scan.nextLine();

                    Student newStudent = new Student(studentID, name, age, program, year, section);

                    // ADD SUBJECTS
                    while (true) {
                        System.out.print("Enter Subject (type 'x' if done): ");
                        String subject = scan.nextLine();
                        if (subject.equalsIgnoreCase("x")) {
                            break;
                        }
                        newStudent.subjects.add(subject);
                    }

                    students.add(newStudent);
                    System.out.println("Student added successfully!");
                    break;

                case 2: // view all students
                    if (students.isEmpty()) {
                        System.out.println("No Students Available");
                    } else {
                        for (Student student : students) {
                            System.out.println(student);
                        }
                    }
                    break;

                case 3: // update Student
                    System.out.print("Enter the Student ID of the student you want to update: ");
                    String updateID = scan.nextLine();

                    Student studentToUpdate = null;
                    for (Student student : students) {
                        if (student.studentID.equals(updateID)) {
                            studentToUpdate = student;
                            break;
                        }
                    }

                    if (studentToUpdate != null) {
                        System.out.println("Current details of the student:");
                        System.out.println(studentToUpdate);

                        System.out.println("What do you want to update?");
                        System.out.println("1. Name");
                        System.out.println("2. Age");
                        System.out.println("3. Program");
                        System.out.println("4. Year");
                        System.out.println("5. Section");
                        System.out.println("6. Subjects");
                        System.out.println("7. All");
                        System.out.println("8. Cancel");
                        System.out.print("Enter your choice (1-8): ");

                        int updateChoice = scan.nextInt();
                        scan.nextLine();

                        switch (updateChoice) {
                            case 6: // Update Subjects
                                System.out.println("Current Subjects:");
                                for (int i = 0; i < studentToUpdate.subjects.size(); i++) {
                                    System.out.println((i + 1) + ". " + studentToUpdate.subjects.get(i));
                                }

                                System.out.println("1. Edit Subject");
                                System.out.println("2. Delete Subject");
                                System.out.println("3. Add New Subject");
                                System.out.print("Enter your choice: ");
                                int subjectChoice = scan.nextInt();
                                scan.nextLine(); 

                                switch (subjectChoice) {
                                    case 1: // Edit Subject
                                        System.out.print("Enter the number of the subject to edit: ");
                                        int editNumber = scan.nextInt() - 1;
                                        scan.nextLine();
                                        if (editNumber >= 0 && editNumber < studentToUpdate.subjects.size()) {
                                            System.out.print("Enter new subject name: ");
                                            String newSubject = scan.nextLine();
                                            studentToUpdate.subjects.set(editNumber, newSubject);
                                            System.out.println("Subject updated successfully!");
                                        } else {
                                            System.out.println("Invalid subject number.");
                                        }
                                        break;

                                    case 2: // Delete Subject
                                        System.out.print("Enter the number of the subject to delete: ");
                                        int deleteNumber = scan.nextInt() - 1;
                                        if (deleteNumber >= 0 && deleteNumber < studentToUpdate.subjects.size()) {
                                            studentToUpdate.subjects.remove(deleteNumber);
                                            System.out.println("Subject deleted successfully!");
                                        } else {
                                            System.out.println("Invalid subject number.");
                                        }
                                        break;

                                    case 3: // Add New Subject
                                        System.out.print("Enter new subject: ");
                                        String newSubject = scan.nextLine();
                                        studentToUpdate.subjects.add(newSubject);
                                        System.out.println("Subject added successfully!");
                                        break;

                                    default:
                                        System.out.println("Invalid choice.");
                                }
                                break;

                            default:
                                System.out.println("Invalid choice! No changes made.");
                        }
                    } else {
                        System.out.println("No student found with ID: " + updateID);
                    }
                    break;

                case 4:
                    System.out.println("You selected: Delete Student");
                    break;
                case 5:
                    System.out.println("You selected: Search Student");
                    break;
                case 6:
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a number between 1 and 5.");
            }
            System.out.println();
        } while (choice != 6);
        scan.close();
    }
}
