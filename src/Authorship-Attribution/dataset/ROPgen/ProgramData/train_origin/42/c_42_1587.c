/*
 * 5.1DeleteNumber3.cpp
 *
 *  Created on: 2012-11-8
 *      Author: ???
 *      ??????????????????????????????????????
                                    ?????????????????????????????

 *
 */
int main()
{

int n,k,i,j,a[150000],valid;                 //valid????????
   cin>>n;
  for(i=0;i<n;i++)                       //???????
         cin>>a[i];
  valid=n;                               //????????n
     for(i=0,cin>>k;i<valid;i++)         //???????
     {if(a[i]==k)                        //???????
        { for(j=i;j<valid;j++)           //??????
    	     a[j]=a[j+1];
    	      i=i-1;                     //??????????K
           valid--;}}                    //???????????????-1
     if(valid>=1)
     {for(i=0;i<valid;i++)                //???????????
      cout<<a[i]<<(i<valid-1?" ":"\n");}
     else if(valid==0)
         cout<<endl;


    return 0;
}

