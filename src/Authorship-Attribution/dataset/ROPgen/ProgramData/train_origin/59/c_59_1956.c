/*
 * x.cpp
 *  Created on: 2013-12-21
 *      Author: de
 *??????????????????????n*n????????????????????????????
 ????????????????????????????????????????????????????????
 ????m????????
????
???????n?n???100????n*n??????
????n????n????.???????????????#????????@????????????????
???????????m?m???100.
????
???m????????
????
5
....#
.#.@.
.#@..
.....
4
????
16*/

int sum=0,n,t=65;
char room[101][101];
void F(int x,int y){
	if(x-1>=0)                              //??????
	{
		if(room[x-1][y]=='.'){
			sum++;                             //????????????????t+1????????t
			room[x-1][y]=t+1;
		}
	}
	if(y-1>=0)
		{
			if(room[x][y-1]=='.'){
				sum++;
				room[x][y-1]=t+1;
			}
		}
	if(x+1<n)
		{
			if(room[x+1][y]=='.'){
				sum++;
				room[x+1][y]=t+1;
			}
		}
	if(y+1<n)
		{
			if(room[x][y+1]=='.'){
				sum++;
				room[x][y+1]=t+1;
			}
		}
}
int main(){
	int i,j,m;
	cin>>n;
	for(i=0;i<n;i++)
		for(j=0;j<n;j++)
			cin>>room[i][j];
	cin>>m;
	for(i=0;i<n;i++)
		for(j=0;j<n;j++)
		  if(room[i][j]=='@')      //??????????
			 {sum++;
			 F(i,j);}              //??????????
	t++;
	while(t<m+64){                             //??m-1?
		for(i=0;i<n;i++)
		   for(j=0;j<n;j++){
			   if(room[i][j]==t)
			   F(i,j);
		   }
		t++;
	}
	cout<<sum<<endl;
	return 0;
}
