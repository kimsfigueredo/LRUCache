import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LRUCacheThirdImplementation {

    public static void main (String args[]) {

        LRUCacheThirdImplementation lruCache = new LRUCacheThirdImplementation(3);
        lruCache.put(1, "Nick");
        lruCache.put(2, "Jack");
        lruCache.put(3, "Lisa");
        lruCache.get(1);
        lruCache.put(4, "Larry");
        System.out.println(lruCache);

    }

    Integer capacity;

    private Map<Integer, Node> map;
    private LinkedList<Node> list;

    LRUCacheThirdImplementation(Integer capacity) {
        this.capacity = capacity;
        map = new HashMap(capacity);
        list = new LinkedList();
    }

    public void put(Integer key, Object value) {
        Node node = new Node();
        node.key = key;
        node.value = value;
        
        if(map.containsKey(key)) {
            Node existingNode = map.get(key);
            
            list.remove(existingNode);
            map.put(key, node);
            list.add(node);
            return;
        }

        if(map.size() >= capacity) {
            map.remove(list.getFirst().key);
            list.remove(list.getFirst());
        }
        map.put(key, node);
        list.add(node);
    }

    public Object get(Integer key) {
        if(map.containsKey(key)) {
            Node node = map.get(key);
            list.remove(node);
            list.add(node);
            return map.get(key);
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        Collections.reverse(list);
        return list.toString();
    }

    private class Node {
        Integer key;
        Object value;

        @Override
        public String toString() {
            return value.toString();
        }
    }
}


