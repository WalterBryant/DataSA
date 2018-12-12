package com.becky.data.list;

public class LinkedList<E> {

    Node<E> first;
    Node<E> last;
    int size;

    public LinkedList() {
    }

    public void add(E e) {
        linkLast(e);
    }

    /**
     * 在 index 的位置添加一元素
     * @param index
     * @param e
     */
    public void add (int index, E e) {
        if (index < 0 || index > size) {
            return;
        }
        if (index == size) {
            linkLast(e);
        } else {
            Node<E> target = node(index);
            Node<E> pre = target.prev;
            Node<E> newNode = new Node<E>(pre, e, target);
            //有坑
//            pre.next = newNode;
//            pre = newNode;
            if (pre == null) {
                first = newNode;
            } else {
                pre.next = newNode;
            }
            pre = newNode;
            size++;
        }
    }

    public E get(int index) {
        if (index < 0 || index > size) {
            return null;
        }
        return node(index).item;
    }

    /**
     * 删除 index 的元素
     * @param index
     */
    public void remove(int index) {
        Node<E> target = node(index);
        unlinkNode(target);
    }

    private void unlinkNode(Node<E> p) {
        //有坑
//        p.prev.next = p.next;
//        p.next.prev = p.prev;

        Node<E> pre = p.prev;
        Node<E> next = p.next;

        //等价与删除第一个节点
        if (pre == null) {
            first = p.next;
        } else {
            pre.next = p.next;
        }

        //等价与删除尾节点
        if (next == null) {
            last = p.prev;
        } else {
            next.prev = p.prev;
        }
        size--;
    }

    private Node<E> node(int index) {
        if (index < (size >> 1)) {//index 处于前半部分
            Node<E> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        } else {//index 处于后半部分
            Node<E> node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
            return node;
        }
    }

    private void linkLast(E e) {
        Node<E> newNode = new Node<>(last, e, null);
        Node<E> l = last;

        last = newNode;

        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}
