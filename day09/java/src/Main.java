import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static class Filef{
        public int id;
        public int index;
        public int size;

        public Filef(int i, int z, int id){
            this.index = i;
            this.size = z;
            this.id = id;
        }
    }
    private static class Spacef{
        public int index;
        public int size;

        public Spacef(int i, int z){
            this.index = i;
            this.size = z;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input1.txt");
        Scanner scanner = new Scanner(file);

//        ArrayList<Integer>init = new ArrayList<>(200000);

        // iterate -- store free space starting index and size -- store file id and size and index
        // iterate from end of file/s and start of space -- if space idx < file idx then store it

        ArrayList<Filef>files = new ArrayList<>();
        ArrayList<Spacef>spaces = new ArrayList<>();



        int id = 0;
        int curIdx = 0;
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();

            for(int i=0;i<line.length();i++){
                int size = Integer.parseInt(String.valueOf(line.charAt(i)));
                if(i%2==1){
                    // free
                    if(size!=0)
                        spaces.add(new Spacef(curIdx,size));
                }else{
                    files.add(new Filef(curIdx,size,id));
                    id++;
                }
                curIdx+=size;
            }
        }

        //compact2
        int n = files.size();

        for(int i=n-1;i>=0;i--){
            for(int j=0;j<spaces.size();j++){
                Filef curf = files.get(i);
                Spacef curs = spaces.get(j);
                if(curf.index<curs.index){
                    break;
                }
                if(curf.size<=curs.size){
                    curf.index = curs.index;
                    // remove curs --- add modified on
                    int diff = curs.size - curf.size;
                    if(diff!=0) spaces.set(j,new Spacef(curs.index+curf.size,diff));
                    else spaces.remove(curs);
                    break;
                }
            }
        }

        long result = 0L;

        for(Filef  curf : files){
            for(int i=0;i<curf.size;i++){
                result+=((long) curf.id *(curf.index+i));
            }
        }


        System.out.println("************");
        System.out.println(result);
    }
}
