package com.company;

/**
 * Created by Fan
 * on 8/13/16.
 */
public class FSList<Item> {
    public class IntNode{
        public Item item;
        public IntNode next;
        public IntNode (Item i, IntNode n){
            item = i;
            next = n;
        }
    }

    private IntNode sentinel;
    private int size;

    public FSList(){
        size = 0;
        sentinel = new IntNode(null, null);
    }

    public  FSList(Item x){
        size = 1;
        sentinel = new IntNode(null, null);
        sentinel.next = new IntNode(x, null);
    }

    private IntNode getBackNode(){
        IntNode p = sentinel;
        while (p.next != null){
            p = p.next;
        }
        return p;
    }

    public void insertFront(Item x){
        sentinel.next = new IntNode(x, sentinel.next);
        size ++;
    }

    public void insertBack(Item x){
        getBackNode().next = new IntNode(x, null);
        size ++;
    }

    public int size(){
        return size;
    }
}
