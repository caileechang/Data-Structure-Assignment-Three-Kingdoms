package DSAssignment.BasicFeatures;

import java.util.*;

public class Q2 {

    public static class General {

        private String name;
        private String type;
        private int leadership;
        private int strength;
        private int intelligence;
        private int politicalSkills;
        private int hitPoints;

        public General(String name, String type, int leadership, int strength, int intelligence, int politicalSkills, int hitPoints) {
            this.name = name;
            this.type = type;
            this.leadership = leadership;
            this.strength = strength;
            this.intelligence = intelligence;
            this.politicalSkills = politicalSkills;
            this.hitPoints = hitPoints;
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }

        public int getLeadership() {
            return leadership;
        }

        public int getStrength() {
            return strength;
        }

        public int getIntelligence() {
            return intelligence;
        }

        public int getPoliticalSkills() {
            return politicalSkills;
        }

        public int getHitPoints() {
            return hitPoints;
        }

        public int getAbility() {
            return leadership + strength + intelligence + politicalSkills + hitPoints;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setLeadership(int leadership) {
            this.leadership = leadership;
        }

        public void setStrength(int strength) {
            this.strength = strength;
        }

        public void setIntelligence(int intelligence) {
            this.intelligence = intelligence;
        }

        public void setPoliticalSkills(int politicalSkills) {
            this.politicalSkills = politicalSkills;
        }

        public void setHitPoints(int hitPoints) {
            this.hitPoints = hitPoints;
        }

        @Override
        public String toString() {
            return "General{"
                    + "name='" + name + '\''
                    + ", type='" + type + '\''
                    + ", leadership=" + leadership
                    + ", strength=" + strength
                    + ", intelligence=" + intelligence
                    + ", politicalSkills=" + politicalSkills
                    + ", hitPoints=" + hitPoints
                    + '}';
        }
    }

    public static void main(String[] args) {
        // Declare the generals array
        General[] generalsArray = {
            new General("Sun Quan", "Cavalry", 98, 96, 72, 77, 95),
            new General("Zhou Yu", "Cavalry", 86, 80, 97, 80, 90),
            new General("Zhang Zhao", "Archer", 80, 22, 89, 99, 60),
            new General("Xu Sheng", "Archer", 78, 90, 72, 40, 94),
            new General("Zhu Ge Jin", "Archer", 61, 63, 88, 82, 71),
            new General("Lu Su", "Infantry", 87, 43, 84, 88, 53),
            new General("Tai Shi Ci", "Cavalry", 81, 96, 43, 33, 97),
            new General("Xiao Qiao", "Infantry", 52, 42, 89, 77, 34),
            new General("Da Qiao", "Cavalry", 62, 39, 90, 62, 41),
            new General("Zhou Tai", "Infantry", 89, 92, 72, 43, 99),
            new General("Gan Ning", "Archer", 92, 98, 45, 23, 97),
            new General("Lu Meng", "Cavalry", 77, 70, 93, 83, 88),
            new General("Huang Gai", "Infantry", 98, 83, 72, 42, 89)
        };

        Arrays.sort(generalsArray, Comparator.comparingInt(General::getLeadership));
        General[] sortedByLeadership = Arrays.copyOf(generalsArray, generalsArray.length);
        System.out.println("Generals sorted by leadership: ");
        printGenerals(sortedByLeadership);

        // Sort generals by strength
        Arrays.sort(generalsArray, Comparator.comparingInt(General::getStrength));
        General[] sortedByStrength = Arrays.copyOf(generalsArray, generalsArray.length);
        System.out.println("Generals sorted by strength: ");
        printGenerals(sortedByStrength);

        // Sort generals by intelligence
        Arrays.sort(generalsArray, Comparator.comparingInt(General::getIntelligence));
        General[] sortedByIntelligence = Arrays.copyOf(generalsArray, generalsArray.length);
        System.out.println("Generals sorted by intelligence: ");
        printGenerals(sortedByIntelligence);

        // Sort generals by political skill
        Arrays.sort(generalsArray, Comparator.comparingInt(General::getPoliticalSkills));
        General[] sortedByPoliticalSkill = Arrays.copyOf(generalsArray, generalsArray.length);
        System.out.println("Generals sorted by political skill: ");
        printGenerals(sortedByPoliticalSkill);

        // Sort generals by hitPoints
        Arrays.sort(generalsArray, Comparator.comparingInt(General::getHitPoints));
        General[] sortedByHitPoints = Arrays.copyOf(generalsArray, generalsArray.length);
        System.out.println("Generals sorted by hitPoints: ");
        printGenerals(sortedByHitPoints);

        // Sort generals based on their ability (sum of attributes)
        Arrays.sort(generalsArray, Comparator.comparingInt(General::getAbility));
        General[] sortedByAbility = Arrays.copyOf(generalsArray, generalsArray.length);
        System.out.println("Generals sorted by ability: ");
        printGenerals(sortedByAbility);

        System.out.println("\nFormation of teams:");
        String[] levels = {"S", "A", "B", "C"};
        for (String level : levels) {
            System.out.println("Level " + level);
            System.out.println("Leadership Team: " + formLeadershipTeam(sortedByLeadership, level));
            System.out.println("Strength Team: " + formStrengthTeam(sortedByStrength, level));
            System.out.println("Intelligence Team: " + formIntelligenceTeam(sortedByIntelligence, level));
            System.out.println("Political Skills Team: " + formPoliticalSkillsTeam(sortedByPoliticalSkill, level));
            System.out.println("Hit Points Team: " + formHitPointsTeam(sortedByHitPoints, level));
            System.out.println("");
        }
    }

