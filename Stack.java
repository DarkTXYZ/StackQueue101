public class Stack implements List{
    // Implement Stack using Linked List without tail
    Node head;
    
    // push() จะทำการนำ Node ต่อหน้า List (pushFront())
    public void push(Node node){
        if (head == null){
            // ถ้า List ว่าง ให้ Node ใหม่ เป็น head
            head = node;
        }else{
            // ถ้าไม่ ให้ทำการ pushFront()
            node.next = head;
            head = node;
        }
    }
    
    // pop() จะทำการนำ Node ด้านหน้าออก (popFront())
    public void pop(){
        if(head == null){
            //ถ้า List ว่าง ให้ขึ้น Error
            System.out.println("Error: Stack Underflow");
        }
        else if(head.next == null){
            //ถ้า List มี Node ตัวเดียว ให้ head ชี้ null เลย
            head = null;
        }
        else{
            //ถ้า List มี Node หลายตัว ให้ทำการ popFront()
            Node del = head;
            head = head.next;
            del.next = null;
        }
    }
    
    //top() จะ return หัวแถว (topFront())
    public Node top(){
        return head;
    }
}
