//both path compression and union by size
public class DSU {
	private int parent[],size[];
	public DSU(int n) {
		parent=new int[n+1];
		size=new int[n+1];
		for(int i=0;i<=n;i++) {
			parent[i]=-1;
			size[i]=0;
		}
			
	}
	public void makeSet(int a) {
		if(parent[a]!=-1) 
			throw new RuntimeException(a+" is already present in a set.");
		parent[a]=a;
		size[a]=1;
	}
	public boolean isSet(int a) {
		return parent[a]!=-1;
	}
	//path compression in else part as here we assign the parent to the root of
	//those set which are not assigned yet while passing through that element
	public int findroot(int a) {
		if(parent[a]==a)
			return a;
		else	
			return parent[a]=findroot(parent[a]);
	}
	//union by size
	public boolean unioun(int a,int b) {
		int ra=findroot(a),rb=findroot(b);
		if(ra==rb)	return false; // returns false if both are already in the same set else true;
		if(size[ra]>=size[rb]) {
			parent[rb]=ra;
			size[rb]+=size[ra];
		}
		else {
			parent[ra]=rb;
			size[ra]+=size[rb];
		}
		return true;
	}
}
