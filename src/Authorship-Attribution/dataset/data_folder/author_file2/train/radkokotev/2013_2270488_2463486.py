lst = [1,4,9,121,484,10201,12321,14641,40804,44944,1002001,1234321,4008004,
    100020001,102030201,104060401,121242121,123454321,125686521,400080004,
    404090404,10000200001,10221412201,12102420121,12345654321,40000800004,
    1000002000001,1002003002001,1004006004001,1020304030201,1022325232201,
    1024348434201,1210024200121,1212225222121,1214428244121,1232346432321,
    1234567654321,4000008000004,4004009004004,100000020000001,100220141022001,
    102012040210201,102234363432201,121000242000121,121242363242121,123212464212321,
    123456787654321,400000080000004]
 
 
def solve(lo,hi):
    global lst
    count = 0
    for i in range(len(lst)):
        if (lst[i] >= lo and lst[i] <= hi ):
            count += 1
    return count
 
fname = raw_input("name of file: ")
oname = raw_input("output file name:")
f = open(fname,"r")
out = open(oname,"w")
n = int(f.readline()[:-1])
case_num = 1
for line in f:
    args = line[:-1].split()
    lo = int(args[0])
    hi = int(args[1])
    out.write("Case #%d: %d
" %(case_num,solve(lo,hi)))
    case_num += 1
     
out.close()
f.close()
     
