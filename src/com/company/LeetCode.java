package com.company;


import java.util.*;


public class LeetCode {

    // Maximum subarray
    public int maxSubArray(int[] nums) {
        if(nums == null || nums.length==0)
            return 0;

        int[] local = new int[nums.length];
        local[0] = nums[0];
        int global = nums[0];
        for(int i = 1; i < nums.length; i ++){
            local[i] = Math.max(nums[i], local[i - 1] + nums[i]);
            global = Math.max(global, local[i]);
        }
        return global;
    }

    // merge k lists with heap
    public ListNode mergeKListsHeap(ArrayList<ListNode> lists) {
        PriorityQueue<ListNode> heap = new PriorityQueue<>(10,new Comparator<ListNode>(){
            @Override
            public int compare(ListNode n1, ListNode n2)
            {
                return n1.val-n2.val;
            }
        });
        for(int i=0;i<lists.size();i++)
        {
            ListNode node = lists.get(i);
            if(node!=null)
            {
                heap.offer(node);
            }
        }
        ListNode head = null;
        ListNode pre = head;
        while(heap.size()>0)
        {
            ListNode cur = heap.poll();
            if(head == null)
            {
                head = cur;
                pre = head;
            }
            else
            {
                pre.next = cur;
            }
            pre = cur;
            if(cur.next!=null)
                heap.offer(cur.next);
        }
        return head;
    }

    // Merge k lists using merge sort
    public ListNode mergeKLists(ArrayList<ListNode> lists) {
        if(lists==null || lists.size()==0)
            return null;
        return helper(lists,0,lists.size()-1);
    }
    private ListNode helper(ArrayList<ListNode> lists, int l, int r)
    {
        if(l<r)
        {
            int m = (l+r)/2;
            return merge(helper(lists,l,m),helper(lists,m+1,r));
        }
        return lists.get(l);
    }
    private ListNode merge(ListNode l1, ListNode l2)
    {
        ListNode dummy = new ListNode(0);
        dummy.next = l1;
        ListNode cur = dummy;
        while(l1!=null && l2!=null)
        {
            if(l1.val<l2.val)
            {
                l1 = l1.next;
            }
            else
            {
                ListNode next = l2.next;
                cur.next = l2;
                l2.next = l1;
                l2 = next;
            }
            cur = cur.next;
        }
        if(l2!=null)
            cur.next = l2;
        return dummy.next;
    }

    // Word distance
    public class WordDistance {
        public HashMap<String, ArrayList<Integer>> positions;

        public WordDistance(String[] words) {
            positions = new HashMap<>();
            for(int i = 0; i < words.length; i ++){
                if(positions.containsKey(words[i])){
                    ArrayList<Integer> pos = positions.get(words[i]);
                    pos.add(i);
                    positions.put(words[i], pos);
                } else {
                    ArrayList<Integer> pos = new ArrayList<>();
                    pos.add(i);
                    positions.put(words[i], pos);
                }
            }
        }

        // Find smallest different between to sorted arrays
        public int shortest(String word1, String word2) {
            ArrayList<Integer> pos1 = positions.get(word1);
            ArrayList<Integer> pos2 = positions.get(word2);
            int distance = Integer.MAX_VALUE;
            int i = 0, j = 0;
            while(i < pos1.size() && j < pos2.size()){
                distance = Math.min(Math.abs(pos1.get(i) - pos2.get(j)), distance);
                if(pos1.get(i) < pos2.get(j)){
                    i++;
                } else {
                    j++;
                }
            }
            return distance;
        }
    }


    // Two sum
    // use a hash map to store the number needed
    public int[] twoSum(int[] numbers, int target) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int[] result = new int[2];

        for (int i = 0; i < numbers.length; i++) {
            if (map.containsKey(numbers[i])) {
                int index = map.get(numbers[i]);
                result[0] = index ;
                result[1] = i;
                break;
            } else {
                map.put(target - numbers[i], i);
            }
        }

        return result;
    }

    // Two sum
    // two pointers method works is array is sorted or if we don't need to return the index

    // Two sum class
    public class TwoSum {
        private ArrayList<Integer> nums;

        public TwoSum(){
            nums = new ArrayList<Integer>();
        }

        // Add the number to an internal data structure.
        public void add(int number) {
            nums.add(number);
        }

        // Find if there exists any pair of numbers which sum is equal to the value.
        public boolean find(int value) {
            HashSet<Integer> set = new HashSet<Integer>();
            for (int i = 0; i < nums.size(); i++) {
                if (set.contains(nums.get(i))) {
                    return true;
                } else {
                    set.add(value - nums.get(i));
                }
            }
            return false;
        }
    }

    // Isomorphic stings
    public boolean isIsomorphic(String s, String t) {
        HashMap<Character, Character> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                if (map.get(s.charAt(i)) != t.charAt(i)) {
                    return false;
                }
            } else {
                if (map.containsValue(t.charAt(i))) {
                    return false;
                }
                map.put(s.charAt(i), t.charAt(i));
            }
        }
        return true;
    }

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
