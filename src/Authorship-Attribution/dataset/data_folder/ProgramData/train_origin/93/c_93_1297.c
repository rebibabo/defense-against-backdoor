/*
??? 5.cpp
?? ???1300017667
?? 3013.9.27
?? ?????????3,5,7??
*/
int main()//???
{
	int x,i;//????x???i
	cin >> x;//??x
	switch((x % 3 == 0) + (x % 5 == 0) + (x % 7 == 0))//??switch????x??3,5,7????????
	{
	case 3:cout << "3 5 7";break;//????????,??3,5,7
	case 2:if((x % 3 == 0 && x % 5 == 0))//????????,?????????
	{
		cout << "3 5";
	}
	if((x % 3 == 0 && x % 7 == 0))
	{
		cout << "3 7";
	}
	if((x % 7 == 0 && x % 5 == 0))
	{
		cout << "5 7";
	};break;
	case 1:for(i = 3; i <= 7; i = i + 2)//?????????,????????
	{
		if(x % i == 0)
		{
			cout << i;
		}
	};break;
	default:cout << "n";break;//????????????????,???n
	}
	return 0;//??
}
