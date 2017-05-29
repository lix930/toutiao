package com.nowcoder;

import java.util.Arrays;

/**
 * Created by Administrator on 2017/5/17.
 */
public class MyStack <E> {

    private Object[] stack;
    private int size;
    public MyStack() {
        stack = new Object[10]; // 初始长度为10
    }

    public boolean isEmpty(){
        return size==0;
    }

    public E peek(){
        if(isEmpty())
            return null;
        return (E) stack[size-1];
    }

    public E pop() {
        E e = peek();
        stack[size-1] = null;
        size--;
        return e;
    }

    public E push(E item) {
        ensureCapacity(size+1);
        stack[size++] = item;
        return item;
    }

    private void ensureCapacity(int size) {
        int len = stack.length;
        if(size > len) {
            int newLen = 10;
            stack = Arrays.copyOf(stack, newLen);
        }
    }

    public static void main(String[] args) {
        MyStack<Integer> s = new MyStack<Integer>();
        s.push(1);
        s.push(2);
        System.out.println("栈中的元素个数为：" + s.size);
        System.out.println("栈顶元素为："  + s.pop());
        System.out.println("栈顶元素为："  + s.pop());

    }


}
