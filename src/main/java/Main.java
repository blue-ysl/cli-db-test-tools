import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true)
        {
            System.out.println();
            System.out.println("=== CLI DB Test Tools ===");
            System.out.println("1. Create Sessions");
            System.out.println("2. Make Lock Contentions");
            System.out.println("3. Execute a Long-running Query");
            System.out.println("4. Manage Sessions");
            System.out.println("0. Quit");
            System.out.print("Select an option: ");

            String input = reader.readLine();

            switch (input)
            {
                case "1":
                    System.out.println("[Create Sessions]");
                    break;
                case "2":
                    System.out.println("[Make Lock Contentions]");
                    break;
                case "3":
                    System.out.println("[Execute a Long-running Query]");
                    break;
                case "4":
                    System.out.println("[Manage Sessions]");
                    break;
                case "0":
                    System.out.println("Bye.");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
