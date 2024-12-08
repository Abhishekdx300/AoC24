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

        // for each up/low/num ---> hashMap key -- val(arraylist of pos)
        // antinode matrix
        //iterate on hashMap --> for each 2 pos (rdiff, cdiff) --> add/reduce --> 2 pos if valid mark on matrix


        HashMap<Character, ArrayList<Pair>>map = new HashMap<>();

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

                int dr = p1.row - p2.row;
                int dc = p1.col - p2.col;

                int nr = p1.row+dr;
                int nc = p1.col+dc;
                if(nr>=0 && nr<n && nc>=0 && nc<m) matrix.get(nr).set(nc,1);
                nr = p2.row-dr;
                nc = p2.col-dc;
                if(nr>=0 && nr<n && nc>=0 && nc<m) matrix.get(nr).set(nc,1);
            }
        }



    }
}
