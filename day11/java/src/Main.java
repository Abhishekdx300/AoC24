import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input1.txt");
        Scanner scanner = new Scanner(file);

        List<Long> nums = Arrays.stream(scanner.nextLine().split(" "))
                                    .map(Long::parseLong).toList();


        HashMap<Long,Long>map = new HashMap<>();
        for(Long v : nums){
            map.put(v, map.getOrDefault(v,0L)+1);
        }


        for(int i=0;i<25;i++){
            HashMap<Long,Long>map2 = new HashMap<>(2* map.size());

            for(Map.Entry<Long,Long> entry : map.entrySet()){
                Long num = entry.getKey();
                Long cnt = entry.getValue();
                String strnum = num.toString();
                if(num==0) map2.put(1L,map2.getOrDefault(1L,0L)+cnt);
                else if(strnum.length()%2==0){
                    Long n1 = Long.parseLong(strnum.substring(0,strnum.length()/2));
                    Long n2 = Long.parseLong(strnum.substring(strnum.length()/2));
                    map2.put(n1,map2.getOrDefault(n1,0L)+cnt);
                    map2.put(n2,map2.getOrDefault(n2,0L)+cnt);
                }else{
                    map2.put(num*2024,map2.getOrDefault(num*2024,0L)+cnt);
                }
            }
            map = map2;
        }

        Long result = 0L;

        for(Map.Entry<Long,Long>entry : map.entrySet()){
            result += entry.getValue();
        }

        System.out.println(result);
    }
}
