package io.teknek.collections;

//HEAD ptr -> Node [ data, next]
class Node {
    int data;
    Node next;
    public Node(int data, Node next){
        this.data = data;
        this.next = next;
    }
}
public class LinkedList {
    Node head;
    public LinkedList(){

    }   //5

    //HEAD -> [5, next=null]

    public void addToFront(int toAdd){
        if (head == null){
            head = new Node(toAdd, null);
        } else {
            Node newNode = new Node(toAdd, head);
            head = newNode;
        }
    }


    public int size(){
        Node toPrint = head;
        int counter=0;
        if (toPrint == null){
            return counter;
        }
        do {
            //System.out.println(toPrint.data);
            counter++;
            //counter = counter+1;
            toPrint = toPrint.next;
        } while (toPrint != null);
        return counter;
    }



    public void printIt(){
        Node toPrint = head;
        if (toPrint == null){
            System.out.println("it is empty");
        }
        do {
            System.out.println(toPrint.data);
            toPrint = toPrint.next;
        } while (toPrint != null);

    }

    public boolean search(int lookingFor){
        Node tosearch =head;
        if (tosearch==null){
            return false;
        }
        do {
            if (tosearch.data == lookingFor) {
                return true;
            }
            tosearch= tosearch.next;
        } while(tosearch != null);
        return false;
    }
}
