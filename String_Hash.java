
public class String_hash {
	int p=53,m=(int)(1e9+9);
	int pow[];
	public String_hash() {
		pow=new int[(int)(1e5)];
		pow[0]=1;
		pow[1]=p;
		for(int i=2;i<pow.length;i++) {
			pow[i]=(int)((1L*pow[i-1]*p)%m);
		}
	}
	public String_hash(int length) {
		pow=new int[length];
		pow[0]=1;
		if(length==1)	return;
		pow[1]=p;
		for(int i=2;i<pow.length;i++) {
			pow[i]=(int)((1L*pow[i-1]*p)%m);
		}
	}
	int hash(String s) {
		long sum=0;
		for(int i=0,n=s.length();i<n;i++)
			sum=((sum+(s.charAt(i)-'a'+1)*pow[i])%m+m)%m;
		return (int)sum%m;
	}
}
