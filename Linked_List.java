public class LinkedList<T> {
	private class Node{
		T data;Node next,prev;
		public Node(T a) {	data=a;		}
	}
	
	private Node head,tail;
	private int size=0;
	int size() {return size;}
	void addLast(T a) {
		Node t=new Node(a);
		if(head==null) {head=t;tail=t;}
		else {
			tail.next=t;
			t.prev=tail;
			tail=t;
		}
		size++;
	}
	void addFirst(T a) {
		if(head==null)	addLast(a);
		else {
			Node t=new Node(a);
			t.next=head;
			head.prev=t;
			head=t;
			size++;
		}
	}
	void add(int index,T a) {
		if(index==size)	addLast(a);
		else if(index==0)	addFirst(a);
		else if(index>size || index<0)	return;
		else {
			Node t=new Node(a),temp=head;
			while(index--!=1) 
				temp=temp.next;
			t.next=temp.next;
			temp.next.prev=t;
			t.prev=temp;
			temp.next=t;
		}
	}
	T removeFirst() {
		if(head==null)	return null;
		else {
			T k=head.data;
			head=head.next;
			size--;
			if(head==null)	tail=null;
			else	head.prev=null;
			return k;
		}
	}
	T removeLast() {
		if(head==null)	return null;
		else if(head==tail) return removeFirst();
		else {
			T k=tail.data;
			tail=tail.prev;
			tail.next=null;
			size--;
			return k;
		}
	}
	T remove(int index) {
		if(index<0 || index>=size)	return null;
		else if(index==0)	return removeFirst();
		else if(index==size-1)	return removeLast();
		else {
			Node t=head;
			int kk=0;
			while(kk++!=index-1) 
				t=t.next;
			T k=t.next.data;
			t.next=t.next.next;
			t.next.prev=t;
			size--;
			return k;
		}
	}
	T get(int index) {
		if(index<0 || index>=size)	return null;
		else if(index==0)	return head.data;
		else if(index==size-1)	return tail.data;
		else {
			Node t=head;
			while(index--!=0)
				t=t.next;
			return t.data;
		}
		
	}
	void set(int index,T val) {
		if(index<0 || index>=size)	return;
		else if(index==0)	head.data=val;
		else if(index==size-1)	tail.data=val;
		else {
			Node t=head;
			while(index--!=0)
				t=t.next;
			t.data=val;;
		}
		
	}
	public String printFromFirst() {
		StringBuilder ans=new StringBuilder("");
		Node t=head;int s=size;
		while(s--!=0) {
			ans.append(t.data+" ");
			t=t.next;
		}
		return ans.toString();
	}
	public String printFromLast() {
		StringBuilder ans=new StringBuilder("");
		Node t=tail;int s=size;
		while(s--!=0) {
			ans.append(t.data+" ");
			t=t.prev;
		}
		return ans.toString();
	}
	
}
