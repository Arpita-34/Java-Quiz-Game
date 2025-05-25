import java.util.*;
import java.util.concurrent.*;

public class QuizGame {
    static Scanner scanner = new Scanner(System.in);
    static int score = 0;

    public static void main(String[] args) {
        List<Question> questions = loadQuestions();
        Collections.shuffle(questions);

        System.out.println("🧠 Welcome to the Quiz Game!");
        System.out.println("You have 20 seconds to answer each question.");
        System.out.println("Type A, B, C, or D to answer. Let's begin!\n");

        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            System.out.println("Question " + (i + 1) + ":");
            q.display();

            char userAnswer = getAnswerWithTimeout();

            if (userAnswer == 0) {
                System.out.println("⏰ Time's up! Moving to the next question.\n");
            } else if (q.isCorrect(userAnswer)) {
                System.out.println("✅ Correct!\n");
                score++;
            } else {
                System.out.println("❌ Wrong! The correct answer was " + q.correctOption + ".\n");
            }
        }

        System.out.println("🎉 Quiz Over! Your Score: " + score + "/" + questions.size());
    }

    public static List<Question> loadQuestions() {
        List<Question> list = new ArrayList<>();

        list.add(new Question("What is the capital of France?",
                new String[]{"Rome", "Berlin", "Paris", "Madrid"}, 'C'));

        list.add(new Question("Which planet is known as the Red Planet?",
                new String[]{"Earth", "Mars", "Venus", "Jupiter"}, 'B'));

        list.add(new Question("What is 10 + 15?",
                new String[]{"20", "25", "30", "35"}, 'B'));

        list.add(new Question("Which is the largest ocean?",
                new String[]{"Indian", "Arctic", "Atlantic", "Pacific"}, 'D'));

        list.add(new Question("Who wrote 'Romeo and Juliet'?",
                new String[]{"Shakespeare", "Hemingway", "Dickens", "Austen"}, 'A'));

        return list;
    }

    public static char getAnswerWithTimeout() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(() -> {
            System.out.print("Your answer (A/B/C/D): ");
            return scanner.nextLine();
        });

        try {
            String input = future.get(20, TimeUnit.SECONDS);
            if (input.length() > 0) {
                return Character.toUpperCase(input.charAt(0));
            } else {
                return 0;
            }
        } catch (TimeoutException e) {
            future.cancel(true);
            return 0;
        } catch (Exception e) {
            return 0;
        } finally {
            executor.shutdownNow();
        }
    }
}
