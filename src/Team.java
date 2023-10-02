import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Team {
    private String name;
    private Integer conflict; // The pool that this team can't be placed in

    public Team(String name) {
        this.name = name;
        if (this.name.equals("DiG")) {
            this.conflict = 1;
        }
        if (this.name.equals("Ring of Fire")) {
            this.conflict = 3;
        }
    }

    public static String simulateDraw() {
        List<String> teams = Arrays.asList("Machine", "Ring", "DiG", "Bravo");
        Collections.shuffle(teams);

        // Pool A will always have whatever team is drawn first
        String poolA = teams.get(0);

        // Pool B is assigned the team that is drawn second.
        String poolB = teams.get(1);
        String poolC;
        // If the second drawn team is DiG, they will be moved to pool C and a new team will be drawn
        if (poolB.equals("DiG")) {
            poolC = poolB;
            poolB = teams.get(2);
        } else {
            // Otherwise, pool C is assigned the third drawn team
            poolC = teams.get(2);
        }

        // Pool D is assigned the final team
        String poolD = teams.get(3);

        if (poolD.equals("Ring")) {
            poolD = poolC;
            poolC = "Ring";
        }
        return poolC;
    }

    public static void main(String[] args) {
        int machineCount = 0;
        int ringCount = 0;
        int digCount = 0;
        int bravoCount = 0;
        int sampleSize = 10000000;
        for (int i=0; i<sampleSize; i++) {
            String result = simulateDraw();
            if (result.equals("Machine")) {
                machineCount++;
            } else if (result.equals("Ring")) {
                ringCount++;
            } else if (result.equals("DiG")) {
                digCount++;
            } else if (result.equals("Bravo")) {
                bravoCount++;
            } else {
                System.err.println("An invalid team was assigned to pool C");
            }
        }
        System.out.println("Machine: " + (double) machineCount / sampleSize * 100 + "%");
        System.out.println("Ring: " + (double) ringCount / sampleSize * 100 + "%");
        System.out.println("DiG: " + (double) digCount / sampleSize * 100 + "%");
        System.out.println("Bravo: " + (double) bravoCount / sampleSize * 100 + "%");
    }
}
