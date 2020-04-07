package socket;

import java.io.*;
import java.net.Socket;

/**
 * Created by weiwen on 2020/4/7
 */

public class Client {
    public static void main(String[] args) {
        final String QUIT = "quit";
        final String DEFAULT_SERVER_HOST = "127.0.0.1";
        final int DEFAULT_SERVER_PORT = 8888;
        Socket socket = null;
        BufferedWriter writer = null;

        try {
            //创建Socket
            socket = new Socket(DEFAULT_SERVER_HOST, DEFAULT_SERVER_PORT);
            //创建IO流
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream()));

            //等待用户从控制台输入待发送的消息
            BufferedReader consoleReader = new BufferedReader(
                    new InputStreamReader(System.in)
            );
            while (true) {
                String input = consoleReader.readLine();
                //发送消息到服务器
                writer.write(input + "\n");
                writer.flush();
                //读取服务端返回的消息
                // 读取服务器返回的消息
                String msg = reader.readLine();
                System.out.println(msg);
                //判断用户是否输入退出程序指令
                if (QUIT.equalsIgnoreCase(input)) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (writer != null) {
                try {
                    writer.close();
                    System.out.println("关闭socket");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}