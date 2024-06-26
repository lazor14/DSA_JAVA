
public class AVL<T extends Comparable<T>> {
	
	private class Node{
		T val;
		Node left,right;
		int height;
		Node(T val){
			this.val=val;
			this.height=0;
		}
		public String toString() {
			return val+"";
		}
	}
	private Node root;
	private int size=0;
	public void add(T val) {
		add(val,root);
	}
	private void add(T val,Node n) {
		if(n==null) {
			root=new Node(val);
			size++;
			return;
		}
		int diff=compare(val,n.val);
		if(diff==0){
			return;
		}
		else if(diff>0) {
			if(n.right==null) {
				n.right=new Node(val);
				size++;
			}
			else
				add(val,n.right);
		}
		else {
			if(n.left==null) {
				n.left=new Node(val);
				size++;
			}
			else
				add(val,n.left);
		}
		setHeight(n);
		balance(n);
	}
	
	public boolean contains(T val) {
		return contains(val,root);
	}
	private boolean contains(T val,Node n) {
		if(n==null)return false;
		int diff=compare(val, n.val);
		if(diff==0)
			return true;
		else if(diff<0)
			return contains(val,n.left);
		else 
			return contains(val,n.right);
	}
	
	public void remove(T val) {
		if(root==null)
			return;
		root=remove(val,root);
		setHeight(root);
		balance(root);
	}
	private Node remove(T val,Node n) {
		if(n==null)
			return null;
		if(compare(n.val,val)==0) {
			if(n.left==null)
				return n.right;
			else if(n.right==null)
				return n.left;
			else {
				swapWithLeft(n);
				n.left=remove(val,n.left);
			}
		}
		else if(compare(val, n.val)>0)
			n.right=remove(val,n.right);
		else
			n.left=remove(val,n.left);
		setHeight(n);
		balance(n);
		return n;
	}
	private void swapWithLeft(Node n) {
		Node temp=n.left;
		while(temp.right!=null)
			temp=temp.right;
		swap(n, temp);
	}
	
	private void balance(Node n) {
		if(n==null)	return;
		int diff=Math.abs(getHeight(n.left)-getHeight(n.right));
		if(diff<=1) {}
		else {
			if(getHeight(n.left)>getHeight(n.right))
				if(getHeight(n.left.left)>getHeight(n.left.right))
					balance(n,1);
				else
					balance(n,2);
			else
				if(getHeight(n.right.right)>getHeight(n.right.left))
					balance(n,3);
				else
					balance(n,4);
		}
	}
	private void balance(Node n,int type) {
		Node x,y,a,b,c,d;
		x=y=a=b=c=d=null;
		if(type==1) {//LL
			x=n.left;y=x.left;a=y.left;b=y.right;c=x.right;d=n.right;
			swap(n,x);
			x.left=c;
			x.right=d;
			n.left=y;
			n.right=x;
		}
		else if(type==2) {//LR
			x=n.left;y=x.right;a=x.left;b=y.left;c=y.right;d=n.right;
			swap(n,y);
			y.left=c;
			y.right=d;
			x.left=a;
			x.right=b;
			n.left=x;
			n.right=y;
		}
		else if(type==3) {//RR
			x=n.right;y=x.right;a=n.left;b=x.left;c=y.left;d=y.right;
			swap(n,x);
			x.left=a;
			x.right=b;
			n.left=x;
			n.right=y;
		}
		else if(type==4){//RL
			x=n.right;y=x.left;a=n.left;b=y.left;c=y.right;d=x.right;
			swap(n,y);
			y.left=a;
			y.right=b;
			x.left=c;
			x.right=d;
			n.left=y;
			n.right=x;
		}
		setHeight(x);setHeight(y);setHeight(n);
	}
	
	private void swap(Node a,Node b) {
		T temp=a.val;
		a.val=b.val;
		b.val=temp;
	}
	private int compare(T a,T b) {
		return a.compareTo(b);
	}
	private int getHeight(Node n) {
		if(n==null)
			return -1;
		else return n.height;
	}
	private void setHeight(Node n) {
		if(n==null)	return;
		n.height=1+Math.max(getHeight(n.left), getHeight(n.right));
	}
	
	private java.util.ArrayList<T> ar;
	public java.util.ArrayList<T> preOrder(){
		ar=new java.util.ArrayList<>();
		preorder(root);
		return ar;
	}
	private void preorder(Node root) {
		if(root==null)return;
		preorder(root.left);
		ar.add(root.val);
		preorder(root.right);
	}
	
	public int getRootHeigth() {
		return root.height;
	}
	
	@Override
	public String toString() {
		StringBuilder ans=new StringBuilder();
		preOrder();
		ans.append("[ ");
		for(int i=0;i<ar.size()-1;i++)
			ans.append(ar.get(i)+", ");
		ans.append(ar.get(ar.size()-1));
		ans.append("]");
		return ans.toString();
	}
}
