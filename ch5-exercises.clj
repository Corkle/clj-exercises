;; 1. You used (comp :intelligence :attributes) to create a function that returns a character’s intelligence. Create a new function, attr, that you can call like (attr :intelligence) and that does the same thing.

(def character
{:name "Smooches McCutes"
:attributes {:intelligence 10
:strength 4
:dexterity 5}})
(def c-int (comp :intelligence :attributes))
(def c-str (comp :strength :attributes))
(def c-dex (comp :dexterity :attributes))

(defn attr
  [c-attr]
  (comp c-attr :attributes))

((attr :strength) character)


;; 2. Implement the comp function.

(defn my-comp
  [& fns]
  (fn [& args]
    (let [fns-list (reverse fns)
          first-result (apply (first fns-list) args)
          rest-fns (rest fns-list)]
      (reduce (fn [prev-result next-fn] (next-fn prev-result))
            first-result rest-fns))))

((comp str inc *) 2 3 2)
((my-comp str inc *) 2 3 2)



;; 3. Implement the assoc-in function. Hint: use the assoc function and define its parameters as [m [k & ks] v].




;; 4. Look up and use the update-in function.



;; 5. Implement update-in.
