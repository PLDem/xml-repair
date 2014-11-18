
public class MyQueue<T> implements Queue<T>{

	public Node<T> head;
	public Node<T> tail;
	public int size;
	
	public MyQueue(){
		head = null;
		tail = null;
		size = 0;
		
	}
	
	public int size() {
		return size;
		
	}

	public void enqueue(T element) {
		if(size == 0){
			head = new Node<T>(element);
			tail = head;
		}else{
			tail.next = new Node<T>(element);
			tail = tail.next;
		}
		
		tail.next = null;
		size++;
		
	}

	public T dequeue() {
		if(size == 0)
			return null;
		
		T data = head.data;
		
		if(size == 1){
			head = null;
		}else	
			head = head.next;
		
		size--;
		return data;			
		
	}

	public T peek() {
		if(size == 0)
			return null;
		
		return head.data;
	}
	

}
