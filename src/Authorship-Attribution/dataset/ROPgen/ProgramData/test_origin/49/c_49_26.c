int main()
{
	char s[100],b[100]={0};
	int len,i,j,k,m;
    gets(s);
	len=strlen(s);
	for(i=0;i<len;i++)
		b[i+1]=s[i];
	for(i=2;i<=len;i++)//i?0?8?0?5?0?3?0?1?0?2?0?2?0?7?0?7?0?3?0?8?0?2?0?6?0?9?0?6?0?5?0?1?0?6?0?9?0?0?0?2?0?92?0?0?0?2?0?0?0?4?0?5?0?1?0?6?0?6?0?2?0?9len?0?0?0?2?0?0?0?4
		for(j=1;j<=len+1-i;j++)//j?0?8?0?5?0?6?0?9?0?6?0?2?0?9i?0?8?0?2?0?3?0?1?0?2?0?2?0?7?0?7?0?3?0?8?0?2?0?8?0?0?0?4?0?8?0?3?0?8?0?5b?0?0?0?4?0?8?0?2?0?2?0?3?0?0?0?1?0?5?0?1?0?7?0?71?0?8?0?5len+1-i?0?3?0?5
		{
		
	     for(k=j;k<=(2*j+i-1)/2;k++)//k?0?8?0?5?0?3?0?1?0?2?0?2?0?7?0?7?0?3?0?0?0?4?0?8?0?2?0?0?0?0?0?2?0?0?0?4?0?8?0?3?0?8?0?5?0?0?0?4?0?8?0?2?0?2?0?3?0?0?0?1
		 {
	     	if(b[k]==b[i+2*j-1-k])continue; //?0?3?0?1?0?2?0?2?0?0?0?7?0?3?0?8?0?2?0?0?0?1?0?4?0?6?0?5?0?1?0?9?0?8?0?6?0?4?0?2?0?3?0?0?0?1?0?8?0?2?0?0?0?4?0?3?0?1?0?1
			else break;
			
		 }
      	if(k==(i+2*j-1)/2+1)  //?0?9?0?7?0?4?0?9?0?8?0?6?0?4?0?2?0?3?0?0?0?1?0?0?0?4?0?9?0?4?0?3?0?1?0?1
			{
				for(m=j;m<j+i-1;m++)
		         printf("%c",b[m]);printf("%c\n",b[j+i-1]); //?0?8?0?1?0?6?0?2?0?0?0?7?0?3
			}
		
		}
		
		return 0;
}