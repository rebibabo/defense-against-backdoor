/*
 * ????0601.cpp
 * ??????
 * ?????2012-11-4
 * ??????????????????????
 */
int main(){
	int n, i, j, k, num = 0;
	cin >> n;
	const int N = n;
	int a[N]; //???????
	for(i = 0; i < n; ++ i) cin >> a[i]; //????
	cin >> k; //???????
	i = j = 0; //???i?j
	while(j < n){
		while(a[j] == k){
			++ j;
			++ num; //????????????k?
		} //????????????k?????
		a[i] = a[j]; //?????i+1???k????????????
		++ i;
		++ j;
	} //?????????k
	cout << a[0]; //???????
	for(j = 1; j < n - num; ++ j) cout << " " << a[j]; //?????????????????????
	return 0; //????
}