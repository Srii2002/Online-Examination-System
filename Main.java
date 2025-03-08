import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void AdminWorking() {
        while (true) {
            System.out.println("\n========================================");
            System.out.println("||        WELCOME TO ADMIN PORTAL     ||");
            System.out.println("========================================");
            System.out.println(" Admin login");
            System.out.println(" Admin register");
            System.out.println(" exit");
            System.out.print("enter what do you want to do (login/register/exit) ");
            String choice = sc.nextLine();

            switch (choice.toUpperCase()) {
                case "LOGIN":
                    Admin.login();
                    break;
                case "REGISTER":
                    Admin.register();
                    break;
                case "EXIT":
                    System.out.println("||        EXITED SUCCESSFULLY         ||");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    public static void StudentWorking(){
        while (true) {
            System.out.println("\n========================================");
            System.out.println("||        WELCOME TO STUDENT PORTAL     ||");
            System.out.println("========================================");
            System.out.println(" Student login");
            System.out.println(" Student registeration");
            System.out.println(" exit");
            System.out.print("enter what do you want (login/register/exit) ");
            String choiceStudent = sc.nextLine();

            switch (choiceStudent.toUpperCase()) {
                case "LOGIN":
                    Student.loginStudent();
                    break;
                case "REGISTER":
                    Student.registerStudent();
                    break;
                case "EXIT":
                    System.out.println("||        EXITED SUCCESSFULLY         ||");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }




    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println(" WELCOME TO ONLINE EXAMINATION PORTAL ");
        System.out.println("========================================");
        while (true) {
            System.out.println("\nSELECT USER TYPE:");
            System.out.println(" Admin");
            System.out.println(" Student ");
            System.out.println(" Exit");
            System.out.print("ENTER CHOICE: ");
            String option = sc.nextLine();

            switch (option.toUpperCase()) {
                case "ADMIN":
                    AdminWorking();
                    break;
                case "STUDENT":
                    StudentWorking();
                    break;
                case "EXIT":
                    System.out.println("||        EXITED SUCCESSFULLY         ||");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}