package com.company;


import java.util.HashMap;

public class LeetCode {

    //Longest Substring Without Repeating Characters
    public int lengthOfLongestSubstring(String s) {
        char[] characters = s.toCharArray();
        HashMap<Character, Integer> position = new HashMap<>();
        int p1 = 0;
        int p2 = 0;
        int maxlen = 0;

        while(p2 < characters.length){
            if(position.containsKey(characters[p2]) && p1 <= position.get(characters[p2])){
                p1 = position.get(characters[p2]) + 1;
            }
            int len = p2 - p1 + 1;
            if(len > maxlen){
                maxlen = len;
            }
            position.put(characters[p2], p2);
            p2 ++;
        }
        return maxlen;
    }

    //Remove Duplicates from Sorted List
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        while(dummy.next.next != null){
            if(dummy.next.val == dummy.next.next.val){
                if(dummy.next.next.next == null){
                    dummy.next.next = null;
                } else {
                    dummy.next.next = dummy.next.next.next;
                }
            } else {
                dummy.next = dummy.next.next;
            }
        }
        return head;
    }

    // 19. Remove Nth Node From End of List
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null || head.next == null){
            return null;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        int len = 0;
        while(dummy.next.next != null){
            dummy.next = dummy.next.next;
            len ++;
        }

        dummy.next = head;
        if(len - n == -1){
            head = head.next;
        }
        while(len - n > 0){
            dummy.next = dummy.next.next;
            n ++;
        }
        dummy.next.next = dummy.next.next.next;
        return head;
    }
    public static void main(String[] args){

    }
}
