(ns leiningen.hooks.lesscss
  (:require leiningen.compile
            [leiningen.core.main :as main])
  (:use [clojure.java.io :only [file delete-file]]
        [leiningen.lesscss :only (lesscss)]
        robert.hooke))

(defn run-less-after-compile [f & args]
  (apply f args)
  (apply lesscss args))

(robert.hooke/add-hook #'leiningen.compile/compile run-less-after-compile)
