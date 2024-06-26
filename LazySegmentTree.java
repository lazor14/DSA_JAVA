
public class LazySegmentTree {
	private int a[][],size=0;//{ {value,update},{},... }
	public LazySegmentTree(int ar[]) {
		size=ar.length;
		a=new int[4*size][2];
		make(ar,0,ar.length-1,0);
	}
	private int make(int ar[],int i,int j,int in) {
		if(i==j)
			return a[in][0]=ar[i];
		else {
			int m=(i+j)/2;
			return a[in][0]=make(ar,i,m,2*in+1)+make(ar,m+1,j,2*in+2);
		}
	}
	
	//sum of values from index i to j
	public int get(int i,int j) {
		return get(i,j,0,size-1,0);
	}
	private int get(int i,int j,int x,int y,int in) {
		updateNode(in,x,y);
		if(j<x || i>y)
			return 0;
		if(x>=i && y<=j)
			return a[in][0];
		int m=(x+y)/2;
		return get(i,j,x,m,2*in+1) + get(i,j,m+1,y,2*in+2);
	}
	
	//updates a node if there is any update remaining to do
	private void updateNode(int in,int x,int y) {
		if(in>=a.length)	return;
		if(x==y) 
			a[in][0]+=a[in][1];
		else {
			a[in][0]+=(y-x+1)*a[in][1];
			childUpdate(in, a[in][1]);
		}
		a[in][1]=0;
	}
	
	//increase value of all index from i to j by value
	public void add(int i,int j,int value) {
		add(i,j,0,size-1,0,value);
	}
	private void add(int i,int j,int x,int y,int in,int val) {
		updateNode(in,x,y);
		if(j<x || i>y)
			return;
		if(x>=i && y<=j) {
			a[in][0]+=(y-x+1)*val;
			childUpdate(in,val);
		}
		else {
			int m=(x+y)/2;
			add(i,j,x,m,2*in+1,val);add(i,j,m+1,y,2*in+2,val);
			a[in][0]=a[2*in+1][0]+a[2*in+2][0];
		}
	}
	
	private void childUpdate(int in,int val) {
		int l=2*in+1,r=2*in+2;
		if(l>=a.length) return;
		else if(r>=a.length) {
			a[l][1]+=val;
		}
		else {
			a[l][1]+=val;
			a[r][1]+=val;
		}
	}
}
