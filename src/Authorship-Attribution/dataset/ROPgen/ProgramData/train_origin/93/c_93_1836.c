//********************************
//*???5.cpp   **
//*?????3?5?7????   **
//*?????? 1300012838 **
//*???2013.9.26  **
//********************************

int main()
{ 

    int a, num = 0;   //?? ??a?????? ????? 
    cin >> a;
    if (a % 3 == 0)
    {
        cout << "3";   //??3????3 
        num++;   //??????1 
    }
    if (a % 5 == 0)
    {
        if (num != 0)   //???????????? 
        {
            cout << " ";
        }
        cout << "5";   //??5????5
        num++;   //??????1
    }
    if (a % 7 == 0)
    {
        if (num != 0)   //????????????
        {
            cout << " ";
        }
        cout << "7";   //??7????7
        num++;   //??????1
    }
    if (num == 0)   //??????????n 
    {
        cout << "n";
    }
    
    return 0;                    
}  