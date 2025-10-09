import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 1234);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Connected to server.");
            System.out.println("Enter calculations in the format: <num1> <op> <num2>");
            System.out.println("Type 'close' to exit.");

            while (true) {
                System.out.print("Enter: ");
                String input = scanner.nextLine();

                writer.write(input);
                writer.newLine();
                writer.flush();

                if (input.equalsIgnoreCase("close")) {
                    System.out.println("Connection closed.");
                    break;
                }

                String response = reader.readLine();
                if (response == null) {
                    System.out.println("Server disconnected.");
                    break;
                }

                System.out.println("Server: " + response);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
