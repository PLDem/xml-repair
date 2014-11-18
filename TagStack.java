public class TagStack implements Stack<Tag> {

	private int size;
	private Tag[] theStack;
	
	public TagStack(){
		theStack = new Tag[1];
		size = 0;
		
	}
	
	public int size() {
		
		return size;
	}
	
	public void push(Tag element) {
		
		if(size == theStack.length){
			Tag[] temp = new Tag[size*2];
		
			for(int i = 0; i < size; i++)
				temp[i] = theStack[i];	
			
			theStack = temp;
		}
		
		theStack[size] = element;
		size++;
		
	}
	
	public Tag pop() {
			if(size == 0){
				return null;		
			}
			
			Tag data = theStack[size-1];
			theStack[size-1] = null;
			size--;	
			return data;
			
	}
		

	public Tag peek() {
		if(size == 0)
			return null;
		return theStack[size-1];
	}

public Tag[] getStack(){
	
	return theStack;
}

}
