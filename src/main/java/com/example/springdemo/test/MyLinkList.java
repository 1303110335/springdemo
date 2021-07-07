/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.example.springdemo.test;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.AbstractSequentialList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * @author xuleyan
 * @version MyLinkList.java, v 0.1 2019-06-01 3:28 PM xuleyan
 */
public class MyLinkList<E> extends AbstractSequentialList<E>
        implements List<E>, Deque<E>, Cloneable, Serializable {

    /**
     * 网商最小提现金额
     */
//    public static final BigDecimal MIN_WITHDRAW_MONEY = BigDecimal.valueOf(0.01);
    public static final BigDecimal MIN_WITHDRAW_MONEY = new BigDecimal("0.01");
    /**
     * 网商最大提现金额
     */
    public static final BigDecimal MAX_WITHDRAW_MONEY = new BigDecimal("999999999.99");
    transient int size = 0;
    transient Node<E> first;
    transient Node<E> last;

    public MyLinkList() {

    }

    public MyLinkList(Collection<? extends E> c) {
        this();
        addAll(c);
    }

    public static void main(String[] args) {

//        MyLinkList<Integer> myLinkList = new MyLinkList<>(Arrays.asList(1,5,8,10));
//        Integer result = myLinkList.removeFirst();
//        Integer result = myLinkList.remove(3);
//        System.out.println(result);
//        System.out.println(myLinkList.first);

        Integer a = 1;
        System.out.println(a);
        change(a);
        System.out.println(a);
    }

    public static void change(Integer a) {
        a = 20;
        System.out.println(a);
    }

    /**
     * 将元素放入到链表第一个位置
     *
     * @param e
     */
    private void linkFirst(E e) {
        final Node<E> f = first;
        final Node<E> newNode = new Node<>(null, e, f);
        first = newNode;
        if (f == null) {
            last = newNode;
        } else {
            f.prev = newNode;
        }
        size++;
        modCount++;
    }

    /**
     * 将元素放入到链表最后一个位置
     *
     * @param e
     */
    void linkLast(E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(last, e, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    /**
     * Inserts element e before non-null Node succ.
     *
     * @param e
     * @param succ
     */
    void linkBefore(E e, Node<E> succ) {
        // Assert succ != null
        final Node<E> pred = succ.prev;
        final Node<E> newNode = new Node<>(pred, e, succ);
        if (pred == null) {
            first = newNode;
        } else {
            pred.next = newNode;
        }
        size++;
        modCount++;
    }

    /**
     * 删除第一个
     *
     * @param f
     * @return
     */
    private E unlinkFirst(Node<E> f) {

        final E item = f.item;
        Node<E> next = f.next;

        // help GC
        f.item = null;
        f.next = null;

        first = next;

        if (next == null) {
            last = next;
        } else {
            next.prev = null;
        }

        size--;
        modCount++;

        return item;
    }

    private E unLinkLast(Node<E> l) {
        final E element = l.item;
        Node<E> pred = l.prev;

        l.item = null;
        l.prev = null;

        last = pred;
        if (pred == null) {
            first = pred;
        } else {
            pred.next = null;
        }

        size--;
        modCount++;

        return element;
    }

    /**
     * 删除节点
     *
     * @param x
     * @return
     */
    E unlink(Node<E> x) {
        // Assert x != null
        final E element = x.item;
        Node<E> next = x.next;
        Node<E> prev = x.prev;

        x.item = null;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        size--;
        modCount++;
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        linkLast(e);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = 0;
        if (o == null) {
            for (Node<E> x = first; index < size; x = x.next) {
                if (x.item == null) {
                    return true;
                }
                index++;
            }
        } else {
            for (Node<E> x = first; index < size; x = x.next) {
                if (o.equals(x.item)) {
                    return true;
                }
                index++;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return addAll(size, c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        checkPositionIndex(index);

        // 判断新插入的数组的长度是否大于0
        Object[] a = c.toArray();
        int newNum = a.length;
        if (newNum == 0) {
            return false;
        }

        // 判断需要插入的前一个元素和后一个元素
        Node<E> pred, succ;
        if (index == size) {
            succ = null;
            pred = last;
        } else {
            succ = node(index);
            pred = succ.prev;
        }

        for (Object o : a) {
            @SuppressWarnings("unchecked") E e = (E) o;
            Node<E> newNode = new Node<>(pred, e, null);
            if (pred == null) {
                first = newNode;
            } else {
                pred.next = newNode;
            }
            pred = newNode;
        }

        if (succ == null) {
            last = pred;
        } else {
            pred.next = succ;
            succ.prev = pred;
        }

        size += newNum;
        modCount++;

        return true;
    }

    private Node<E> node(int index) {
        // 判断index是在整个链表的前半段还是后半段
        if (index < (size >> 2)) {
            Node<E> x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;
        } else {
            Node<E> x = last;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
            return x;
        }
    }

    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index)) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        int index = 0;
        for (Node<E> x = first; index < size; ) {
            Node<E> next = x.next;
            x.item = null;
            x.prev = null;
            x.next = null;
            x = next;
        }
        first = last = null;
        size = 0;
        modCount++;
    }

    @Override
    public E get(int index) {
        checkPositionIndex(index);
        return node(index).item;
    }

    @Override
    public E set(int index, E element) {
        checkPositionIndex(index);
        Node<E> oldNode = node(index);
        E oldValue = oldNode.item;
        oldNode.item = element;
        return oldValue;
    }

    @Override
    public void add(int index, E element) {
        checkPositionIndex(index);
        if (index == size) {
            linkLast(element);
        } else {
            linkBefore(element, node(index));
        }
    }

    @Override
    public E remove(int index) {
        checkPositionIndex(index);
        unlink(node(index));
        return null;
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            for (Node<E> x = first; index < size; x = x.next) {
                if (x.item == null) {
                    return index;
                }
                index++;
            }
        } else {
            for (Node<E> x = first; index < size; x = x.next) {
                if (o.equals(x.item)) {
                    return index;
                }
                index++;
            }
        }
        return -1;
    }

    /**
     * Tells if the argument is the index of a valid position for an
     * iterator or an add operation.
     */
    private boolean isElementIndex(int index) {
        return index >= 0 && index <= size;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public void addFirst(E e) {
        linkFirst(e);
    }

    @Override
    public void addLast(E e) {
        linkLast(e);
    }

    /**
     * Inserts the specified element at the front of this list.
     *
     * @param e the element to insert
     * @return {@code true} (as specified by {@link Deque#offerFirst})
     * @since 1.6
     */
    @Override
    public boolean offerFirst(E e) {
        addFirst(e);
        return true;
    }

    /**
     * Inserts the specified element at the end of this list.
     *
     * @param e the element to insert
     * @return {@code true} (as specified by {@link Deque#offerLast})
     * @since 1.6
     */
    @Override
    public boolean offerLast(E e) {
        addLast(e);
        return true;
    }

    @Override
    public E removeFirst() {
        final Node<E> f = first;
        if (f == null) {
            throw new NoSuchElementException();
        }
        return unlink(f);
    }

    @Override
    public E removeLast() {
        final Node<E> l = last;
        if (l == null) {
            throw new NoSuchElementException();
        }
        return unlink(l);
    }

    @Override
    public E pollFirst() {
        return null;
    }

    @Override
    public E pollLast() {
        return null;
    }

    @Override
    public E getFirst() {
        Node<E> f = first;
        if (f == null) {
            throw new NoSuchElementException();
        }
        return f.item;
    }

    @Override
    public E getLast() {
        Node<E> l = last;
        if (l == null) {
            throw new NoSuchElementException();
        }
        return l.item;
    }

    @Override
    public E peekFirst() {
        return null;
    }

    @Override
    public E peekLast() {
        return null;
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean offer(E e) {
        return add(e);
    }

    @Override
    public E remove() {
        return removeFirst();
    }

    @Override
    public E poll() {
        final Node<E> f = first;
        return (f == null) ? null : unlinkFirst(f);
    }

    /**
     * Retrieves, but does not remove, the head (first element) of this list.
     *
     * @return the head of this list
     * @throws NoSuchElementException if this list is empty
     * @since 1.5
     */
    @Override
    public E element() {
        return getFirst();
    }

    /**
     * Retrieves, but does not remove, the head (first element) of this list.
     *
     * @return the head of this list, or {@code null} if this list is empty
     * @since 1.5
     */
    @Override
    public E peek() {
        final Node<E> f = first;
        return (f == null) ? null : f.item;
    }

    @Override
    public void push(E e) {
        addFirst(e);
    }

    @Override
    public E pop() {
        return removeFirst();
    }

    @Override
    public Iterator<E> descendingIterator() {
        return null;
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.prev = prev;
            this.item = element;
            this.next = next;
        }
    }

    private class ListItr implements ListIterator<E> {

        private Node<E> lastReturned;
        private Node<E> next;
        private int nextIndex;
        private int expectedModCount = modCount;

        ListItr(int index) {
            next = (index == size) ? null : node(index);
            nextIndex = index;
        }

        @Override
        public boolean hasNext() {
            return nextIndex < size;
        }

        @Override
        public E next() {
            checkForComodification();
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.item;
        }

        @Override
        public boolean hasPrevious() {
            return false;
        }

        @Override
        public E previous() {
            return null;
        }

        @Override
        public int nextIndex() {
            return 0;
        }

        @Override
        public int previousIndex() {
            return 0;
        }

        @Override
        public void remove() {

        }

        @Override
        public void set(E e) {

        }

        @Override
        public void add(E e) {

        }

        final void checkForComodification() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }
}