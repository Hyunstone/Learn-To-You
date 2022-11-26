package server;

import database.Total;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    Total total;

    // 서버 소켓과 클라이언트 연결 소켓
    private ServerSocket ss = null;
    private Socket s = null;

    public Server(Total total) {
        this.total = total;

    }

    public static void main(String[] args) {
        Total total = new Total();
        Server server = new Server(total);
        server.run();
    }

    public void run() {
        try {
            // 서버 소켓 생성
            ss = new ServerSocket(8889);
            System.out.println("server start");

            while(true) {
                System.out.println("up");
                s = ss.accept();
                System.out.println("down");
                ServerThread serverThread = new ServerThread();
                serverThread.start();
            }

        } catch(Exception e) {
            System.out.println("Server Exception 발생!!");
            e.printStackTrace();
        }
    }


    class ServerThread extends Thread {

        // 입출력 스트림
        private BufferedReader requestBuffer = null;
        private BufferedWriter responseBuffer = null;

        public void start() {
            ServerController serverController = new ServerController(total);

            try {
                requestBuffer = new BufferedReader(new InputStreamReader(s.getInputStream()));
                responseBuffer = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

                System.out.println(requestBuffer);
                String request = requestBuffer.readLine();
                String response = serverController.response(request);

                if (response != null) {
                    responseBuffer.write(response);
                    responseBuffer.flush();
                }


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }



}
