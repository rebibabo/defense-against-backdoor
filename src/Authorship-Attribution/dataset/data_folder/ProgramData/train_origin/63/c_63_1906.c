/*???int a[4][3]???int b[3][5]??????????????????c=a*b. ????? 
c00 c01 c02 c03 c04 
c10 c11 c12 c13 c14 
c20 c21 c22 c23 c24 
c30 c31 c32 c33 c34 

?????????: 
????A[m][q]*B[q][n], 
????????C[m][n]?????C?????? 
C[i][j] = A[i][0]*B[0][j] + A[i][1]*B[1][j] + ..... + A[i][q]*B[q][j] 

?????????????4*3?3*5?????????????
????
????????????x1?y1?1<=x1<=100, 1<=y1<=100?? 
????x1?????y1???????????? 
???????????????x2?y2?1<=x2<=100?1<=y2<=100?????x2?y1???? 
????x2?????y2???????????? 
?????????int??????? 

??????? 
x1 y1 
a00 a01 a02 
a10 a11 a12 
a20 a21 a22 
a30 a31 a32 
x2 y2 
b00 b01 b02 b03 b04 
b10 b11 b12 b13 b14 
b20 b21 b22 b23 b24
????
?????x1???????????y2??????????c?????????? 

??????? 
c00 c01 c02 c03 c04 
c10 c11 c12 c13 c14 
c20 c21 c22 c23 c24 
c30 c31 c32 c33 c34*/
int main()
{
    int x1,y1,x2,y2,i,j,t;
    cin>>x1>>y1;
    int a[x1][y1];
    for(i=0;i<=x1-1;i++)
    {
         for(j=0;j<=y1-1;j++)
         cin>>a[i][j];
         }
    cin>>x2>>y2;
    int b[x2][y2];
    for(i=0;i<=x2-1;i++)
    {
         for(j=0;j<=y2-1;j++)
         cin>>b[i][j];
         }
    int c[x1][y2];
     for(i=0;i<=x1-1;i++)
    { for(j=0;j<=y2-1;j++)
    c[i][j]=0;} 
    for(i=0;i<=x1-1;i++)
    { for(j=0;j<=y2-1;j++)
         {for(t=0;t<=y1-1;t++)
            c[i][j]=a[i][t]*b[t][j]+c[i][j];
            }}
    for(i=0;i<=x1-1;i++)
    { for(j=0;j<=y2-1;j++)
    {if(j==y2-1)
    cout<<c[i][j]<<endl;
    else
    cout<<c[i][j]<<" ";
}}
return 0;
}
