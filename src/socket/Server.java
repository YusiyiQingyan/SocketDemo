package socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by weiwen on 2020/4/7
 */

public class Server {

    public static void main(String[] args) {
        final String QUIT = "quit";
        final int DEFAULT_PORT = 8888;
        ServerSocket serverSocket = null;

        try {
            //绑定监听端口
            serverSocket = new ServerSocket(DEFAULT_PORT);
            System.out.println("服务开启，监听端口：" + DEFAULT_PORT);
            //等待客户端连接
            Socket socket = serverSocket.accept();
            System.out.println("客户端[" + socket.getPort() + "]已连接");
            //客户端和服务端进行IO操作
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream()));
            //读取客户端发送的消息
            String msg = null;
            while ((msg=reader.readLine())!=null){
                System.out.println("客户端[" + socket.getPort() + "]:" + msg);

                //对客户端发来的消息给予响应
                writer.write("服务器：" + msg + "\n");
                writer.flush();
                //判断客户端是否想要退出
                if (msg.equals(QUIT)) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                    System.out.println("关闭serverSocket");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}