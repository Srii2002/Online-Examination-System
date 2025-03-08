import java.util.Scanner;

public class Student {
    static Scanner sc = new Scanner(System.in);
    static String Studentname = "";
    static String Studentpassword = "";
    static String previousScores = ""; // To store previous scores
    static boolean examTaken = false; // Flag to check if exam was taken

    public static void loginStudent(){
        if (Studentname.isEmpty() || Studentpassword.isEmpty()) {
            System.out.println("No student registered. Please register first.");
            return;
        }
        System.out.println("enter student name :");
        String studentUser = sc.nextLine();
        System.out.println("enter student password:");
        String studentPass = sc.nextLine();
        if(studentUser.equals(Studentname) && studentPass.equals(Studentpassword)){
            System.out.println("LOGIN SUCCESSFUL");
            examTaken = false; // Reset flag on login
            while(true){
                System.out.println("\n ENTER WHAT SHOULD DO");
                System.out.println("write exam");
                System.out.println("check results");
                System.out.println("logout");
                String studentOption = sc.nextLine();

                switch(studentOption.toUpperCase()){
                    case "WRITE EXAM":
                        Exam.startExam();
                        examTaken = true; // Set flag after exam
                        break;
                    case "CHECK RESULTS":
                        if (examTaken) {
                            if (!previousScores.isEmpty()) {
                                System.out.println("Your previous scores: " + previousScores);
                            } else {
                                System.out.println("No previous scores available.");
                            }
                            Exam.checkResults();
                        } else {
                            if (!previousScores.isEmpty()) {
                                System.out.println("Your previous scores: " + previousScores);
                            } else {
                                System.out.println("No previous scores available. Please take an exam first.");
                            }
                        }
                        break;
                    case "LOGOUT":
                        System.out.println("===============================");
                        System.out.println("LOGGED OUT SUCCESSFULLY.");
                        System.out.println("================================");
                        return;
                    default:
                        System.out.println("Invalid credentials !!");
                        break;
                }
            }
        }
    }

    public static void registerStudent(){
        System.out.println("enter new student username :");
        String newStudentUser = sc.nextLine();
        System.out.println("enter new student password :");
        String newStudentPass = sc.nextLine();
        Studentname = newStudentUser;
        Studentpassword = newStudentPass;
        System.out.println("REGISTRATION SUCCESSFUL!");
    }

    public static void updatePreviousScores(int score) {
        if (previousScores.isEmpty()) {
            previousScores = "Score: " + score;
        } else {
            previousScores += ", Score: " + score;
        }
    }
}
