import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    private static class Tuple{
        public int row;
        public int col;
        public int height;

        public Tuple(int row,int col,int height){
            this.row = row;
            this.col = col;
            this.height = height;
        }

    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input1.txt");
        Scanner scanner = new Scanner(file);

        ArrayList<ArrayList<Integer>>matrix = new ArrayList<>(45);

        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            ArrayList<Integer>temp = new ArrayList<>();
            for(char ch : line.toCharArray()) temp.add(Integer.parseInt(String.valueOf(ch)));
            matrix.add(temp);
        }

        long result = 0L;
        for(int i=0;i< matrix.size();i++){
            for(int j=0;j<matrix.getFirst().size();j++){
                if(matrix.get(i).get(j)==0) result+=bfs(i,j,matrix);
            }
        }

        System.out.println("*************");
        System.out.println(result);
    }
    private static int bfs(int r,int c, ArrayList<ArrayList<Integer>>mat){
        int count=0;

        int n = mat.size();
        int m = mat.getFirst().size();

        ArrayList<ArrayList<Boolean>>visited = new ArrayList<>(45);
        for(int i=0;i< mat.size();i++){
            ArrayList<Boolean>temp = new ArrayList<>(mat.getFirst().size());
            for(int j=0;j<mat.getFirst().size();j++){
                temp.add(false);
            }
            visited.add(temp);
        }

        int[] drow = {-1,0,1,0};
        int[] dcol = {0,1,0,-1};

        Queue<Tuple>queue = new LinkedList<>();
        queue.add(new Tuple(r,c,0));
        visited.get(r).set(c,true);

        while(!queue.isEmpty()){
            Tuple cur = queue.poll();

            if(cur.height==9){
                count++;
                continue;
            }

            for(int i=0;i<4;i++){
                int nrow = cur.row + drow[i];
                int ncol = cur.col + dcol[i];

                if(nrow>=0 && nrow<n && ncol>=0 && ncol<m && mat.get(nrow).get(ncol)==cur.height+1 && !visited.get(nrow).get(ncol)){
                    visited.get(nrow).set(ncol,true);
                    queue.add(new Tuple(nrow,ncol,cur.height+1));
                }

            }
        }

        return count;
    }
}
