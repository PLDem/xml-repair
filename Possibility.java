
public class Possibility {

	public TagStack stack;
	public MyQueue<Tag> tags;
	
	public Possibility(){
		stack = new TagStack();
		tags = new MyQueue<Tag>();
	}
	
	public Possibility(Possibility original){
		
		stack = new TagStack();
		tags = new MyQueue<Tag>();
		
		Node<Tag> curr = original.tags.head;
		for(int i = 0; i < original.tags.size(); i++){
			tags.enqueue(curr.data);
			curr = curr.next;
		
		}
		
		for(int i = 0; i < original.stack.size(); i++)
			stack.push(original.stack.getStack()[i]);
	}
	
}
