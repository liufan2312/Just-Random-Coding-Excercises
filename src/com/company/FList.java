package com.company;

/**
 * Created by Fan
 * on 8/13/16.
 */
public interface FList<Item> {
    void insertFront(Item x);
    void insertBack(Item y);
    Item getFront();
    Item getBack();
    Item get(int i);
    Item deleteBack();
    void insert(Item x, int position);
    int size();

    default public void print(){
        for (int i = 0; i < size(); i ++){
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }
}
