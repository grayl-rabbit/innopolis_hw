package main.java.part01.lesson09.task01;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Класс-клиент чат-сервера.
 * @author L
 */
public class Client2 {
    final Socket socket;
    final BufferedReader socketReader;
    final BufferedWriter socketWriter;
    final BufferedReader userInput;
    private String nickname;

    public Client2(String host, int port) throws IOException {

        setNickname();

        socket = new Socket(host, port);

        socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
        socketWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));

        userInput = new BufferedReader(new InputStreamReader(System.in));
        new Thread(new Receiver()).start();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname() throws IOException {
        System.out.println("Type nick: ");

        Scanner scanner = new Scanner(System.in);
        String nick;
        if (!(nick = scanner.nextLine()).isEmpty()) {
            this.nickname = nick;
        }else{
            this.nickname = "Anonymous";
        }
    }

    /**
     * read from console and send to server
     */
    public void run() {
        System.out.println("Type: ");

        while (true) {
            String userString = null;
            try {
                userString = userInput.readLine();
            } catch (IOException ignored) {}
            if (userString == null || userString.length() == 0 || socket.isClosed()) {
                close();
                break;
            } else {
                try {
                    socketWriter.write(nickname + ": " + userString);
                    socketWriter.write("\n");
                    socketWriter.flush();
                } catch (IOException e) {
                    close();
                }
            }
        }
    }

    /**
     * метод закрывает коннект и выходит из
     * программы
     */
    public synchronized void close() {
        if (!socket.isClosed()) {
            try {
                socket.close();
                System.exit(0);
            } catch (IOException ignored) {
                ignored.printStackTrace();
            }
        }
    }

    public static void main(String[] args)  {
        try {
            new Client2("localhost", 45000).run();
        } catch (IOException e) {
            System.out.println("Unable to connect. Server not running?");
        }
    }

    /**
     * Вложенный приватный класс асинхронного чтения
     */
    private class Receiver implements Runnable{
        public void run() {
            while (!socket.isClosed()) {
                String line = null;
                try {
                    line = socketReader.readLine();
                } catch (IOException e) {
                    if ("Socket closed".equals(e.getMessage())) {
                        break;
                    }
                    System.out.println("Connection lost");
                    close();
                }
                if (line == null) {
                    System.out.println("Server has closed connection");
                    close();
                } else {
                    System.out.println(line);
                }
            }
        }
    }
}