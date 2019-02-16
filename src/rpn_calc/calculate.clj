(ns rpn-calc.calculate)

(defn apply-stack
  "Pops the given number of arguments from the stack and pushes the result from applying func on them."
  [[func arity] stack]
  (let [[head tail] (split-at arity stack)]
    (conj tail (apply func head))))

(defn- num-fn
  "Wraps a number in an arity 1 function."
  [num] (fn [] num))

(defn- parse-num-fn
  "Parses a number from a string and wraps it like num-fn."
  [input] (num-fn (Double/parseDouble input)))

(defn evaluate
  "Evaluates input as a rpn symbol and returns a function that manipulates the number stack accordingly."
  [input]
  (partial apply-stack
           (case input
             "+" [+ 2]
             "-" [- 2]
             [(parse-num-fn input) 0])))

(defn compute
  "Reduces a sequence of rpn symbols to its numerical value."
  [symbols]
  (reduce #((evaluate %2) %1) [] symbols))
