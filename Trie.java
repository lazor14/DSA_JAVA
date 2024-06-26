
public class Trie<T> {
	private static class Node<T>{
		Node<T> a[];
		T val;
		@SuppressWarnings("unchecked")
		Node(){
			a=new Node[256];
		}
	}
	Node<T> root;
	Trie(){
		root=new Node<>();
	}
	void add(String s,T val) {
		Node<T> temp=root;
		for(int i=0;i<s.length();i++) {
			if(temp.a[s.charAt(i)]==null)
				temp.a[s.charAt(i)]=new Node<>();
			temp=temp.a[s.charAt(i)];
		}
		temp.val=val;
	}
	T get(String s) {
		Node<T> temp=root;
		for(int i=0;i<s.length();i++) {
			if(temp.a[s.charAt(i)]==null)
				return null;
			temp=temp.a[s.charAt(i)];
		}
		return temp.val;
	}
	boolean contain(String s) {
		Node<T> temp=root;
		for(int i=0;i<s.length();i++) {
			if(temp.a[s.charAt(i)]==null)
				return false;
			temp=temp.a[s.charAt(i)];
		}
		return temp.val!=null;
	}

}
