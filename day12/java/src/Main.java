import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    private static class Cell{
        public int row;
        public int col;
        public Cell(int row,int col){
            this.row = row;
            this.col = col;
        }
    }
    private static class Corner{
        public double row;
        public double col;
        public Corner(double row,double col){
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Corner corner = (Corner) o;
            return Double.compare(row, corner.row) == 0 && Double.compare(col, corner.col) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input1.txt");
        Scanner scanner = new Scanner(file);

        ArrayList<ArrayList<Character>>matrix = new ArrayList<>(140);

        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            ArrayList<Character>temp = new ArrayList<>();
            for(Character ch : line.toCharArray()){
                temp.add(ch);
            }
            matrix.add(temp);
        }

        int n,m;
        n = matrix.size();
        m = matrix.getFirst().size();

        ArrayList<ArrayList<Integer>>visited = new ArrayList<>(140);
        for(int i=0;i< n;i++){
            ArrayList<Integer>temp = new ArrayList<>();
            for(int j=0;j<m;j++){
                temp.add(-1);
            }
            visited.add(temp);
        }


        ArrayList<ArrayList<Cell>>regions = new ArrayList<>();

        int regionCnt=0;

        for(int r=0;r<n;r++){
            for(int c=0;c<m;c++){
                if(visited.get(r).get(c)==-1){
                    regions.add(bfs(matrix,visited,r,c,regionCnt));
                    regionCnt++;
                }
            }
        }

        long result = 0L;

        for(int idx=0;idx<regions.size();idx++){
            ArrayList<Cell> region = regions.get(idx);
            result+= (long) region.size()*findCorners(region,visited,idx);
        }

        System.out.println("******************");
        System.out.println(result);
    }

    private static int findCorners(ArrayList<Cell> region, ArrayList<ArrayList<Integer>> visited,int regionId) {
        int n = visited.size();
        int m = visited.getFirst().size();
        int corners=0;

        // half cartesian points
        double[] dr = {-0.5,0.5,-0.5,0.5};
        double[] dc = {-0.5,-0.5,0.5,0.5};

        Set<Corner>set = new HashSet<>();

        for(Cell curCell : region){
            for(int i=0;i<4;i++){
                double cr = curCell.row + dr[i];
                double cc = curCell.col + dc[i];
                set.add(new Corner(cr,cc));
            }
        }


        for(Corner cur : set){
            int dirCount=0; // how many directions has the same char
            int opposite = 0; // +1 each for top-left && bottom-right corner
            for(int i=0;i<4;i++){
                int pr = (int) (cur.row + dr[i]);
                int pc = (int) (cur.col + dc[i]);
                if(pr>=0 && pr<n && pc>=0 && pc<m && visited.get(pr).get(pc)==regionId){
                    dirCount++;
                    if(i==0 || i==3) opposite++;
                }
            }

            if(dirCount==1 || dirCount==3) corners++;
            else if(dirCount==2 && opposite%2==0){ // if 2 dirs are opposite, then +2 else invalid corner
                corners+=2;
            }
        }
        return corners;
    }

    private static ArrayList<Cell> bfs(ArrayList<ArrayList<Character>> matrix, ArrayList<ArrayList<Integer>> visited, int r, int c,int regionCnt) {
        int n = matrix.size();
        int m = matrix.getFirst().size();
        Character ch = matrix.get(r).get(c);

        int[] drow = {-1,0,1,0};
        int[] dcol = {0,1,0,-1};

        visited.get(r).set(c,regionCnt);
        Cell start = new Cell(r,c);
        ArrayList<Cell>region = new ArrayList<>();
        region.add(start);
        Queue<Cell>queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()){
            Cell cur = queue.poll();

            for(int i=0;i<4;i++){
                int nrow = cur.row + drow[i];
                int ncol = cur.col + dcol[i];

                if(nrow>=0 && nrow<n && ncol>=0 && ncol<m && visited.get(nrow).get(ncol)==-1 && ch.equals(matrix.get(nrow).get(ncol))){
                    region.add(new Cell(nrow,ncol));
                    visited.get(nrow).set(ncol,regionCnt);
                    queue.add(new Cell(nrow,ncol));
                }
            }
        }
        return region;
    }
}
