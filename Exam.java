// Exam.java
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Scanner;

public class Exam {
    static Scanner sc = new Scanner(System.in);
    private static int score = 0;
    private static ArrayList<Integer> unattemptedQuestions = new ArrayList<>();
    private static Student student;

    public Exam(Student student) {
        this.student = student;
    }

    private static Thread timerThread;

    public static void startExam() {
        System.out.println("Welcome " + Student.Studentname + "! All the best!!!");
        if (Admin.questions.isEmpty()) {
            System.out.println("No questions available. Please contact the admin.");
            return;
        }

        System.out.println("Exam started! Answer the following questions:");
        score = 0;
        unattemptedQuestions.clear();

        for (int i = 0; i < Admin.questions.size(); i++) {
            final int questionIndex = i + 1;
            Question q = Admin.questions.get(i);

            System.out.println("\nQuestion " + questionIndex + ": " + q.question);
            for (int j = 0; j < q.optionsAnswers.length; j++) {
                System.out.println((j + 1) + ") " + q.optionsAnswers[j]);
            }

            System.out.println("You have 15 seconds to answer. Time starts now...");
            long startTime = System.currentTimeMillis();
            AtomicInteger remainingTime = new AtomicInteger(15);
            boolean[] isAnswered = {false};
            boolean[] timeUp = {false};

            timerThread = new Thread(() -> {
                while (remainingTime.get() > 0 && !Thread.currentThread().isInterrupted()) {
                    try {
                        Thread.sleep(1000);
                        remainingTime.getAndDecrement();
                        System.out.print("\rTime remaining: " + remainingTime + " seconds   ");
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
                if (!Thread.currentThread().isInterrupted()) {
                    timeUp[0] = true;
                    System.out.println("\nTime's up! Moving to the next question.");
                }
            });

            timerThread.start();

            boolean answered = false;
            while (System.currentTimeMillis() - startTime < 15000 && !answered) {
                try {
                    if (System.in.available() > 0) {
                        if (sc.hasNextInt()) {
                            int answer = sc.nextInt();
                            sc.nextLine();
                            if (answer >= 1 && answer <= 4) {
                                isAnswered[0] = true;
                                answered = true;
                                timerThread.interrupt();

                                if (answer == q.correctOptionAnswer) {
                                    score++;
                                }
                            } else {
                                System.out.println("Please enter a valid option (1-4)");
                                sc.nextLine();
                            }
                        } else {
                            sc.next();
                            System.out.println("Invalid input. Please enter a number.");
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }

            if (!answered) {
                unattemptedQuestions.add(questionIndex);
            }

            timerThread.interrupt();

            if (timeUp[0] && i == Admin.questions.size() - 1) {
                System.out.println("Exam finished.");
                return;
            }
        }

        System.out.println("\nExam completed! You can now check your results.");
    }

    public static void checkResults() {
        System.out.println("Hello " + Student.Studentname + "! Here are your results:");
        System.out.println("Your score: " + score + " out of " + Admin.questions.size());
        if (!unattemptedQuestions.isEmpty()) {
            System.out.println("Unattempted questions: " + unattemptedQuestions);
            System.out.println("Total unattempted questions: " + unattemptedQuestions.size());
        }
        if (score == Admin.questions.size()) {
            System.out.println("Congratulations! You got all questions correct!!!");
        }

        // Update previous scores
        Student.updatePreviousScores(score);
    }
}







