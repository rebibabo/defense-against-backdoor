int main()
{
    int n,k,t=0;
    cin>>n>>k;
    int a[n];
    for(int i=0;i<n;i++)cin>>a[i];
    for(int ia=0,ib=1;ia<n-1;ia++)
    {
        for(ib=ia+1;ib<n;ib++)
        {
            if(a[ia]+a[ib]==k)
            {
                cout<<"yes";
                t=1;                     //t????????t=1???????yes
                break;                   //???yes???????
            }
        }
        if(t==1)break;                 //?????????????????t???
    }
    if(t==0)cout<<"no";                  //??????????t?0????????yes????no
    return 0;
}