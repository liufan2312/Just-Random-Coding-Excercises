package com.company;

/**
 * Created by Fan
 * on 8/13/16.
 */
public interface FList<Item> {
    public void insertFront(Item x);
    public void insertBack(Item y);
    public Item getFront();
    public Item getBack();
    public Item get(int i);
    public Item deleteBack();
    public void insert(Item x, int position);
    public int size();

    default public void print(){
        for (int i = 0; i < size(); i ++){
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }
}
