import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input1.txt");
        Scanner scanner = new Scanner(file);

        Long ans = 0L;

        while(scanner.hasNextLine()){
            String[] line = scanner.nextLine().split(": ");

            long res = Long.parseLong(line[0]);

            String[] numstr = line[1].split(" ");
            ArrayList<Long>nums = new ArrayList<>();
            for(String s : numstr){
                nums.add(Long.parseLong(s));
            }

            if(isPossible(nums,res,1,nums.getFirst())) ans+= res;
        }

        System.out.println("***********");
        System.out.println(ans);
    }
    // 0 --> +, 1 --> *
    private static boolean isPossible(ArrayList<Long> nums, long res, int index, long prev){
        if(index==nums.size()) return prev==res;
        if(prev>res) return false;

        return isPossible(nums,res,index+1,prev+nums.get(index)) ||
                isPossible(nums,res,index+1,prev* nums.get(index)) ||
                isPossible(nums,res,index+1,Long.parseLong(prev + String.valueOf(nums.get(index))));
    }
}
