package com.company;

/**
 * Created by Fan
 * on 8/13/16.
 */

public class FIntList {
    private int head;
    private FIntList tail;
    private int size;

    public FIntList(int h) {
        head = h;
        tail = null;
        size = 1;
    }

    public FIntList(int h, FIntList l) {
        head = h;
        tail = l;
        if (l == null) {
            size = 1;
        } else {
            size = l.size + 1;
        }
    }

    public int getSize() {
        return size;
    }

    private void setHead(int x) {
        head = x;
    }

    public int getHead() {
        return head;
    }

    public int get(int i) {
        if (i == 0) {
            return head;
        } else {
            return tail.get(i - 1);
        }
    }

    public static FIntList incrList(FIntList l, int x) {
        FIntList q = null;
        int pointer = l.size - 1;
        while (pointer != 0) {
            q = new FIntList(l.get(pointer) + x, q);
            pointer -= 1;
        }
        return q;
    }

    public static FIntList dincrList(FIntList l, int x) {
        if (l.tail == null) {
            l.setHead(l.head + x);
            return l;
        } else {
            return dincrList(l.tail, x);
        }
    }
}
