import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input.txt");
        Scanner scanner = new Scanner(file);

        int safeCnt = 0;

        int lineNo = 1;

        while(scanner.hasNextLine()){
            List<Integer> nums = Arrays.stream(scanner.nextLine().trim().split("\\s+"))
                    .map(Integer::parseInt)
                    .toList();

            // increasing but 1 is lower than prev n next
            // decreasing but 1 is higher than prev n next
            // diff between prev n cur <1 or >3

            Boolean anySafe = false;
            
            if(consider(0,nums)) anySafe = true;

            for(int i=0;i< nums.size()-1;i++){
                int diff = nums.get(i+1) - nums.get(i);
                if(Math.abs(diff) < 1 || Math.abs(diff) > 3){
                    if(consider(i,nums) || consider(i+1,nums)) anySafe=true;
                    break;
                }
                if(i+2 < nums.size()){
                    int diff2 = nums.get(i+2) - nums.get(i+1);
                    // both diff are different in sign ---> valley/pyramid --> any of 3 issue
                    if( (diff > 0) != (diff2 > 0)){
                     if(consider(i,nums) || consider(i+1,nums) || consider(i+2,nums) ) anySafe = true;
                     break;
                    }
                }

            }

            if(anySafe) safeCnt++;



        }
        System.out.println("**************");
        System.out.println(safeCnt);
    }

    private static Boolean consider(int idx, List<Integer>nums){
        List<Integer>newList = new ArrayList<>();
        for(int i=0;i<nums.size();i++){
            if(i!=idx) newList.add(nums.get(i));
        }

        return isSafe(newList);
    }

    private static boolean isSafe(List<Integer>nums){

        boolean inc = true;
        boolean dec = true;
        boolean ok = true;

        for(int i=0;i< nums.size()-1;i++){
            int diff= nums.get(i+1) - nums.get(i);
            if(diff>0) dec = false;
            else if(diff < 0) inc = false;
            if(Math.abs(diff)< 1 || Math.abs(diff) > 3){
                ok = false;
                break;
            }
        }

        return ok && (inc || dec);
    }
}