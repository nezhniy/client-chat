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

        while (true) {
            try {
                if (serverReader.getMessage().equals("exit")) {
                    break;
                } else {
                    System.out.println(serverReader.getMessage());
                }
            } catch (IOException e) {
                throw new NullPointerException();
            }
        }
        try{
            socket.close();
        } catch (IOException e){
            System.out.println("Socket not closed");
        }

    }
}
