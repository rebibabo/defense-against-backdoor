
struct student //?????
{
	char id[10];
	char name[20];
	char gender;
	int age;
	char score[10];
	char address[20];
	struct student *next, *up;//next?????????,up???????????????????
};
int main()
{
		struct student *head, *tail, *p, *r,*q;
	p=r=head=(struct student *)malloc(len);//head???????
	p->up=NULL;//??????up????null??????????up??????????????
	do
	{
		scanf("%s",p->id);
		if(strcmp(p->id,"end")!=0)//???????end??????????
		{
			scanf("%s %c %d %s %s",p->name,&p->gender,&p->age,p->score,p->address);
			p->next=r=(struct student *)malloc(len);//??????next???????up???????
			r->up=p;
			p=r;
		}
		else
		{
			p->next=NULL;//??????
			tail=p->up;//?tail????????????????up???????????
			break;
		}
	}while(1);
	q=tail;
	do
	{
		printf("%s %s %c %d %s %s\n",q->id,q->name,q->gender,q->age,q->score,q->address);//?up?????
		q=q->up;
	}while(q!=NULL);
		return 0;
}