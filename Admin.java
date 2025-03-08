import java.util.ArrayList;
import java.util.Scanner;

class Question {
    String question;
    String[] optionsAnswers;
    int correctOptionAnswer;

    public Question(String question, String[] optionsAnswers, int correctOptionAnswer) {
        this.question = question;
        this.optionsAnswers = optionsAnswers;
        this.correctOptionAnswer = correctOptionAnswer;
    }
}

public class Admin {
    static Scanner sc = new Scanner(System.in);
    static String name = "";
    static String password = "";
    static ArrayList<Question> questions = new ArrayList<>();

    public static void Addquestions() {
        System.out.println("Enter number of questions to add:");
        int n = getValidIntInput();

        for (int i = 0; i < n; i++) {
            System.out.println("Enter question " + (i + 1) + ":");
            String questionText = sc.nextLine();

            String[] options = new String[4];
            for (int j = 0; j < 4; j++) {
                System.out.println("Enter option " + (j + 1) + ":");
                options[j] = sc.nextLine();
            }
            int correct = getValidOptionInput();

            questions.add(new Question(questionText, options, correct));
            System.out.println("Question added successfully!");
        }
    }

    public static void Viewquestions() {
        if (questions.isEmpty()) {
            System.out.println("No questions available.");
            return;
        }

        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            System.out.println((i + 1) + ". " + q.question);
            for (int j = 0; j < q.optionsAnswers.length; j++) {
                System.out.println("   " + (j + 1) + ") " + q.optionsAnswers[j]);
            }
            System.out.println("Correct Answer: Option " + q.correctOptionAnswer);
        }
    }

    public static void Updatequestion() {
        if (questions.isEmpty()) {
            System.out.println("No questions available to update.");
            return;
        }

        System.out.println("Enter question number to update:");
        int index = getValidQuestionIndex();

        System.out.println("Enter new question:");
        String questionText = sc.nextLine();

        String[] options = new String[4];
        for (int j = 0; j < 4; j++) {
            System.out.println("Enter option " + (j + 1) + ":");
            options[j] = sc.nextLine();
        }
        int correct = getValidOptionInput();

        questions.set(index, new Question(questionText, options, correct));
        System.out.println("Question updated successfully!");
    }

    public static void Deletequestion() {
        if (questions.isEmpty()) {
            System.out.println("No questions available to delete.");
            return;
        }

        System.out.println("Enter question number to delete:");
        int index = getValidQuestionIndex();

        questions.remove(index);
        System.out.println("Question deleted successfully!");
    }

    public static void login() {
        if (name.isEmpty() || password.isEmpty()) {
            System.out.println("No admin registered. Please register first.");
            return;
        }

        System.out.println("enter admin username:");
        String adminUser = sc.nextLine();
        System.out.println("enter admin password:");
        String adminPass = sc.nextLine();

        if (adminUser.equals(name) && adminPass.equals(password)) {
            System.out.println("LOGIN SUCCESSFUL!");
            while (true) {
                System.out.println("\nSELECT OPERATION:");
                System.out.println(" ADD QUESTIONS");
                System.out.println(" VIEW QUESTIONS");
                System.out.println(" UPDATE QUESTION");
                System.out.println(" DELETE QUESTION");
                System.out.println(" LOGOUT");
                System.out.println("enter what do you want to do (add/view/update/delete/logout)");
                String choice = sc.nextLine();

                switch (choice.toUpperCase()) {
                    case "ADD":
                        Addquestions();
                        break;
                    case "VIEW":
                        Viewquestions();
                        break;
                    case "UPDATE":
                        Updatequestion();
                        break;
                    case "DELETE":
                        Deletequestion();
                        break;
                    case "LOGOUT":
                        System.out.println("LOGGED OUT SUCCESSFULLY.");
                        return;
                    default:
                        System.out.println("Invalid option. Try again.");
                }
            }
        } else {
            System.out.println("Invalid credentials. Try again.");
        }
    }

    public static void register() {
        System.out.println("enter new username:");
        String newUser = sc.nextLine();
        System.out.println("enter new password:");
        String newPass = sc.nextLine();
        name = newUser;
        password = newPass;
        if(Student.Studentname.equals(newUser) && Student.Studentpassword.equals(newPass)){
            System.out.println("Don't register with student login,pls create it new !!! ");
        }
        else{
        System.out.println("REGISTRATION SUCCESSFUL!");
    }
        }
    private static int getValidIntInput() {
        while (true) {
            try {
                int input = sc.nextInt();
                sc.nextLine();
                if (input > 0) {
                    return input;
                } else {
                    System.out.println("Please enter a positive integer.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter an integer.");
                sc.nextLine();
            }
        }
    }

    private static int getValidOptionInput() {
        while (true) {
            try {
                int input = sc.nextInt();
                sc.nextLine();
                if (input >= 1 && input <= 4) {
                    return input;
                } else {
                    System.out.println("Please enter a number between 1 and 4.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine();
            }
        }
    }

    private static int getValidQuestionIndex() {
        while (true) {
            try {
                int input = sc.nextInt() - 1;
                sc.nextLine();
                if (input >= 0 && input < questions.size()) {
                    return input;
                } else {
                    System.out.println("Invalid question index. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine();
            }
        }
    }
}
