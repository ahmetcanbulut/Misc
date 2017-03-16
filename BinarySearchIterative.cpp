// Sorted Array

int binary_search(int A[],int N,int key){

    // considering 1 based index
    int low , high , mid ;
    low = 1 ;
    high = N ;
    while(low <= high){
        mid = (low + high)/2 ;
        if(A[mid] == key){
            return mid ; // a match is found
        }else if(A[mid] < key){ // if middle element is less than the key 
            low = mid + 1 ;     // key will be right part if it exists
        }else{
            high = mid - 1 ;    // otherwise key will be in left part if it exists
        }
    }
    return -1 ; // indicating no such key exists 
}
