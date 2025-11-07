import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("Server started. Waiting for client...");

            while (true) {
                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))
                ) {
                    System.out.println("Client connected.");

                    String msgFromClient;
                    while ((msgFromClient = bufferedReader.readLine()) != null) {
                        if (msgFromClient.equalsIgnoreCase("close")) {
                            System.out.println("Client disconnected.");
                            break;
                        }

                        System.out.println("Client: " + msgFromClient);

                        try {
                            BasicOperation op = new BasicOperation(msgFromClient);
                            double result = op.calculate();
                            bufferedWriter.write("Result: " + result);
                        } catch (Exception e) {
                            bufferedWriter.write("Error: " + e.getMessage());
                        }

                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                    }

                } catch (IOException e) {
                    System.out.println("Connection error: " + e.getMessage());
                }
            }

        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}
