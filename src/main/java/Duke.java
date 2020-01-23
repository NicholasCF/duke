import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Duke {
    ArrayList<Task> tasks;

    public Duke() {
        tasks = new ArrayList<>();
    }

    public static void main(String[] args) {
        Duke chatbot = new Duke();

        chatbot.greet();

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String command = sc.nextLine();

            if (command.equals("bye")) {
                chatbot.bye();
                break;
            } else {
                chatbot.process(command);
            }
        }
    }

    private void greet() {
        String greeting = "Hello. I'm Duke.\n"
                + "Oh, it's you, can you please stop coming?";

        say(greeting);
    }

    private void process(String speech) {
        String[] words = speech.split(" ");
        String firstWord = words[0];

        switch (firstWord) {
            case "list":
                say("Here are your procrastinated tasks:\n"
                        + getTasks());
                break;
            default:
                addTask(speech);
                say("Added: " + speech);
                break;
        }
    }

    private void bye() {
        String parting = "Bye. Please don't come back again or I'll call the cops.";

        say(parting);
    }

    private void say(String speech) {
        String separator = "\t============================================================\n";

        String[] lines = speech.split("\n");
        String indented = "\t " + String.join("\n\t ", lines) + "\n";

        String result = separator + indented + separator;

        System.out.println(result);
    }

    private void addTask(String task) {
        tasks.add(new Task(task));
    }

    private String getTasks() {
        String result = "";

        int index = 1;
        for (Task t : tasks) {
            result += index++
                    + "." + t + "\n";
        }

        return result;
    }
}
