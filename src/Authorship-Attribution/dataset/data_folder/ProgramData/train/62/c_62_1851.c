//****************************************
//*???      ??????    **
//*????? 1100012996       **
//*???2011.11.23**
//****************************************
int main() 
{ 
	int i = 0 ; //i?????? 
	char *p , *m ;  //???????? 
	char str[100] ; //???????? 
	gets(str) ;
	m = str ; 
	p = str ; 
	while( str[i] != '\0' )
	{ 
		if ( str[i] == ' ' ) //????????????????? 
		{ 
			*p = ' ' ; //???????' ' 
			p++ ; //p????????? 
			while ( str[i] == ' ' ) //????????????????? 
				 i++ ; 
			*p = str[i] ; //???p?????
		}	
		else //??????? 
		{ 
			*p = str[i] ;
			p++ ; 
			i++ ;
		} 
	}
	*p = '\0' ; 
	cout << m << endl ; //???????? 
	return 0; 
}