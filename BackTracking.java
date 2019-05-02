//Name: Vy Phuong Tran - ID: L20333232
//COSC 
//Program 5: Stable Marriage (Backtracking)
//Adopted Algorithms: Gale–Shapley algorithm
//Assessment: The program can produce expected correct output

package stablemarriage;


public class BackTracking {
    static int N = 8; 
    private int engagedCount; 
    private int[][] menPref;
    private int[][] womenPref;
    private int[] men;
    private int[] women;
    private int[] womenPartner;
    private int[] menPartner;
    private boolean[] menEngaged;
    private int[] deviation;
    
    public BackTracking(int[] m, int[] w, int[][] mp, int[][] wp) {
        engagedCount = 0;
        men = m;
        women = w;
        menPref = mp;
        womenPref = wp;
        menEngaged = new boolean[N]; // all false
        womenPartner = new int[N]; //all 0
        menPartner = new int[N];
        deviation = new int[N];
        for (int i=0; i<N; i++) {
            womenPartner[i] = 0;
            menPartner[i] = 0;
        }
        calcMatches();
    }
    
    private void calcMatches() {        
        while (engagedCount < N){
            int free;
            for (free = 0; free < N; free++)
                if (!menEngaged[free])
                    break;
            
            for (int i = 0; i < N && !menEngaged[free]; i++) {
                int index = menIndexOf(menPref[free][i]); 
                if (womenPartner[index] == 0) {
                    womenPartner[index] = men[free];  
                    menEngaged[free] = true; 
                    engagedCount++; 
                }
                else {
                    int currentPartner = womenPartner[index];
                    if (morePreference(currentPartner, men[free], index)) {
                        womenPartner[index] = men[free];
                        menEngaged[free] = true;
                        menEngaged[womenIndexOf(currentPartner)] = false;
                    }
                }
            }            
        }
        printCouples();   
    } 
    
    //Find men index
    private int menIndexOf(int womenPref) {
        for (int i = 0; i < N; i++)
            if (men[i] == womenPref)
                return i;
        return -1; 
    }

    private int womenIndexOf(int menPref) {
        for (int i = 0; i < N; i++)
            if (women[i] == menPref)
                return i;
        return -1;
    }
    
    private boolean morePreference(int curPartner, int newPartner, int index) {
        for (int i = 0; i < N; i++) {
            if (womenPref[index][i] == newPartner)
                return true;
            if (womenPref[index][i] == curPartner)
                return false;
        }
        return false;
    }
    
    private int[] deviation(int[] partner, int[][] original) {
        for (int k=0; k<N; k++)
            deviation[k] = 0;
        
        int i = 0;
        int j = 0;
        
        do {
            if (partner[i] == original[i][j]) {
                i++;
                j = 0;
            }
            else {
                j++;
                deviation[i] = deviation[i] + 1;
            }
        } while(i<N);
               
        return deviation;
    }
    
    public void printCouples() {
        int m = 1;
        int value;
        int[] temp = new int[N];
        System.out.println("MALE OPTIMAL SOLUTION: ");       
        do {
            for (int i=0; i<N; i++) {
                if (womenPartner[i] == m) {
                    temp[i] = i+1;
                    value = temp[i];
                    menPartner[m-1] = value;
                    System.out.print(menPartner[m-1] + " ");
                    m++;
                }               
            }            
        }while(m<N);
        System.out.println("");
        
        int[] man_deviation = deviation(menPartner, menPref);
        for (int k=0; k<N; k++)
            System.out.println( (k+1) + ". The deviation of Man-"+ (k+1) + " is "+ man_deviation[k]);
        
        System.out.println("");
        
        System.out.println("FEMALE OPTIMAL SOLUTION: ");
        for (int i = 0; i < N; i++){
            System.out.print(womenPartner[i] +" ");
        }        
        System.out.println("");
        int[] woman_deviation = deviation(womenPartner, womenPref);
        for (int k=0; k<N; k++)
            System.out.println( (k+1) + ". The deviation of Woman-"+ (k+1) + " is "+ woman_deviation[k]);
    }
       
    public static void main(String[] args) {
        // TODO code application logic here
        int[] m = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] w = {1, 2, 3, 4, 5, 6, 7, 8};
        
        int mp[][] = {
            {7, 2, 6, 5, 1, 3, 8, 4}, //men
            {4, 3, 2, 6, 8, 1, 7, 5},
            {3, 2, 4, 1, 8, 5, 7, 6},
            {3, 8, 4, 2, 5, 6, 7, 1},
            {8, 3, 4, 5, 6, 1, 7, 2},
            {8, 7, 5, 2, 4, 3, 1, 6},
            {2, 4, 6, 3, 1, 7, 5, 8},
            {6, 1, 4, 2, 7, 5, 3, 8}, 
        };
        int wp[][] = {
            {4, 6, 2, 5, 8, 1, 3, 7}, //women
            {8, 5, 3, 1, 6, 7, 4, 2},
            {6, 8, 1, 2, 3, 4, 7, 5},
            {3, 2, 4, 7, 6, 8, 5, 1},
            {6, 3, 1, 4, 5, 7, 2, 8},
            {2, 1, 3, 8, 7, 4, 6, 5},
            {3, 5, 7, 2, 4, 1, 8, 6},
            {7, 2, 8, 4, 5, 6, 3, 1}            
        };
        
        
        
        System.out.println("MEN'S PREFERENCE: ");
        for (int i=0; i < N; i++) {
            for (int j=0; j<N; j++) {
                System.out.print(mp[i][j] +" ");
            }
            System.out.println("\n");
        }
        
        System.out.println("WOMEN'S PREFERENCE: ");
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                System.out.print(wp[i][j] +" ");
            }
            System.out.println("\n");
        }  
        
        BackTracking bt = new BackTracking(m, w, mp, wp);
    }
}
