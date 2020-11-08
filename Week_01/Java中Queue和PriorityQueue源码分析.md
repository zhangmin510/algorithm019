# Java Queue源码分析

在Java中Queue被定义成为接口。继承了Collection和Iterable接口。继承Queue的子接口有BlockingQueue, BlockingDeque, Deque, TransferQueue。

Queue中定义的操作：
1. 插入。 add和offer。 前者出错时抛异常，后者返回特殊值。大多数情况下插入是不能报错的，在队列大小限制的条件下，可以使用offer。
2. 删除。remove和poll。同上。
3. 查看。 element和peek。同上。

Queue中的元素顺序不一定是FIFO的，也可能是PriorityQueue中根据Comparator或者自然顺序，或者LIFO顺序。

Queue中一般是不能插入null元素的，因为Queue的实现中使用了null作为返回值，如poll和peek。

Queue不提供equals和hashCode实现，因为queue是有顺序属性的，即相同的元素，但是顺序不同。

Queue的实现可以分为：
1. 数组实现。
   1. 阻塞队列。 ArrayBlockingQueue
   2. 双端队列。 ArrayDeque
2. 列表实现。
   1. 阻塞队列。LinkedBlockingQueue
   2. 双端队列。LinkedList

## ArrayDeque实现

ArrayDeque是一种基于可变数组的实现，不限制数据大小。不是线程安全的，不能插入null元素。 大部分实现都是均摊的常数时间复杂度。
可以作为栈和队列来使用，而且比Stack和LinkedList性能好。

由于是Java集合框架的类，所以要考虑集合的操作实现，以及迭代器的实现，特别是考虑迭代过程中，队列元素发生变化的情况。

核心实现点：
1. Object[] elements。 总是2的倍数大小（内存管理）。没有存储元素时，值是null。这个数组不能是满的。
2. head。队列头部元素索引。
3. tail。队列尾部元素下一个索引。tail==head时表示队列满了，需要扩容。
4. 主要的方法是： addFirst,addLast, pollFirst, pollLast。


### 核心代码

计算任意一个数的最小2的幂次算法。

```
    private static int calculateSize(int numElements) {
        int initialCapacity = MIN_INITIAL_CAPACITY;
        // Find the best power of two to hold elements.
        // Tests "<=" because arrays aren't kept full.
        if (numElements >= initialCapacity) {
            initialCapacity = numElements;
            initialCapacity |= (initialCapacity >>>  1);
            initialCapacity |= (initialCapacity >>>  2);
            initialCapacity |= (initialCapacity >>>  4);
            initialCapacity |= (initialCapacity >>>  8);
            initialCapacity |= (initialCapacity >>> 16);
            initialCapacity++;

            if (initialCapacity < 0)   // Too many elements, must back off
                initialCapacity >>>= 1;// Good luck allocating 2 ^ 30 elements
        }
        return initialCapacity;
    }
```

数组扩容（2倍）算法：
```
    /**
     * Doubles the capacity of this deque.  Call only when full, i.e.,
     * when head and tail have wrapped around to become equal.
     */
    private void doubleCapacity() {
        assert head == tail;
        int p = head;
        int n = elements.length;
        int r = n - p; // number of elements to the right of p
        int newCapacity = n << 1;
        if (newCapacity < 0)
            throw new IllegalStateException("Sorry, deque too big");
        Object[] a = new Object[newCapacity];
        System.arraycopy(elements, p, a, 0, r);
        System.arraycopy(elements, 0, a, r, p);
        elements = a;
        head = 0;
        tail = n;
    }
```

ArrayDeque基于循环数组实现，所以会出现`tail<head`的的情况。所以在赋值head和tail时要使用下面的方法，保证tail和head是正整数。
前提：数组的大小是2的幂次方。

```
    /**
     * Inserts the specified element at the front of this deque.
     *
     * @param e the element to add
     * @throws NullPointerException if the specified element is null
     */
    public void addFirst(E e) {
        if (e == null)
            throw new NullPointerException();
        elements[head = (head - 1) & (elements.length - 1)] = e;
        if (head == tail)
            doubleCapacity();
    }

    /**
     * Inserts the specified element at the end of this deque.
     *
     * <p>This method is equivalent to {@link #add}.
     *
     * @param e the element to add
     * @throws NullPointerException if the specified element is null
     */
    public void addLast(E e) {
        if (e == null)
            throw new NullPointerException();
        elements[tail] = e;
        if ( (tail = (tail + 1) & (elements.length - 1)) == head)
            doubleCapacity();
    }
```

# Java Priority Queue源码分析

PriorityQueue基于优先级堆实现。优先级是通过自然排序或者传入的Comparator来确定。不是线程安全的。不支持null元素。
入队和出队时间复杂度是O(logn)，删除和是否包含时间复杂度是O(n)，查看元素是O(1)。

堆的实现：

```
    private void heapify() {
        for (int i = (size >>> 1) - 1; i >= 0; i--)
            siftDown(i, (E) queue[i]);
    }

    /**
     * Inserts item x at position k, maintaining heap invariant by
     * demoting x down the tree repeatedly until it is less than or
     * equal to its children or is a leaf.
     *
     * @param k the position to fill
     * @param x the item to insert
     */
    private void siftDown(int k, E x) {
        if (comparator != null)
            siftDownUsingComparator(k, x);
        else
            siftDownComparable(k, x);
    }

    private void siftDownComparable(int k, E x) {
        Comparable<? super E> key = (Comparable<? super E>)x;
        int half = size >>> 1;        // loop while a non-leaf
        while (k < half) {
            int child = (k << 1) + 1; // assume left child is least
            Object c = queue[child];
            int right = child + 1;
            if (right < size &&
                ((Comparable<? super E>) c).compareTo((E) queue[right]) > 0)
                c = queue[child = right];
            if (key.compareTo((E) c) <= 0)
                break;
            queue[k] = c;
            k = child;
        }
        queue[k] = key;
    }
```


插入元素时调整堆：
```
    private void siftUpComparable(int k, E x) {
        Comparable<? super E> key = (Comparable<? super E>) x;
        while (k > 0) {
            int parent = (k - 1) >>> 1;
            Object e = queue[parent];
            if (key.compareTo((E) e) >= 0)
                break;
            queue[k] = e;
            k = parent;
        }
        queue[k] = key;
    }
```


# 总结

Java中的Queue和PriorityQueue工业级别实现，除了最基本的数据结构实现，还要考虑语言的特性，如集成，多态，迭代器，对象序列化等，
在实现中要时时刻刻考虑性能，如大量使用位运算，以及2的幂次方的大小（内存管理），算法（计算某个数最小的2的幂次方）。

另外需要提供各种额外的方法，使得类更易用，如使用其他集合构建，转成其他对象等。