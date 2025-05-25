public class Question {
    String question;
    String[] options;
    char correctOption;

    public Question(String question, String[] options, char correctOption) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }

    public void display() {
        System.out.println(question);
        System.out.println("A. " + options[0]);
        System.out.println("B. " + options[1]);
        System.out.println("C. " + options[2]);
        System.out.println("D. " + options[3]);
    }

    public boolean isCorrect(char userAnswer) {
        return Character.toUpperCase(userAnswer) == correctOption;
    }
}
