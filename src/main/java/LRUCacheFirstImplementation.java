import java.util.HashMap;
import java.util.Map;

public class LRUCacheFirstImplementation {

    public static void main(String args[]) {
        LRUCacheFirstImplementation lruCache = new LRUCacheFirstImplementation(3);

        System.out.println(lruCache);
        lruCache.put(1, "Nick");
        lruCache.put(2, "Jack");
        lruCache.put(3, "Lisa");
        lruCache.get(1);
        lruCache.put(4, "Larry");
        System.out.println(lruCache);
    }

    final Node head = new Node();
    final Node tail = new Node();
    private Map<Integer, Node> node_map;
    private int cache_capacity;

    LRUCacheFirstImplementation(int capacity) {
        node_map = new HashMap(capacity);
        this.cache_capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    public Object get(int key) {
        Object result = -1;
        Node node = node_map.get(key);

        if(node != null) {
            result = node.value;
            removeNode(node);
            addNode(node);
        }

        return result;
    }

    @Override
    public String toString() {

        return node_map.toString();
    }

    public void put(Integer key, String value) {

        Node node = node_map.get(key);

        if (node != null) {
            removeNode(node);
            node.value = value;
            addNode(node);
        } else {
            if(node_map.size() == cache_capacity) {
                node_map.remove(tail.prev.key);
                removeNode(tail.prev);
            }

            Node new_node = new Node();
            new_node.key = key;
            new_node.value = value;
            node_map.put(key, new_node);
            addNode(new_node);
        }
    }

    private void addNode(Node node) {
        Node head_next = head.next;
        node.next = head_next;
        head_next.prev = node;
        head.next =  node;
        node.prev = head;
    }

    private void removeNode(Node node) {
        Node next_node = node.next;
        Node prev_node = node.prev;

        next_node.prev = prev_node;
        prev_node.next = next_node;

    }

    private class Node {
        Integer key;
        String value;
        Node prev;
        Node next;

        @Override
        public String toString() {
            return value;
        }
    }
}


