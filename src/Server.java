import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("Server started. Waiting for client...");

            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            String message;

            while ((message = reader.readLine()) != null) {
                if (message.equalsIgnoreCase("close")) {
                    writer.write("Connection closed.\n");
                    writer.flush();
                    break;
                }

                // Expecting input in format: num1 operator num2
                String[] parts = message.split(" ");
                if (parts.length != 3) {
                    writer.write("Invalid format. Use: <num1> <op> <num2>\n");
                    writer.flush();
                    continue;
                }

                try {
                    int num1 = Integer.parseInt(parts[0]);
                    String op = parts[1];
                    int num2 = Integer.parseInt(parts[2]);
                    int result = 0;
                    boolean valid = true;

                    switch (op) {
                        case "+" -> result = num1 + num2;
                        case "-" -> result = num1 - num2;
                        case "*" -> result = num1 * num2;
                        case "/" -> {
                            if (num2 == 0) {
                                writer.write("Cannot divide by zero!\n");
                                valid = false;
                            } else {
                                result = num1 / num2;
                            }
                        }
                        case "%" -> {
                            if (num2 == 0) {
                                writer.write("Cannot modulo by zero!\n");
                                valid = false;
                            } else {
                                result = num1 % num2;
                            }
                        }
                        default -> {
                            writer.write("Invalid operator. Use + - * / %\n");
                            valid = false;
                        }
                    }

                    if (valid) writer.write("Result: " + result + "\n");
                } catch (NumberFormatException e) {
                    writer.write("Invalid number format.\n");
                }

                writer.flush();
            }

            socket.close();
            System.out.println("Client disconnected.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
