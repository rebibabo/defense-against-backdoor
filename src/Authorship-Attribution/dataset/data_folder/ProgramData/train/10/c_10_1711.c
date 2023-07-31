
int cal(int *p,int N,int M){//N&#206;a&#191;a&#188;&#189;&#207;?&#196;y?&#196;&#206;&#187;&#214;&#195;?&#172;M&#206;a?&#207;&#194;?&#196;y?&#196;&#184;&#246;y?&#172;N+M=?&#196;y&#184;&#246;y 
	int i,max,sub;
	max=0;
	if(M==0){
		return 1;
	}
	for(i=N+1;i<=N+M;i++){
		if(*(p+N)>=*(p+i)){
			sub=cal(p,i,N+M-i);
			if(sub>=max){
				max=sub;
			}
		}
	} 
	return max+1;
}

int main()
{
	int k,i;
	scanf("%d\n",&k);
	int height[26];
	height[0]=10000000; 
	scanf("%d",&height[1]);
	for(i=1;i<=k-1;i++){
		scanf(" %d",&height[i+1]);
	}
	
	printf("%d",cal(height,0,k+1)-2);
	
	return 0;
 } 
 
