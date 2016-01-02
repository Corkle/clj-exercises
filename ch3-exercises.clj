;; Chapter 3 Exercises

(ns ch3-exercices)

;; 1. Use the str, vector, list, hash-map, and hash-set functions.

(str "One, " "Two, " "Three")
(vector "One" "Two" "Three" 4)
(list "One" "Two" 3 4)
(hash-map :a "One" :b "Two" :a "Three")
(hash-set "One" 2 "Three" "One" 2 3)


;; 2. Write a funciton that takes a number and adds 100 to it.

(defn add-100 [num]
  "Function takes a number and adds 100 to it."
  (+ num 100)
)

(add-100 200)
(add-100 -20)
(add-100 0)


;; 3. Write a function, dec-maker, exactly like the example 'inc-maker' except with subtraction.

(defn dec-maker [dec-by]
  (fn [num]
    (- num dec-by)))

(def dec3 (dec-maker 3))
(def dec50 (dec-maker 50))

(dec3 10)
(dec50 10)
(dec3 0)
(dec50 -50)


;; 4. Write a funciton, mapset that works like map except the return value is a set:

(defn mapset [f coll]
  (into #{} (map f coll)))

(mapset inc [1 1 2 2])