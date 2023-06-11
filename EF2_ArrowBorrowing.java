package DSAssignment;

import java.util.*;

public class EF2_ArrowBorrowing {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] men = {10, 50, 50, 15};
        int[] times = {0, 0, 0, 0};

        //show information of number of straw men
        System.out.println("Number of straw men");
        System.out.println("Front: "+men[0]);
        System.out.println("Left: "+men[1]);
        System.out.println("Right: "+men[2]);
        System.out.println("Back: "+men[3]);

        //create copy of men and times for second use
        int [] man = new int [men.length];
        man = Arrays.copyOf(men, men.length);
        int [] time = new int [0];
        time = Arrays.copyOf(times, times.length);
        
        String arrowInput = "[300,1500,1000,2000,600,800,300,500,400]";
        arrowInput = arrowInput.replaceAll("[\\[\\]]", ""); // Remove square brackets and commas
        String[] values = arrowInput.split("\\,"); // Split the input into individual values

        List<Integer> arrow = new ArrayList<>();
        List<Integer> arrow1 = new ArrayList<>();

        for (String value : values) {
            arrow.add(Integer.parseInt(value));
            arrow1.add(Integer.parseInt(value));
        }        
        System.out.println("Arrow: " + arrow);

        List<Integer> arrowReceived = new ArrayList<>();
        List<String> direction = new ArrayList<>(Collections.nCopies(arrow.size(), ""));
        int [] menUsed = new int [arrow.size()];
        int total=0;
        List<Integer> sorted = new ArrayList<>(arrow);
        Collections.sort(sorted, Collections.reverseOrder());
        
        for (int i=8, count=0; i<sorted.size() && count<sorted.size()-8; i++, count++){
            int indexArrow = arrow.indexOf(sorted.get(i));
            arrow.remove(indexArrow);
            arrow.add(indexArrow, 0);
            direction.remove(indexArrow);
            direction.add(indexArrow, " ");
            menUsed[indexArrow]=-1;
        }
        
        for (int i=0; i<8 && i<sorted.size(); i++){
            int numArrow = sorted.get(i);
            int indexArrow = arrow.indexOf(numArrow);
            arrow.remove(indexArrow);
            arrow.add(indexArrow, 0);
            int indexDirection = maxDirection(men);
            List<Integer> allMax = new ArrayList<>();
            if (sameMax(men, men[indexDirection])){
                allMax=getAllMax(men, men[indexDirection]);
                for (int index: allMax){
                    if (getIndex(menUsed, index)<indexArrow){
                        indexDirection = index;
                        break;
                    }
                }
            }
            menUsed[indexArrow]=indexDirection;
            String currentDirect = direct(indexDirection);
            direction.remove(indexArrow);
            direction.add(indexArrow, direct(indexDirection));
            times[indexDirection]++;
            men[indexDirection] = efficiency(times[indexDirection], men[indexDirection]);
        }
               
        for (int i=0; i<arrow1.size(); i++){
            int numArrow = arrow1.get(i);
            if (menUsed[i]==-1){
                arrowReceived.add(0);
                continue;
            }
            int eff = man[menUsed[i]];
            int receive = (int)(numArrow * ((double)eff/100));
            
            arrowReceived.add(receive);
            total+=receive;
            time[menUsed[i]]++;
            man[menUsed[i]] = efficiency(time[menUsed[i]], eff);
        }

        System.out.println("Boat direction: " + direction);
        System.out.println("Arrow Received: " + arrowReceived);
        System.out.println("Total = " + total);
        
    }
    
    public static int maxDirection(int [] men){
        int max=men[0];
        int maxIndex = 0;
        for (int i=1; i<men.length; i++){
            if (men[i]>max){
                max=men[i];
                maxIndex=i;
            }
        }
        return maxIndex;
    }
    
    public static boolean sameMax(int [] men, int max){
        int found=0;
        for (int i=1; i<men.length; i++){
            if (men[i]==max){
                found++;
            }
        }
        if (found>1){
            return true;
        }
        return false;
    }
    
    public static List<Integer> getAllMax(int [] men, int max){
        List<Integer> allMax = new ArrayList<>();
        for (int i=1; i<men.length; i++){
            if (men[i]==max){
                allMax.add(i);
            }
        }
        return allMax;
    }
    
    public static int getIndex (int [] menUsed, int in){
        for (int i=0; i<menUsed.length; i++){
            if (menUsed[i]==in){
                return i;
            }
        }
        return -1;
    }
    
    public static int efficiency(int times, int men) {
        double eff = 0;
        if (times == 0) {
            eff = men;
        }
        else if (times == 1) {
            eff = (int) (men * 0.8);
        }
        return (int) eff;
    }

    public static String direct(int index) {
        if (index == 0) {
            return "front";
        } else if (index == 1) {
            return "left";
        } else if (index == 2) {
            return "right";
        } else {
            return "back";
        }
    }

}

