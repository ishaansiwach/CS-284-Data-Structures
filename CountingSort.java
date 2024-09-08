//ishaan siwach
//I pledge my honor that I have abided by the Stevens Honor System
public class CountingSort {
    /**
     * Sorts a list of integers into ascending order using the Counting Sort algorithm
     * @param A the list to be sorted
     * @return the sorted list
     */
    public static int[] sort(int[] A){
        if(A.length==0){
            int[] B={};
            return B;
        }
        if(A.length==1){
            int[] B={A[0]};
            return B;
        }
        int k=0;
        int n=A.length;
        for(int i=0; i<n; i++){
            if(A[i]>k){
                k=A[i];
            }
        }
        int[] B=new int[n+1];
        int[] C=new int[k+1];
        for(int i=0; i<k; i++){
            C[i]=0;
        }
        for(int j=0; j<n; j++){
            C[A[j]]=C[A[j]]+1;
        }
        for(int i=1; i<k+1; i++){
            C[i]=C[i]+C[i-1];
        }
        for(int j=n-1; j>-1; j--){
            B[C[A[j]]]=A[j];
            C[A[j]]=C[A[j]]-1;
        }
        int[] D=new int[B.length-1];
        for(int i=1; i<B.length; i++) {
            D[i-1]=B[i];
        }
        return D;
    }
}
