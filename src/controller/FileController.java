package controller;

import model.FileMessage;
import util.JSONUtil;

import java.io.*;
import java.net.Socket;

public class FileController {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 5000;

    public static FileMessage enviarComando(FileMessage msg) {
        try (
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            out.println(JSONUtil.toJSON(msg)); // envia a msg como json
            return JSONUtil.fromJSON(in.readLine()); //lê a resposta e converte p de json p objeto, aí retorna p interface
        } catch (Exception e) {
            e.printStackTrace();
            return new FileMessage();
        }
    }
}
