import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    static class Student {
        String studentID;
        String name;
        String age;
        String program;
        String year;
        String section;
        List<String> subjects; 
        String status; // regular or irregular student

        public Student(String studentID, String name, String age, String program, String year, String section, String status) {
            this.studentID = studentID;
            this.name = name;
            this.age = age;
            this.program = program;
            this.year = year;
            this.section = section;
            this.subjects = new ArrayList<>();
            this.status = status;
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
                    "Status: " + status + "\n" +
                    "Subjects:\n" + subjectList.toString();
                    
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();
        int choice;

        do {
            System.out.println("+====================================+");
            System.out.println("|     Student Management System      |");
            System.out.println("+====================================+");
            System.out.println("|   1. Add Student                   |");
            System.out.println("|   2. View All Students             |");
            System.out.println("|   3. Update Student                |");
            System.out.println("|   4. Delete Student                |");
            System.out.println("|   5. Search a Student              |");
            System.out.println("|   6. Exit                          |");
            System.out.println("+------------------------------------+");
            System.out.print("└──>Enter your choice (1-6): ");

            while (!scan.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number between 1 and 7.");
                scan.next();
            }
            choice = scan.nextInt();
            scan.nextLine(); 

            switch (choice) {
                case 1: // Add Student
                    

                    System.out.println("+====================================+");
                    System.out.println("|          Add New Student           |");
                    System.out.println("+------------------------------------+");
                    System.out.println("└──>Note: Type 'x' to cancel");
                    System.out.print("└──>Enter Student ID  : ");
                    String studentID = scan.nextLine();
                    if (studentID.equals("x")){
                        break;
                    }

                    System.out.print("| Enter Student Name (Full Name)  : ");
                    String name = scan.nextLine();
                    if (name.equals("x")){
                        break;
                    }
                    //ERROR WHEN TYPED STRING--------------------------------------------
                    System.out.print("| Enter Student Age  : ");
                    String age = scan.nextLine();
                  
                    if (age.equals("x")){
                        break;
                    }

                    System.out.print("| Enter Program  : ");
                    String program = scan.nextLine();
                    if (program.equals("x")){
                        break;
                    }

                    System.out.print("| Enter Year  : ");
                    String year = scan.nextLine();
                    if (year.equals("x")){
                        break;
                    }

                    System.out.print("| Enter Section  : ");
                    String section = scan.nextLine();
                    if (section.equals("x")){
                        break;
                    }

                    System.out.print("| Enter status (Regular/Irregular)  : ");
                    String status = scan.nextLine();
                    if (status.equals("x")){
                        break;
                    }
                    

                    Student newStudent = new Student(studentID, name, age, program, year, section, status);

                    // ADD SUBJECTS
                    while (true) {
                        System.out.print("| Enter Subject (type 'x' if done)  : ");
                        String subject = scan.nextLine();

                        if (subject.equalsIgnoreCase("x")) {
                            break;
                        }
                        newStudent.subjects.add(subject);
                    }

                    students.add(newStudent);
                    System.out.println("└──>Student added successfully!");
                    break;

                case 2: // View all students
                    if (students.isEmpty()) {
                        System.out.println("No Students Available");
                    } else {
                        for (Student student : students) {
                            System.out.println(student);
                        }
                    }
                    break;

                case 3: // Update Student
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
                        System.out.println("6. Status");
                        System.out.println("7. Subjects");
                        System.out.println("8. All");
                        System.out.println("9. Cancel");
                        System.out.print("Enter your choice (1-9): ");

                        int updateChoice = scan.nextInt();
                        scan.nextLine(); 

                        switch (updateChoice) {
                            case 1: // UPDATE NAME
                                System.out.print("Enter new Name: ");
                                String newName = scan.nextLine();
                                studentToUpdate.name = newName;
                                System.out.println("Name updated successfully!");
                                break;
                            case 2: // UPDATE AGE
                                System.out.print("Enter new Age: ");
                                String newAge = scan.nextLine();
                                studentToUpdate.age = newAge;
                                System.out.println("Age updated successfully!");
                                break;
                            case 3: // UPDATE PROGRAM
                                System.out.print("Enter new Program: ");
                                String newProgram = scan.nextLine();
                                studentToUpdate.program = newProgram;
                                System.out.println("Program updated successfully!");
                                break;
                            case 4: // UPDATE YEAR
                                System.out.print("Enter new Year: ");
                                String newYear = scan.nextLine();
                                studentToUpdate.year = newYear;
                                System.out.println("Year updated successfully!");
                                break;
                            case 5: // UPDATE SECTION
                                System.out.print("Enter new Section: ");
                                String newSection = scan.nextLine();
                                studentToUpdate.section = newSection;
                                System.out.println("Section updated successfully!");
                                break;
                            case 6: // UPDATE STATUS
                                System.out.print("Enter new Status: ");
                                String newStatus = scan.nextLine();
                                studentToUpdate.status = newStatus;
                                System.out.println("Status updated successfully!");
                                break;
                            case 7: // UPDATE SUBJECTS
                            boolean updateSubjectLoop = true;
                            while (updateSubjectLoop) {
                                System.out.println("Current Subjects:");
                                for (int i = 0; i < studentToUpdate.subjects.size(); i++) {
                                    System.out.println((i + 1) + ". " + studentToUpdate.subjects.get(i));
                                }
                                System.out.println("1. Edit Subject");
                                System.out.println("2. Delete Subject");
                                System.out.println("3. Add New Subject");
                                System.out.println("4. Cancel");
                                System.out.print("Enter your choice: ");
                                int subjectChoice = scan.nextInt();
                                scan.nextLine();

                                switch (subjectChoice) {
                                    case 1: // edit Subject
                                        System.out.print("Enter the number of the subject to edit: ");
                                        int editNumber = scan.nextInt() - 1;
                                        scan.nextLine(); // Consume newline character
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
                                        String newSubjectName = scan.nextLine();
                                        studentToUpdate.subjects.add(newSubjectName);
                                        System.out.println("Subject added successfully!");
                                        break;
                                    case 4: // Cancel
                                        updateSubjectLoop = false;
                                        System.out.println("Subject update canceled.");
                                        break;
                                    default:
                                        System.out.println("Invalid choice. Please try again.");
                                }
                            }
                            break;

                            case 8: // Update All Details
                            System.out.print("Enter new Name: ");
                            studentToUpdate.name = scan.nextLine();
                            System.out.print("Enter new Age: ");
                            studentToUpdate.age = scan.nextLine();
                            scan.nextLine(); 
                            System.out.print("Enter new Program: ");
                            studentToUpdate.program = scan.nextLine();
                            System.out.print("Enter new Year: ");
                            studentToUpdate.year = scan.nextLine();
                            System.out.print("Enter new Section: ");
                            studentToUpdate.section = scan.nextLine();
                            System.out.print("Enter new Status: ");
                            studentToUpdate.status = scan.nextLine();

                       
                            boolean updatesubjectloop = true;
                            while (updatesubjectloop) {
                                System.out.println("Current Subjects:");
                                for (int i = 0; i < studentToUpdate.subjects.size(); i++) {
                                    System.out.println((i + 1) + ". " + studentToUpdate.subjects.get(i));
                                }
                                System.out.println("1. Edit Subject");
                                System.out.println("2. Delete Subject");
                                System.out.println("3. Add New Subject");
                                System.out.println("4. Cancel");
                                System.out.print("Enter your choice: ");
                                int subjectChoice = scan.nextInt();
                                scan.nextLine();

                                switch (subjectChoice) {
                                    case 1: // edit Subject
                                        System.out.print("Enter the number of the subject to edit: ");
                                        int editNumber = scan.nextInt() - 1;
                                        scan.nextLine(); // consume newline character
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
                                        String newSubjectName = scan.nextLine();
                                        studentToUpdate.subjects.add(newSubjectName);
                                        System.out.println("Subject added successfully!");
                                        break;
                                    case 4: // Cancel
                                        updatesubjectloop = false;
                                        System.out.println("Subject update canceled.");
                                        break;
                                    default:
                                        System.out.println("Invalid choice. Please try again.");
                                }
                            }

                            System.out.println("All details updated successfully!");
                            break;


                            case 9: // Cancel
                                System.out.println("Update operation canceled.");
                                break;
                            default:
                                System.out.println("Invalid choice.");
                        }
                    } else {
                        System.out.println("No student found with ID: " + updateID);
                    }
                    break;

                case 4: // DELETE A STUDENT
                    System.out.print("Enter the Student ID of the student you want to delete: ");
                    String deleteID = scan.nextLine();

                    Student studentToDelete = null;
                    for (Student student : students) {
                        if (student.studentID.equals(deleteID)) {
                            studentToDelete = student;
                            break;
                        }
                    }

                    if (studentToDelete != null) {
                        students.remove(studentToDelete);
                        System.out.println("Student with ID " + deleteID + " has been deleted.");
                    } else {
                        System.out.println("No student found with ID: " + deleteID);
                    }
                    break;

                case 5: // Search a Student
                    System.out.print("Enter the Student ID you want to search: ");
                    String searchID = scan.nextLine();
                    Student foundStudent = null;

                    for (Student student : students) {
                        if (student.studentID.equals(searchID)) {
                            foundStudent = student;
                            break;
                        }
                    }

                    if (foundStudent != null) {
                        System.out.println("Student found:\n" + foundStudent);
                    } else {
                        System.out.println("No student found with ID: " + searchID);
                    }
                    break;


                case 6: // Exit
                    System.out.println("Exiting the program. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 7.");
            }
        } while (choice != 6);

        scan.close();
    }
}
