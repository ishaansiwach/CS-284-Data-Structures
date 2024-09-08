//Ishaan Siwach
//I pledge my honor that I have abided by the Stevens Honor System
import java.util.Iterator;

public class TaskList<E> {
    private ListQueue<E> all;
    private ListQueue<E> completed;
    private ListQueue<E> active;
    private int LOW_PRIORITY = Integer.MAX_VALUE;
    private int HIGH_PRIORITY=1;

    /**
     * No-parameter constructor for TaskList
     */
    public TaskList(){
        all=new ListQueue<E>();
        completed=new ListQueue<E>();
        active=new ListQueue<E>();
    }

    /**
     * Creates a task and adds it to all and active ListQueues
     * @param item data of the Node
     * @return true indicating success, false indicating failure to add
     */
    public boolean createTask(E item){
        if(item==null){
            return false;
        }
        return (all.addRear(item) && active.addRear(item));
    }

    /**
     * Creates a task with a priority and adds it to active and all ListQueues
     * @param item data of the Node
     * @param priority priority of the Node
     * @return true indicating success, false indicating failure to add
     */
    public boolean createTask(E item, int priority){
        if(item==null){
            return false;
        }else if(priority==LOW_PRIORITY){
            createTask(item);
            return true;
        }
        return (all.offer(item,priority) && active.offer(item, priority));
    }

    /**
     * Setter for all
     * @param a new all
     */
    public void setAll(ListQueue<E> a){
        all=a;
    }

    /**
     * setter for completed
     * @param c new completed
     */
    public void setCompleted(ListQueue<E> c){
        completed=c;
    }

    /**
     * setter for active
     * @param a new active
     */
    public void setActive(ListQueue<E> a){
        active=a;
    }

    /**
     * getter for all
     * @return all
     */
    public ListQueue<E> getAll(){
        return all;
    }

    /**
     * getter for completed
     * @return completed
     */
    public ListQueue<E> getCompleted(){
        return completed;
    }

    /**
     * getter for active
     * @return active
     */
    public ListQueue<E> getActive(){
        return active;
    }

    /**
     * returns top three pending tasks
     * @return top three tasks in the active ListQueue
     */
    public String getTopThreeTasks(){
        Iterator<E> iterator = all.iterator();
        int counter=1;
        String s = "";
        while(counter<4 && iterator.hasNext()){
            s += counter+". "+iterator.next() + " ";
            counter++;
        }
        return s;
    }

    /**
     * prints out active tasks
     */
    public void showActiveTasks(){
        printTasks(active);
    }

    /**
     * prints out all tasks
     */
    public void showAllTasks(){
        printTasks(all);
    }

    /**
     * prints out completed tasks
     */
    public void showCompletedTasks(){
        printTasks(completed);
    }

    /**
     * removes highest priority task from active and adds it to completed
     * @return true indicating success, false indicating failure to add
     */
    public boolean crossOffMostUrgent(){
        if(active.getSize()==0){
            System.out.println("Unsuccessful operation! Please try again!");
            return false;
        } else{
            E temp = active.getFront().getData();
            boolean b = active.remove(active.getFront());
            completed.addRear(temp);
            return b;
        }
    }

    /**
     * removes task at a specific task number and adds it to completed
     * @param taskNumber task number to be removed
     * @return true indicating success, false indicating failure to add
     */
    public boolean crossOffTask(int taskNumber){
        if(taskNumber>active.getSize() || taskNumber<1){
            System.out.println("Unsuccessful operation! Please try again!");
            return false;
        } else{
            Iterator<E> iterator = active.iterator();
            int counter=1;
            while(iterator.hasNext()){
                E nodeData=iterator.next();
                if(counter==taskNumber){
                    boolean b = active.remove(new ListQueue.Node<E>(nodeData));
                    completed.addRear(nodeData);
                    return b;
                }
                counter++;
            }
        }
        return false;
    }

    /**
     * prints out ListQueue passed into the method
     * @param queueName ListQueue to be printed
     */
    private void printTasks(ListQueue<E> queueName) {
        Iterator<E> iterator = queueName.iterator();
        // hasNext() -> Boolean if there's a next element
        // next() -> ret the data of next element
        int counter=1;
        while(iterator.hasNext()){
            System.out.println(counter + ". " + iterator.next());
            counter++;
        }
        //things we need to do:
        //create a counter
        //loop through while hasNext
        // -- print counter + next()
        // -- iterate counter


    }
}
