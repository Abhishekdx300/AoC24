import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input.txt");
        Scanner scanner = new Scanner(file);

        ArrayList<Long>list1 = new ArrayList<>();
        ArrayList<Long>list2  = new ArrayList<>();

        HashMap<Long,Integer>hm = new HashMap<>();


        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] nums = line.trim().split("\\s+");
            list1.add(Long.parseLong(nums[0]));
//            list2.add(Long.parseLong(nums[1]));
            long num2 = Long.parseLong(nums[1]);
            hm.put(num2,hm.getOrDefault(num2,0)+1);
        }

//        Collections.sort(list1);
//        Collections.sort(list2);

        long result = 0;
//
//        for (int i = 0; i < list1.size(); i++) {
//            result += Math.abs(list1.get(i)-list2.get(i));
//        }
//        System.out.println(result);

        for(int i=0;i< list1.size();i++){
            result += list1.get(i)*hm.getOrDefault(list1.get(i),0);
        }

        System.out.println(result);

    }
}