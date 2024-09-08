//Ishaan Siwach
//I pledge my honor that I have abided by the Stevens Honor System
import HW3Updated.ListQueueTest;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListQueue<E> {

    private Node<E> front;
    private int size;

    /**
     * No-parameter constructor
     */
    public ListQueue(){
        front=null;
        size=0;
    }

    /**
     * One-parameter constructor
     * @param first Node that will be set as the head
     */
    public ListQueue(Node<E> first){
        front=first;
        size=1;
    }

    /**
     * Setter for size
     * @param s new size of ListQueue
     */
    public void setSize(int s){
        size=s;
    }

    /**
     * Getter for size
     * @return size
     */
    public int getSize() {
        return size;
    }

    /**
     * Setter for front
     * @param f new front node
     */
    public void setFront(Node<E> f){
        Node<E> temp=front;
        front=f;
        f.next=temp;
        size++;
    }

    /**
     * Getter for front
     * @return front node
     */
    public Node<E> getFront(){
        return front;
    }

    /**
     * Checks front node and returns its data
     * @return front node's data
     */
    public E peek(){
        if(front==null){
            return null;
        }
        return front.getData();
    }

    /**
     * Adds a Node to the ListQueue at a specific spot
     * @param item data of the Node
     * @param priority position of the Node
     * @return true indicating success, false indicating failure to add
     */
    public boolean offer(E item, int priority){
        if(item==null){
            throw new NullPointerException();
        }
        Node<E> n = new Node<E>(item, priority);
        Node<E> f = front;
        if(size==0){
            front=n;
            n.next=null;
            size++;
        } else if(priority==1){
            Node<E> temp=front;
            front=n;
            n.next=temp;
            size++;
        }else if(size==1){
            front.next=n;
            n.next=null;
            size++;
        }
        else{
            while(f.getNext()!=null){
                if(f.getNext().priority>=priority){
                    Node<E> temp=f.getNext();
                    f.next=n;
                    n.next=temp;
                }
                f=f.getNext();
            }
            size++;
        }
        return true;
    }

    /**
     * Adds a Node to the end of the ListQueue
     * @param item data of the New Node
     * @return true indicating success, false indicating failure to add
     */
    public boolean addRear(E item){
        if(item==null){
            throw new NullPointerException();
        } else if(front==null){
            front=new Node<E>(item);
            size++;
            return true;
        }
        Node<E> n = new Node<E>(item);
        Node<E> f = front;
        while(f!=null){
            if(f.getNext()==null){
                f.next=n;
                n.next=null;
            }
            f=f.getNext();
        }
        size+=1;
        return true;

    }

    /**
     * Removes the front Node
     * @return the data of the front Node
     */
    public E poll(){
        if(front==null){
            return null;
        }
        E data=front.getData();
        front=front.getNext();
        size-=1;
        return data;
    }

    /**
     * Removes a specific Node in the ListQueue
     * @param toBeRemoved the Node that has to be removed
     * @return true indicating success, false indicating failure to remove
     */
    public boolean remove(Node<E> toBeRemoved){
        if(front==null){
            throw new NullPointerException();
        } else if (toBeRemoved == null) {
            return false;
        }
        else if (size == 0) {
            throw new NullPointerException();
        } else if (front.data == toBeRemoved.getData()) {
            front=front.next;
            size--;
            return true;
        } else {
            Node<E> f = front;
            while (f.getNext() != null) {
                if (f.getNext().getData().equals(toBeRemoved.getData())) {
                    f.next = f.next.next;
                    size--;
                    return true;
                }
                f=f.next;
            }
            return false;
        }
    }

    /**
     * creates an instance of the Iter class
     * @return new instance of Iter
     */
    public Iterator<E> iterator(){
        return new Iter();
    }

    public static class Node<E>{
        private E data;
        private Node<E> next;
        private int priority;

        /**
         * One-parameter constructor for Node
         * @param dataItem data of the Node
         */
        public Node(E dataItem){
            data=dataItem;
            this.priority=Integer.MAX_VALUE;
        }

        /**
         * two-parameter constructor for the Node
         * @param dataItem data of the Node
         * @param priority priority of the Node
         */
        public Node(E dataItem, int priority){
            data=dataItem;
            this.priority=priority;
        }

        /**
         * three-parameter constructor for the Node
         * @param dataItem data of the Node
         * @param next the Node's next node
         * @param priority priority of the Node
         */
        public Node(E dataItem, Node<E> next, int priority){
            data=dataItem;
            this.next=next;
            this.priority=priority;
        }

        /**
         * getter for data
         * @return data of node
         */
        public E getData(){
            return this.data;
        }

        /**
         * getter for next node
         * @return next node
         */
        public Node<E> getNext(){
            return this.next;
        }

    }

    public class Iter implements Iterator<E>{
        private Node<E> next = front;

        /**
         * boolean stating if the next node exists
         * @return true if exists, false if doesn't exist
         */
        public boolean hasNext(){
            return next!=null;
        }

        /**
         * Sets next to the next node in ListQueue
         * @return the current next's data
         */
        public E next(){
            if(next.getData()==null){
                throw new NoSuchElementException();
            } else{
                E temp = next.getData();
                next=next.getNext();
                return temp;
            }
        }

        /**
         * Remove function
         */
        public void remove(){
            throw new UnsupportedOperationException();
        }
    }




}
