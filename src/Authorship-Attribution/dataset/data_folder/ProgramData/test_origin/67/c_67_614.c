/*
 * ????review(2).cpp
 * ??????
 * ????: 2010-12-22
 * ??????????????n??????????????????????????????????
 *       ?n???????????????????????????????????????????
 *       ?????????better??????????worse?????same?
 */

double former;//??????????????
void judge(double a,double b){
	double improve;//???????????????
	improve=b/a;//?????????
	if(improve-former>0.05)cout<<"better"<<endl;//?????????better
	else if(former-improve>0.05)cout<<"worse"<<endl;//?????????worse
	else cout<<"same"<<endl;//?????????same
}
int main(void){
	int n;//?????????????
	double Fa,Fb,Ia,Ib;//?????????
	cin>>n>>Fa>>Fb;//????????
	former=Fb/Fa;//??????????????former
	while (n>1){
		cin>>Ia>>Ib;//????????????
		judge(Ia,Ib);//????????
		n--;//n???????
	}
	return 0;//????
}