import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;

public class Main {

    private static class Pair{
        public int num;
        public int index;

        public Pair(int n, int i){
            this.num = n;
            this.index = i;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input1.txt");

        Scanner scanner = new Scanner(file);

        Long sum = 0L;

        StringBuilder sb = new StringBuilder();

        while(scanner.hasNextLine()){
            sb.append(scanner.nextLine());
        }

        sb.append("#######");

        String str = sb.toString();

        int n = str.length();

//        boolean add = true;

        for(int i=0;i<n;i++){
            if(str.charAt(i)=='m'){
                if(str.charAt(i+1)=='u' && str.charAt(i+2)=='l' && str.charAt(i+3)=='('){
                    i+=4;
                    Pair p = extractNum(i,str);
                    i=p.index;
                    if(str.charAt(i)==','){
                        i+=1;
                        Pair q = extractNum(i,str);
                        i = q.index;
                        if(str.charAt(i)==')')
                            if(p.num != -1 && q.num!= -1){
                                sum+=((long) p.num *q.num);
//                                add = false;
                            }
                    }
                }
            }
//            else if(str.charAt(i)=='d' && str.charAt(i+1)=='o'){
//                if(str.charAt(i+2)=='(' && str.charAt(i+3)==')') add = true;
//                else if(str.charAt(i+2)=='n' && str.charAt(i+3)=='\'' && str.charAt(i+4)=='t' && str.charAt(i+5)=='(' && str.charAt(i+6)==')' ) add = false;
//            }
        }


        System.out.println(sum);
    }

    private static Pair extractNum(int idx,String str){
        int num = 0;
        while(num < 1000 && Character.isDigit(str.charAt(idx))){
            num = 10*num + Character.getNumericValue(str.charAt(idx));
            idx++;
        }

        if(num <1000) return new Pair(num, idx);
        return new Pair(-1, idx);
    }

}
