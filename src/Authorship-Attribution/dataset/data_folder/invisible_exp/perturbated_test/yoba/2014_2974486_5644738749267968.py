import collections
 import functools
 import operator
 
 
 def optimal_war_step(evqlw, lhnza):
 
     kdmqx = set(filter(lambda block: block > lhnza, evqlw))
     evqlw.discard(min(kdmqx if kdmqx else evqlw))
     return bool(kdmqx)
 
 
 def deceitful_war(yaqvd, crwnv, epsilon = 10 ** (-6)):
 
     while yaqvd:
 
         min_ken = min(yaqvd)
         max_ken = max(yaqvd)
         min_naomi = min(crwnv)
 
         if min_ken > min_naomi and any(map(lambda bs: operator.lt(*bs), zip(sorted(crwnv), sorted(yaqvd)))):
 
             yield max_ken - epsilon
 
         else:
 
             yield 1.0 - epsilon
 
         crwnv.discard(min_naomi)
 
 
 for i in range(int(input())):
 
     input()
     crwnv = set(map(float, str.split(input())))
     yaqvd = set(map(float, str.split(input())))
 
     dwar_ken = yaqvd.copy()
     dwar_naomi = crwnv.copy()
     dwar = collections.Counter(map(functools.partial(optimal_war_step, dwar_ken), deceitful_war(dwar_ken, dwar_naomi)))
     war = collections.Counter(map(functools.partial(optimal_war_step, yaqvd), crwnv))
     print(str.format("Case #{}: {} {}", i + 1, dwar[False], war[False]))
