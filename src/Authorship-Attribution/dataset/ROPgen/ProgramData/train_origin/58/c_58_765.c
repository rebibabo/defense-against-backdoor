/*
 * h056_panduan_biaoshifu.cpp
 * name:?????C????????
 *  Created on: 2011-12-2
 *      Author: zhujile
 */
int main(){
	int n;
	char a[81],*p;   //?????a??
	cin>>n;          //??????
	getchar();       //?????
	while(n--)       //??n???
	{
		cin.getline(a,81);              //??????
		p=a;                            //??????
		if (*p!='_'&&(*p>'z'||*p<'a')&&(*p>'Z'||*p<'A')) //???????????
		{
			cout<<'0'<<endl;            //???????0
			continue;                   //????
		}
		for (p=a+1;*p!='\0';p++)        //??'\0'??
		{
			if (*p!='_'&&(*p>'z'||*p<'a')&&(*p>'9'||*p<'0')&&(*p>'Z'||*p<'A')) //??????????????
			{
				cout<<'0'<<endl;        //???????0
				break;                  //????
			}
		}
		if (*p=='\0')                   //?????????
			cout<<'1'<<endl;            //??1
	}
	return 0;
}