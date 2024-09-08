//Ishaan Siwach
//I pledge my honor that I have abided by the Stevens Honor System
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Stack;

public class Treap<E extends Comparable<E>> {

    private static class Node<E>{
        public E data;
        public int priority;
        public Node<E> left;
        public Node<E> right;

        /**
         * 2 parameter constructor for Node that sets data and priority
         * @param data key of the node
         * @param priority priority of the node
         */
        public Node(E data, int priority){
            if(data==null){
                throw new NoSuchElementException();
            }
            this.data=data;
            this.priority=priority;
        }

        /**
         * performs a right rotation on the Node
         * @return the new parent Node
         */
        private Node<E> rotateRight(){
            Node<E> newRoot=this.left;
            this.left=newRoot.right;
            newRoot.right=this;
            return newRoot;
        }

        /**
         * performs a left rotation on the Node
         * @return the new parent Node
         */
        private Node<E> rotateLeft(){
            Node<E> newRoot=this.right;
            this.right=newRoot.left;
            newRoot.left=this;
            return newRoot;
        }

        /**
         * converts the Node to a string
         * @return Node as a string
         */
        public String toString(){
            return "(key="+data+", priority="+priority+")";
        }
    }

    private Random priorityGenerator;
    private Node<E> root;

    /**
     * no parameter constructor for Treap
     */
    public Treap(){
        root=null;
    }

    /**
     * 1 parameter constructor for Treap
     * @param seed seed of the Treap for random number generation
     */
    public Treap(long seed){
        root=null;
        priorityGenerator=new Random(seed);
    }

    /**
     * Add a Node with a random priority
     * @param key data of the Node
     * @return true or false indicating if Node was added or not
     */
    public boolean add(E key){
        return add(key, priorityGenerator.nextInt());
    }

    /**
     * Add a Node with a set priority
     * @param key data of the Node
     * @param priority priority of the Node
     * @return true or false indicating if Node was added or not
     */
    public boolean add(E key, int priority){
        Stack<Node<E>> path=new Stack<>();
        if(key==null){
            return false;
        }
        if(find(key)){
            return false;
        }
        if(root==null){
            root=new Node<E>(key, priority);
            return true;
        }
        Node<E> newNode=new Node<>(key, priority);
        Node<E> newRoot=root;
        while(newRoot!=null){
            if(newRoot.data.compareTo(key) < 0){
                path.push(newRoot);
                if(newRoot.right==null){
                    newRoot.right=newNode;
                    reheap(path, newNode);
                    return true;
                }else{
                    newRoot=newRoot.right;
                }
            } else{
                path.push(newRoot);
                if(newRoot.left==null){
                    newRoot.left=newNode;
                    reheap(path, newNode);
                    return true;
                }
                else{
                    newRoot=newRoot.left;
                }
            }
        }
        return true;
    }

    /**
     * helper function to restore heap properties after add
     * @param path the stack that shows the path to the root from newNode
     * @param newNode the Node that was just added
     */
    public void reheap(Stack<Node<E>> path, Node<E> newNode){
        while(!path.isEmpty()){
            Node<E> temp=path.pop();
             if(temp.priority<newNode.priority){
                 if(temp.data.compareTo(newNode.data)>0){
                     newNode=temp.rotateRight();
                 }else{
                     newNode=temp.rotateLeft();
                 }
                 if(!path.isEmpty()){
                     if(path.peek().right==null){
                         path.peek().left=newNode;
                     } else if(path.peek().right.equals(temp)){
                         path.peek().right=newNode;
                     } else{
                         path.peek().left=newNode;
                     }
                 } else{
                     root=newNode;
                 }
             } else{
                 break;
             }

        }
    }

    /**
     * Delete a node from the Treap
     * @param key data of the Node to be deleted
     * @return true or false indicating whether Node was deleted or not
     */
    public boolean delete(E key) {
        if (root==null) {
            return false;
        }
        else if (!find(key)) {
            return false;
        }
        else {
            root = deleteHelper(root, key);
            return true;
        }
    }

    /**
     * recursive helper function for delete
     * @param node starting Node in search for the Node to be deleted
     * @param key data of the Node to be deleted
     * @return the Node that was deleted
     */
    public Node<E> deleteHelper(Node<E> node, E key) {
        if (node==null) {
            return null;
        }
        if (node.data.compareTo(key) < 0) {
            node.right = deleteHelper(node.right, key);
        }
        else if (node.data.compareTo(key)>0) {
            node.left=deleteHelper(node.left, key);
        } else {
            if (node.right == null) {
                node = node.left;
            }
            else if (node.left==null) {
                node=node.right;
            } else {
                if (node.left.priority<node.right.priority) {
                    node = node.rotateLeft();
                    node.left = deleteHelper(node.left, key);
                } else {
                    node=node.rotateRight();
                    node.right=deleteHelper(node.right, key);
                }
            }
        }
        return node;
    }


    /**
     * Finds a Node with a specific key and in a Treap with Root root
     * @param root root of the Treap
     * @param key data of the Node to be found
     * @return true or false indicating whether the Node was found or not
     */
    public boolean find(Node<E> root, E key){
        if(root==null){
            return false;
        }
        if(key==null){
            return false;
        }
        if(root.data.equals(key)){
            return true;
        }
        Node<E> currentNode=root;
        while(currentNode!=null){
            if(currentNode.data.compareTo(key)>0){
                currentNode=currentNode.left;
            } else if(currentNode.data.compareTo(key)<0){
                currentNode=currentNode.right;
            } else if(currentNode.data.equals(key)){
                return true;
            }
        }
        return false;
    }

    /**
     * Finds a Node with key as the data in the Treap with this.root as its root
     * @param key
     * @return
     */
    public boolean find(E key){
        return find(this.root, key);
    }

    /**
     * returns spaces for padding for toString()
     * @param spaces number of spaces to be added
     * @return String of spaces
     */
    public String getSpaces(int spaces){
        String s="";
        for(int i=0; i<spaces; i++){
            s+="  ";
        }
        return s;
    }
    /**
     * helper function for toString()
     * @param node Node to be added to the string
     * @return String with the Node and its children
     */
    //I got part of this code from class
    public String preOrder(Node<E> node, int spaces){
        if(node==root){
            if(root==null){
                return "null"+"\n";
            }
            return node+preOrder(node.left, spaces)+preOrder(node.right, spaces);
        }
        if(node==null){
            spaces++;
            return "\n"+getSpaces(spaces)+"null";
        }
        spaces++;
        return "\n"+getSpaces(spaces) + node+getSpaces(spaces)+preOrder(node.left, spaces)+getSpaces(spaces)+preOrder(node.right, spaces);
    }

    /**
     * Prints the Treap
     * @return String of the Treap
     */
    public String toString(){
        String s=preOrder(root, 0);
        return s;
    }

}
