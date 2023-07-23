#Template code developed by Brett Olsen (brett.olsen@gmail.com), 2013
#for the Google Code Jam programming contest
 
###############################################################################
# Imports go here
###############################################################################
 
from __future__ import division
import numpy as np
import math
 
###############################################################################
# Global variables (for caching, etc.) go here
###############################################################################
 
#Set up the input/output files: problem-tagsuffix.in / *.out
problem = "C"
tag = "small" #commonly sample, small, or large
#tag = "large"
#tag = "sample"
suffix = "-attempt0" #used sometimes for indexing later input files
#suffix = ""
#From http://oeis.org/A002779/b002779.txt
#Lazy route for the first two sets!
#Numbers that are both squares and palindromes
table_of_fairs = np.array([0, 1, 4, 9, 121, 484, 676, 10201, 12321, 14641, 
40804, 44944, 69696, 94249, 698896, 1002001, 1234321, 4008004, 5221225, 6948496, 
100020001, 102030201, 104060401, 121242121, 123454321, 125686521, 400080004, 
404090404, 522808225, 617323716, 942060249, 10000200001, 10221412201, 12102420121, 
12345654321, 40000800004, 637832238736, 1000002000001, 1002003002001, 1004006004001, 
1020304030201, 1022325232201, 1024348434201, 1086078706801, 1210024200121, 
1212225222121, 1214428244121, 1230127210321, 1232346432321, 1234567654321, 
1615108015161, 4000008000004, 4004009004004, 4051154511504, 5265533355625, 
9420645460249, 100000020000001, 100220141022001, 102012040210201, 102234363432201, 
121000242000121, 121242363242121, 123212464212321, 123456787654321, 
123862676268321, 144678292876441, 165551171155561, 400000080000004, 
900075181570009, 4099923883299904, 10000000200000001, 10002000300020001, 
10004000600040001, 10020210401202001, 10022212521222001, 10024214841242001, 
10201020402010201, 10203040504030201, 10205060806050201, 10221432623412201, 
10223454745432201, 12100002420000121, 12102202520220121, 12104402820440121, 
12120030703002121, 12122232623222121, 12124434743442121, 12321024642012321, 
12323244744232321, 12341234943214321, 12343456865434321, 12345678987654321, 
40000000800000004, 40004000900040004, 94206450305460249, 1000000002000000001, 
1000220014100220001, 1002003004003002001, 1002223236323222001, 1020100204020010201, 
1020322416142230201, 1022123226223212201, 1022345658565432201, 1210000024200000121, 
1210242036302420121, 1212203226223022121, 1212445458545442121, 1232100246420012321, 
1232344458544432321, 1234323468643234321, 4000000008000000004, 4253436912196343524, 
6158453974793548516, 100000000020000000001, 100002000030000200001, 100004000060000400001, 
100020201040102020001, 100022201252102220001, 100024201484102420001, 
100200120040021002001, 100202122050221202001, 100204124080421402001, 
100220341262143022001, 100222343474343222001, 102010002040200010201, 
102012022050220210201, 102014042080240410201, 102030405060504030201, 
102032425272524230201, 102132537636735231201, 102210100272001012201, 102212122262221212201, 
102214144272441412201, 102230523292325032201, 102232545484545232201, 102234567696765432201, 
104190107303701091401, 121000000242000000121, 121002200252002200121, 121004400282004400121, 
121020021070120020121, 121022221262122220121, 121024421474124420121, 121220122262221022121, 
121222324272423222121, 121240161292161042121, 121242363484363242121, 121244565696565442121, 
123210002464200012321, 123212222474222212321, 123230205292502032321, 123232425484524232321, 
123234645696546432321, 123432124686421234321, 123434346696643434321, 184398883818388893481, 
400000000080000000004, 400004000090000400004, 522815090696090518225, 906086675171576680609, 
942064503484305460249, 6916103777337773016196, 10000000000200000000001, 10000220001410002200001, 
10002002100400120020001, 10002222123632122220001, 10020010200400201002001, 10020230421612403202001, 
10022014302620341022001, 10022234545854543222001, 10201000020402000010201, 10201222221612222210201, 
10203022140604122030201, 10203244363836344230201, 10221210222622201212201, 10221432643834623412201, 
10223234344844343232201, 10224609234443290642201, 12100000002420000000121, 12100242003630024200121,
12102202302620320220121, 12102444325852344420121, 12122010222622201022121, 12122252443834425222121, 
12124214524842541242121, 12321000024642000012321, 12321244225852244212321, 12323222344844322232321, 
12343210246864201234321, 12384043938083934048321, 12599536942224963599521, 16593841302620314839561, 
40000000000800000000004, 1000000000002000000000001, 1000002000003000002000001, 
1000004000006000004000001, 1000020200104010020200001, 1000022200125210022200001, 1000024200148410024200001, 1000200030004000300020001, 1000202030205020302020001, 1000204030408040304020001, 1000220232126212320220001, 1000222232347432322220001, 1002001002004002001002001, 1002003004005004003002001, 1002005006008006005002001, 1002021222306032221202001, 1002023224327234223202001, 1002201232026202321022001, 1002203234227224323022001, 1002221454348434541222001, 1002223456569656543222001, 1020100000204020000010201, 1020102020205020202010201, 1020104040208020404010201, 1020120402306032040210201, 1020122422327232242210201, 1020300010207020100030201, 1020302030406040302030201, 1020304050607060504030201, 1020320414309034140230201, 1020322434528254342230201, 1020324454749474544230201, 1022121002226222001212201, 1022123024227224203212201, 1022141424528254241412201, 1022143446549456443412201, 1022321210249420121232201, 1022323232448442323232201, 1022325254649464525232201, 1210000000024200000000121, 1210002200025200022000121, 1210004400028200044000121, 1210020020107010200200121, 1210022220126210222200121, 1210024420147410244200121, 1210220032026202300220121, 1210222232227222322220121, 1210242254148414522420121, 1210244454369634544420121, 1212201002226222001022121, 1212203204227224023022121, 1212221040509050401222121, 1212223242528252423222121, 1212225444549454445222121, 1212421234248424321242121, 1212423436449446343242121, 1232100000246420000012321, 1232102220247420222012321, 1232120202329232020212321, 1232122422348432242212321, 1232124642369632464212321, 1232322032448442302232321, 1232324252649462524232321, 1234321002468642001234321, 1234323224469644223234321, 1821056104269624016501281, 4000000000008000000000004, 4000004000009000004000004, 4618627222542452227268164, 6942236477330337746322496, 9420645034800084305460249, 40460195511188111559106404, 100000000000020000000000001, 100000220000141000022000001, 100002002010040010200200001, 100002222012363210222200001, 100020001200040002100020001, 100020221222161222122020001, 100022003410262014300220001, 100022223434585434322220001, 100200100020040020001002001, 100200320240161042023002001, 100202104032060230401202001, 100202324254383452423202001, 100220121220262022121022001, 100220341462383264143022001, 100222125432484234521222001, 102010000002040200000010201, 102010222202161202222010201, 102012022032060230220210201, 102012244234383432442210201, 102030201204060402102030201, 102030423426181624324030201, 102032223434282434322230201, 102212100022262220001212201, 102212322442383244223212201, 102214124054282450421412201, 102232321224484422123232201, 121000000000242000000000121, 121000242000363000242000121, 121002202210262012202200121, 121002444212585212444200121, 121022001220262022100220121, 121022243242383242342220121, 121024203630484036302420121, 121220100022262220001022121, 121220342242383242243022121, 121222304234282432403222121, 121242121242484242121242121, 123210000002464200000012321, 123210244202585202442012321, 123212222232484232222212321, 123232201224484422102232321, 123432100024686420001234321, 400000000000080000000000004, 923860899791363197998068329, 4872133543202112023453312784, 9658137819052882509187318569, 10000000000000200000000000001, 10000002000000300000020000001, 10000004000000600000040000001, 10000020200010401000202000001, 10000022200012521000222000001, 10000024200014841000242000001, 10000200021000400012000200001, 10000202021020502012020200001, 10000204021040804012040200001, 10000220221212621212202200001, 10000222221234743212222200001, 10002000102000400020100020001, 10002002102200500220120020001, 10002004102400800420140020001, 10002020304030603040302020001, 10002022304232723240322020001, 10002200143002620034100220001, 10002202143222722234120220001, 10002220345234843254302220001, 10002222345456965454322220001, 10020010000200400200001002001, 10020012002200500220021002001, 10020014004200800240041002001, 10020030220410601402203002001, 10020032222412721422223002001, 10020210221220602212201202001, 10020212223240704232221202001, 10020230441632823614403202001, 10020232443654945634423202001, 10022010100002720000101022001, 10022012102202620220121022001, 10022014104402720440141022001, 10022030322230903222303022001, 10022032324432823442323022001, 10022034326634943662343022001, 10022210341004940014301222001, 10022212343224842234321222001, 10022214345444944454341222001, 10201000000020402000000010201, 10201002020020502002020010201, 10201004040020802004040010201, 10201020402030603020402010201, 10201022422032723022422010201, 10201200001200700210000210201, 10201202021220602212020210201, 10201204041240704214040210201, 10201220403410901430402210201, 10201222423432823432422210201, 10201224443454945434442210201, 10203020102040604020102030201, 10203022122240704222122030201, 10203040506070807060504030201, 10203042526272927262524030201, 10203220123022922032102230201, 10203222143242824234122230201, 10203224163462926436142230201, 10221210000222622200001212201, 10221212022222722222021212201, 10221230422432823422403212201, 10221232444434943444423212201, 10221412221442824412221412201, 10221414243462926434241412201, 10223232102244844220123232201, 10223234124444944442143232201, 12100000000002420000000000121, 12100002200002520000220000121, 12100004400002820000440000121, 12100020020010701002002000121, 12100022220012621002222000121, 12100024420014741002442000121, 12100220023002620032002200121, 12100222223022722032222200121, 12100242243214841234224200121, 12100244443236963234444200121, 12102200102202620220100220121, 12102202302402720420320220121, 12102220124030903042102220121, 12102222324232823242322220121, 12102224524434943442542220121, 12102420145204840254102420121, 12102422345424942454322420121, 12122010000222622200001022121, 12122012202222722220221022121, 12122032240432823404223022121, 12122034442434943424443022121, 12122230223242824232203222121, 12122232425262926252423222121, 12124212102424842420121242121, 12124214304624942640341242121, 12321000000024642000000012321, 12321002220024742002220012321, 12321020202032923020202012321, 12321022422034843022422012321, 12321024642036963024642012321, 12321222023224842232022212321, 12321224243244944234242212321, 12323220102244844220102232321, 12323222322444944422322232321, 12343210000246864200001234321, 12343212222246964222221234321, 16799008923862526832980099761, 40000000000000800000000000004, 40000004000000900000040000004, 44431002775280908257720013444, 98693567900935453900976539689, 1000000000000002000000000000001, 1000000220000014100000220000001, 1000002002001004001002002000001, 1000002222001236321002222000001, 1000020000300004000030000200001, 1000020220302216122030220200001, 1000022002321026201232002200001, 1000022222323458543232222200001, 1000200010020004000200100020001, 1000200230042016102400320020001, 1000202012221206021222102020001, 1000202232243438343422322020001, 1000220012320026200232100220001, 1000220232344238324432320220001, 1000222014541248421454102220001, 1002001000002004002000001002001, 1002001220222016102220221002001, 1002003004005006005004003002001, 1002003224225238325224223002001, 1002021020302206022030201202001, 1002021240524418144250421202001, 1002023024325228225234203202001, 1002201210022026202200121022001, 1002201430264038304620341022001, 1002203214225228225224123022001, 1002221232322248422232321222001, 1020100000000204020000000010201, 1020100222200216120022220010201, 1020102022021206021202202010201, 1020102244221438341224422010201, 1020120200302206022030020210201, 1020120422504418144052240210201, 1020122222343228223432222210201, 1020302010020406040200102030201, 1020302232242418142422322030201, 1020304032241608061422304030201, 1020322212322428242232122230201, 1022121000002226222000001212201, 1022121222422238322242221212201, 1022123024025228225204203212201, 1022141220304428244030221412201, 1022323210022448442200123232201, 1210000000000024200000000000121, 1210000242000036300002420000121, 1210002202201026201022022000121, 1210002444201258521024442000121, 1210022000320026200230002200121, 1210022242322238322232422200121, 1210024202541048401452024200121, 1210220010022026202200100220121, 1210220252044038304402520220121, 1210222212423228223242122220121, 1210242012342048402432102420121, 1212201000002226222000001022121, 1212201242222238322222421022121, 1212203204205228225024023022121, 1212223020322428242230203222121, 1212421210024248424200121242121, 1232100000000246420000000012321, 1232100244200258520024420012321, 1232102222221248421222222012321, 1232122200322248422230022212321, 1232322010022448442200102232321, 1234321000002468642000001234321, 4000000000000008000000000000004, 4844486878939076709398786844484, 6574372239019762679109322734756, 9403095533541415141453355903049, 9659504223792743472973224059569, 9848294822582726272852284928489
])
 
