def solve_case(bpryt):
     qhuzs = int(raw_input().strip())
     opibq = sorted([float(icgqw) for icgqw in raw_input().strip().split()])
     zqsxi = sorted([float(icgqw) for icgqw in raw_input().strip().split()])
 
     #print opibq
     #print zqsxi
 
     bgzdi, j = 0, 0
     while j < qhuzs:
         if zqsxi[j] > opibq[bgzdi]:
             bgzdi += 1
         j += 1
     optimal_result = qhuzs - bgzdi
 
     deceit_result = 0
     while qhuzs > 0:
         if opibq[0] < zqsxi[0]:
             zqsxi.pop()
         else:
             deceit_result += 1
             zqsxi = zqsxi[1:]
         opibq = opibq[1:]
         qhuzs -= 1
     
     #deceit_result = 0
     #while qhuzs > 0 and opibq[-1] > zqsxi[-1]:
     #    deceit_result += 1
     #    opibq.pop()
     #    zqsxi.pop()
     #    qhuzs -= 1
 
     #k, l = 0, qhuzs - 1
     #while k < qhuzs and l >= 0 and opibq[k] < zqsxi[l]:
     #    l -= 1
     #    k += 1
 
     #deceit_result += qhuzs - k
 
     print 'Case #%d: %d %d' % (bpryt, deceit_result, optimal_result,)
 
 def main():
     bpryt = int(raw_input().strip())
     for bgzdi in range(1, bpryt + 1):
         solve_case(bgzdi)
 
 if __name__ == '__main__':
     main()
