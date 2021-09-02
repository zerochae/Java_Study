package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{
	
   public static void main(String[] args)  {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      String[] input =null;
      int T=0;
      try {
         T = Integer.parseInt(br.readLine());
      } catch (NumberFormatException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      int H,W,N;
      String room ="";

      for(int i=0; i<T; i++) {
         int cnt = 1;
         try {
            input = br.readLine().split(" ");
         } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
         H = Integer.parseInt(input[0]);   // 층수
         W = Integer.parseInt(input[1]); // 옆 호실
         N = Integer.parseInt(input[2]); // 사람 수
         if(H>=N) {
            room = N*10+ "" +01;
         } else {
            //N-H = 층수
            while(H<N) {
               cnt++;
               N -= H;
               if(cnt<10) {
                  room = N +""+0+cnt;
               } else {
                  room = N +""+cnt;
               }
            }
         }
         System.out.println(room);
      }
   }
}