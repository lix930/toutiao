package com.nowcoder;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by Administrator on 2017/5/17.
 */
public class MyQueue {
    Stack<Integer> inStack = new Stack<Integer>();
    Stack<Integer> outStack = new Stack<Integer>();
    Queue<Integer> queue = new LinkedList<Integer>();

    public void add(int x) {
        inStack.push(x);

    }

    public int pop(){
        if(!outStack.isEmpty())
            return outStack.pop();
        else{
            while(!inStack.isEmpty()){
                outStack.push(inStack.pop());
            }
            return outStack.pop();
        }

    }

    public int size() {
        return inStack.size() + outStack.size();
    }

    public boolean isEmpty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }


    public static void main(String[] args) {
        MyQueue q = new MyQueue();
        q.add(1);
        q.add(2);
        q.add(3);
        System.out.println("out:" + q.pop());
        System.out.println("out:" + q.pop());
        System.out.println("out:" + q.pop());
    }
}
