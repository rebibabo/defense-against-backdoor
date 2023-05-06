vwcir = open('input.in')
 aoslp = open('output', 'w')
 
 paoqg = int(vwcir.readline()[:-1])
 
 for lzauk in xrange(paoqg) :
     hgwbt = int(vwcir.readline()[:-1])
     Naomi = sorted(map(float, vwcir.readline()[:-1].split()))
     Ken = sorted(map(float, vwcir.readline()[:-1].split()))
     Ken2 = Ken[:]
     War, DWar = 0, 0
     for n in Naomi :
         if n > max(Ken) :
             Ken.pop(0)
             War += 1
         else :
             for i, k in enumerate(Ken) :
                 if k > n : break
             Ken.pop(i)
     Ken = Ken2
     for n in Naomi :
         if n < min(Ken) :
             Ken.pop(-1)
         else :
             Ken.pop(0)
             DWar += 1
     output = 'Case #' + str(lzauk + 1) + ': ' + str(DWar) + ' ' + str(War)
     aoslp.write(output + '\n')
     print output
 
 vwcir.close()
 aoslp.close()
