package com.zxs.study.algorithm.datastructure;

/**
 * 大顶堆
 *
 * @author zxs
 * @date 2021/7/1 11:48 下午
 */
public class Heap {
    /**
     * 存储容器
     */
    private int[] data;
    /**
     * 存储容量
     */
    private int capacity;
    /**
     * 存储数目
     */
    private int count;

    public Heap(int capacity) {
        this.capacity = capacity;
        this.data = new int[capacity + 1];
        this.count = 0;
    }

    /**
     * 插入
     *
     * @author zxs
     * @date 2021/7/2 12:05 上午
     */
    public Heap insert(int number) {
        if (count == capacity) {
            throw new RuntimeException("no place");
        }
        count++;
        data[count] = number;
        int offset = count;
        int parent;
        while (offset > 1) {
            parent = offset / 2;
            if (data[parent] > data[offset]) {
                break;
            }
            data[offset] = data[parent];
            data[parent] = number;
            offset = parent;
        }
        return this;
    }

    public void removeMax() {
        if (count == 0) {
            return;
        }
        count--;
        data[1] = data[count];
        int offset = 1;
        int temp, pos;
        while (2 * offset <= count) {
            pos = offset;
            if (data[offset] < data[2 * offset]) {
                pos = 2 * offset;
            }
            if (2 * offset + 1 <= count && data[pos] < data[2 * offset + 1]) {
                pos = 2 * offset + 1;
            }
            if (pos == offset) {
                break;
            }
            temp = data[offset];
            data[offset] = data[pos];
            data[pos] = temp;
            offset = pos;
        }
    }

    public void print() {
        int multiple = (int) (Math.log(count) / Math.log(2)) + 1;
        int start = 1;
        for (int i = 1; i <= multiple; i++) {
            System.out.print("第" + i + "层的数为:");
            while (start < (1 << i) && start <= count) {
                System.out.print(data[start] + ",");
                start++;
            }
            System.out.println();

        }
    }

    public static void main(String[] args) {
        Heap heap = new Heap(15);
        heap.insert(101)
                .insert(11)
                .insert(19)
                .insert(178)
                .insert(99)
                .insert(89)
                .insert(66)
                .insert(56)
                .insert(7)
                .insert(93)
                .insert(34)
                .insert(87)
                .insert(39)
                .insert(29)
                .insert(54);
        heap.print();
        System.out.println("----------------------");
        heap.removeMax();
        heap.print();
        System.out.println("----------------------");
        heap.removeMax();
        heap.print();
    }
}
