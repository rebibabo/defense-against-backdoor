//****************************************
//*?????                       
//*?????? 1100012789      
//*???2011.10.19                       
//****************************************

int main()
{
	int a[16], i = -1, j, number = 0; //???????number??????
	do //?????n???
	{
		for (i = 0; i < 16; i++) //????????
		{
			a[i] = 0;
		}
		i = -1;
		do //??????????0?-1
		{
			i++;
			cin >> a[i];
		} while (a[i] !=0 && a[i] != -1);
		number = 0; //?????
		for (i = 0; a[i] != 0 && a[i] != -1; i++) //???????????
		{
			for (j = 0; a[j] != 0 && a[i] != -1; j++) //??????????????
			{
				if (a[j] == a[i] * 2) //???????++
				{
					number++;
				}
			}
		}
		if (a[0] != -1) //???????????????
		{
			cout << number <<endl;
		}
	} while (a[0] != -1);
    return 0;
}