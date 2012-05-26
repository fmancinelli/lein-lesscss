(ns leiningen.hooks.lesscss
  (:require [leiningen.compile] 
            [leiningen.core.main :as main]
            [robert.hooke])
  (:use [leiningen.lesscss :only (lesscss)]))

(defn run-less-after-compile [f & args]
  (apply f args)
  (apply lesscss args))

(robert.hooke/add-hook #'leiningen.compile/compile
  run-less-after-compile)
