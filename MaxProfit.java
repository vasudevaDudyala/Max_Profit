import java.util.*;

public class MaxProfit {

    static class Building {
        String name;
        int time;
        int earnings;
        Building(String name, int time, int earnings) {
            this.name = name;
            this.time = time;
            this.earnings = earnings;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Time Unit: ");
        int n = sc.nextInt();
        Building[] buildings = {
            new Building("T", 5, 1500),
            new Building("P", 4, 1000),
            new Building("C", 10, 2000)
        };
        solve(n, buildings);
    }
    public static void solve(int n, Building[] b) {
        long maxProfit = 0;
        List<String> results = new ArrayList<>();
        for (int t = 0; t <= n / 5; t++) {
            for (int p = 0; p <= (n - t * 5) / 4; p++) {
                int remaining = n - (t * 5) - (p * 4);
                int c = remaining / 10;
                long profit = calculateProfit(n, t, p, c, b);
                if (profit > maxProfit) {
                    maxProfit = profit;
                    results.clear();
                    results.add(format(t, p, c));
                } else if (profit == maxProfit && profit > 0) {
                    results.add(format(t, p, c));
                }
            }
        }
        System.out.println("Earnings: $" + maxProfit);
        System.out.println("Solutions:");
        for (int i = 0; i < results.size(); i++) {
            System.out.println((i + 1) + ". " + results.get(i));
        }
    }
    private static long calculateProfit(int n, int t, int p, int c, Building[] b) {
        List<Building> order = new ArrayList<>();
        order.add(b[0]);
        order.add(b[1]);
        order.add(b[2]);
        order.sort((a, d) -> (d.earnings / d.time) - (a.earnings / a.time));
        Map<String, Integer> count = new HashMap<>();
        count.put("T", t);
        count.put("P", p);
        count.put("C", c);
        int currentTime = 0;
        long total = 0;
        for (Building building : order) {
            int cnt = count.get(building.name);
            for (int i = 0; i < cnt; i++) {
                currentTime += building.time;
                total += (long) (n - currentTime) * building.earnings;
            }
        }
        return total;
    }
    private static String format(int t, int p, int c) {
        return "T: " + t + " P: " + p + " C: " + c;
    }
}