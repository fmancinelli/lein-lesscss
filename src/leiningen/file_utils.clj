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

(ns leiningen.file-utils)

;; The default extensions for identifying Less CSS files.
(def less-file-extensions #{"less"})

;; Convert the argument to a java.io.File
(defn to-file [o]
  (cond (instance? java.io.File o) o
        (instance? java.lang.String o) (new java.io.File o)
        :else (new java.io.File (str o))
        )
  )

;; Replace the file extension with another one.
(defn replace-extension [filename new-extension]
   (clojure.string/replace filename  (re-pattern (str (org.apache.commons.io.FilenameUtils/getExtension filename) "$")) new-extension)
  )

;; Check if the file is a Less CSS file by looking at its extension.
(defn is-less-file? [x]  
  (let [file (to-file x)]       
    (and
     (.isFile file)
     (org.apache.commons.io.FilenameUtils/isExtension (.getName file) less-file-extensions)
     )
    )
  )

;; Recursively inspect the path to discover Less CSS files.
(defn list-less-files [path]
  (let [dir (to-file path)
        all-files (.listFiles dir)
        directories (filter #(.isDirectory %) all-files)
        standard-files (filter is-less-file? all-files)
        ]
    (concat standard-files  (mapcat list-less-files directories))
    ))
