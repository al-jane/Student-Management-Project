import java.util.ArrayList;
import java.util.Scanner;

public class App {
     static class Student {
        String studentID;
        String name;
        int age;
        String program;
        String year;
        String section;

        
        public Student(String studentID, String name, int age, String program, String year, String section) {
            this.studentID = studentID;
            this.name = name;
            this.age = age;
            this.program = program;
            this.year = year;
            this.section = section;
            
        }
        @Override
        public String toString() {
            return  "Student ID: " + studentID + "\n" + "Name: " + name + "\n" + "Age: " + age + "\n" + "Program: " + program + "\n" + "Year: " + year + "\n" + "Section: " + section;


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
            System.out.println("5. Search a Student");
            System.out.println("6. Exit");
            System.out.println("=====================");
            System.out.print("Enter your choice (1-6): ");

            while (!scan.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number between 1 and 5.");
                System.out.print("Enter your choice (1-5): ");
                scan.next();
            }
            choice = scan.nextInt();

            switch (choice) {
                case 1: //ADD STUDENT
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

                    System.out.print("Enter Year: ");
                    String year = scan.nextLine();

                    System.out.print("Enter Section: ");
                    String section = scan.nextLine();


                    students.add(new Student(studentID, name, age, program, year, section));
                    System.out.println("Student added successfully!");
                    break;
                case 2: //VIEW STUDENT
                    System.out.println("=== View All Students ===");
                    if (students.isEmpty()) {
                        System.out.println("No Students Available");
                    } else {
                        for (Student student : students) {
                            System.out.println(student);
                        }
                    }
                    break;
                case 3: // UPDATE STUDENT
                    System.out.print("Enter the Student ID of the student you want to update: ");
                    scan.nextLine(); 
                    String updateID = scan.nextLine();

                    // Search for the student by Student ID
                    Student studentToUpdate = null;
                    for (Student student : students) {
                        if (student.studentID.equals(updateID)) {
                            studentToUpdate = student;
                            break;
                        }
                    }

                    if (studentToUpdate != null) {
                        // display current student details
                        System.out.println("Current details of the student:");
                        System.out.println(studentToUpdate);

                        // ask user for what to update
                        System.out.println("What do you want to update?");
                        System.out.println("1. Name");
                        System.out.println("2. Age");
                        System.out.println("3. Program");
                        System.out.println("4. Year");
                        System.out.println("5. Section");
                        System.out.println("6. All");
                        System.out.println("7. Cancel");
                        System.out.print("Enter your choice (1-7): ");

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
                                int newAge = scan.nextInt();
                                studentToUpdate.age = newAge;
                                System.out.println("Age updated successfully!");
                                break;

                            case 3: // UPDATE PROGRAM
                                System.out.print("Enter new Program: ");
                                String newProgram = scan.nextLine();
                                studentToUpdate.program = newProgram;
                                System.out.println("Program updated successfully!");
                                break;

                            case 4: //UPDATE YEAR
                                System.out.print("Enter new Year: ");
                                String new_year = scan.nextLine();
                                studentToUpdate.year = new_year;
                                System.out.println("Year updated successfully!");
                                break;

                            case 5: //UPDATE SECTION
                                System.out.print("Enter new Section: ");
                                String new_section = scan.nextLine();
                                studentToUpdate.section = new_section;
                                System.out.println("Section updated successfully!");
                                break;

                            case 6: // UPDATE ALL
                                System.out.print("Enter new Name: ");
                                String allNewName = scan.nextLine();
                                studentToUpdate.name = allNewName;

                                System.out.print("Enter new Age: ");
                                int allNewAge = scan.nextInt();
                                scan.nextLine();
                                studentToUpdate.age = allNewAge;

                                System.out.print("Enter new Program: ");
                                String allNewProgram = scan.nextLine();
                                studentToUpdate.program = allNewProgram;

                                System.out.print("Enter new Year: ");
                                String allNew_year = scan.nextLine();
                                studentToUpdate.year = allNew_year;

                                System.out.print("Enter new Section: ");
                                String allNew_section = scan.nextLine();
                                studentToUpdate.section = allNew_section;



                                System.out.println("All details updated successfully!");
                                break;

                            case 7: // CANCEL
                                System.out.println("Update operation canceled.");
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
