void main()
{
        int n,k,i,j,a[100000],*p;
        scanf("%d",&n);
        for (i=0;i<n;i++)
        scanf("%d",&a[i]);
        scanf("%d",&k);
        p=a;
        for (i=0;i<n;i++)
        {
                if(*(p+i)==k)
                {
                        for(j=1;i+j<n;j++)
                        {
                                if(*(p+(i+j))!=k) {*(p+i)=*(p+(i+j));*(p+(i+j))=k;break;}
                        }
                }
                if(*(p+i)==k) break;
        }
        for (j=0;j<i-1;j++)
        printf("%d ",*(p+j));
        printf("%d",*(p+(i-1)));
}
