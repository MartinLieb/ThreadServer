import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer extends Thread{

    int port;


    public WebServer(int port) {
        this.port = port;
    }

    public void start() {
        try {

            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server is running");

            while (true) {

                new Thread(() -> {
                    try {

                        Socket server = serverSocket.accept();
                        String html = "<!DOCTYPE html>\n" +
                                "<html lang=\"en\">\n" +
                                "<head>\n" +
                                "    <meta charset=\"UTF-8\">\n" +
                                "    <title>Min første webserver</title>\n" +
                                "</head>\n" +
                                "<body>\n" +
                                "<img src='https://i.imgur.com/IeKZqf3.png' width='500px' height='auto'>" +
                                "</body>\n" +
                                "</html>";


                        BufferedReader in = new BufferedReader(new InputStreamReader(
                                server.getInputStream()));
                        PrintWriter out = new PrintWriter(server.getOutputStream());

                        in.readLine();
                        out.println(html);
                        out.flush();
                        System.out.println("Connection received.");


                        server.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }).start();

            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
