package com.nowcoder;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Administrator on 2017/5/17.
 */
public class MyLinkedList {

    Node head = null;

    public MyLinkedList(int a[]) {
        for (int i = 0; i < a.length; i++) {
            addNode(a[i]);
        }
    }
    /**
     * 增加结点
     *
     * @param d 增加的数据
     */

    public void addNode(int d) {
        Node newNode = new Node(d);
        if (head == null) {
            head = newNode;
            return;
        }

        Node tmp = head;
        while (tmp.next != null) {
            tmp = tmp.next;
        }
        tmp.next = newNode;
    }

    /**
     * 删除结点
     *
     * @param index 第几个结点
     * @return
     */
    public Boolean deleteNode(int index) {
        if (index < 1 || index > length()) {
            return false;
        }

        if (index == 1) {
            head = head.next;
            return true;
        }

        int i = 2;
        Node preNode = head;
        Node curNode = preNode.next;
        while (curNode != null) {
            if (i == index) {
                preNode.next = curNode.next;
                return true;
            }
            preNode = curNode;
            curNode = curNode.next;
            i++;
        }
        return true;
    }

    /**
     * 返回长度
     *
     * @return
     */
    public int length() {
        int length = 0;
        Node tmp = head;
        while (tmp != null) {
            length++;
            tmp = tmp.next;
        }
        return length;
    }

    /**
     * 排序链表：使用插入排序
     *
     * @return
     */
    public Node orderList() {
        Node nextNode = null;
        int temp = 0;
        Node curNode = head;
        while (curNode.next != null) {
            nextNode = curNode.next;
            while (nextNode != null) {
                if (curNode.data > nextNode.data) {
                    temp = curNode.data;
                    curNode.data = nextNode.data;
                    nextNode.data = temp;
                }
                nextNode = nextNode.next;
            }
            curNode = curNode.next;
        }
        return head;
    }

    /**
     * 删除重复数据
     *
     * @param head
     */
    public void deleteDuplecate(Node head) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        Node tmp = head;
        Node pre = null;
        while (tmp != null) {
            if (map.containsKey(tmp.data))
                pre.next = tmp.next;
            else {
                map.put(tmp.data, 1);
                pre = tmp;
            }
            tmp = tmp.next;
        }
    }

    /**
     * 找倒数第k个元素
     * @param head
     * @param k
     * @return
     */
    public Node findElement(Node head, int k) {
        if (k < 1) {
            return null;
        }
        Node p1 = head;
        Node p2 = head;

        for (int i = 0; i < k-1 && p1 != null; i++) { //前移k-1步
            p1 = p1.next;
        }
        if(p1 == null) {
            System.out.println("k 不合法");
            return null;
        }

        while(p1.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }

        return p2;
    }

    /**
     * 找中间结点
     * @param head
     * @return
     */
    public Node searchMidElement(Node head) {
        Node p1 = head;
        Node p2 = head;
        while(p1 != null && p1.next != null && p1.next.next != null){
            p1 = p1.next.next;
            p2 = p2.next;
        }
        return p2;
    }

    /**
     * 检测一个链表是否有环
     * @param head
     * @return
     * 快慢指针
     * p1 为快指针
     * p2 为慢指针
     */
    public boolean isLoop(Node head) {
        Node p1 = head;
        Node p2 = head;
        if(p1 == null)
            return false;
        while(p1 != null && p1.next != null) {
            p1 = p1.next.next;
            p2 = p2.next;
            if(p1 == p2) {
                return true;
            }
        }
        return !(p1 == null);
    }

    /**
     * 找到环的入口
     * @param head
     * @return
     */
    public Node findLoopPort(Node head) {
        Node slow = head;
        Node fast = head;
        while(fast != null && fast.next != null) { //找相遇点
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast)
                break;
        }

        if(fast == null || fast.next == null)   //没有相遇，则没有环
            return null;

        slow = head;
        while(slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    /**
     * 两个链表是否相交，判断尾结点是否为同一结点。
     * @param h1
     * @param h2
     * @return
     */
    public boolean isIntersect(Node h1, Node h2) {
        if(h1 == null || h2 == null)
            return false;

        Node tail1 = h1;
        while(tail1.next != null)
            tail1 = tail1.next;

        Node tail2 = h2;
        while(tail2.next != null)
            tail2 = tail2.next;

        return tail2==tail1;
    }

    /**
     * 找出 相交链表中  相交的第一个结点
     * 先判断是否相交
     * 然后把链表尾端对齐~
     * 遍历链表 第一个相等结点 即为结果
     * @param h1
     * @param h2
     * @return
     */
    public Node getFirstMeetNode(Node h1, Node h2) {

        //判断是否 相交
        if(h1 == null || h2 == null)
            return null;

        Node tail1 = h1;
        int len1 = 1;
        while(tail1.next != null) {
            tail1 = tail1.next;
            len1 ++;
        }

        Node tail2 = h2;
        int len2 = 1;
        while(tail2.next != null) {
            tail2 = tail2.next;
            len2++;
        }

        if(tail2!=tail1) //不相交 则退出
            return null;

        //找出较长的链表，先遍历
        Node t1 = h1;
        Node t2 = h2;
        if(len1 > len2) {
            int d = len1 - len2;
            while(d != 0){
                t1 = t1.next;
                d--;
            }
        }else {
            int d = len2 - len1;
            while(d != 0) {
                t2 = t2.next;
                d--;
            }
        }

        while(t1 != t2){
            t1 = t1.next;
            t2 = t2.next;
        }

        return t1;
    }


    public void printList() {
        Node tmp = head;
        while(tmp != null){
            System.out.print(tmp.data + "->");
            tmp = tmp.next;
        }
    }
}

/**
 * 结点数据结构
 */
class Node {
    public int data;
    public Node next = null;

    public Node(int data) {
        this.data = data;
    }
}