public class Stock {
    private List list;
    private int totalShares;

    public Stock(String costBasis) {
        switch (costBasis) {
            case "FIFO":
                list = new Queue();
                break;
            case "LIFO":
                list = new Stack();
                break;
            default:
                System.out.println("Invalid cost basis. Choose FIFO or LIFO");
                break;
        }
    }

    public void buy(int boughtShares, double boughtPrice) {
        // Complete this code
        // Something is missing here
        list.push(new Node(boughtShares, boughtPrice));
        totalShares += boughtShares;
    }

    public void sell(int soldShares, double soldPrice) {

        // Concept :
        // เราจะทำการขายหุ้น (pop()) จนกว่าจะครบตามจำนวน โดยระหว่างนี้ จะคำนวณ
        // realizedGain ไปด้วย
        // จากนั้น จึงคำนวณ unrealizedGain จากหุ้นที่เหลือใน List โดยการ pop() ออกมา

        // check ก่อน ว่า จำนวนหุ้นที่ต้องการขาย ไม่เกิน จำนวนหุ้นที่มี 
        if (soldShares <= totalShares) {
            double realizedGain = 0.0;
            double unrealizedGain = 0.0;

            // ถ้ายังขายหุ้นไม่หมดตามที่ต้องการ ให้ขายต่อไปเรื่อยๆ (จำนวนหุ้นที่ต้องขาย > 0)
            while (soldShares > 0) {
                // หุ้นชุดนี้
                Node shareNow = list.top();
                int no_share = shareNow.shares;
                double price = shareNow.price;

                if (soldShares >= no_share) {
                    // จำนวนหุ้นที่ต้องขาย > จำนวนหุ้นชุดนี้ ก็ให้ขายหุ้นชุดนี้ให้หมด
                    // คิด realizedGain ของหุ้นชุดนี้
                    realizedGain += no_share * (soldPrice - price);
                    // หักลบจำนวนหุ้นที่ต้องขาย
                    soldShares -= no_share;
                    // หุ้นชุดนี้จะเหลือ 0 หุ้น
                    no_share = 0;
                } else {
                    // จำนวนหุ้นที่ต้องขาย < จำนวนหุ้นชุดนี้ ก็ให้ขายหุ้นชุดนี้ตามจำนวนหุ้นที่ต้องขาย
                    realizedGain += soldShares * (soldPrice - price);
                    // หักลบจำนวนหุ้นชุดนี้
                    no_share -= soldShares;
                    // จำนวนหุ้นที่ต้องขายจะเหลือ 0 หุ้น
                    soldShares = 0;
                }

                // ถ้าหุ้นชุดนี้ เหลือ 0 หุ้น ให้ pop() ทิ้ง เพื่อดูหุ้นชุดต่อไป
                // ถ้าไม่ ก็เปลี่ยนค่าจำนวนของหุ้นชุดนี้ใหม่
                if (no_share == 0)
                    list.pop();
                else
                    shareNow.shares = no_share;
            }

            // ทำการคำนวณ unrealizedGain จากหุ้นใน List ที่เหลือ

             // // วิธีที่ 1
            // // การไล่ใน List จะทำการสร้าง List ชนิดเดียวกันขึ้นมาอีกตัว 
            // // มาเก็บ Node ที่ทุก pop() ไป

            // List temp;
            // // check ว่า List เป็นชนิดใด
            // if (list.getClass() == new Stack().getClass())
            //     temp = new Stack();
            // else
            //     temp = new Queue();

            // // ถ้า List ยังไม่ว่าง ให้คำนวณ unrealizedGain
            // // จากนั้นจึง pop() ทิ้ง    
             
            // while (list.top() != null) {
            //     Node shareNow = list.top();

            //     int no_share = shareNow.shares;
            //     double price = shareNow.price;
            //     temp.push(new Node(no_share, price));

            //     unrealizedGain += no_share * (soldPrice - price);
            //     list.pop();
            // }

            // // นำแต่ละหุ้นใน temp กลับเข้ามา List เดิม
            // // ด้วยการ push()
            // while (temp.top() != null) {
            //     Node shareNow = temp.top();
            //     int no_share = shareNow.shares;
            //     double price = shareNow.price;
            //     list.push(new Node(no_share, price));
            //     temp.pop();
            // }
            // หักลบจำนวนหุ้นทั้งหมดที่มี
            // totalShares -= soldShares;

            // วิธีที่ 2
            Node cur = list.top();
            while(cur != null){
                int no_share = cur.shares;
                double price = cur.price;
                unrealizedGain += (soldPrice - price) * no_share;
                cur = cur.next;
            }
            
            totalShares -= soldShares;
            System.out.println("Realized P/L = " + realizedGain + " Unrealized P/L = " + unrealizedGain);
        } else {
            System.out.println("Sell command rejected");
        }
    }

    public void showList() {
        Node currentNode = list.top();
        System.out.print("head -> ");
        while (currentNode != null) {
            System.out.print("[" + currentNode.shares + "@" + currentNode.price + "B] -> ");
            currentNode = currentNode.next;
        }
        System.out.println("tail");
    }
}