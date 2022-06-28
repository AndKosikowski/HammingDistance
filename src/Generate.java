import java.util.Scanner;

//By Andrew Kosikowski, last updated 6/28/2022
//Made to print the unique Hamming Distances of a linear graph
//Does print mirrored graphs

public class Generate {

    public static int n;

    public static int ceiling;

    public static int counter = 0;

    public static void main(String[] args){

        if(args.length == 0){
            System.out.println("You need to pass a number of vertices");
            return;
        }
        n = Integer.parseInt(args[0]);

        ceiling =  (int) Math.pow(2, n-1);

        recursionTime(new int[n], 0);

        //BELOW IS THE FASTER WAY BUT ONLY WORKS FOR 3 VERTICES (THOUGH COULD BE EASILY MODIFIED)

//        for(int i = 0; i < ceiling; i++){
//
//            for(int j = 0; j < ceiling; j++){
//                for(int k = 0; k < ceiling; k++){
//                    if ( i != j && j != k && i != k) {
//                        int x1 = Integer.bitCount(i ^ j);//Counts number of 1 bits in XOR of i and j
//                        int x2 = Integer.bitCount(j ^ k);
//
//                        if (x1 != x2) {
//                            counter++;
//                            System.out.print("Valid path " + counter + ": "+ binaryStringFormat(i) + " --" + x1);
//                            System.out.println("-- " + binaryStringFormat(j) + " --" + x2 + "-- " + binaryStringFormat(k));
//                        }
//                    }
//
//                }
//            }
//        }
    }

    public static void recursionTime(int[] a, int depth){
        for(int i = 0; i < ceiling; i++){
            boolean alreadyExists = false;
            for(int j = 0; j < depth; j++){
                if (a[j] == i){
                    alreadyExists = true; // Mark as duplicate
                    break;
                }
            }
            if(!alreadyExists){ //add to array if not a duplicate
                a[depth] = i;
                if(depth == n - 1){ //If at end of array print outcome
                    printPath(a);
                }
                else{ //Not at end of array so we need to recurse again
                    recursionTime(a, depth + 1);
                }
            }
        }
    }

    public static void printPath(int[] a){
        int[] newArray = new int[n-1];
        for(int i = 0; i < n-1; i++){
            newArray[i] = Integer.bitCount(a[i] ^ a[i+1]);
            for(int j = 0; j < i; j++){
                if(newArray[j] == newArray[i]){ // If any edges have same distance dont print
                    return;
                }
            }
        }

        counter++;
        System.out.print("Valid path " + counter + ": ");

        for(int i = 0; i < n-1; i++){
            System.out.print(binaryStringFormat(a[i]) + " --" + newArray[i] + "-- ");
        }

        System.out.println(binaryStringFormat(a[n-1]));
    }

    public static String binaryStringFormat(int x){ //Clean printing method
        String s = Integer.toBinaryString(x); //Non padded binary
        int difference = (n-1) - s.length();
        if(difference != 0){ //Pad string if wrong length
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < difference; i++){
                sb.append("0");
            }
            return sb.toString() + s;
        }
        return s;
    }



}
