//+= /= *= %= ++ --; for; switch test
int main(){
	int a;
	int b;
	int i;
	for (i = 0; i <= 6; ++i){
		a = 10;
		b = 6;
		switch (i) {
			case 0:
				(a += b);
			case 1:
				(a -= b);
			case 2:
				(a *= b);
			case 3:
				(a /= b);
			case 4:
				(a %= b);
			case 5:
				(++a);	
			case 6:
				(--a);		
		}
		print a;
	}
}
