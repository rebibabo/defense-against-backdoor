//********************** 
//*?????????***
//*?????? 1300012728***
//*???2013.9.28******
//**********************
int main()
{
	int a, b, c, d;                  // ??abcd?????5??????
	int num;                         // ??????
	cin >> num;                     // ?????
	if (num == 10000)                   // ??????10000????00001
	{
		cout << "00001";
	}
	else if (num <10000 && num > 999)           // ???4??
	{
		d = num / 1000;                      // d???
		c = num / 100 - d * 10;                   // c???
		b = num / 10 - d * 100 -c * 10;           // b???
		a = num % 10;                // a???
		cout << a << b << c << d;                    //?????????
	}
	else if (num < 1000 && num >99)               //?????
	{
		c = num / 100;                      // c???
		b = num / 10 - c * 10;           // b???
		a = num % 10;                // a???
		cout <<a << b << c;             //????????
	}
	else if (num < 100 && num > 9)               //?????
	{
		b = num / 10;                             // b???
		a = num - b * 10;                // a???
		cout <<a << b;                      //????????
	}
	else
	{
		cout << num;                      //???????????? 
	}
	return 0;
}
