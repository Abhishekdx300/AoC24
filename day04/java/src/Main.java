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

        int[] drow = {-1,1,1,-1};
        int[] dcol = {-1,1,-1,1};

        for(int row=0;row<n;row++){
            for(int col=0;col<m;col++){
                if(matrix.get(row).get(col)=='A'){
                    // M A S  -- if one is M opposite must be S;
                    boolean valid = true;
                    for(int i=0;i<4;i++){
                        int nr = row+drow[i];
                        int nc = col+dcol[i];
                        if(nr<0 || nr>=n || nc<0 || nc>=m){
                            valid=false;
                            break;
                        }
                    }
                    if(!valid) continue;

                    char c1,c2;
                    c1 = matrix.get(row-1).get(col-1);
                    c2 = matrix.get(row+1).get(col+1);
                    if(c1==c2 || c1=='X' || c2=='X' || c1=='A' || c2=='A') continue;

                    c1 = matrix.get(row+1).get(col-1);
                    c2 = matrix.get(row-1).get(col+1);
                    if(c1==c2 || c1=='X' || c2=='X' || c1=='A' || c2=='A') continue;

                    result++;
                }
            }
        }
        System.out.println("*********");
        System.out.println(result);
    }
}
