package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main2 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        final int START_MONEY = Integer.parseInt(st.nextToken());
        final int YEARS_LIMIT = Integer.parseInt(st.nextToken());
        int money[] = new int[YEARS_LIMIT + 1];
        money[0] = START_MONEY;
        for (int i = 1; i <= YEARS_LIMIT; i++) {
            money[i] = (int) (money[i - 1] * 1.05);
            if (i >= 3) {
                money[i] = Math.max(money[i], (int) (money[i - 3] * 1.2));
            }
            if (i >= 5) {
                money[i] = Math.max(money[i], (int) (money[i - 5] * 1.35));
            }
        }
        sb.append(money[YEARS_LIMIT]);
        sb.append("\n");
        bw.write(sb.toString());

        bw.flush();
        br.close();
        bw.close();

    }


}