1 file is hashMapImpl i.e hashmap implementation 
This interface stores data as Key-Value pairs .It works by sending the key to hashfunction and getting an unique code
Its time complexity is O(1) for adding,removing,geting the values
Working:1:we will create generic class hashMap<K,V> this defines that we can store any type of values there and private class Node
        2.we create an array called [bucket] which is type of linkedlist which stores nodes and each index will store a linkedlist
        3.put functions uses to functions one is to get bucket index by hashCode where it gives both negative numbers and positive so we do abs and another functions to search if the key is already existed or not.we will get linkedlist at that particular index and search node.key=key
        4.get and remove are simple by using bucket[bi].get(di); and remove(di) di is index of key in linked list which can be get it from searchLl
