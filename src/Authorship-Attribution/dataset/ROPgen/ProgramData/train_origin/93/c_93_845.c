int main()
{
    int x,a,b,c;
    scanf("%d",&x);
	a=x%3;
	b=x%5;
	c=x%7;
	if(a==0&&b==0&&c==0){
		printf("3 5 7");
	}else if(a==0&&b==0&&c!=0){
		printf("3 5");
	}else if(a==0&&b!=0&&c==0){
		printf("3 7");
	}else if(a!=0&&b==0&&c==0){
		printf("5 7");
	}else if(a!=0&&b!=0&&c==0){
		printf("7");
	}else if(a!=0&&b==0&&c!=0){
        printf("5");
    }else if(a==0&&b!=0&&c!=0){
		printf("3");
    }else{
		printf("n");
	}
	return 0;
}