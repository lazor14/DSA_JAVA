public class PriorityQueue<T extends Comparable<T>> {
	private java.util.Comparator<T> comparator=null;
	private java.util.ArrayList<T> ar;
	public PriorityQueue() {
		ar=new java.util.ArrayList<>();
	}
	public PriorityQueue(java.util.Comparator<T> comparator) {
		this();
		this.comparator=comparator;
	}
	public PriorityQueue(T a[]) {
		this();
		buildheap(a);
	}
	public int size() {
		return ar.size();
	}
	public boolean isEmpty() {
		return ar.size()==0;
	}
	private void buildheap(T a[]) {
		for(int i=0;i<a.length;i++)
			ar.add(a[i]);
		for(int n=ar.size(),i=(n-1)/2;i>=0;i--) {
			heapify1(i);
		}
	}
	private void swap(int i,int j) {
		T t=ar.get(i);
		ar.set(i, ar.get(j));
		ar.set(j, t);
	}
	private void heapify1(int i) {
		int in1=(i*2+1),in2=(i*2+2);
		if(in1>=ar.size())return;
		if(in2>=ar.size()) {
			if(compare(ar.get(in1),ar.get(i))>0) { 
				swap(in1,i);
				heapify1(in1);
			}
		}
		else {
			if(compare(ar.get(in1),ar.get(i))>=0 && compare(ar.get(in2),ar.get(i))>=0) 
				if(compare(ar.get(in1),ar.get(in2))>=0) {
					swap(in1,i);
					heapify1(in1);
				}
				else {
					swap(in2,i);
					heapify1(in2);
				}
			else if(compare(ar.get(in1),ar.get(i))>=0) {
				swap(in1,i);
				heapify1(in1);
			}
			else if(compare(ar.get(in2),ar.get(i))>=0) {
				swap(in2,i);
				heapify1(in2);
			}
		}
	}
	private void heapify2(int i) {
		int in=(i-1)/2;
		if(in>=0) {
			if(compare(ar.get(in),ar.get(i))<0) {
				swap(in,i);
				heapify2(in);
			}
		}
	}
	public void add(T n) {
		ar.add(n);
		heapify2(ar.size()-1);
	}
	public T remove() {
		swap(0,ar.size()-1);
		T k=ar.remove(ar.size()-1);
		heapify1(0);
		return k;
	}
	private int compare(T a,T b) {
		if(comparator!=null)
			return comparator.compare(b, a);
		return a.compareTo(b);
	}
}
