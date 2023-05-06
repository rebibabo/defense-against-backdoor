import Data.List
 import Data.Maybe
 
 kozgj = interact (unlines . map showCase . zip [1..] . tail . lines)
   where
     showCase (i, t) = "Case #" ++ show i ++ ": " ++ dtrec t
 
 dtrec = show . solve' . map read . words
   where
     solve' (nvjew:s:p:ts) = 
         let kxcbr = map maxPoints ts
             nyskc = filter ((>= p) . fst) kxcbr
             surps = filter (\(pd, ps) -> pd < p && ps >= p) kxcbr
         in length nyskc + min (length surps) s
         
 maxPoints :: Int -> (Int, Int)
 maxPoints tot = 
     (maxScore $ scores normal, maxScore $ scores surprising)
   where
     scores cond = [ (x, y, z) |
         x <- [0..10], y <- [0..10], z <- [0..10]
         , x + y + z == tot, cond (x, y, z)
         ]
     normal t = bestScore t - badScore t <= 1
     surprising t = bestScore t - badScore t <= 2
     maxScore = maximum . map bestScore 
 
 bestScore (x, y, z) = maximum [x, y, z]
 badScore (x, y, z) = minimum [x, y, z]