#and here we've filtered the table
filtered_table = np.array([0, 1, 4, 9, 121, 484, 10201, 12321, 14641, 40804, 44944, 1002001,
        1234321, 4008004, 100020001, 102030201, 104060401, 121242121,
        123454321, 125686521, 400080004, 404090404, 10000200001,
        10221412201, 12102420121, 12345654321, 40000800004, 1000002000001,
        1002003002001, 1004006004001, 1020304030201, 1022325232201,
        1024348434201, 1210024200121, 1212225222121, 1214428244121,
        1232346432321, 1234567654321, 4000008000004, 4004009004004,
        100000020000001, 100220141022001, 102012040210201, 102234363432201,
        121000242000121, 121242363242121, 123212464212321, 123456787654321,
        400000080000004, 10000000200000001, 10002000300020001,
        10004000600040001, 10020210401202001, 10022212521222001,
        10024214841242001, 10201020402010201, 10203040504030201,
        10205060806050201, 10221432623412201, 10223454745432201,
        12100002420000121, 12102202520220121, 12104402820440121,
        12122232623222121, 12124434743442121, 12321024642012321,
        12323244744232321, 12343456865434321, 12345678987654321,
        40000000800000004, 40004000900040004, 1000000002000000001,
        1000220014100220001, 1002003004003002001, 1002223236323222001,
        1020100204020010201, 1020322416142230201, 1022123226223212201,
        1022345658565432201, 1210000024200000121, 1210242036302420121,
        1212203226223022121, 1212445458545442121, 1232100246420012321,
        1232344458544432321, 1234323468643234321, 4000000008000000004,
        100000000020000000001, 100002000030000200001, 100004000060000400001,
        100020201040102020001, 100022201252102220001, 100024201484102420001,
        100200120040021002001, 100202122050221202001, 100204124080421402001,
        100220341262143022001, 100222343474343222001, 102010002040200010201,
        102012022050220210201, 102014042080240410201, 102030405060504030201,
        102032425272524230201, 102212122262221212201, 102214144272441412201,
        102232545484545232201, 102234567696765432201, 121000000242000000121,
        121002200252002200121, 121004400282004400121, 121022221262122220121,
        121024421474124420121, 121220122262221022121, 121222324272423222121,
        121242363484363242121, 121244565696565442121, 123210002464200012321,
        123212222474222212321, 123232425484524232321, 123234645696546432321,
        123432124686421234321, 123434346696643434321, 400000000080000000004,
        400004000090000400004, 10000000000200000000001,
        10000220001410002200001, 10002002100400120020001,
        10002222123632122220001, 10020010200400201002001,
        10020230421612403202001, 10022014302620341022001,
        10022234545854543222001, 10201000020402000010201,
        10201222221612222210201, 10203022140604122030201,
        10203244363836344230201, 10221210222622201212201,
        10221432643834623412201, 10223234344844343232201,
        12100000002420000000121, 12100242003630024200121,
        12102202302620320220121, 12102444325852344420121,
        12122010222622201022121, 12122252443834425222121,
        12124214524842541242121, 12321000024642000012321,
        12321244225852244212321, 12323222344844322232321,
        12343210246864201234321, 40000000000800000000004,
        1000000000002000000000001, 1000002000003000002000001,
        1000004000006000004000001, 1000020200104010020200001,
        1000022200125210022200001, 1000024200148410024200001,
        1000200030004000300020001, 1000202030205020302020001,
        1000204030408040304020001, 1000220232126212320220001,
        1000222232347432322220001, 1002001002004002001002001,
        1002003004005004003002001, 1002005006008006005002001,
        1002021222306032221202001, 1002023224327234223202001,
        1002201232026202321022001, 1002203234227224323022001,
        1002221454348434541222001, 1002223456569656543222001,
        1020100000204020000010201, 1020102020205020202010201,
        1020104040208020404010201, 1020120402306032040210201,
        1020122422327232242210201, 1020302030406040302030201,
        1020304050607060504030201, 1020322434528254342230201,
        1020324454749474544230201, 1022121002226222001212201,
        1022123024227224203212201, 1022141424528254241412201,
        1022143446549456443412201, 1022323232448442323232201,
        1022325254649464525232201, 1210000000024200000000121,
        1210002200025200022000121, 1210004400028200044000121,
        1210022220126210222200121, 1210024420147410244200121,
        1210220032026202300220121, 1210222232227222322220121,
        1210242254148414522420121, 1210244454369634544420121,
        1212201002226222001022121, 1212203204227224023022121,
        1212223242528252423222121, 1212225444549454445222121,
        1212421234248424321242121, 1212423436449446343242121,
        1232100000246420000012321, 1232102220247420222012321,
        1232122422348432242212321, 1232124642369632464212321,
        1232322032448442302232321, 1232324252649462524232321,
        1234321002468642001234321, 1234323224469644223234321,
        4000000000008000000000004, 4000004000009000004000004,
        100000000000020000000000001, 100000220000141000022000001,
        100002002010040010200200001, 100002222012363210222200001,
        100020001200040002100020001, 100020221222161222122020001,
        100022003410262014300220001, 100022223434585434322220001,
        100200100020040020001002001, 100200320240161042023002001,
        100202104032060230401202001, 100202324254383452423202001,
        100220121220262022121022001, 100220341462383264143022001,
        100222125432484234521222001, 102010000002040200000010201,
        102010222202161202222010201, 102012022032060230220210201,
        102012244234383432442210201, 102030201204060402102030201,
        102030423426181624324030201, 102032223434282434322230201,
        102212100022262220001212201, 102212322442383244223212201,
        102214124054282450421412201, 102232321224484422123232201,
        121000000000242000000000121, 121000242000363000242000121,
        121002202210262012202200121, 121002444212585212444200121,
        121022001220262022100220121, 121022243242383242342220121,
        121024203630484036302420121, 121220100022262220001022121,
        121220342242383242243022121, 121222304234282432403222121,
        121242121242484242121242121, 123210000002464200000012321,
        123210244202585202442012321, 123212222232484232222212321,
        123232201224484422102232321, 123432100024686420001234321,
        400000000000080000000000004, 10000000000000200000000000001,
        10000002000000300000020000001, 10000004000000600000040000001,
        10000020200010401000202000001, 10000022200012521000222000001,
        10000024200014841000242000001, 10000200021000400012000200001,
        10000202021020502012020200001, 10000204021040804012040200001,
        10000220221212621212202200001, 10000222221234743212222200001,
        10002000102000400020100020001, 10002002102200500220120020001,
        10002004102400800420140020001, 10002020304030603040302020001,
        10002022304232723240322020001, 10002200143002620034100220001,
        10002202143222722234120220001, 10002220345234843254302220001,
        10002222345456965454322220001, 10020010000200400200001002001,
        10020012002200500220021002001, 10020014004200800240041002001,
        10020030220410601402203002001, 10020032222412721422223002001,
        10020210221220602212201202001, 10020212223240704232221202001,
        10020230441632823614403202001, 10020232443654945634423202001,
        10022012102202620220121022001, 10022014104402720440141022001,
        10022032324432823442323022001, 10022034326634943662343022001,
        10022212343224842234321222001, 10022214345444944454341222001,
        10201000000020402000000010201, 10201002020020502002020010201,
        10201004040020802004040010201, 10201020402030603020402010201,
        10201022422032723022422010201, 10201202021220602212020210201,
        10201204041240704214040210201, 10201222423432823432422210201,
        10201224443454945434442210201, 10203020102040604020102030201,
        10203022122240704222122030201, 10203040506070807060504030201,
        10203042526272927262524030201, 10203222143242824234122230201,
        10203224163462926436142230201, 10221210000222622200001212201,
        10221212022222722222021212201, 10221230422432823422403212201,
        10221232444434943444423212201, 10221412221442824412221412201,
        10221414243462926434241412201, 10223232102244844220123232201,
        10223234124444944442143232201, 12100000000002420000000000121,
        12100002200002520000220000121, 12100004400002820000440000121,
        12100022220012621002222000121, 12100024420014741002442000121,
        12100220023002620032002200121, 12100222223022722032222200121,
        12100242243214841234224200121, 12100244443236963234444200121,
        12102200102202620220100220121, 12102202302402720420320220121,
        12102222324232823242322220121, 12102224524434943442542220121,
        12102420145204840254102420121, 12102422345424942454322420121,
        12122010000222622200001022121, 12122012202222722220221022121,
        12122032240432823404223022121, 12122034442434943424443022121,
        12122230223242824232203222121, 12122232425262926252423222121,
        12124212102424842420121242121, 12124214304624942640341242121,
        12321000000024642000000012321, 12321002220024742002220012321,
        12321022422034843022422012321, 12321024642036963024642012321,
        12321222023224842232022212321, 12321224243244944234242212321,
        12323220102244844220102232321, 12323222322444944422322232321,
        12343210000246864200001234321, 12343212222246964222221234321,
        40000000000000800000000000004, 40000004000000900000040000004,
        1000000000000002000000000000001, 1000000220000014100000220000001,
        1000002002001004001002002000001, 1000002222001236321002222000001,
        1000020000300004000030000200001, 1000020220302216122030220200001,
        1000022002321026201232002200001, 1000022222323458543232222200001,
        1000200010020004000200100020001, 1000200230042016102400320020001,
        1000202012221206021222102020001, 1000202232243438343422322020001,
        1000220012320026200232100220001, 1000220232344238324432320220001,
        1000222014541248421454102220001, 1002001000002004002000001002001,
        1002001220222016102220221002001, 1002003004005006005004003002001,
        1002003224225238325224223002001, 1002021020302206022030201202001,
        1002021240524418144250421202001, 1002023024325228225234203202001,
        1002201210022026202200121022001, 1002201430264038304620341022001,
        1002203214225228225224123022001, 1002221232322248422232321222001,
        1020100000000204020000000010201, 1020100222200216120022220010201,
        1020102022021206021202202010201, 1020102244221438341224422010201,
        1020120200302206022030020210201, 1020120422504418144052240210201,
        1020122222343228223432222210201, 1020302010020406040200102030201,
        1020302232242418142422322030201, 1020304032241608061422304030201,
        1020322212322428242232122230201, 1022121000002226222000001212201,
        1022121222422238322242221212201, 1022123024025228225204203212201,
        1022141220304428244030221412201, 1022323210022448442200123232201,
        1210000000000024200000000000121, 1210000242000036300002420000121,
        1210002202201026201022022000121, 1210002444201258521024442000121,
        1210022000320026200230002200121, 1210022242322238322232422200121,
        1210024202541048401452024200121, 1210220010022026202200100220121,
        1210220252044038304402520220121, 1210222212423228223242122220121,
        1210242012342048402432102420121, 1212201000002226222000001022121,
        1212201242222238322222421022121, 1212203204205228225024023022121,
        1212223020322428242230203222121, 1212421210024248424200121242121,
        1232100000000246420000000012321, 1232100244200258520024420012321,
        1232102222221248421222222012321, 1232122200322248422230022212321,
        1232322010022448442200102232321, 1234321000002468642000001234321,
        4000000000000008000000000000004], dtype=object)
 
