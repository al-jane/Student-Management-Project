import java.util.*;


public class App {
    static class Student {
        String studentID;
        String name;
        String age;
        String sex;
        String program;
        String year;
        String section;
        List<String> subjects;
        Map<String, Double>grades;
        String status; // regular or irregular student



        public Student(String studentID, String name, String age, String program, String year, String section, String status, String sex) {
            this.studentID = studentID;
            this.name = name;
            this.age = age;
            this.sex = sex;
            this.program = program;
            this.year = year;
            this.section = section;
            this.subjects = new ArrayList<>();
            this.grades = new HashMap<>();
            this.status = status;


        }
        public double convertGradeToGPA(double grade) {
            // converting % grade to GPA 
            int student_grade = (int) grade;
            if (student_grade >= 93 && student_grade <= 100) return 4.0;
            if (student_grade >= 90 && student_grade <= 92) return 3.7;
            if (student_grade >= 87 && student_grade <= 89) return 3.3;
            if (student_grade >= 83 && student_grade <= 86) return 3.0;
            if (student_grade >= 80 && student_grade <= 82) return 2.7;
            if (student_grade >= 77 && student_grade <= 79) return 2.3;
            if (student_grade >= 73 && student_grade <= 76) return 2.0;
            if (student_grade >= 70 && student_grade <= 72) return 1.7;
            if (student_grade >= 67 && student_grade <= 69) return 1.3;
            if (student_grade >= 65 && student_grade <= 66) return 1.0;
            return 0.0; //other grades
            
        }

        public String determinePassFail(double gpa) {
            if (gpa >= 2.0) return "Passed";
            return "Failed";
        }



        @Override
        public String toString() {
            StringBuilder subjectList = new StringBuilder();
            for (int i = 0; i < subjects.size(); i++) {
                String grade = grades.get(subjects.get(i)) != null ? String.valueOf(grades.get(subjects.get(i))) : "N/A";
                double gpa = grade.equals("N/A") ? -1 : convertGradeToGPA(Double.parseDouble(grade));
                String passFail = gpa != -1 ? determinePassFail(gpa) : "N/A";
                subjectList.append(i + 1).append(". ").append(subjects.get(i)).append(" - Grade: ").append(grade)
                        .append(", GPA: ").append(gpa != -1 ? gpa : "N/A")
                        .append(", Status: ").append(passFail).append("\n");
            }
            return "Student ID: " + studentID + "\n" +
                    "Name: " + name + "\n" +
                    "Age: " + age + "\n" +
                    "Sex: " + sex + "\n" +
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
        int mainChoice = -1; 

        do {
            System.out.println("+====================================+");
            System.out.println("|        Welcome to the System       |");
            System.out.println("+====================================+");
            System.out.println("|   1. Login                         |");
            System.out.println("|   2. Exit                          |");
            System.out.println("+------------------------------------+");
            System.out.print("└──> Enter your choice (1-2): ");
            

            String input = scan.nextLine();
            System.out.println("+------------------------------------+");
            // check if the input is numeric
            if (input.matches("\\d+")) { 
                mainChoice = Integer.parseInt(input); // convert to integer
                if (mainChoice < 1 || mainChoice > 2) { // validate range
                    System.out.println("\n");
                    System.out.println("Error: Invalid input. Please choose an option from the list (1-2).");
                    System.out.println("Note: Ensure you enter a number and not any other characters.\n");

                    mainChoice = -1; //reset to stay in the loop
                }
            } else {
                System.out.println("\n Invalid input. Please enter a valid number between 1 and 2.");
                mainChoice = -1; // rreset to stay in the loop
            }
            


            switch (mainChoice) {
                case 1: // Login

                    
                    System.out.print("└──> Enter Username/ID: ");
                    String usernameID = scan.nextLine();


                    if (usernameID.equals(adminUsername)){
                        System.out.print("└──> Enter Password: ");
                        String admin_password = scan.nextLine();
                        if(admin_password.equals(adminPassword)){
                            adminMenu(scan, students);
                        }else{
                            System.out.println("Invalid Password");
                        }
        
                    }else if(userAccounts.containsKey(usernameID)){
                        User user = userAccounts.get(usernameID);
                        System.out.print("└──> Enter password: ");
                        String user_password = scan.nextLine();

                        if (user.password.equals(user_password)){
                            userMenu(scan, students, usernameID);
                        }else{
                            System.out.println("Invalid password");
                        }
                    }else {
                        System.out.println("\n Invalid credentials!");
                    }
                    break;

                
                case 2: // Exit
                    System.out.println("Exiting the system. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please select from 1-3.");
            }
        } while (mainChoice != 2);

        scan.close();
    }

