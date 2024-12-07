import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input1.txt");
        Scanner scanner = new Scanner(file);

        ArrayList<ArrayList<Character>> matrix = new ArrayList<>();

        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            ArrayList<Character> temp = new ArrayList<>();
            for(char ch : line.toCharArray()){
                temp.add(ch);
            }
            matrix.add(temp);
        }

        int result=0;

        int n = matrix.size();
        int m = matrix.getFirst().size();

        int[] drow = {-1,0,1,0,1,1,-1,-1};
        int[] dcol = {0,1,0,-1,1,-1,1,-1};
        char[] str = {'X','M','A','S'};

        for(int row=0;row<n;row++){
            for(int col=0;col<m;col++){
                if(matrix.get(row).get(col)=='X'){
                    for(int i=0;i<8;i++){
                        int allMatch = 1;
                        for(int j=0;j<4;j++){
                            int nrow = row + drow[i]*j;
                            int ncol = col + dcol[i]*j;
                            if(nrow<0 || nrow>=n || ncol<0 || ncol>=m || matrix.get(nrow).get(ncol)!=str[j]){
                                allMatch = 0;
                                break;
                            }
                        }
                        result+=allMatch;
                    }
                }
            }
        }
        System.out.println("*********");
        System.out.println(result);
    }
}
