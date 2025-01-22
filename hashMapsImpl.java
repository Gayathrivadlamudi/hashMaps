import java.util.*;
public class hashMapsImpl{
    static class hashMap<K,V>{//it is called generic where it can accept any value like String,float,int
        private class Node{
            K key;
            V value;
            public Node(K key,V value){
                this.key=key;
                this.value=value;
            }
        }
       private int n;
       private int N;
       private LinkedList<Node> bucket[];//it is an array of type linkedlist and stores nodes
       @SuppressWarnings("unchecked")
       public hashMap(){
        this.N=4;
        this.bucket=new LinkedList[4];
        for(int i=0;i<4;i++){
            bucket[i]=new LinkedList<>();
        }
       }
       public int hashFunction(K key){
        int hc=key.hashCode();//the hashCode() gives big values like 1234 and negative values also
        return Math.abs(hc)%N;//so to get psotive value we use abs and to get value in the range of indexes we do modulo
       }
       public int searchInLl(K key, int bi){
        LinkedList<Node> ll=bucket[bi];
        int di=0;
        for(int i=0;i<ll.size();i++){
            Node node=ll.get(i);
            if(node.key==key){
                return di;
            }
            di++;
        }    
        return -1;
       }
       public V get(K key){
        int bi=key.hashCode();
        int di=searchInLl(key,bi);
        if(di!=-1){
            Node node=bucket[bi].get(di);
            return node.value;
        }
        else{
            return null;
        }
    }
       public V remove(K key){
            int bi=key.hashCode();
            int di=searchInLl(key,bi);
            if(di!=-1){
                Node node=bucket[bi].remove(di);
                n--;
                return node.value;
            }
            else{
                return null;
            }

       }
   @SuppressWarnings("unchecked")
       private void rehash(){
        LinkedList<Node> oldBucket[]=bucket;
        bucket=new LinkedList[N*2];
        N=2*N;
        for(int i=0;i<N;i++){
            bucket[i]=new LinkedList<>();
        }
        //put all the nodes in oldBucket into newly created bucket by increasing its size
        for(int i=0;i<oldBucket.length;i++){
            LinkedList<Node> ll=oldBucket[i];
            for(int j=0;j<ll.size();j++){
                Node node=ll.get(j);
                put(node.key,node.value);
            }
        }
       }
       public boolean containaKey(K key){
        int bi=key.hashCode();
        int di=searchInLl(key,bi);
        if(di!=-1){
            return true;
        }
        else{
            return false;
        }
       }

       public void put(K key,V value){
        int bi=hashFunction(key);
        int di=searchInLl(key,bi);
        if(di!=-1){
            Node node=bucket[bi].get(di);
            node.value=value;
        }
        else{
            LinkedList<Node> l=bucket[bi];
            l.add(new Node(key,value));
            n++;
        }
        //Need to check if the lambda value is greater than threshold value
        double lambda=(double)n/N;
        if(lambda>2.0){
            rehash();
        }
       }
       public ArrayList<K> keySet(){
        ArrayList<K> keys=new ArrayList<>();
        for(int i=0;i<bucket.length;i++){
            LinkedList<Node> ll=bucket[i];
            for(Node node:ll){
                keys.add(node.key);
            }
        }
        return keys;
       }
       public boolean isEmpty(){
        return n==0;
       }
        
    }
    public static void main(String[] args){
        hashMap<String,Integer>hm=new hashMap<>();
        hm.put("India",100);
        hm.put("China",150);
        hm.put("US",50);
        hm.put("Nepal",5);
        ArrayList<String> keys=hm.keySet();
        for(String key:keys){
            System.out.println(key+" key");
        }
    }
}