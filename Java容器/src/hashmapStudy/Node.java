package hashmapStudy;

public class Node {
    Object data;
    Node next = null;

    /**
     *
     * 不用构造出第一个完整节点也是可以连接的。
     *
     * @return
     */
    static Node head_build() {
        Node head = null;
        for (int i = 0; i < 10; i++) {
            Node node = new Node();
            node.data = i;
            node.next = head;
            head = node;
        }
        return head;
    }

    /**
     * 不构造出第一个节点可以吗？不可以，因为head指针能保存到第一个
     * @return
     */
    static Node rear_build() {
        Node tmp = null;
        Node head = null;
        for (int i = 0; i < 10; i++) {
            if (i == 0) {
                Node node = new Node();
                node.data = i;
                tmp = node;
                head = node;
            } else {
                Node node = new Node();
                node.data = i;
                tmp.next = node;
                tmp = node;
            }
/*          错误，tmp没有指向实例，里面的data、next不能使用，next不能指向。而tmp是可以用的
            {
                Node node = new Node();
                node.data = i;
                tmp.next = node;
                tmp = node;
                if(i==0){
                    head = tmp;
                }
            }*/
        }
        return head;
    }

    public static void main(String[] args) {

       Node node = rear_build();
//        Node node = head_build();
        while (node != null) {
            System.out.println(node.data);
            node = node.next;
        }
    }

    //结论：关键是一开始tmp没有指向，tmp.next不可用。但tmp可以指向或被指向，当指向tmp有指向时，tmp就可以用了。

}
