/*
 * @lc app=leetcode id=146 lang=java
 *
 * [146] LRU Cache
 */

// @lc code=start
class LRUCache {
    private Map<Integer, Node> map;
    private int capacity;
    private int count;
    private Node head;
    private Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.count = 0;
        this.head = new Node();
        this.tail = head;
        this.map = new HashMap<>();
    }

    public int get(int key) {
        Node p = find(key);
        if (p == null) {
            return -1;
        }
        moveToTail(p);
        return p.value;
    }

    public void put(int key, int value) {
        Node p = find(key);
        if (p != null) {
            p.value = value;
            moveToTail(p);
            return;
        }

        this.count++;

        if (this.count > this.capacity) {
            Node tmp = head.next;
            head.next = tmp.next;
            if (tmp.next == null) {
                tail = tmp.pre;
            } else {
                tmp.next.pre = head;
            }
            map.remove(tmp.key);
            tmp = null;
            this.count--;
        }

        p = new Node();
        p.key = key;
        p.value = value;

        map.put(key, p);

        if (head == tail) {
            head.next = p;
        }

        p.pre = tail;
        p.next = null;
        tail.next = p;

        tail = p;

        return;
    }

    private Node find(int key) {
        return map.get(key);
        // Node p = head;
        // while (p != null) {
        //     if (p.key == key) {
        //         return p;
        //     }
        //     p = p.next;
        // }
        // return null;
    }

    private void moveToTail(Node p) {
        // p is tail
        if (tail == p) {
            return;
        }

        p.next.pre = p.pre;
        p.pre.next = p.next;

        tail.next = p;
        p.pre = tail;
        p.next = null;

        tail = p;
    }

    class Node {
        int key;
        int value;
        Node pre;
        Node next;

        Node() {
            this.key = -1;
            this.value = -1;
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
// @lc code=end

