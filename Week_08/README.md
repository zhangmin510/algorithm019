# 学习笔记

## 位运算

之所以使用位运算，就是因为计算机本质上只能识别0和1，基于二进制的位操作更高效。

常见的位运算Java版本
```java
public class Main {
    public static void main(String[] args) {

    int x = 10;
    System.out.println(Integer.toBinaryString(x));
    System.out.println("x ^ 0 = x\n" + Integer.toBinaryString(x ^ 0));
    System.out.println("x ^ (~0) =  -x\n" + Integer.toBinaryString(x ^ (~0)));
    System.out.println("x ^ (-x) = 1的个数\n" + Integer.toBinaryString(x ^ (-x)));
    System.out.println("x ^ x = 0\n" + Integer.toBinaryString(x ^ x));

    int a = 1;
    int b = 2;
    // 交换2个数
    a = a ^ b;
    b = b ^ a;
    a = a ^ b;
    System.out.println("a=" + a + ", b=" + b);

    x = 20;
    int n = 3;
    System.out.println("x=" + Integer.toBinaryString(x));
    System.out.println("将x=" + x + "的最右边的" + n + "位清零: \n" + Integer.toBinaryString(x & (~0 << n)));
    System.out.println("获取x=" + x + "的第" + n + "位的值: \n" + Integer.toBinaryString((x >> n) & 1));
    System.out.println("获取x=" + x + "的第" + n + "位的幂值: \n" + Integer.toBinaryString((x & (1 << n))));
    System.out.println("将x=" + x + "的第" + n + "位的置为1: \n" + Integer.toBinaryString((x | (1 << n))));
    System.out.println("将x=" + x + "的第" + n + "位的置为0: \n" + Integer.toBinaryString((x & (~(1 << n)))));
    System.out.println("将x=" + x + "的最高位至第" + n + "位的清0: \n" + Integer.toBinaryString((x & ((1 << n) - 1))));


    System.out.println("x判断是否是偶数" + ((x & 1) == 0));
    System.out.println("x判断是否是奇数" + ((x & 1) == 1));

    x = 21;
    System.out.println("x=" + Integer.toBinaryString(x));
    System.out.println("x除以2=" +  (x >> 1));
    System.out.println("清0最低位的1=" +  Integer.toBinaryString(x & (x - 1)));
    System.out.println("得到最低位的1=" +  (x & (-x)));
    }
}

```

## 布隆过滤器

布隆过滤器主要用于检索一个元素是否在一个集合中，并且能够容忍一定的误差。其基本原理就是使用一个二进制向量和多个
随机映射函数。

布隆过滤器的优点是空间和时间效率非常高，但缺点是有误识别率和无法进行删除操作。

如果布隆过滤器判断判断不在， 那么该元素一定不在集合中。但是如果判断存在，则可能存在，需要进一步的确认。

所以在工业中，一般使用布隆过滤器作为一个缓存，判断当前元素在不在某一个集群，如果在，则再去数据库中进行准确的判断，
如果不在，则直接去其他的集群判断。


## LRU

对于缓存最关键的2个要素是缓存的大小和替换策略。缓存的查询和更新必须是O(1)。一般是基于哈希表和双向链表来实现LRU的缓存。

```java
mport java.util.LinkedHashMap;
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

```
