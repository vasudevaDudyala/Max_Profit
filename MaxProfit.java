import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static class Solution {
        int t, p, c;
        Solution(int t, int p, int c) {
            this.t = t;
            this.p = p;
            this.c = c;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Time Unit: ");
        int n = sc.nextInt();
        int maxProfit = 0;
        List<Solution> solutions = new ArrayList<>();
        for (int t = 0; t <= n / 5; t++) {
            for (int p = 0; p <= n / 4; p++) {
                for (int c = 0; c <= n / 10; c++) {
                    int totalTime = (t * 5) + (p * 4) + (c * 10);
                    if (totalTime < n) {
                        int currentTime = 0;
                        int profit = 0;
                        for (int i = 0; i < t; i++) {
                            currentTime += 5;
                            profit += (n - currentTime) * 1500;
                        }
                        for (int i = 0; i < p; i++) {
                            currentTime += 4;
                            profit += (n - currentTime) * 1000;
                        }
                        for (int i = 0; i < c; i++) {
                            currentTime += 10;
                            profit += (n - currentTime) * 2000;
                        }
                        if (profit > maxProfit) {
                            maxProfit = profit;
                            solutions.clear();
                            solutions.add(new Solution(t, p, c));
                        }
                        else if (profit == maxProfit) {
                            solutions.add(new Solution(t, p, c));
                        }
                    }
                }
            }
        }
        System.out.println("\nEarnings: $" + maxProfit);
        System.out.println("Solutions:");
        int count = 1;
        for (Solution s : solutions) {
            System.out.println(
                    count + ". T: " + s.t +
                    " P: " + s.p +
                    " C: " + s.c
            );
            count++;
        }
        sc.close();
    }
}
