import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    private static class Cell{
        public int row;
        public int col;
        public Cell(int row,int col){
            this.row = row;
            this.col = col;
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

        ArrayList<ArrayList<Boolean>>visited = new ArrayList<>(140);
        for(int i=0;i< n;i++){
            ArrayList<Boolean>temp = new ArrayList<>();
            for(int j=0;j<m;j++){
                temp.add(false);
            }
            visited.add(temp);
        }

        long result = 0L;

        for(int r=0;r<n;r++){
            for(int c=0;c<m;c++){
                if(!visited.get(r).get(c)){
                    result+= bfs(matrix,visited,r,c);
                }
            }
        }

        System.out.println("******************");
        System.out.println(result);
    }

    private static long bfs(ArrayList<ArrayList<Character>> matrix, ArrayList<ArrayList<Boolean>> visited, int r, int c) {
        int n = matrix.size();
        int m = matrix.getFirst().size();
        Character ch = matrix.get(r).get(c);

        int[] drow = {-1,0,1,0};
        int[] dcol = {0,1,0,-1};

        visited.get(r).set(c,true);
        Queue<Cell>queue = new LinkedList<>();
        queue.add(new Cell(r,c));

        long area=0;
        long perimeter = 0;

        while (!queue.isEmpty()){
            Cell cur = queue.poll();
            area++;

            for(int i=0;i<4;i++){
                int nrow = cur.row + drow[i];
                int ncol = cur.col + dcol[i];

                if(nrow>=0 && nrow<n && ncol>=0 && ncol<m){
                    if(!ch.equals(matrix.get(nrow).get(ncol))){
                        perimeter++;
                    }else{
                        if(!visited.get(nrow).get(ncol)){
                            visited.get(nrow).set(ncol,true);
                            queue.add(new Cell(nrow,ncol));
                        }
                    }
                }else{
                    perimeter++;
                }
            }
        }
        return area*perimeter;
    }
}
