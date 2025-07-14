package servidor;

import model.FileMessage;
import util.FileUtil;
import util.JSONUtil;
import java.io.*;
import java.net.*;
import java.util.*;

public class ServerApp {

    private static final int PORT = 5000;
    private static final String DIR = new File("servidor/arquivos").getAbsolutePath();

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(PORT);
            System.out.println("Servidor pronto na porta " + PORT);

            while (true) {
                Socket client = server.accept(); //acewita conexao
                new Thread(() -> handleClient(client)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket socket) {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            String line = in.readLine();
            FileMessage req = JSONUtil.fromJSON(line);
            FileMessage resp = new FileMessage();

            switch (req.cmd) {
                case "list_req": //lista os arquivos da pasta servidor/arquivos
                    File[] files = new File(DIR).listFiles();
                    resp.cmd = "list_resp";
                    resp.files = new ArrayList<>();
                    for (File f : files) {
                        resp.files.add(f.getName()); //devolve a lista com os nomes
                    }
                    break;

                case "put_req": //verifica se tem o arquivo na pasta do cliente, se tiver retorna o arquivo com ok, se não retorna fail
                    String nomeArquivo = new File(req.file).getName(); // só o nome seguro
                    String pathPut = DIR + "\\" + nomeArquivo;

                    System.out.println(pathPut);
                    
                    FileUtil.base64ToFile(req.value, pathPut);

                    String hash = FileUtil.hashFile(pathPut);
                    resp.cmd = "put_resp";
                    resp.file = nomeArquivo;
                    resp.status = hash.equals(req.hash) ? "ok" : "fail";
                    break;

                case "get_req": // se sexistir envia get_resp com os dados, se não existir envia vazio
                    String pathGet = DIR + "/" + req.file;
                    if (new File(pathGet).exists()) {
                        resp.cmd = "get_resp";
                        resp.file = req.file;
                        resp.value = FileUtil.fileToBase64(pathGet);
                        resp.hash = FileUtil.hashFile(pathGet);
                    } else {
                        resp.cmd = "get_resp";
                        resp.file = req.file;
                        resp.value = "";
                        resp.hash = "";
                    }
                    break;
            }

            out.println(JSONUtil.toJSON(resp));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
