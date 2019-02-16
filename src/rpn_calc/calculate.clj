(ns rpn-calc.calculate)

(defn apply-stack
  "Pops the given number of arguments from the stack and pushes the result from applying func on them."
  [[func arity] stack]
  (let [[head tail] (split-at arity stack)]
    (conj tail (apply func head))))
