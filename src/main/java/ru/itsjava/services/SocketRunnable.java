package ru.itsjava.services;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.net.Socket;

@RequiredArgsConstructor
public class SocketRunnable implements Runnable{
    private final Socket socket;

    @Override
    public void run() {
        MessageInputService serverReader = null;
        try {
            serverReader = new MessageInputServiceImpl(socket.getInputStream());
        } catch (IOException e) {
            try {
                socket.close();
            } catch (IOException ex) {
                throw new NullPointerException();
            }
        }
        String messageFromClient = null;
        try {
            messageFromClient = serverReader.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            if ((messageFromClient == null) || messageFromClient.equals("exit")) {
                break;
            } else {
                System.out.println(messageFromClient);
            }
        }
        try{
            socket.close();
        } catch (IOException e){
            System.out.println("Socket not closed");
        }
        System.exit(0);

    }
}
