
int main()
{
 int n[200],i,j,k;
 double a=0,x[200]={0},s=0,w=0,sum[200]={0};

 scanf("%d",&k);
 for(j=0;j<k;j++){
	 scanf("%d\n",&n[j]);
	 s=0;
	 w=0;
	 a=0;
	 
	 for(i=0;i<n[j];i++){
		 scanf("%lf",&x[i]);
	     s=s+x[i];
		 
	 }
         a=s/n[j];
	 for(i=0;i<n[j];i++){
		w=w+((x[i]-a)*(x[i]-a));
		 sum[j]=sqrt(w/n[j]);
	 }
 }
 for(j=0;j<k;j++){

	 printf("%.5lf\n",sum[j]);
 }

	return 0;
}