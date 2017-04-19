
public class MyLinkedList {
	private Node head;
	private int size;
	
	public class Node{
		Object data;
		Node next;
		public Node (Object data){
			this.data = data;
			this.next = null;
		}
		public Node(Object data,Node next){
			this.data = data;
			this.next = next;
		}
		public String toString(){
			String stringview = "";
			stringview+="[";
			stringview+=data.toString();
			stringview+="]";
			return stringview;
			
		}
}
	MyLinkedList(){
		size = 0;
	}
	MyLinkedList(Object headData){
		head = new Node(headData);
		size = 1;
	}
	public Node getHead(){
		return this.head;
	}
	
	public void setHead(Node head){
		this.head = head;
	}
	public int getSize(){
		return size;
	}
	public void addFirst(Object data){
		Node e  = new Node(data);
		e.next = head;
		head = e;
		size++;
		
	}
	public void addLast(Object data){
		if(head==null)addFirst(data);
		else{
			
			Node x = head;
			while(x.next!=null){
				x = x.next;
				
			}
			x.next = new Node(data);
			size++;
		}
		
	}

	}
	
	

