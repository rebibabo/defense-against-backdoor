//************************************************************
//*9.14????????????                    ***********
//*?????  1100012844                          ***********
//*???2011.9.16                                 ***********
//*?????????,?????100??,??!      ***********
//************************************************************
int main()
{
	int n,k;                   //??n,k?????
	double a,b,c;              //??a,b,c???????
	double d,x1,x2,x3;         //??d,x1,x2,x3???????
	cin >> n;                  //??????n
	for (k=0;k<n;k++)          //for?????????
	{
	    cin >> a >> b >> c;    //??????a,b,c
		d = b*b-4*a*c;         //??????????d
		if (d>=0)              //?????????????0
		    if (d>0)           //???????0,???????
			{   x1 = (-b+sqrt(d))/(2*a);    //??????x1??
		        x2 = (-b-sqrt(d))/(2*a);    //??????x2??
                cout << fixed << setprecision(5) << "x1=" << x1 << ";" << "x2=" << x2 << endl;    //??x1,x2??
			}
		    else               //??????0,???????
			{   x1 = (-b)/(2*a);    //??????????
		        cout << fixed << setprecision(5) << "x1=x2=" << x1 << endl;    //????
			}
		else                        //???????0,???????
		{       x3 = (-b)/(2*a);    //?????x3
			    x1 = sqrt(-d)/(2*a);    //?x1?????
				x2 = -sqrt(-d)/(2*a);    //?x2?????
                if (fabs(x3)<0.0000000001)    //???????
                    x3 = 0;
				cout << fixed << setprecision(5) << "x1=" << x3 << "+" << x1 << "i" << ";" 
					     << "x2=" << x3 << x2 << "i" << endl;
		}
    }
	return 0;
}

