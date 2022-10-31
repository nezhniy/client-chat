package ru.itsjava.services;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientServiceImpl implements ClientService {
    public final static int PORT = 8081;
    public final static String HOST = "localhost";

    @Override
    public void start() throws IOException {
        Socket socket = new Socket(HOST, PORT);

        if (socket.isConnected()) {
            new Thread(new SocketRunnable(socket)).start();

            PrintWriter serverWriter = new PrintWriter(socket.getOutputStream());

            MessageInputService messageInputService = new MessageInputServiceImpl(System.in);
            System.out.println("Введите сообщение:");
            String consoleMessage = messageInputService.getMessage();

            serverWriter.println(consoleMessage);
            serverWriter.flush();
        }
    }
}
