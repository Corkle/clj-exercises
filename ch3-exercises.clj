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


;; 5. Create a function that’s similar to symmetrize-body-parts except that it has to work with weird space aliens with radial symmetry. Instead of two eyes, arms, legs, and so on, they have five.

;; (def asym-hobbit-body-parts [{:name "head" :size 3}
;;                              {:name "left-eye" :size 1}
;;                              {:name "left-ear" :size 1}
;;                              {:name "mouth" :size 1}
;;                              {:name "nose" :size 1}
;;                              {:name "neck" :size 2}
;;                              {:name "left-shoulder" :size 3}
;;                              {:name "left-upper-arm" :size 3}
;;                              {:name "chest" :size 10}
;;                              {:name "back" :size 10}
;;                              {:name "left-forearm" :size 3}
;;                              {:name "abdomen" :size 6}
;;                              {:name "left-kidney" :size 1}
;;                              {:name "left-hand" :size 2}
;;                              {:name "left-knee" :size 2}
;;                              {:name "left-thigh" :size 4}
;;                              {:name "left-lower-leg" :size 3}
;;                              {:name "left-achilles" :size 1}
;;                              {:name "left-foot" :size 2}])



;; (defn matching-part
;;   [part]
;;   {:name (clojure.string/replace (:name part) #"^left-" "right-")
;;    :size (:size part)})

;; (defn better-symmetrize-body-parts
;;   "Expects a seq of maps that have a :name and :size"
;;   [asym-body-parts]
;;   (reduce (fn [final-body-parts part]
;;             (into final-body-parts (set [part (matching-part part)])))
;;           []
;;           asym-body-parts))



(def asym-alien-body-parts [{:name "head"}
                            {:name "ear-one"}
                            {:name "mouth"}
                            {:name "nose"}
                            {:name "shoulder-one"}
                            {:name "arm-one"}
                            {:name "hand-one"}
                            {:name "back"}
                            {:name "leg-one"}
                            {:name "knee-one"}
                            {:name "foot-one"}])
(defn matching-alien-parts
  [part]
  (set [part {:name (clojure.string/replace (:name part) #"-one" "-two")} {:name (clojure.string/replace (:name part) #"-one" "-three")} {:name (clojure.string/replace (:name part) #"-one" "-four")} {:name (clojure.string/replace (:name part) #"-one" "-five")}]))


(defn symmetrize-alien-parts
  [asym-parts]
  (reduce (fn [final-body-parts part] (into final-body-parts (matching-alien-parts part))) [] asym-parts))

(def sym-parts (symmetrize-alien-parts asym-alien-body-parts))

(do sym-parts)


