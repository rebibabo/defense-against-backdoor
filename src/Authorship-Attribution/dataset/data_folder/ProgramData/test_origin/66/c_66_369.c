
int main()
{
	int a,b,c,d,e,f;
	scanf("%d %d %d",&a,&b,&c);
	if(b==1){e=c;}
	if(b==2){e=31+c;}
	if(b==3){e=31+28+c;}
	if(b==4){e=31+28+31+c;}
	if(b==5){e=31+28+31+30+c;}
	if(b==6){e=31+28+31+30+31+c;}
	if(b==7){e=31+28+31+30+31+30+c;}
	if(b==8){e=31+28+31+30+31+30+31+c;}
	if(b==9){e=31+28+31+30+31+30+31+31+c;}
	if(b==10){e=31+28+31+30+31+30+31+30+31+c;}
	if(b==11){e=31+28+31+30+31+30+31+30+31+31+c;}
	if(b==12){e=31+28+31+30+31+30+31+30+31+31+30+c;}
	if(b>2&&((a%4==0&&a%100!=0)||a%400==0)){
		d=(a-1)*365+(a-1)/4-(a-1)/100+(a-1)/400-a/3200-a/86400+e+1;
		}
	else{
		d=(a-1)*365+(a-1)/4-(a-1)/100+(a-1)/400-a/3200-a/86400+e;
	}
	f=d%7;
	if(f==1){printf("Mon.");}
	if(f==2){printf("Tue.");}
	if(f==3){printf("Wed.");}
	if(f==4){printf("Thu.");}
	if(f==5){printf("Fri.");}
	if(f==6){printf("Sat.");}
	if(f==0){printf("Sun.");}
	
 return 0;
}
