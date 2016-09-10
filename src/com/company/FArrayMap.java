package com.company;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Created by Fan
 * on 8/14/16.
 */

public class FArrayMap<K, V> {
    private ArrayList<K> keys;
    private ArrayList<V> values;
    private int size;

    public FArrayMap(){
        keys = new ArrayList<>();
        values = new ArrayList<>();
        size = 0;
    }

    public void put(K key, V value){
        int i = keys.indexOf(key);
        if (i > -1){
            values.add(i, value);
            return;
        }
        keys.add(size, key);
        values.add(size, value);
        size ++;
    }

    public V get(K key){
        int location = keys.indexOf(key);
        if (location < 0){
            throw new IllegalArgumentException("Key " + key + " does not exist in map");
        }
        return values.get(keys.indexOf(key));
    }

    public boolean containsKey(K key){
        int i = keys.indexOf(key);
        return (i > -1);
    }

    public ArrayList<K> keys(){
        return keys;
    }

    public static <K extends Comparable<K>, V> K maxKey(FArrayMap<K, V> am){
        ArrayList<K> keys = am.keys();
        K maxKey = keys.get(0);
        for (K key : keys){
            if (key.compareTo(maxKey) > 0){
                maxKey = key;
            }
        }
        TreeMap<Integer, Integer> bst = new TreeMap<>();
        return maxKey;
    }

}
