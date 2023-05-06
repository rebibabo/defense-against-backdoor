# In general 1[10]*1 square is always a palindrome and some subset of [12][012]*[12] is also good but it's just
 # too much hassle to figure that out. Also needs a more complex generator for ranges. 55p is hard...
 
 
 sbota = 10
 
 def check_palindrome(cqxif):
     mjvwt = 0
     iwube = cqxif
     while iwube > 0:
         mjvwt = mjvwt * 10 + (iwube % 10)
         iwube /= 10
     if mjvwt == cqxif:
         return True
     else:
         return False
 
 def list_to_number(wogkj):
     zlowy = len(wogkj)
     bzevo = 0; rres = 0
     multi = 1
     for i in xrange(1, zlowy + 1):
         bzevo += wogkj[zlowy - i] * multi
         rres += wogkj[i - 1] * multi
         multi *= 10
     return (bzevo, rres)
 
 def gen_one_side(side_length):
     state = [0 for _ in xrange(side_length)]
     done = False
 
     while not done:
         if state[-1] != 0:
             yield state
         state[-1] += 1
         curr = side_length - 1
         while state[curr] == sbota:
             if curr == 0:
                 done = True
                 break
             state[curr] = 0
             curr -= 1
             state[curr] += 1
 
 def gen_odd_palindrome(side_length):
     if side_length == 0:
         for i in xrange(1, 10):
             yield i
     else:
         multi = 10**side_length
         for one_side in gen_one_side(side_length):
             cqxif, rnumber = list_to_number(one_side)
             for i in xrange(sbota):
                 yield cqxif + i * multi + rnumber * multi * 10
 
 def gen_even_palindrome(side_length):
     multi = 10**side_length
     for one_side in gen_one_side(side_length):
         cqxif, rnumber = list_to_number(one_side)
         yield cqxif + rnumber * multi
 
 
 def gen_palindrome():
     zlowy = 1
 
     while True:
         if zlowy % 2 == 1:
             for odd_pal in gen_odd_palindrome((zlowy - 1) / 2):
                 yield odd_pal
         else:
             for even_pal in gen_even_palindrome(zlowy / 2):
                 yield even_pal
         zlowy += 1
 
 
 cache = []
 upper = 10**14
 for pal in gen_palindrome():
     pal2 = pal**2
     if pal2 > upper:
         break
     if check_palindrome(pal2):
         cache.append(pal2)
 
 cl = len(cache)
 T = int(raw_input().strip())
 for i in xrange(T):
     low_ind = 0
     high_ind = cl - 1
     A, B = map(int, raw_input().strip().split(' '))
 
     # The cache is small for 10**14
     while cache[low_ind] < A:
         low_ind += 1
     while cache[high_ind] > B:
         high_ind -= 1
 
 
     if low_ind <= high_ind:
         print "Case #%s: %s" % (i + 1, high_ind - low_ind + 1)
     else:
         print "Case #%s: 0" % (i + 1)
