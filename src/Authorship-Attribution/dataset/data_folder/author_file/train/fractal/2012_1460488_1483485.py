import fileinput
 
str1="""ejp mysljylc kd kxveddknmc re jsicpdrysi
rbcpc ypc rtcsra dkh wyfrepkym veddknkmkrkcd
de kr kd eoya kw aej tysr re ujdr lkgc jv"""
 
strlist=str1.split()
 
str2="""our language is impossible to understand
there are twenty six factorial possibilities
so it is okay if you want to just give up"""
 
strlist2=str2.split()
 
mapLang={"y":"a","e":"o","q":"z"}
for x,y in zip(strlist,strlist2):
    for xi,yj in zip(x,y):
        if xi not in mapLang:
            mapLang[xi]=yj
             
alphabet= ['a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z']
for letter in  alphabet:
    if letter not in mapLang.keys():
        for lv in alphabet:
            if lv not in mapLang.values():
                mapLang[letter]=lv
                break
     
#print("dict size is:", len(mapLang))
#print(mapLang)
ncases=0
sp=" "\nfor txt in fileinput.input():\n    if fileinput.isfirstline():\n        ncases=int(txt)\n        continue\n    txtList=txt.split()\n    outList=[]\n    for word in txtList:\n        outword=""
        for letter in word:
            outword += mapLang[letter]
        outList.append(outword)
    outstr=sp.join(outList)
    print("Case #%(k)i: %(str)s" % {"k":fileinput.lineno()-1,"str":outstr})
