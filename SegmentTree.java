
public class SegmentTree {
	private int a[],size=0;
	public SegmentTree(int ar[]) {
		size=ar.length;
		a=new int[4*size];
		make(ar,0,ar.length-1,0);
	}
	private int make(int ar[],int i,int j,int in) {
		if(i==j)
			return a[in]=ar[i];
		else {
			int m=(i+j)/2;
			return a[in]=make(ar,i,m,2*in+1)+make(ar,m+1,j,2*in+2);
		}
	}
	public int get(int i,int j) {
		return get(i,j,0,size-1,0);
	}
	private int get(int i,int j,int x,int y,int in) {
		if(j<x || i>y)
			return 0;
		if(x>=i && y<=j)
			return a[in];
		int m=(x+y)/2;
		return get(i,j,x,m,2*in+1) + get(i,j,m+1,y,2*in+2);
	}
	public void update(int in,int val) {
		update(0,size-1,0,in,val);
	}
	private int update(int i,int j,int in,int index,int val) {
		if(i>index || j<index)	return a[in];
		if(i==j && i==index)
			return a[in]=val;
		int m=(i+j)/2;
		return a[in]=update(i,m,2*in+1,index,val)+update(m+1,j,2*in+2,index,val);
	}
}
