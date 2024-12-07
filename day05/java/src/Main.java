import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("input1.txt");
        Scanner scanner = new Scanner(file);

        ArrayList<ArrayList<Integer>>adj = new ArrayList<>();
        ArrayList<ArrayList<Integer>>prereq = new ArrayList<>();
        for(int i=0;i<100;i++){
            adj.add(new ArrayList<>());
            prereq.add(new ArrayList<>());
        }


        String line = scanner.nextLine();
        while(scanner.hasNextLine() && !line.isEmpty()){
            int x = Integer.parseInt(line.substring(0,2));
            int y = Integer.parseInt(line.substring(3,5));

            adj.get(x).add(y);
            prereq.get(y).add(x);
            line = scanner.nextLine();
        }

        int[] nums;
        Long result = 0L;

        Set<Integer> allowed = new HashSet<>();

        while(scanner.hasNextLine()){
            line = scanner.nextLine();
            nums = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();

            int n = nums.length;

            allowed.add(nums[0]);
            boolean possible = true;

            for(int i=1;i<n;i++){
                for(int dep : adj.get(nums[i])){
                    if(allowed.contains(dep)){
                        possible = false;
                        break;
                    }
                }
                allowed.add(nums[i]);
            }

            if(!possible){
                // topo sort
                // allowed has all the current nums
                ArrayList<Integer>indegree = new ArrayList<>(nums.length);
                ArrayList<Integer>fixed = new ArrayList<>(nums.length);
                for(int num : nums){
                    int ind=0;
                    for(Integer pre : prereq.get(num)){
                        if(allowed.contains(pre)) ind++;
                    }
                    indegree.add(ind);
                }

                Queue<Integer>q = new LinkedList<>();

                for(int i=0;i<n;i++){
                    if(indegree.get(i)==0) q.add(nums[i]);
                }

                while(!q.isEmpty()){
                    Integer top = q.poll();
                    fixed.add(top);

                    for(int next : adj.get(top)){
                        if(allowed.contains(next)){
                            int index=0;
                            for(int i=0;i<n;i++){
                                if(nums[i]==next){
                                    index =i;
                                    break;
                                }
                            }

                            indegree.set(index,indegree.get(index)-1);
                            if(indegree.get(index)==0) q.add(next);
                        }
                    }

                }


                result+=fixed.get(n/2);
            }
            allowed.clear();
        }

        System.out.println(result);
    }
}
