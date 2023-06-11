package DSAssignment;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Enter 0 for basic features, 1 for extra features, -1 for exit: ");
            int a = sc.nextInt();
            if (a == 0) {
                callBasic(args);
            } else if (a == 1) {
                callExtra(args);
            } else if (a == -1) {
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }

    }

    public static void callBasic(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Option:\n1-Forming Wu Kingdom’s Hierarchy\n2-Soldier’s Arrangement\n3-Borrowing Arrows with Straw Boats\n4-Enemy Fortress Attack Simulation\n5-Food Harvesting (2 marks)\n6-Encrypted Text\n7-Red Cliff on Fire\n8-Engaging Cao Cao at Hua Rong Road");
        System.out.print("Enter option: ");
        int a = sc.nextInt();
        System.out.println("");
        switch (a) {
            case 1:
                callBF1(args);
                break;
            case 2:
                callBF2(args);
                break;
            case 3:
                callBF3(args);
                break;
            case 4:
                callBF4(args);
                break;
            case 5:
                callBF5(args);
                break;
            case 6:
                callBF6(args);
                break;
            case 7:
                callBF7(args);
                break;
            case 8:
                callBF8(args);
                break;
            default:
                System.out.println("Invalid option. Try again");
                callBasic(args);
        }
    }

    public static void callExtra(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Option:\n1-Extra Algorithm Implementation\n2-Dynamic Arrow Borrowing\n3-Enemy Fortress Attack Simulation I\n4-Text Converter with More Secured Encryption\5-Red Cliff on Fire with Optimized Points");
        System.out.print("Enter option: ");
        int a = sc.nextInt();
        System.out.println("");
        switch (a) {
            case 1:
                callEF1(args);
                break;
            case 2:
                callEF2(args);
                break;
            case 3:
                callEF3(args);
                break;
            case 4:
                callEF4(args);
                break;
            default:
                System.out.println("Invalid option. Try again");
                callExtra(args);
        }
    }
    
    public static void seperate() {
        System.out.println("\n\n**********************************************************************************************************\n\n");
    }

    public static void callBF1(String[] args) {
        System.out.println("Basic Feature 1:  Forming Wu Kingdom’s Hierarchy (2 marks)");
        BF1_FormingHierarchy.main(args);
        seperate();
    }

    public static void callBF2(String[] args) {
        System.out.println("Basic Feature 2: Soldier’s Arrangement  (1 mark)");
        BF2_SoldiersArrangement.main(args);
        seperate();
    }

    public static void callBF3(String[] args) {
        System.out.println("Basic Feature 3: Borrowing Arrows with Straw Boats  (1 mark)");
        BF3_BorrowingArrows.main(args);
        BF3_BorrowingArrows.main(args);
        seperate();
    }

    public static void callBF4(String[] args) {
        System.out.println("Basic Feature 4: Enemy Fortress Attack Simulation  (2 marks)");
        BF4_EnemyFortressAttack.main(args);
        seperate();
    }

    public static void callBF5(String[] args) {
        System.out.println("Basic Feature 5: Food Harvesting (2 marks)");
        BF5_FoodHarvesting.main(args);
        seperate();
    }

    public static void callBF6(String[] args) {
        System.out.println("Basic Feature 6: Encrypted Text (1 mark)");
        BF6_EncryptedText.main(args);
        seperate();
    }

    public static void callBF7(String[] args) {
        System.out.println("Basic Feature 7:  Red Cliff on Fire (1 mark)");
        BF7_RedCliffOnFire.main(args);
        seperate();
    }

    public static void callBF8(String[] args) {
        System.out.println("Basic Feature 8: Engaging Cao Cao at Hua Rong Road (2 marks)");
        BF8_EngagingCaoCao.main(args);
        seperate();
    }
    
    public static void callEF1(String[] args) {
        System.out.println("Extra Feature 1:  Extra Algorithm Implementation");
        EF1_ExtraAlgorithm.main(args);
        seperate();
    }

    public static void callEF2(String[] args) {
        System.out.println("Extra Feature 2: Dynamic Arrow Borrowing");
        EF2_ArrowBorrowing.main(args);
        seperate();
    }

    public static void callEF3(String[] args) {
        System.out.println("Extra Feature 3: Enemy Fortress Attack Simulation I");
        EF3_EnemyFortressAttack.main(args);
        seperate();
    }

    public static void callEF4(String[] args) {
        System.out.println("Extra Feature 4: Text Converter with More Secured Encryption");
        EF4_TextConverter.main(args);
        seperate();
    }
    
}
