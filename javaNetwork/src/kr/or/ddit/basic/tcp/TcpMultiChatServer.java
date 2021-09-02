package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TcpMultiChatServer {
   // 접속한 클라이언트 정보를 저장할 Map객체 변수 선언
   // ==> key값 : 접속한 사람의 이름
   // ==> value값 : 접속한 클라이언트와 연결된 Socket객체  
   private Map<String, Socket> clientMap;

   // 생성자
   public TcpMultiChatServer() {
      // clientMap을 동기화 처리가 되도록 생성한다.
      clientMap = Collections.synchronizedMap(new HashMap<>());
   }
   
   public static void main(String[] args) {
      new TcpMultiChatServer().serverStart();
   }
   
   public void serverStart() {
      ServerSocket server = null;
      Socket socket = null;
      try {
         server = new ServerSocket(7777);
         System.out.println("서버가 시작되었습니다.");
         
         while (true) {
            socket = server.accept();  // 클라이언트의 접속을 기다린다.
            
            System.out.println("[" + socket.getInetAddress() + " : " + socket.getPort() + "]에서 접속했습니다.");
         
            // 메세지를 받아서 전체에게 전송하는 Thread 실행하기
            ServerReceiver serverThread = new ServerReceiver(socket);
            serverThread.start();
         }
      } catch (Exception e) {
         // TODO: handle exception
      } finally {
         if (server != null) try { server.close(); } catch (IOException e) {   }
      }
   }  // serverStart()메서드 끝...

   // clientMap에 저장된 전체 사용자에게 메세지를 전송하는 메서드
   private void sendToAll(String msg) {
      // clientMap의 데이터 개수만큼 반복
      for (String name : clientMap.keySet()) {
         try {
            // 각 클라이언트와 연결된 소켓으로 전송할 OutputStream객체를 구한다.
            DataOutputStream dos = new DataOutputStream(
                  clientMap.get(name).getOutputStream());
            dos.writeUTF(msg);
         } catch (Exception e) {
            // TODO: handle exception
         }
      }
   }  // sendToAll()메서드 끝...
   
   // Inner Class형태로 서버에서 클라이언트로 메세지를 전송하는 Thread를 만든다.
   // ==> 이 쓰레드에서는 접속한 사람의 이름 중복 여부도 검사한다.
   class ServerReceiver extends Thread {
      private Socket socket;
      private DataInputStream dis;
      private DataOutputStream dos;
      
      // 생성자
      public ServerReceiver(Socket socket) {
         this.socket = socket;
         
         try {
            // 수신용
            dis = new DataInputStream(socket.getInputStream());
            // 송신용
            dos = new DataOutputStream(socket.getOutputStream());
         } catch (Exception e) {
            // TODO: handle exception
         }
      }  // 생성자 끝...
      
      @Override
      public void run() {
         String name = "";
         try {
            while (true) {
               // 클라이언트가 최초로 보낸 데이터는 사용자 이름인데
               // 이 이름이 중복되었는지 여부를 feedback으로 클라이언트에게 보내준다.
               name = dis.readUTF();
               
               if (clientMap.containsKey(name)) {  // 이름 중복되면
                  dos.writeUTF("이름중복");  // '이름중복' 메세지 전송
               } else {  // 중복되지 않을 때
                  dos.writeUTF("OK");
                  break;  // 반복문 탈출
               }
            }  // while문 끝...
            
            // 대화명을 받아서 전체 클라이언트에게 대화방 참여 메세지를 보낸다.
            sendToAll("[" + name + "]님이 들어오셨습니다...");
            
            // 대화명과 클라이언트와 연결된 Socket객체를 Map에 저장한다.
            clientMap.put(name, socket);
            
            // 현재 접속자 수 출력
            System.out.println("현재 서버에 접속한 인원 수 : " + clientMap.size() + "명");
            
            // 한 클라이언트가 보낸 메세지를 전체 클라이언트에게 보낸다.
            while (dis != null) {
               sendToAll(dis.readUTF());
            }
         } catch (Exception e) {
            // TODO: handle exception
         } finally {
            // 이 finally절이 실행된다는 것은 클라이언트가 접속을 종료했다는 의미이다.
            sendToAll("[" + name + "]님이 접속을 종료했습니다...");

            // 접속자 목록에서 해당 사람을 삭제한다.
            clientMap.remove(name);
            
            System.out.println("[" + socket.getInetAddress() + "]에서 접속을 종료했습니다.");
            
            // 현재 접속자 수 출력
            System.out.println("현재 서버에 접속한 인원 수 : " + clientMap.size() + "명");
         }
      }
   }
}