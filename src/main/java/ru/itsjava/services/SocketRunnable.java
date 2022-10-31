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
            e.printStackTrace();
        }

        while (true){
            try {
                System.out.println(serverReader.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
