
int N;

struct stu
{
  int num;
  char au[50];
  struct stu *next;

};

struct stu *creat() //????
{
  struct stu *p1,*p2,*head;
  int i;
  
  for(i=0;i<N;i++)
  {
    p2=p1;
	p1=Z;
    scanf("%d%s",&p1->num,p1->au);
    p1->next=0;
	if(i==0) head=p1;
	else p2->next=p1;
  }

  return head;

  
}

void main()
{
  struct stu *head,*p;
  int i,n,j,max,max1;
  int c[26]={0};          //?????????????c[i]??ASC??i+A????????
  int d[26][1000]={0};    //??????????????d[i]??ASC??i+A???????
  int e[26]={0};          //??1???e[],????????d[]???????????????????
    

  scanf("%d",&N);
  head=creat();

  for(p=head;p!=0;p=p->next)
  {
      n=strlen(p->au);
	  for(i=0,j=0;i<n;i++)
	  {
	    c[p->au[i]-'A']++;
		d[p->au[i]-'A'][e[p->au[i]-'A']++]=p->num;  //??2??e[]?????j???????j????????0
	        
	  }

  }

  for(i=0,max=0;i<26;i++)
  {
	  if(max<c[i]) 
	  {
		  max1=i;
		  max=c[i];   //??3?????????????max????????????0
	  }
  }

  printf("%c\n",max1+'A');   //??3??????
  printf("%d\n",c[max1]);    //??4???????????
  i=0;
  while(d[max1][i]!=0)
    printf("%d\n",d[max1][i++]);



}