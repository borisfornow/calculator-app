import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws IOException {

        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        boolean running = true;

        ServerSocket serverSocket = new ServerSocket(1234);

        while (true){

            try {

                socket = serverSocket.accept();

                inputStreamReader = new InputStreamReader(socket.getInputStream());
                outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

                bufferedReader = new BufferedReader(inputStreamReader);
                bufferedWriter = new BufferedWriter(outputStreamWriter);

                while (true){

                    String msgfromClient = bufferedReader.readLine();

                    System.out.println("Client: " + msgfromClient);

//                    bufferedWriter.write("MSG Received.");
                    bufferedWriter.newLine();
                    bufferedWriter.flush();

                    if(msgfromClient.equalsIgnoreCase("close"))
                        break;

                    String num1Str = msgfromClient;

                    String op = msgfromClient;

                    String num2Str = msgfromClient;

                    // Validate numbers
                    if (!num1Str.matches("-?\\d+") || !num2Str.matches("-?\\d+")) {
                        System.out.println("Invalid numbers! Please enter digits only.");
                        continue; // Restart loop
                    }

                    // Validate operator
                    if (!op.equals("-") && !op.equals("+") && !op.equals("/") && !op.equals("%") && !op.equals("*")) {
                        System.out.println("Invalid operator! Use - + / % *");
                        continue; // Restart loop
                    }

                    int number1 = Integer.parseInt(num1Str);
                    int number2 = Integer.parseInt(num2Str);
                    int ans = 0;

                    // Handle operations
                    switch (op) {
                        case "-" -> ans = number1 - number2;
                        case "+" -> ans = number1 + number2;
                        case "/" -> {
                            if (number2 == 0) {
                                continue;
                            }
                            ans = number1 / number2;
                        }
                        case "%" -> {
                            if (number2 == 0) {
                                continue;
                            }
                            ans = number1 % number2;
                        }
                        case "*" -> ans = number1 * number2;
                    }

                    // Ask if user wants to continue
                    System.out.print("Do you want to calculate again? (yes/no): ");
                    String again = msgfromClient.trim().toLowerCase();
                    if (!again.equals("yes")) {
                        continue;
                    }

                }
                socket.close();
                inputStreamReader.close();
                outputStreamWriter.close();
                bufferedReader.close();
                bufferedWriter.close();


            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }


    }
}
