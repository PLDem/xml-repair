
public class Node<T> {

	T data;
	Node<T> next;
	
	public Node(){
		data = null;
		next = null;
	}
	
	public Node(T element){
		data = element;
		next = null;
		
	}
	
}
