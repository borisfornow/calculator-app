import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {

        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        try{
            socket = new Socket("localhost", 1234);

            inputStreamReader = new InputStreamReader(socket.getInputStream());
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);

            Scanner scanner = new Scanner(System.in);

            while (true){

                System.out.println("Enter a number: ");


                String msg = scanner.nextLine();
                bufferedWriter.write(msg);
                bufferedWriter.newLine();
                bufferedWriter.flush();

                String num1Str = scanner.nextLine();
                bufferedWriter.write(msg);
                bufferedWriter.newLine();
                bufferedWriter.flush();

                String response = bufferedReader.readLine();
                System.out.println("Server : " + response);

                if(msg.equalsIgnoreCase("close"))
                    break;


                System.out.println("Enter an operation (- + / % *): ");
                String op = scanner.nextLine();
                bufferedWriter.write(msg);
                bufferedWriter.newLine();
                bufferedWriter.flush();

                System.out.println("Enter another number: ");
                String num2Str = scanner.nextLine();
                bufferedWriter.write(msg);
                bufferedWriter.newLine();
                bufferedWriter.flush();

                int number1 = Integer.parseInt(num1Str);
                int number2 = Integer.parseInt(num2Str);
                int ans = 0;

                if (number2 == 0) {
                    System.out.println("Cannot divide by zero!");
                }
                if (number2 == 0) {
                    System.out.println("Cannot modulo by zero!");
                }
                System.out.println("Your answer is: " + ans);

                System.out.print("Do you want to calculate again? (yes/no): ");
                String again = msg.trim().toLowerCase();
                if (!again.equals("yes")) {

                }

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if (socket != null)
                    socket.close();

                if(inputStreamReader != null)
                    inputStreamReader.close();

                if (outputStreamWriter != null)
                    outputStreamWriter.close();

                if (bufferedReader != null)
                    bufferedReader.close();

                if (bufferedWriter != null)
                    bufferedWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
