package com.nowcoder;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Administrator on 2017/5/17.
 */
public class MyStack2 {
    Queue<Integer> inQueue = new LinkedList<Integer>();
    Queue<Integer> outQueue = new LinkedList<Integer>();

    public int size() {
        return inQueue.size() + outQueue.size();
    }

    public boolean isEmpty() {
        return inQueue.isEmpty() && outQueue.isEmpty();
    }

    public void push(int item) {
        inQueue.add(item);
    }

    public int pop() {
        if (inQueue.size() == 0) {
            System.out.println("stack is empty!");
            return 0;
        }
        else if(inQueue.size() == 1)
            return inQueue.poll();
        else {
            while(inQueue.size() > 1){
                outQueue.add(inQueue.poll());
            }
            int out = inQueue.poll();
            while(!outQueue.isEmpty()) {
                inQueue.add(outQueue.poll());
            }
            return out;
        }
    }

    public static void main(String[] args) {
        MyStack2 stack2 = new MyStack2();
        stack2.push(1);
        stack2.push(2);
        stack2.push(3);
        stack2.push(11);
        stack2.push(22);
        stack2.push(33);
        System.out.println("out:" + stack2.pop());
        System.out.println("out:" + stack2.pop());
        System.out.println("out:" + stack2.pop());
        System.out.println("out:" + stack2.pop());
        System.out.println("out:" + stack2.pop());
        System.out.println("out:" + stack2.pop());
        System.out.println("out:" + stack2.pop());

    }

}
