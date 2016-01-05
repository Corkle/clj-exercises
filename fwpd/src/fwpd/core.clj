(ns fwpd.core)
(def filename "suspects.csv")

(def vamp-keys [:name :glitter-index])

(defn str->int [str]
  (Integer. str))



(def conversions {:name identity
                   :glitter-index str->int})

(defn- convert [vamp-key value]
  ((get conversions vamp-key) value))




(def validations {:name string?
                 :glitter-index integer?})

(defn- validate [key-validations record]
  (every? identity (map (fn [key-name] ((get key-validations key-name) (get record key-name))) (keys key-validations))))




(defn- parse
  "Convert a CSV into rows of columns"
  [string]
  (map #(clojure.string/split % #",")
       (clojure.string/split string #"\r\n")))

(defn mapify
  "Return a seq of maps like {:name \"Edward Cullen\" :glitter-index 10}"
  [rows]
  (map (fn [unmapped-row]
         (reduce (fn [row-map [vamp-key value]]
                   (assoc row-map vamp-key (convert vamp-key value)))
                 {}
                 (map vector vamp-keys unmapped-row)))
       rows))

(parse (slurp filename))

(defn glitter-filter [minimum-glitter records]
  (map #(:name %) (filter #(>= (:glitter-index %) minimum-glitter) records)))



(defn append
  "Append new suspect to list of suspects"
  [new-suspect suspect-list]
  (if (validate validations new-suspect)
    (conj suspect-list new-suspect)
    (println "Invalid Format. Please provide suspect name and glitter index value")))


(defn stringify
  "Convert list mapped records to a list of vectors"
  [mapped-records]
  (map (fn [mapped-record]
         (reduce (fn [unmapped-row map-key] (conj unmapped-row (str (get mapped-record map-key))))
                 []
                 (keys mapped-record)))
       mapped-records))

(keys {:name "John" :glitter-index 4})

(defn convert-to-csv [records]
  (clojure.string/join "\n"
                       (stringify records)))






(def current-supsects(mapify (parse (slurp filename))))

(do current-supsects)

(glitter-filter 3 current-supsects)

(append {:name "Luke" :glitter-index 10} current-supsects)

(convert-to-csv current-supsects)

(stringify current-supsects)
