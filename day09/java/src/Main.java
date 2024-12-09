import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input1.txt");
        Scanner scanner = new Scanner(file);

        ArrayList<Integer>init = new ArrayList<>(200000);

        int id = 0;
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();

            for(int i=0;i<line.length();i++){
                int k = Integer.parseInt(String.valueOf(line.charAt(i)));
                if(i%2==1){
                    // free
                    for(int j = 0; j< k; j++){
                        init.add(-1);
                    }
                }else{
                    for(int j = 0; j< k; j++){
                        init.add(id);
                    }
                    id++;
                }
            }
        }

        // compact
        int n = init.size();
        int left=0, right=n-1;

        while(left<n && init.get(left)!=-1) left++;
        while(right>=0 && init.get(right)==-1) right--;

        while(left<n && right>=0 && left<right){
            init.set(left, init.get(right));
            init.set(right,-1);
            while(left<n && init.get(left)!=-1) left++;
            while(right>=0 && init.get(right)==-1) right--;
        }

        long result = 0L;

        for(int i=0;i<n;i++){
            int cur = init.get(i);
            if(cur==-1) break;
            result+= ((long) i *cur);
        }

        System.out.println("************");
        System.out.println(result);
    }
}
