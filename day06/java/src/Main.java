import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Main {

    private static class Pair{
        public int first;
        public int second;

        public Pair(int f, int s){
            this.first = f;
            this.second = s;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input1.txt");
        Scanner scanner = new Scanner(file);
        ArrayList<ArrayList<Character>>matrix = new ArrayList<>(130);

        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            ArrayList<Character>temp = new ArrayList<>(line.length());
            for(char ch : line.toCharArray()) temp.add(ch);
            matrix.add(temp);
        }

        int n = matrix.size();
        int m = matrix.getFirst().size();
        
        int r=-1,c=-1;
        int dir = 0; // 0- up, 1-right, 2-down, 3-left
        
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                char ch = matrix.get(i).get(j);
                if(ch=='^'){
                    r=i;c=j;
                    break;
                }
            }
            if(r != -1) break;
        }
        int result=0;

        Pair[] dirs = {new Pair(-1,0),new Pair(0,1),new Pair(1,0),new Pair(0,-1)};

        ArrayList<ArrayList<Integer>>vis = new ArrayList<>();
        for(int i=0;i<n;i++){
            ArrayList<Integer>temp = new ArrayList<>();
            for(int j=0;j<m;j++) temp.add(0);
            vis.add(temp);
        }

        vis.get(r).set(c,1);

        while(true){
            int nr = r + dirs[dir].first;
            int nc = c + dirs[dir].second;
            if(nr<0 || nr>=n || nc<0 || nc>=m) break;
            if(matrix.get(nr).get(nc)=='#'){
                dir = (dir+1)%4;
            }else{
                r = nr;
                c = nc;
                vis.get(r).set(c,1);
            }
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(vis.get(i).get(j)==1) result++;
            }
        }

        System.out.println();
        System.out.println("***********");
        System.out.println(result);
    }
}
