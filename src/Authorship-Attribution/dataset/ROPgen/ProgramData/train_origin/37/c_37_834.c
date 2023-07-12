int main()
{
	int i=0,k=0,t,a;//&para;&Ograve;&aring;&auml;&Aacute;&iquest;&pound;&not;&frac14;&AElig;&Ecirc;&yacute;&Ecirc;&yacute;&pound;&not;&frac14;&Ccedil;&auml;&Aacute;&iquest;
	char string[100000],b[26];//&para;&Ograve;&aring;&Ouml;&ucirc;&Ecirc;&yacute;&Oacute;&Atilde;&Oacute;&Uacute;&acute;&aelig;&Aring;&Ouml;&ucirc;&acute;&reg;&ordm;&Iacute;26&cedil;&ouml;&Ouml;&Auml;&cedil;&iexcl;&pound;
	for(i=0;i<26;i++)//&Ouml;&eth;&Ntilde;&iexcl;a~z&iexcl;&cedil;&sup3;&micro;&frac12;&para;&Ocirc;&Oacute;&brvbar;&micro;&Auml;&Ecirc;&yacute;b&micro;&Auml;&para;&Ocirc;&Oacute;&brvbar;&Ocirc;&ordf;&Euml;&Oslash;&Ouml;&ETH;&iexcl;&pound;
	{
		b[i]=97+i;
	}
	cin>>t;//&Ecirc;&auml;&Egrave;&euml;&Ntilde;&shy;&raquo;&acute;&Icirc;&Ecirc;&yacute;&iexcl;&pound;
	for(i=0;i<t;i++)
	{	
		cin>>string;//&Otilde;&Ograve;&sup3;&ouml;&Ouml;&ucirc;&acute;&reg;&micro;&Auml;&sup3;&para;&Egrave;p
		int count[26]={0},p=0,flag=0;
		//*********************k<100&Igrave;&laquo;&ETH;&iexcl;&Aacute;&Euml; 
		 
		for(k=0;k<100;k++)
		{
			if(string[k]!='\0')p=k;
			else break;
			
		}
		p++;
		for(k=0;k<p;k++)//&para;&Ocirc;&Ouml;&ucirc;&acute;&reg;&Ouml;&ETH;&Atilde;&iquest;&cedil;&ouml;&Ouml;&Auml;&cedil;&sup3;&ouml;&Iuml;&Ouml;&acute;&Icirc;&Ecirc;&yacute;&frac12;&oslash;&ETH;&ETH;&frac14;&AElig;&Ecirc;&yacute;&iexcl;&pound;
		{
			for(int j=0;j<26;j++)
			{
				if(string[k]==b[j])
				{
					count[j]++;break;
				}
			}
		}
		for(k=0;k<26;k++)//&iquest;&acute;&Ouml;&ucirc;&acute;&reg;&Ouml;&ETH;&Ecirc;&Ccedil;&ntilde;&Oacute;&ETH;&Ouml;&raquo;&sup3;&ouml;&Iuml;&Ouml;&Ograve;&raquo;&acute;&Icirc;&micro;&Auml;&Ouml;&Auml;&cedil;&iexcl;&pound;
		{
			if(count[k]==1)
			{
				flag=1;break;
			}
		}
		if(flag==1)//&Egrave;&ccedil;&sup1;&ucirc;&Oacute;&ETH;&Ouml;&raquo;&sup3;&ouml;&Iuml;&Ouml;&Ograve;&raquo;&acute;&Icirc;&micro;&Auml;&Ouml;&Auml;&cedil;&pound;&not;&Ecirc;&auml;&sup3;&ouml;&micro;&Uacute;&Ograve;&raquo;&cedil;&ouml;&ucirc;&ordm;&Iuml;&Ograve;&ordf;&Ccedil;&micro;&Auml;&Ouml;&Auml;&cedil;&iexcl;&pound;
		{
			for(k=0;k<p;k++)
			{
				for(int j=0;j<26;j++)
				{
					if(string[k]==b[j])
					{
						a=j;break;
					}
				}
				if(count[a]==1)
				{
					cout<<string[k]<<endl;break;
				}
			}
		}
		if(flag==0) cout<<"no"<<endl;//&Egrave;&ccedil;&sup1;&ucirc;&Atilde;&raquo;&Oacute;&ETH;&pound;&not;&Ocirc;&Ecirc;&auml;&sup3;&ouml;&iexcl;no&iexcl;
	
	}
	return 0;
}