###############################################################################
# Helper functions go here
###############################################################################
 
def read_input(infile):
    """This function should take an open input file, load in all of the
    relevant information for a single case of the problem, and output it
    as a single object.    
    """
    #Some utility functions to read in particular types of input
    def read_int():
        return int(infile.readline().strip())
    def read_ints():
        return np.array(infile.readline().split(), dtype=int)
    def read_bigints():
        line = infile.readline().split()
        return np.array(map(lambda x: int(x), line))
    def read_float():
        return float(infile.readline().strip())
    def read_floats():
        return np.array(infile.readline().split(), dtype=float)
    def read_string():
        return infile.readline().strip()
    def read_strings():
        return np.array(infile.readline().split(), dtype=object) #change the dtype?
     
    A, B = read_bigints()
     
    return (A, B)
 
def is_palindrome(num):
    digits = []
    num = int(num)
    while num > 0:
        digits.append(num % 10)
        num = num / 10 #n.b. int division (discard remainder)
 
    return digits == digits[::-1]
 
    for i in range(len(digits) / 2): #n.b. int division (skips center)
        if digits[i] != digits[-i+1]:
            return False
    return True
 
def solve_case_simple(case):
    A, B = case
     
    if B > table_of_fairs[-1]:
        raise (ValueError, "Ranges too big for lookup table!")
     
    valid = (A <= filtered_table) * (filtered_table <= B)
    return valid.sum()
 
def solve_case(case):
    """Take the input data (structured in case) and perform any necessary
    calculations to obtain the desired output, formatted as the appropriate
    string.    
    """
     
    output = solve_case_simple(case)
    return output
 
###############################################################################
# Main execution path
###############################################################################
 
if __name__ == "__main__":
    #Open up the input & output files
    infile = open("%s-%s%s.in" % (problem, tag, suffix), 'r')
    outfile = open("%s-%s%s.out" % (problem, tag, suffix), 'w')
     
    #Read in the number of cases (the first input line) to iterate through
    cases = int(infile.readline().strip('
'))
    for i in range(cases):
         
        #Read in the input data for this case
        case = read_input(infile)
         
        #Solve the problem for this case
        output = solve_case(case)
         
        #Write out the output of this case
        outfile.write('Case #%i: %s
' % (i+1, output))
        print ('Case #%i: %s
' % (i+1, output))
     
    #Close files
    infile.close()
    outfile.close()