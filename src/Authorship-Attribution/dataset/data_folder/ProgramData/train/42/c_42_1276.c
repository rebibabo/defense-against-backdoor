/*
 * ????601.cpp
 * ??????
 * ?????2012-10-28
 * ????????????
 */
int main(){
	int n, k, i;
	cin >> n;
	const int N = n;
	int a[N]; //?????????
	for(i = 0; i < N; i ++){
		cin >> a[i];
	} //????
	cin >> k; //??????
	n = 0; //????n???????????????
	for(i = 0; i < N; i ++){
		if(a[i] != k){
			if(n != 0)
				cout << " " << a[i];
			else
				cout << a[i];
			n ++;
		}
	} //???????
	return 0; //????
}