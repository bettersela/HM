//?: test
void main(int n) {
	int a;
	for(a=0; a<n; ++a)
		a%2==0?print a/2:print (a+2)/2;
	println;
}
