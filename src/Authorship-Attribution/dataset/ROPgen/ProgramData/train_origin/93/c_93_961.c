//********************************
//*??3,5,7????   **
//*?????? 1200012784 **
//*???2012.9.28  **
//********************************

int main()
{
	int num;         //??????num??????
	cin >> num;      //??num
	if (num % 3 == 0)//??3????num
	{
		cout << 3;   //?????3
		if (!(num % 5) || !(num % 7)) cout << ' ';  //?num???5?7??????????
	}
	if (num % 5 == 0)//??5????num
	{
		cout << 5;   //?????5
		if (!(num % 7)) cout << ' ';  //?num???7???????????
	}
	if (num % 7 == 0) cout << 7;  //?7??num????7
	if (num % 3 && num % 5 && num % 7) cout << 'n';  //?3?5?7?????num????n

    return 0;
}