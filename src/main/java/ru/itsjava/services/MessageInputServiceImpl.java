package ru.itsjava.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MessageInputServiceImpl implements MessageInputService{
    private final BufferedReader bufferedReader;

    public MessageInputServiceImpl(InputStream inputStream) {
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    }

    @Override
    public String getMessage() throws IOException {
        return bufferedReader.readLine();
    }
}
