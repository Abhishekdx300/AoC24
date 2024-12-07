import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("input1.txt");
        Scanner scanner = new Scanner(file);

        ArrayList<ArrayList<Integer>>adj = new ArrayList<>();
        for(int i=0;i<100;i++) adj.add(new ArrayList<>());

        // go from left to right
        // check if any dep is already present in set  --> if true ---> invalid
        // add cur to set

        String line = scanner.nextLine();
        while(scanner.hasNextLine() && !line.isEmpty()){
            int x = Integer.parseInt(line.substring(0,2));
            int y = Integer.parseInt(line.substring(3,5));

            adj.get(x).add(y);
            line = scanner.nextLine();
        }

        int[] numStrs;
        Long result = 0L;

        Set<Integer> allowed = new HashSet<>();

        while(scanner.hasNextLine()){
            line = scanner.nextLine();
            numStrs = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();

            int n = numStrs.length;
            allowed.add(numStrs[0]);
            boolean possible = true;

            for(int i=1;i<n;i++){
                for(int dep : adj.get(numStrs[i])){
                    if(allowed.contains(dep)){
                        possible = false;
                        break;
                    }
                }
                if(!possible) break;
                allowed.add(numStrs[i]);
            }

            if(possible){
                result+=numStrs[n/2];
            }
            allowed.clear();
        }

        System.out.println(result);
    }
}
