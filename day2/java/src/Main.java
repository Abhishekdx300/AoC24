import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input.txt");
        Scanner scanner = new Scanner(file);

        int safeCnt = 0;

        while(scanner.hasNextLine()){
            List<Integer> nums = Arrays.stream(scanner.nextLine().trim().split("\\s+"))
                    .map(Integer::parseInt)
                    .toList();

            boolean isSafe = true;
            boolean increasing = nums.get(1) > nums.get(0);

            // pt 2
            int incWrongCnt = increasing ? 0 : 1;
            int decWrongCnt = increasing ? 1 : 0;

            if(increasing){
                for(int i=1;i< nums.size();i++){
                    if(nums.get(i) <= nums.get(i-1) || nums.get(i)- nums.get(i-1)>3){
                        isSafe=false;
                        break;
                    }
                }

            }else{
                for(int i=1;i< nums.size();i++){
                    if(nums.get(i-1) <= nums.get(i) || nums.get(i-1)- nums.get(i)>3){
                        isSafe=false;
                        break;
                    }
                }
            }

            if(isSafe) safeCnt++;

        }
        System.out.println(safeCnt);
    }
}