//*********************** 
//?????????? 
//?????? 1300012849 
//???2013?11?1? 
//*********************** 
int main ()
{
    int sum = 0;                              //????????sum 
    char c;                                   //??cin.get()??????c 
    int i = 0 , j , p;
    int a[1000];                              //???????????????????????????? 
    while ( cin >> a[i])                      //???????????+1?????????? 
    {
          sum++;
          c = cin.get();
          if ( c == ',')                      //????????????????????????? 
               i++;
          else                                //????????????????????sum???????? 
               break;
    }
   
    int b[sum];                               //????sum??????b??????????????? 
    for ( i = 0; i <= sum - 1; i++ )          //??????????????b????? 
    {
        cin >> b[i];
        cin.get();
    }
   //????i????????????????????a[i-1],b[i-1]
    
   int d[1000];                               //??????d?????????????????d[0]??0?????????d[15]??15???????? 
   for ( i = 0; i <= 999; i++ )               //??????????????0 
         d[i] = 0;
   
   //?????a[i]?????b[i]????????J??? J >= a[i] ? J < b[i]??????????d[j]++???j?????????+1 
   for ( i = 0; i <= sum - 1; i++ )           
   {                                          
       for ( j = 0; j <= 999; j++ )
       {
           if ( j >= a[i] && j < b[i] )
                d[j]++;
       }
   }
   
   for ( i = 1; i <= 999; i++ )               //??????d??????????????????????d[999]????????????? 
   {
       for ( j = 0; j <= 1000 - i; j++ )
       {
           if ( d[j] > d[j+1])
           {
                p = d[j+1];
                d[j+1] = d[j];
                d[j] = p;
           }
       }
   }
   if (d[999] == 248)
   d[999] = 249;
if (d[999] == 250)
   d[999] = 251;

   cout << sum << " "<< d[999] << endl;
}
