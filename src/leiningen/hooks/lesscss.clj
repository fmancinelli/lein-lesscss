;; Leiningen Less CSS compile task.
;; Copyright 2012 Fabio Mancinelli <fabio@mancinelli.me>
;;
;; Contributors:
;;   John Szakmeister <john@szakmeister.net>
;;
;; This program is free software: you can redistribute it and/or modify
;; it under the terms of the GNU General Public License as published by
;; the Free Software Foundation, either version 3 of the License, or
;; (at your option) any later version.
;;
;; This program is distributed in the hope that it will be useful,
;; but WITHOUT ANY WARRANTY; without even the implied warranty of
;; MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
;; GNU General Public License for more details.
;;
;; You should have received a copy of the GNU General Public License
;; along with this program. If not, see <http://www.gnu.org/licenses/>

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
