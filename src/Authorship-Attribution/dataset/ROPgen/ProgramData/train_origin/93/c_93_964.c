int main(){
	int a;//???a 
	cin>>a;
	int num=0;//??a??3?5?7????? 
	for(int i=3;i<=7;i+=2){
		if(a%i==0){
			num++;
			if(num!=1)cout<<" "<<i;
			else if(num==1)cout<<i;/*?if,else if??????????
			?????? */ 
		   }
		}
		if(num==0)cout<<"n";// 3?5?7?????a???n 
		
		return 0;
	}
