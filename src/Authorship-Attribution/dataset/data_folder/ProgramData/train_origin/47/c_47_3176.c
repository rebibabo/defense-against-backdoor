/*??
?????????????????????????8,6,5,4,1?????1,4,5,6,8?
????
?????????????????n?1 < n < 100)?????n?????????????????
????
??????????????????????????????
????
5

8 6 5 4 1
????
1 4 5 6 8
*/
int main()
{
    int n=0;
    cin>>n;
    int *point=NULL;
    int a[100];
    point=a;
    for(int i=0;i<n;i++)
    {
        cin>>point[i];
    }
    for(point=a+n-1;point>=a;point--)
    {
        if(point==a)
        {
            cout<<*point;
        }
        else
        {
            cout<<*point<<" ";
        }
    }
    return 0;
    
}