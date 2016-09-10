package com.company;

/**
 * Created by Fan
 * on 8/13/16.
 */

public class FAList<Item> implements FList<Item>{
    private Item[] items;
    private int size;
    private static int RFACTOR = 2;

    public FAList(){
        items = (Item[]) new Object[8];
        size = 0;
    }

    private void resize(int cap){
        Item[] a = (Item[]) new Object[cap];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }
    
    public Item get(int i){
        return items[i];
    }

    public void insertBack(Item x){
        if (size == items.length){
            resize(size * RFACTOR);
        }
        items[size] = x;
        size ++;
    }

    public void insertFront(Item x){
        if (size == items.length){
            resize(size * RFACTOR);
        }
        Item[] a = (Item[]) new Object[items.length];
        System.arraycopy(items, 0, a, 1, size);
        a[0] = x;
        items = a;
        size ++;
    }

    public Item getFront(){
        return items[0];
    }

    public Item getBack(){
        return items[size - 1];
    }

    public void insert(Item x, int position){
        if (size == items.length){
            resize(size * RFACTOR);
        }
        Item[] a = (Item[]) new Object[items.length];
        System.arraycopy(items, 0, a, 0, position);
        System.arraycopy(items, position, a, position + 1, size - position);
        a[position] = x;
        items = a;
        size ++;
    }

    public int size(){
        return size;
    }

    public Item deleteBack(){
        Item returnItem = get(size - 1);
        items[size - 1] = null;
        size --;
        return returnItem;
    }

}