    public static void adminMenu(Scanner scan, ArrayList<Student> students) {
        int choice = -1;

         do {
            System.out.println("+====================================+");
            System.out.println("|       Admin Management Menu        |");
            System.out.println("+====================================+");
            System.out.println("|   1. Add Student                   |");
            System.out.println("|   2. View All Students             |");
            System.out.println("|   3. Update Student                |");
            System.out.println("|   4. Search Student                |");
            System.out.println("|   5. Generate Student Account      |");
            System.out.println("|   6. Add Grade                     |"); 
            System.out.println("|   7. View Grades                   |"); 
            System.out.println("|   8. Back                          |");
            System.out.println("+------------------------------------+");
            System.out.print("└──> Enter your choice (1-8): ");
            
            String input2 = scan.nextLine();

                    // check if the input is a #
            if (input2.matches("\\d+")) { 
                choice = Integer.parseInt(input2); // convert to integer
                if (choice < 1 || choice > 8) { 
                    System.out.println("Invalid choice. Please enter a number between 1 and 8");
                    choice = -1; // Reset to stay in the loop
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number between 1 and 8");
                choice = -1; // Reset to stay in the loop
            }



            switch (choice) {
                case 1: // Add Student
                    System.out.println("+====================================+");
                    System.out.println("|          Add New Student           |");
                    System.out.println("+------------------------------------+");
                    System.out.println("   Note: Type 'x' to cancel");
                    System.out.println("+------------------------------------+");
                    System.out.print("└──>Enter Student ID  : ");
                    String studentID = scan.nextLine();
                    System.out.println("+------------------------------------+");

                    if (studentID.equalsIgnoreCase("x")) {
                        break;
                    }

                    // check if the ID is already registered
                    boolean idExists = false;
                    for (Student student : students) {
                        if (student.studentID.equals(studentID)) {
                            idExists = true;
                            break;
                        }
                    }

                    if (idExists) {
                        System.out.println("└──>Student ID already registered. Returning to the main menu.");
                        break; // exit the case and go back to the main menu
                    }

                    // go on if the ID is unique
                    System.out.print("└──>Enter Student Name (Full Name)  : ");
                    String name = scan.nextLine();
                   
                    if (name.equalsIgnoreCase("x")) {
                        break;
                    }
                    System.out.print("└──>Enter Student Age  : ");
                    String age = scan.nextLine();
                    
                    if (age.equalsIgnoreCase("x")) {
                        break;
                    }

                    System.out.print("└──>Enter Student Sex (Female/Male) : ");
                    String sex = scan.nextLine();
                    
                    if (sex.equalsIgnoreCase("x")) {
                        break;
                    }

                    System.out.print("└──>Enter Program  : ");
                    String program = scan.nextLine();
                    
                    if (program.equalsIgnoreCase("x")) {
                        break;
                    }

                    System.out.print("└──>Enter Year (1-4): ");
                    String year = scan.nextLine();
                    if (year.equalsIgnoreCase("x")) {
                        break;
                    }

                    System.out.print("└──>Enter Section  : ");
                    String section = scan.nextLine();
                    
                    if (section.equalsIgnoreCase("x")) {
                        break;
                    }

                    System.out.print("└──>Enter status (Regular/Irregular)  : ");
                    String status = scan.nextLine();
                    
                    if (status.equalsIgnoreCase("x")) {
                        break;
                    }

                    Student newStudent = new Student(studentID, name, age, program, year, section, status, sex);

                    // ADD SUBJECTS
                    while (true) {
                        System.out.print("└──>Enter Subjects  : ");
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
                    System.out.println("+======================================+");
                    System.out.println("|         View All Students            |");
                    System.out.println("+======================================+");

                    if (students.isEmpty()) {
                        System.out.println("|  No Students Available               |");
                        System.out.println("+--------------------------------------+");
                    } else {
                        for (Student student : students) {
                            System.out.println("+======================================+");
                            System.out.println("| Student ID: " + student.studentID);
                            System.out.println("+======================================+");
                            System.out.println("| Name: " + student.name);
                            System.out.println("| Age: " + student.age); 
                            System.out.println("| Sex: " + student.sex);
                            System.out.println("| Program: " + student.program);
                            System.out.println("| Year: " + student.year);
                            System.out.println("| Status: " + student.status);
                            System.out.println("|--------------------------------------|");

                            System.out.println("| Subjects & Grades:");
                            for (String subject : student.subjects) {
                                // check if a grade has been entered for the subject
                                double grade = student.grades.getOrDefault(subject, -1.0);
                                double gpa;
                                String passFail;

                                if (grade == -1.0) {
                                    gpa = -1;
                                    passFail = "N/A";
                                    System.out.printf("| %-15s: Grade: %-5s, GPA: %-5s, Status: %s |\n",
                                            subject, "N/A", "N/A", passFail);
                                } else {
                                    gpa = student.convertGradeToGPA(grade);
                                    passFail = student.determinePassFail(gpa);
                                    System.out.printf("| %-15s: Grade: %-5.2f, GPA: %-5.2f, Status: %s |\n",
                                            subject, grade, gpa, passFail);
                                }
                            }
                            System.out.println("+--------------------------------------+");
                        }
                    }
                    break;



                case 3: // Update Student
                    System.out.print("Enter the Student ID of the student you want to update: ");
                    String updateID = scan.nextLine();
                    int updateChoice = -1;

                    Student studentToUpdate = null;
                    for (Student student : students) {
                        if (student.studentID.equals(updateID)) {
                            studentToUpdate = student;
                            break;
                        }
                    }
                        if (studentToUpdate != null) {
                        System.out.println("Current details of the student:");
                        
                        System.out.println("+======================================+");
                        System.out.println("|      Update Student Information     |");
                        System.out.println("+======================================+");
                        System.out.println("| Student ID: " + studentToUpdate.studentID);
                        System.out.println("| Name: " + studentToUpdate.name);
                        System.out.println("| Age: " + studentToUpdate.age);
                        System.out.println("| Sex: " + studentToUpdate.sex);
                        System.out.println("| Program: " + studentToUpdate.program);
                        System.out.println("| Year: " + studentToUpdate.year);
                        System.out.println("| Section: " + studentToUpdate.section);
                        System.out.println("| Status: " + studentToUpdate.status);
                        System.out.println("| Subjects: " + String.join(", ", studentToUpdate.subjects));
                        System.out.println("+======================================+");

                        System.out.println("|  What do you want to update?         |");
                        System.out.println("+======================================+");
                        System.out.println("|     1. Name                          |");
                        System.out.println("|     2. Age                           |");
                        System.out.println("|     3. Sex                           |");
                        System.out.println("|     4. Program                       |");
                        System.out.println("|     5. Year                          |");
                        System.out.println("|     6. Section                       |");
                        System.out.println("|     7. Status                        |");
                        System.out.println("|     8. Subjects                      |");
                        System.out.println("|     9. All                           |");
                        System.out.println("|     10. Cancel                       |");
                        System.out.println("+--------------------------------------+");
                        System.out.println("Enter your choice (1-9): ");
                        System.out.print("└──> ");

                        String input3 = scan.nextLine();
                        
                        // check if the input is a #
                        if (input3.matches("\\d+")) { 
                            updateChoice = Integer.parseInt(input3); // convert to integer
                            if (updateChoice < 1 || updateChoice > 9) { 
                                System.out.println("Invalid choice. Please enter a number between 1 and 9.");
                                updateChoice = -1; // reset to stay in the loop
                            }
                        } else {
                            System.out.println("Invalid input. Please enter a valid number between 1 and 9.");
                            updateChoice = -1; // rreset to stay in the loop
                        }

                        switch (updateChoice) {
                            case 1: // UPDATE NAME
                                System.out.println("+--------------------------------------+");
                                System.out.print("└──>  Enter new Name: ");
                                String newName = scan.nextLine();
                                studentToUpdate.name = newName;
                                System.out.println("Name updated successfully!");
                                break;
                            case 2: // UPDATE AGE
                                System.out.println("+--------------------------------------+");
                                System.out.print("└──>  Enter new Age: ");
                                String newAge = scan.nextLine();
                                studentToUpdate.age = newAge;
                                System.out.println("Age updated successfully!");
                                break;

                            case 3: // UPDATE SEX
                                System.out.println("+--------------------------------------+");
                                System.out.print("└──>  Enter new Sex (Female/Male): ");
                                String newSex = scan.nextLine();
                                studentToUpdate.sex = newSex;
                                System.out.println("Sex updated successfully!");
                                break;

                            case 4: // UPDATE PROGRAM
                                System.out.println("+--------------------------------------+");
                                System.out.print("└──>  Enter new Program: ");
                                String newProgram = scan.nextLine();
                                studentToUpdate.program = newProgram;
                                System.out.println("Program updated successfully!");
                                break;
                            case 5: // UPDATE YEAR
                                System.out.println("+--------------------------------------+");
                                System.out.print("└──>  Enter new Year: ");
                                String newYear = scan.nextLine();
                                studentToUpdate.year = newYear;
                                System.out.println("Year updated successfully!");
                                break;
                            case 6: // UPDATE SECTION
                                System.out.println("+--------------------------------------+");
                                System.out.print("└──>  Enter new Section: ");
                                String newSection = scan.nextLine();
                                studentToUpdate.section = newSection;
                                System.out.println("Section updated successfully!");
                                break;
                            case 7: // UPDATE STATUS
                                System.out.println("+--------------------------------------+");
                                System.out.print("└──>  Enter new Status: ");
                                String newStatus = scan.nextLine();
                                studentToUpdate.status = newStatus;
                                System.out.println("Status updated successfully!");
                                break;
                            case 8: // UPDATE SUBJECTS
                            boolean updateSubjectLoop = true;
                            while (updateSubjectLoop) {
                                System.out.println("+--------------------------------------+");
                                System.out.println("|           Current Subjects           |");
                                System.out.println("+--------------------------------------+");


                                for (int i = 0; i < studentToUpdate.subjects.size(); i++) {
                                    System.out.println((i + 1) + ". " + studentToUpdate.subjects.get(i));
                                }
                                System.out.println("+--------------------------------------+");
                                System.out.println("|  1. Edit Subject");
                                System.out.println("|  2. Delete Subject");
                                System.out.println("|  3. Add New Subject");
                                System.out.println("|  4. Cancel");
                                System.out.println("+--------------------------------------+");
                                int subjectChoice = -1; 


                                while (true) { 
                                    try {
                                        System.out.print("└──>Please select an action (1-4): ");
                                        subjectChoice = Integer.parseInt(scan.nextLine()); // Parse input
                                        if (subjectChoice >= 1 && subjectChoice <= 4) {
                                            break; 
                                        } else {
                                            System.out.print("|Invalid choice. Please enter a number between 1 and 4.");
                                            System.out.print("└──>Please select an action (1-4): ");
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("|Invalid input. Please enter a number.");
                                    }
                                }
                                System.out.println("+--------------------------------------+");


                                switch (subjectChoice) {
                                    case 1: // edit Subject
                                        Boolean running = true;
                                        while (running) { // loop until valid input is provided
                                                try {
                                                    System.out.print("└──>Enter the number of the subject to edit: ");
                                                    int editNumber = Integer.parseInt(scan.nextLine()) - 1; // Read input and parse it as an integer

                                                    if (editNumber >= 0 && editNumber < studentToUpdate.subjects.size()) {
                                                        System.out.print("└──> Enter new subject name: ");
                                                        String newSubject = scan.nextLine();
                                                        studentToUpdate.subjects.set(editNumber, newSubject);
                                                        System.out.println("| Subject updated successfully!");
                                                        running = false; // exit the loop after successful update
                                                    } else {
                                                        System.out.println("| Invalid subject number.");
                                                    }
                                                } catch (NumberFormatException e) {
                                                    System.out.println("| Invalid input. Please enter a number: ");
                                                    // handle input that is not int
                                                }
                                            }break;


                                    case 2: // DELETE SUBJECT
                                        System.out.print("└──>Enter the number of the subject to delete: ");
                                        int deleteNumber = -1;

                                        while (true) {
                                            try {
                                                deleteNumber = Integer.parseInt(scan.nextLine()) - 1; // Parse input and subtract 1
                                                if (deleteNumber >= 0 && deleteNumber < studentToUpdate.subjects.size()) {
                                                    studentToUpdate.subjects.remove(deleteNumber);
                                                    System.out.println("|Subject deleted successfully!");
                                                    break; // exit loop after successful deletion
                                                } else {
                                                    System.out.print("└──>Invalid subject number. Please enter a valid number: ");
                                                }
                                            } catch (NumberFormatException e) {
                                                System.out.println("|Invalid input. Please enter a number: ");
                                                
                                            }
                                        }break;
                                    case 3: //ADD NEW SUBJ
                                        System.out.print("└──>Enter new subject: ");
                                        String newSubjectName = scan.nextLine();
                                        studentToUpdate.subjects.add(newSubjectName);
                                        System.out.println("|Subject added successfully!");
                                        break;
                                    case 4: // CANCEL CHOOSING
                                        updateSubjectLoop = false;
                                        System.out.println("|Subject update canceled.");
                                        break;
                                    
                                }
                            }
                            break;

                            case 9: // UPDATE ALL THE DETAILS OF THE STUDENT
                            System.out.print("└──>Enter new Name: ");
                            studentToUpdate.name = scan.nextLine();

                            System.out.print("└──>Enter new Age: ");
                            studentToUpdate.age = scan.nextLine();

                            System.out.print("└──>Enter new Sex: ");
                            studentToUpdate.sex = scan.nextLine();

                            System.out.print("└──>Enter new Program: ");
                            studentToUpdate.program = scan.nextLine();

                            System.out.print("└──>Enter new Year: ");
                            studentToUpdate.year = scan.nextLine();

                            System.out.print("└──>Enter new Section: ");
                            studentToUpdate.section = scan.nextLine();

                            System.out.print("└──>Enter new Status: ");
                            studentToUpdate.status = scan.nextLine();

                       
                            boolean running_subject = true;
                            while (running_subject) {
                                System.out.println("+--------------------------------------+");
                                System.out.println("|           Current Subjects           |");
                                System.out.println("+--------------------------------------+");


                                for (int i = 0; i < studentToUpdate.subjects.size(); i++) {
                                    System.out.println((i + 1) + ". " + studentToUpdate.subjects.get(i));
                                }
                                System.out.println("+--------------------------------------+");
                                System.out.println("|  1. Edit Subject");
                                System.out.println("|  2. Delete Subject");
                                System.out.println("|  3. Add New Subject");
                                System.out.println("|  4. Cancel");
                                System.out.println("+--------------------------------------+");
                                int subjectChoice = -1; 


                                while (true) { 
                                    try {
                                        System.out.print("└──>Please select an action (1-4): ");
                                        subjectChoice = Integer.parseInt(scan.nextLine()); // Parse input
                                        if (subjectChoice >= 1 && subjectChoice <= 4) {
                                            break; 
                                        } else {
                                            System.out.print("|Invalid choice. Please enter a number between 1 and 4.");
                                            System.out.print("└──>Please select an action (1-4): ");
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("|Invalid input. Please enter a number.");
                                    }
                                }
                                System.out.println("+--------------------------------------+");


                                switch (subjectChoice) {
                                    case 1: // EDIT SUBJECT
                                        Boolean running = true;
                                        while (running) { // Loop until valid input is provided
                                                try {
                                                    System.out.print("└──>Enter the number of the subject to edit: ");
                                                    int editNumber = Integer.parseInt(scan.nextLine()) - 1; // Read input and parse it as an integer

                                                    if (editNumber >= 0 && editNumber < studentToUpdate.subjects.size()) {
                                                        System.out.print("└──> Enter new subject name: ");
                                                        String newSubject = scan.nextLine();
                                                        studentToUpdate.subjects.set(editNumber, newSubject);
                                                        System.out.println("| Subject updated successfully!");
                                                        running = false; // exit the loop after successful update
                                                    } else {
                                                        System.out.println("| Invalid subject number.");
                                                    }
                                                } catch (NumberFormatException e) {
                                                    System.out.println("| Invalid input. Please enter a number: ");
                                                    // handle input that is not an integer
                                                }
                                            }break;


                                    case 2: // DELETE SUBJ
                                        System.out.print("└──>Enter the number of the subject to delete: ");
                                        int deleteNumber = -1;

                                        while (true) {
                                            try {
                                                deleteNumber = Integer.parseInt(scan.nextLine()) - 1; // Parse input and subtract 1
                                                if (deleteNumber >= 0 && deleteNumber < studentToUpdate.subjects.size()) {
                                                    studentToUpdate.subjects.remove(deleteNumber);
                                                    System.out.println("|Subject deleted successfully!");
                                                    break; // exit loop after successful deletion
                                                } else {
                                                    System.out.print("└──>Invalid subject number. Please enter a valid number: ");
                                                }
                                            } catch (NumberFormatException e) {
                                                System.out.println("|Invalid input. Please enter a number: ");
                                                
                                            }
                                        }break;
                                    case 3: // add New Subject
                                        System.out.print("└──>Enter new subject: ");
                                        String newSubjectName = scan.nextLine();
                                        studentToUpdate.subjects.add(newSubjectName);
                                        System.out.println("|Subject added successfully!");
                                        break;
                                    case 4: // cancel
                                        running_subject = false;
                                        System.out.println("|Subject update canceled.");
                                        break;
                                    
                                }
                            }
                            

                            System.out.println("|All details updated successfully!");
                            break;


                            case 10: // Cancel
                                System.out.println("|Update operation canceled.");
                                break;
                            default:
                                System.out.println("|Invalid choice.");
                        }
                    } else {
                        System.out.println("|No student found with ID: " + updateID);
                    }
                    break;


                case 4: // search student
                    System.out.println("+--------------------------------------+");
                    System.out.println("|           Search Student             |");
                    System.out.println("+--------------------------------------+");   

                    System.out.print("└──>Enter the Student ID you want to search: ");
                    String searchID = scan.nextLine();
                    Student foundStudent = null;

                    for (Student student : students) {
                        if (student.studentID.equals(searchID)) {
                            foundStudent = student;
                            break;
                        }
                    }

                    if (foundStudent != null) {
                        System.out.println("└──>Student Found!");
                        System.out.println("+--------------------------------------+"); 
                        System.out.println("| Student ID: " + foundStudent.studentID);
                        System.out.println("| Name: " + foundStudent.name);
                        System.out.println("| Age: " + foundStudent.age);
                        System.out.println("| Sex: " + foundStudent.sex);
                        System.out.println("| Program: " + foundStudent.program);
                        System.out.println("| Year: " + foundStudent.year);
                        System.out.println("| Section: " + foundStudent.section);
                        System.out.println("| Status: " + foundStudent.status);
                        System.out.println("| Subjects: " + String.join(", ", foundStudent.subjects));
                        System.out.println("+--------------------------------------+"); 
                    } else {
                        System.out.println("|No student found with ID: " + searchID);
                    }
                    break;

                case 5: // generate user account
                    System.out.println("+--------------------------------------+");
                    System.out.println("|       Generate Student Account       |");
                    System.out.println("+--------------------------------------+");   
                    System.out.print("└──>Enter Student ID to create user account: ");
                    String id = scan.nextLine();

                    boolean studentExists = false;
                    boolean accountExists = false;

                    // check if the student exists in the students list
                    for (Student student : students) {
                        if (student.studentID.equals(id)) {
                            studentExists = true;
                            // check if an account already exists for this student ID
                            if (userAccounts.containsKey(id)) {
                                accountExists = true;
                                System.out.println("|An account already exists for this student ID.");
                                break;
                            } else {
                                // generate a new password and create the account
                                String generatedPassword = UUID.randomUUID().toString().substring(0, 8);
                                userAccounts.put(id, new User(id, generatedPassword));
                                System.out.println("└──>Account created successfully!");
                                System.out.println("+--------------------------------------+");
                                System.out.println("| ID: " + id + "\n| Password: " + generatedPassword);

                                break;
                            }
                        }
                    }

                    if (!studentExists) {
                        System.out.println("|No student found with the given ID.");
                    }
                    break;


                case 6: // Add Grade
                    System.out.println("+--------------------------------------+");
                    System.out.println("|              Add Grade               |");
                    System.out.println("+--------------------------------------+"); 
                    System.out.print("└──>Enter Student ID: ");
                    String studentID3 = scan.nextLine();

                    Student student = null;

                    // find student by ID
                    for (Student s : students) {
                        if (s.studentID.equals(studentID3)) {
                            student = s;
                            break;
                        }
                    }

                    if (student != null) {
                        while (true) {
                            System.out.println("+--------------------------------------+"); 
                            System.out.println("Current Subjects:");
                            System.out.println("+--------------------------------------+"); 
                            for (int i = 0; i < student.subjects.size(); i++) {
                                System.out.println((i + 1) + ". " + student.subjects.get(i));
                            }
                            System.out.println("+--------------------------------------+"); 

                            System.out.println("Enter the subject number to add a grade for (or type 'x' to exit): ");
                            System.out.print("└──> ");
                            String input = scan.nextLine();

                            // check for exit condition
                            if (input.equalsIgnoreCase("x")) {
                                System.out.println("|Exiting grade entry for this student.");
                                break; // exit the loop
                            }

                            try {
                                int subjectNumber = Integer.parseInt(input) - 1; // convert input to integer
                                if (subjectNumber >= 0 && subjectNumber < student.subjects.size()) {
                                    String subjectName = student.subjects.get(subjectNumber);
                                    System.out.print("└──> Enter the grade for " + subjectName + "(%) : ");
                                    double grade = scan.nextDouble();
                                    scan.nextLine(); 
                                    student.grades.put(subjectName, grade);
                                    System.out.println("|Grade added successfully for " + subjectName + "!");
                                } else {
                                    System.out.println("|Invalid subject number. Please try again.");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("|Invalid input. Please enter a valid subject number or 'x' to exit.");
                            }
                        }
                    } else {
                        System.out.println("|Student not found.");
                    }
                    break;


                case 7: // view grades
                    System.out.println("+--------------------------------------+"); 
                    System.out.println("|             View Grades              |");
                    System.out.println("+--------------------------------------+"); 

                    System.out.print("└──>Enter Student ID: ");
                    String viewStudentID3 = scan.nextLine();
                    Student viewStudent = null;

                    for (Student s : students) {
                        if (s.studentID.equals(viewStudentID3)) {
                            viewStudent = s;
                            break;
                        }
                    }

                    if (viewStudent != null) {
                        String helloname = viewStudent.name;
                        System.out.println("+--------------------------------------+"); 
                        System.out.println("|GRADES FOR " + helloname.toUpperCase());
                        System.out.println("+--------------------------------------+"); 
                        for (String subject : viewStudent.subjects) {
                            double grade = viewStudent.grades.getOrDefault(subject, -1.0);
                            double gpa = grade != -1 ? viewStudent.convertGradeToGPA(grade) : -1;
                            String passFail = gpa != -1 ? viewStudent.determinePassFail(gpa) : "N/A";
                            System.out.println("| " + subject + "          :" + "Grade: " + (grade != -1 ? grade : "N/A") +
                                    "| GPA: " + (gpa != -1 ? gpa : "N/A") + "| Status: " + passFail);
                        }
                    } else {
                        System.out.println("|Student not found.");
                    }
                    break;


                case 8: // Back
                    System.out.println("└──>Returning to the main menu...");
                    break;

                
            }
        } while (choice != 8);
       
    }

    public static void userMenu(Scanner scan, ArrayList<Student> students, String studentID) {
        int choice;

        do {
            System.out.println("+====================================+");
            System.out.println("|          User Management Menu      |");
            System.out.println("+====================================+");
            System.out.println("|   1. View My Information           |");
            System.out.println("|   2. View My Grades                |");
            System.out.println("|   3. Update Password               |");
            System.out.println("|   4. Back                          |");
            System.out.println("+------------------------------------+");
            System.out.print("└──> Enter your choice (1-4): ");
            choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
                case 1: // View My Info
                    System.out.println("+---------------------------------------+"); 
                    System.out.println("|         View My Information           |");
                    System.out.println("+---------------------------------------+"); 


                    for (Student student : students) {
                        if (student.studentID.equals(studentID)) {
                           
                            System.out.println("| Student ID: " + student.studentID);
                            System.out.println("| Name: " + student.name);
                            System.out.println("| Age: " + student.age);
                            System.out.println("| Sex: " + student.sex);
                            System.out.println("| Program: " + student.program);
                            System.out.println("| Year: " + student.year);
                            System.out.println("| Section: " + student.section);
                            System.out.println("| Status: " + student.status);
                            System.out.println("| Subjects: " + String.join(", ", student.subjects));
                            System.out.println("+--------------------------------------+");
                            
                            break;

                        }
                    }
                    break;

                case 2: //view my grades
                    System.out.println("+---------------------------------------+"); 
                    System.out.println("|            View My Grades             |");
                    System.out.println("+---------------------------------------+");

                    
                    for (Student student : students) {
                    if (student.studentID.equals(studentID)) {
                        for (String subject : student.subjects) {
                            double grade = student.grades.getOrDefault(subject, -1.0);
                            double gpa = grade != -1 ? student.convertGradeToGPA(grade) : -1;
                            String passFail = gpa != -1 ? student.determinePassFail(gpa) : "N/A";

                            System.out.println("|  " + subject + ": Grade: " + (grade != -1 ? grade : "N/A") +
                                    "| GPA: " + (gpa != -1 ? String.format("%.2f", gpa) : "N/A") +
                                    "| Status: " + passFail);
                        }
                        System.out.println("+---------------------------------------+");
                        break;
                    }
                }
                break;

                case 3: // Update Password
                    System.out.println("+---------------------------------------+"); 
                    System.out.println("|          Update My Password           |");
                    System.out.println("+---------------------------------------+");
                    System.out.print("└──>Enter New Password: ");
                    String newPassword = scan.nextLine();
                    userAccounts.get(studentID).password = newPassword;
                    System.out.println("-----------------------------------------");
                    System.out.println("|Password updated successfully!");
                    break;

                case 4: // Back
                    System.out.println("|Returning to the main menu...");
                    break;

                default:
                    System.out.println("|Invalid choice. Please try again.");
            }
        } while (choice != 4);
    }
}
