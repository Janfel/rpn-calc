(ns rpn-calc.calculate
  (:require [clojure.string :as str :only split]))

(defn apply-stack
  "Pops the given number of arguments from the stack and pushes the result from applying func on them."
  [[func arity] stack]
  (let [[head tail] (split-at arity stack)]
    (conj tail (apply func head))))

(defn evaluate
  "Evaluates input as a rpn symbol and returns a function that manipulates the number stack accordingly."
  [input]
  (partial apply-stack
           (case input
             "+" [+ 2]
             "-" [- 2]
             [#(Double/parseDouble input) 0])))

(defn compute
  "Reduces a sequence of rpn symbols to its numerical values."
  [symbols]
  (reduce #((evaluate %2) %1) [] symbols))

(defn compute-str
  "Splits a string and computes its values like compute."
  [input]
  (compute (str/split input #"\s")))
