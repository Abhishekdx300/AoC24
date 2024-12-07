import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
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

        matrix.get(r).set(c,'X');
        while(true){
            if(dir==0){
                while(r>0 && matrix.get(r-1).get(c)!='#'){
                    matrix.get(r-1).set(c,'X');
                    r--;
                }
                if(r==0) break;
                dir++;
            }else if(dir==1){
                while (c<m-1 && matrix.get(r).get(c+1)!='#'){
                    matrix.get(r).set(c+1,'X');
                    c++;
                }
                if(c==m-1) break;
                dir++;
            }else if(dir==2){
                while (r<n-1 && matrix.get(r+1).get(c)!='#'){
                    matrix.get(r+1).set(c,'X');
                    r++;
                }
                if(r==n-1) break;
                dir++;
            }else{
                while (c>0 && matrix.get(r).get(c-1)!='#'){
                    matrix.get(r).set(c-1,'X');
                    c--;
                }
                if(c==0) break;
                dir=0;
            }
        }

        int result=0;

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(matrix.get(i).get(j)=='X') result++;
                System.out.print(matrix.get(i).get(j)+" ");
            }
            System.out.println();
        }

        System.out.println();
        System.out.println("***********");
        System.out.println(result);
    }
}
