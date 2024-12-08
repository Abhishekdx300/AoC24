import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    private static class Pair{
        public int row;
        public int col;

        public Pair(int r,int c){
            this.row = r;
            this.col = c;
        }

    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input1.txt");
        Scanner scanner = new Scanner(file);

        HashMap<Character, ArrayList<Pair>>map = new HashMap<>();

        // for any char found...do a search if another same is found in row,col,2 diagolans
        // if found mark them all

        int n=0;
        int m=0;
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            m=line.length();
            for(int j=0;j<m;j++){
                char ch = line.charAt(j);
                if(Character.isDigit(ch) || Character.isAlphabetic(ch)){
                    map.computeIfAbsent(ch, k -> new ArrayList<>());
                    map.get(ch).add(new Pair(n,j));
                }
            }
            n++;
        }

        ArrayList<ArrayList<Integer>>antinodeMatrix = new ArrayList<>(n);
        for(int i=0;i<n;i++){
            ArrayList<Integer>temp = new ArrayList<>();
            for(int j=0;j<m;j++) temp.add(0);
            antinodeMatrix.add(temp);
        }


        for (Map.Entry<Character, ArrayList<Pair>> entry : map.entrySet()) {
            Character key = entry.getKey();
            ArrayList<Pair> value = entry.getValue();

            mark(antinodeMatrix,value,key,n,m);
        }

        int result = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(antinodeMatrix.get(i).get(j)==1) result++;
            }
        }

        System.out.println("************");
        System.out.println(result);

    }

    private static void mark(ArrayList<ArrayList<Integer>>matrix,ArrayList<Pair>positions,Character ch, int n, int m){

        int sz = positions.size();

        for(int i=0;i<sz;i++){
            Pair p1 = positions.get(i);
            for(int j=i+1;j<sz;j++){
                Pair p2 = positions.get(j);

                // this marking system need to change

                // if p1 & p2 are in proper line (row,col,diagonal) --> mark all on them
                // else do like before in while loop

                // 1st case when --> dr==0 || dc==0 || (abs(dr)==abs(dc))

                int dr = p1.row - p2.row;
                int dc = p1.col - p2.col;

                if(dr==0 || dc==0 || (Math.abs(dr)==Math.abs(dc))){
                    if(dr!=0) dr = (dr<0) ? -1 : 1;
                    if(dc!=0) dc = (dc<0) ? -1 : 1;

                    int row = p1.row;
                    int col = p1.col;
                    while (row>=0 && col>=0 && row<n && col<m){
                        matrix.get(row).set(col,1);
                        row = row + dr;
                        col = col + dc;
                    }

                    row = p1.row;
                    col = p1.col;
                    while(row>=0 && col>=0 && row<n && col<m){
                        matrix.get(row).set(col,1);
                        row = row - dr;
                        col = col - dc;
                    }
                }else{

                    matrix.get(p1.row).set(p1.col,1);
                    matrix.get(p2.row).set(p2.col,1);

                    int nr = p1.row+dr;
                    int nc = p1.col+dc;
                    while(nr>=0 && nr<n && nc>=0 && nc<m){
                        matrix.get(nr).set(nc,1);
                        nr = nr + dr;
                        nc = nc + dc;
                    }
                    nr = p2.row-dr;
                    nc = p2.col-dc;
                    while(nr>=0 && nr<n && nc>=0 && nc<m){
                        matrix.get(nr).set(nc,1);
                        nr = nr - dr;
                        nc = nc - dc;
                    }
                }
            }
        }
    }
}
