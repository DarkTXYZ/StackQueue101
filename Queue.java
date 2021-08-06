public class Queue implements List{
    Node head;
    Node tail;
    
    // push() จะทำการนำ Node ต่อท้าย List (pushBack())
    public void push(Node node){
        if (head == null){
            // ถ้า List ว่าง ให้ Node ใหม่ เป็น head และ tail
            head = node;
            tail = node;
        }else{
            // ถ้าไม่ ทำการ pushBack() และเปลี่ยน tail ใหม่
            tail.next = node;
            tail = node;
        }   
    }
    
    // pop() จะทำการนำ Node ด้านหน้าออก (popFront())
    public void pop(){
        if (head != null){
            if (head != tail){
                //ถ้า List มี Node หลายตัว ให้ทำการ popFront()
                Node del = head;
                head = head.next;
                del.next = null;
            }else{
                //ถ้า List มี Node ตัวเดียว ให้ head และ tail ชี้ null เลย
                head = tail = null;
            }
        }
        else{
            //ถ้า List ว่าง ให้ขึ้น Error
            System.out.println("Error: Queue Underflow");
        }
    }
    
    //top() จะ return หัวแถว (topFront())
    public Node top(){
        return head;
    }
   
}
