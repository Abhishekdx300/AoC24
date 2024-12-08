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

    static Pair[] dirs = {new Pair(-1,0),new Pair(0,1),new Pair(1,0),new Pair(0,-1)};

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


        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(matrix.get(i).get(j)=='.'){
                    matrix.get(i).set(j,'#');
                    if(checkCycle(matrix,r,c,dir)){
                        result++;
                    }
                    matrix.get(i).set(j,'.');
                }
            }
        }

        System.out.println();
        System.out.println("***********");
        System.out.println(result);
    }

    private static boolean checkCycle(ArrayList<ArrayList<Character>>mat, int r, int c, int dir){
        int n = mat.size();
        int m = mat.getFirst().size();

        // make the visited a hashtable????  --- for checking if same r,c,dir is already found
        // 0 - - - | | | | - - -  -  1   -  -  -  |  |  |  |
        // 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20
        // 3*3
        //***  (row*M+col)*4+dir


        ArrayList<Boolean>vis = new ArrayList<>(4*n*m+1);
        for(int i=0;i<4*n*m+1;i++){
            vis.add(false);
        }

        // make 1st pos true
        vis.set((r*m+c)*4,true);

        while(true){
            int nr = r + dirs[dir].first;
            int nc = c + dirs[dir].second;
            if(nr<0 || nr>=n || nc<0 || nc>=m) break;
            if(mat.get(nr).get(nc)=='#'){
                dir = (dir+1)%4;
            }else{
                r = nr;
                c = nc;
                int hash = (r*m+c)*4+dir;
                if(vis.get(hash)) return true;
                vis.set(hash, true);
            }
        }
        return false;
    }

}
