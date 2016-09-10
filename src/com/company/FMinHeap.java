package com.company;

/**
 * Created by Fan
 * on 8/14/16.
 */

public class FMinHeap<E extends Comparable<E>> {
    private E[] heap;
    private int size;
    private static int RFACTOR = 2;

    public FMinHeap(){
        heap = (E[])new Comparable[100];
        size = 0;
    }

    public E getSmallest(){
        return heap[1];
    }

    public int getSize(){
        return size;
    }

    private void swap(int id1, int id2){
        E temp = heap[id1];
        heap[id1] = heap[id2];
        heap[id2] = temp;
    }

    private void promote(int id){
        int parentId = id / 2;
        if (parentId == 0){
            return;
        }
        if (heap[parentId].compareTo(heap[id]) > 0){
            swap(parentId, id);
            promote(parentId);
        }
    }

    private void demote(int id){
        int leftId = id * 2;
        int rightId = id * 2 + 1;

        if (leftId > size){
            return;
        }
        if (heap[leftId].compareTo(heap[id]) < 0){
            swap(id, leftId);
            demote(leftId);
        }
        if (rightId > size){
            return;
        }
        if (heap[rightId].compareTo(heap[id]) < 0){
            swap(id, rightId);
            demote(rightId);
        }
    }

    private void resize(int cap){
        E[] a = (E[]) new Comparable[cap];
        System.arraycopy(heap, 0, a, 0, size);
        heap = a;
    }

    public void add(E e){
        if (size == heap.length){
            resize(size * RFACTOR);
        }
        heap[size + 1] = e;
        size ++;
        promote(size);
    }

    public E removeSmallest(){
        E smallest = heap[1];
        heap[1] = heap[size];
        heap[size] = null;
        size --;
        demote(1);

        return smallest;
    }
}