    public static void printGenerals(General[] generals) {
        for (General general : generals) {
            System.out.println(general.getName());
        }
        System.out.println("");
    }

    public static String formLeadershipTeam(General[] generals, String level) {
        int sumAbility = getLevel(level);
        int min = Integer.MAX_VALUE;
        String output = "No available team";
        for (int i = 0; i < generals.length; i++) {
            int left = i + 1;
            int right = generals.length - 1;
            while (left < right) {
                int sum = generals[i].getLeadership() + generals[left].getLeadership() + generals[right].getLeadership();
                if (sum >= sumAbility && sum <= min) {
                    min = sum;
                    output = generals[i].getName() + ", " + generals[left].getName() + ", " + generals[right].getName() + " {sum of ability = " + min + ")";
                }
                if (sum < sumAbility) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return output;
    }

    public static String formStrengthTeam(General[] generals, String level) {
        int sumAbility = getLevel(level);
        int min = Integer.MAX_VALUE;
        String output = "No available team";
        for (int i = 0; i < generals.length - 2; i++) {
            int left = i + 1;
            int right = generals.length - 1;
            while (left < right) {
                int sum = generals[i].getStrength() + generals[left].getStrength() + generals[right].getStrength();
                if (sum >= sumAbility && sum <= min) {
                    min = sum;
                    output = generals[i].getName() + ", " + generals[left].getName() + ", " + generals[right].getName() + " {sum of ability = " + min + ")";
                }
                if (sum < sumAbility) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return output;
    }

    public static String formIntelligenceTeam(General[] generals, String level) {
        int sumAbility = getLevel(level);
        int min = Integer.MAX_VALUE;
        String output = "No available team";
        for (int i = 0; i < generals.length; i++) {
            int left = i + 1;
            int right = generals.length - 1;
            while (left < right) {
                int sum = generals[i].getIntelligence() + generals[left].getIntelligence() + generals[right].getIntelligence();
                if (sum >= sumAbility && sum <= min) {
                    min = sum;
                    output = generals[i].getName() + ", " + generals[left].getName() + ", " + generals[right].getName() + " {sum of ability = " + min + ")";
                }
                if (sum < sumAbility) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return output;
    }

    public static String formPoliticalSkillsTeam(General[] generals, String level) {
        int sumAbility = getLevel(level);
        int min = Integer.MAX_VALUE;
        String output = "No available team";
        for (int i = 0; i < generals.length; i++) {
            int left = i + 1;
            int right = generals.length - 1;
            while (left < right) {

                int sum = generals[i].getPoliticalSkills() + generals[left].getPoliticalSkills() + generals[right].getPoliticalSkills();
                if (sum >= sumAbility && sum <= min) {
                    min = sum;
                    output = generals[i].getName() + ", " + generals[left].getName() + ", " + generals[right].getName() + " {sum of ability = " + min + ")";
                }
                if (sum < sumAbility) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return output;

    }

    public static String formHitPointsTeam(General[] generals, String level) {
        int sumAbility = getLevel(level);
        int min = Integer.MAX_VALUE;
        String output = "No available team";
        for (int i = 0; i < generals.length; i++) {
            int left = i + 1;
            int right = generals.length - 1;
            while (left < right) {
                int sum = generals[i].getHitPoints() + generals[left].getHitPoints() + generals[right].getHitPoints();
                if (sum >= sumAbility && sum <= min) {
                    min = sum;
                    output = generals[i].getName() + ", " + generals[left].getName() + ", " + generals[right].getName() + " {sum of ability = " + min + ")";
                }
                if (sum < sumAbility) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return output;
    }

    public static int getLevel(String level) {
        int minSumAbility = 0;

        switch (level) {
            case "S":
                minSumAbility = 250;
                break;
            case "A":
                minSumAbility = 220;
                break;
            case "B":
                minSumAbility = 190;
                break;
            case "C":
                minSumAbility = 0;
                break;
            default:
                System.out.println("Invalid level specified.");
        }
        return minSumAbility;
    }
}