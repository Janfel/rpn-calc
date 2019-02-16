(ns rpn-calc.core
  (:gen-class)
  (:require [rpn-calc.calculate :as calc]))

(defn -main
  "Calculates the numeric value of the passed rpn expression."
  [& args]
  (run! println (calc/compute-str (first args))))
