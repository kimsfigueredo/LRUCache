import java.util.*;

public class LRUCacheSecondImplementation<K, V> {

    public static void main(String args[]) {
        LRUCacheSecondImplementation<Integer, String> lruCache = new LRUCacheSecondImplementation(3);
        System.out.println(lruCache);
        lruCache.put(1, "Nick");
        lruCache.put(2, "Jack");
        lruCache.put(3, "Lisa");
        lruCache.get(1);
        lruCache.put(4, "Larry");
        System.out.println(lruCache);
    }

    private int capacity;
    private LinkedHashMap<K, V> cache;

    public LRUCacheSecondImplementation(int size) {
        this.capacity = size;
        this.cache = new LinkedHashMap<K, V>(size);
    }

    public void put(K key, V value) {
        if(this.cache.containsKey(key)) {
            this.cache.remove(key);
            this.cache.put(key, value);
            return;
        }

        if(this.cache.size() >= this.capacity) {
            K first = this.cache.keySet().iterator().next();
            this.cache.remove(first);
        }

        this.cache.put(key, value);
    }

    public V get(K key) {
        if(!this.cache.containsKey(key)) {
            return null;
        } else {
            V value = this.cache.get(key);
            this.cache.remove(key);
            this.cache.put(key, value);
            return value;
        }
    }

    @Override
    public String toString() {
        List<String> reverseOrderedKeys = new ArrayList<String>((Collection<? extends String>) this.cache.values());
        Collections.reverse(reverseOrderedKeys);
        return reverseOrderedKeys.toString();
    }


}


