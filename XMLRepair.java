public class XMLRepair{

	public static void main(String[] args){
		
		XMLRepair main = new XMLRepair();
		MyQueue<Tag> theQueue;
		theQueue = main.fixTags();
		main.outputQueue(theQueue);

	}
	
	
	//Outputs the queue with nested indentation - trickier than it sounds
	public void outputQueue(MyQueue<Tag> output){
		
		Node<Tag> curr = output.head;
		System.out.println(output.size());
		TagStack tracker = new TagStack();
		
		//For each node in list
		while(curr != null){	
			
			//If its closed, pop the stack
			if(curr.next != null)
				if(curr.next.data.isClose())
					tracker.pop();
			
			//Then print the data
			System.out.print(curr.data);
			
			//If it's open, push it
			if(curr.data.isOpen()){tracker.push(curr.data);}
			
			
			//If the next one is an open tag with different name or a close tag, were gonna print some spaces
			if(curr.next != null && (!curr.next.data.getName().equals(curr.data.getName()) || curr.data.isClose())){
				System.out.println();
				//if(curr.next.data.isClose())
					//	tracker.pop();
				for(int i = 0; i < tracker.size(); i++)
					System.out.print("  ");
				
				
			}
			
			curr = curr.next;
		}
		
		System.out.println();	
	}

	//This function does most of the heavy lifting by finding all possibilities of valid xml and outputting the best one
	public MyQueue<Tag> fixTags(){
		TagScanner input = new TagScanner();
		
		//Create new possibility queue and add blank possibility
		MyQueue<Possibility> pQueue = new MyQueue<Possibility>();
		pQueue.enqueue(new Possibility());
		
		//Initialize the pointer
		Node<Possibility> curr;
		
		//For all inputs
		while(input.hasNext()){
			
			Tag next = input.next();
			curr = pQueue.head;
			int size = pQueue.size();
			
			if(next.isOpen()){
				//For each possibility
				for(int i = 0; i < size; i++){
					//Add the open tag to the possibility
					curr.data.tags.enqueue(next);
					//Clone the possibility, add closing tag
					Possibility copy = new Possibility(curr.data);
					copy.tags.enqueue(new Tag(next.getName(),false, true));
					
					//Push the data only onto the original stack
					curr.data.stack.push(next);
					
					//Enqueue the copy
					pQueue.enqueue(copy);
					curr = curr.next;
		
				}
									
			}else{
				for(int i = 0; i < size; i++){
					//Pop stack if the top matches with this close tag
					if(curr.data.stack.peek() != null && curr.data.stack.peek().getName().equals(next.getName()))
						curr.data.stack.pop();
					
					//Otherwise, queue an open tag version of the close tag
					else
						curr.data.tags.enqueue(new Tag(next.getName(), true, false));
					
					//Add this close tag to the possibility
					curr.data.tags.enqueue(next);
					
					curr = curr.next;
				}	
			}	
		}
		
		//Add corresponding close tags to all remaining open tags for all possibilities
		
		//Reset vars
		curr = pQueue.head;
		int size = pQueue.size();
		
		//For each possibility
		for(int i = 0; i < size; i++){
			//For each item on the stack, if any
			while(curr.data.stack.size() != 0){
				//Pop the tag, and add its corresponding closer onto the end
				curr.data.tags.enqueue(new Tag(curr.data.stack.pop().getName(), false, true));
			}
			
			curr = curr.next;
		}
		
		//Iterate through and find the smallest possibility
		curr = pQueue.head;
		MyQueue<Tag> smallest = curr.data.tags;

		while(curr != null){
			if(curr.next != null && smallest.size() > curr.next.data.tags.size()){ smallest = curr.next.data.tags; }	
			curr = curr.next;
		}

		return smallest;
	}

}

