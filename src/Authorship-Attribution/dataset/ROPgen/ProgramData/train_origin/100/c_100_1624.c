int main()
{ 
   char str[303]; //??????
   int bigNum[26]={0}; //???????????0?bigNum[0]??A?bigNum[1]??'B'???
   int smallNum[26]={0};//???????????0?smallNum[0]??a?smalNum[1]??'b'???
   int i,n,tag=1; //tag?????????????????????????????tag???0
   scanf("%s",str); 
   for(i=0;str[i]!='\0';i++)  //??????????
   {
	   if(str[i]>='a' && str[i]<='z')
		   smallNum[str[i]-'a']++;

	   if(str[i]>='A' && str[i]<='Z')
		   bigNum[str[i]-'A']++;
   }

   for(i=0;i<26;i++)
   {
	   if(bigNum[i]>0)
	   {
		   printf("%c=%d\n",'A'+i,bigNum[i]);
		   tag=0;
	   }
   }
   for(i=0;i<26;i++)
   {
	   if(smallNum[i]>0)
	   {
		   printf("%c=%d\n",'a'+i,smallNum[i]);
		   tag=0;
	   }
   }
   if(tag==1) printf("No");

     return 0;
}                  
