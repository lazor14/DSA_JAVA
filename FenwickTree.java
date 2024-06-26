
public class FenwickTree {
	private int BIT[];
	public FenwickTree(int a[]){
		int n=a.length;
		BIT=new int[n+1];
		for(int i=0;i<n;i++) {
			int j=i+1;
			while(j<=a.length) {
				BIT[j]+=a[i];
				j=(j&((~j)+1))+j; //i) 2s complement, ii) And with original, iii) Add with original
			}
		}
	}
	public int sum(int i,int j) {
		return sum(j+1)-sum(i);
	}
	private int sum(int i) {
		if(i<=0)	return 0;
		return BIT[i]+sum(i&(i-1));
	}
	public void update(int in,int val) {
		int k=sum(in,in);
		int j=in+1;
		while(j<BIT.length) {
			BIT[j]+=val-k;
			j=(j&((~j)+1))+j;
		}
	}
}
