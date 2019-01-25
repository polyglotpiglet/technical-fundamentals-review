package socket;

import java.io.*;
import java.net.*;

public class SocketRead {

    public static void main(String... args) throws IOException {

        String hostName = "localhost";
        int portNumber = 4444;

        try (Socket socket = new Socket(hostName, portNumber);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            socket.setSoTimeout(5000);
            String fromServer = in.readLine();
            System.out.println("From server: " + fromServer);

        }
    }
}
