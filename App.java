import java.util.*;

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

    static class User {
        String username;
        String password;

        public User(String username, String password) {
            this.username = username;
            this.password = password;
        }
    }

    static Map<String, User> userAccounts = new HashMap<>();
    static String adminUsername = "admin";
    static String adminPassword = "admin123";

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();
        int mainChoice;

        do {
            System.out.println("+====================================+");
            System.out.println("|        Welcome to the System       |");
            System.out.println("+====================================+");
            System.out.println("|   1. Admin Login                   |");
            System.out.println("|   2. User Login                    |");
            System.out.println("|   3. Exit                          |");
            System.out.println("+------------------------------------+");
            System.out.print("└──> Enter your choice (1-3): ");
            mainChoice = scan.nextInt();
            scan.nextLine();

            switch (mainChoice) {
                case 1: // Admin Login
                    System.out.print("Enter Admin Username: ");
                    String adminInputUsername = scan.nextLine();
                    System.out.print("Enter Admin Password: ");
                    String adminInputPassword = scan.nextLine();

                    if (adminInputUsername.equals(adminUsername) && adminInputPassword.equals(adminPassword)) {
                        adminMenu(scan, students);
                    } else {
                        System.out.println("Invalid admin credentials!");
                    }
                    break;

                case 2: // User Login
                    System.out.print("Enter Student ID: ");
                    String studentID = scan.nextLine();

                    if (userAccounts.containsKey(studentID)) {
                        User user = userAccounts.get(studentID);
                        System.out.print("Enter Password: ");
                        String password = scan.nextLine();

                        if (user.password.equals(password)) {
                            userMenu(scan, students, studentID);
                        } else {
                            System.out.println("Invalid password!");
                        }
                    } else {
                        System.out.println("No account found for the given Student ID.");
                    }
                    break;

                case 3: // Exit
                    System.out.println("Exiting the system. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please select from 1-3.");
            }
        } while (mainChoice != 3);

        scan.close();
    }

    public static void adminMenu(Scanner scan, ArrayList<Student> students) {
        int choice;

        do {
            System.out.println("+====================================+");
            System.out.println("|       Admin Management Menu        |");
            System.out.println("+====================================+");
            System.out.println("|   1. Add Student                   |");
            System.out.println("|   2. View All Students             |");
            System.out.println("|   3. Update Student                |");
            System.out.println("|   4. Delete Student                |");
            System.out.println("|   5. Search Student                |");
            System.out.println("|   6. Generate User Account         |");
            System.out.println("|   7. Back                          |");
            System.out.println("+------------------------------------+");
            System.out.print("└──> Enter your choice (1-7): ");
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

                    if (studentID.equalsIgnoreCase("x")) {
                        break;
                    }

                    // Check if the ID is already registered
                    boolean idExists = false;
                    for (Student student : students) {
                        if (student.studentID.equals(studentID)) {
                            idExists = true;
                            break;
                        }
                    }

                    if (idExists) {
                        System.out.println("└──>Student ID already registered. Returning to the main menu.");
                        break; // Exit the case and go back to the main menu
                    }

                    // Proceed if the ID is unique
                    System.out.print("| Enter Student Name (Full Name)  : ");
                    String name = scan.nextLine();
                    if (name.equalsIgnoreCase("x")) {
                        break;
                    }
                    System.out.print("| Enter Student Age  : ");
                    String age = scan.nextLine();
                    if (age.equalsIgnoreCase("x")) {
                        break;
                    }

                    System.out.print("| Enter Program  : ");
                    String program = scan.nextLine();
                    if (program.equalsIgnoreCase("x")) {
                        break;
                    }

                    System.out.print("| Enter Year  : ");
                    String year = scan.nextLine();
                    if (year.equalsIgnoreCase("x")) {
                        break;
                    }

                    System.out.print("| Enter Section  : ");
                    String section = scan.nextLine();
                    if (section.equalsIgnoreCase("x")) {
                        break;
                    }

                    System.out.print("| Enter status (Regular/Irregular)  : ");
                    String status = scan.nextLine();
                    if (status.equalsIgnoreCase("x")) {
                        break;
                    }

                    Student newStudent = new Student(studentID, name, age, program, year, section, status);

                    // ADD SUBJECTS
                    while (true) {
                        System.out.print("| Enter Subjects (type 'x' if done)  : ");
                        String subject = scan.nextLine();

                        if (subject.equalsIgnoreCase("x")) {
                            break;
                        }
                        newStudent.subjects.add(subject);
                    }

                    students.add(newStudent);
                    System.out.println("└──>Student added successfully!");
                    break;



                case 2: // View All Students
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

                case 4: // Delete Student
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
                case 5: // Search Student
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

                case 6: // Generate User Account
                    System.out.print("Enter Student ID to create user account: ");
                    String id = scan.nextLine();

                    boolean exists = false;
                    for (Student student : students) {
                        if (student.studentID.equals(id)) {
                            exists = true;
                            String generatedPassword = UUID.randomUUID().toString().substring(0, 8);
                            userAccounts.put(id, new User(id, generatedPassword));
                            System.out.println("Account created successfully!");
                            System.out.println("Username: " + id + " | Password: " + generatedPassword);
                            break;
                        }
                    }

                    if (!exists) {
                        System.out.println("No student found with the given ID.");
                    }
                    break;

                case 7: // Back
                    System.out.println("Returning to the main menu...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 7);
    }

    public static void userMenu(Scanner scan, ArrayList<Student> students, String studentID) {
        int choice;

        do {
            System.out.println("+====================================+");
            System.out.println("|          User Management Menu      |");
            System.out.println("+====================================+");
            System.out.println("|   1. View My Info                  |");
            System.out.println("|   2. Update Password               |");
            System.out.println("|   3. Back                          |");
            System.out.println("+------------------------------------+");
            System.out.print("└──> Enter your choice (1-3): ");
            choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
                case 1: // View My Info
                    for (Student student : students) {
                        if (student.studentID.equals(studentID)) {
                            System.out.println(student);
                            break;
                        }
                    }
                    break;

                case 2: // Update Password
                    System.out.print("Enter New Password: ");
                    String newPassword = scan.nextLine();
                    userAccounts.get(studentID).password = newPassword;
                    System.out.println("Password updated successfully!");
                    break;

                case 3: // Back
                    System.out.println("Returning to the main menu...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);
    }
}
