//******************************
// * 1.cpp
// *   ?7???????
// *  Created on: 2012-10-9
// * Author: ?? 1200012934
// *****************************
int main()
{
	int n = 0, i = 0, sum = 0;  //?????????????????
	do          //????????if???????n?????????
	{
		cin >> n;     //????
		if (n >=100 || n <=0)   //??n??????????????
		{
			cout << "wrong!again:" << endl;
			continue;
		}
		//n???????1?n????7??????????
		for (i = 1; i <= n; i++)
		{
		    if (i < 10 && i != 7)   //?i????????
		    	sum = sum + i*i;
		    else     //?i????????
		    {  if (i % 7 !=0 && i % 10 != 7 && i /10 != 7)
		    	   sum = sum + i*i;
		    }
		}
		break;   //????????
	}while(1);
		cout << sum;     //??????
	return 0;
}
