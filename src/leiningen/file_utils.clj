;; Leiningen Less CSS compile task.
;; Copyright 2012 Fabio Mancinelli <fabio@mancinelli.me>
;;
;; Contributors:
;;   John Szakmeister <john@szakmeister.net>
;;   Sergey Shishkin <sergei.shishkin@gmail.com>
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

(ns leiningen.file-utils
  (:require [clojure.java.io :as io])
  (:import [org.apache.commons.io FilenameUtils]))

(defn path
  "Returns canonical path of the clojure.java.io/file built with args."
  [& args]
  (.getCanonicalPath (apply io/file args)))

(def less-file-extensions
  "The default extensions for identifying Less CSS files."
  #{"less"})

(defn replace-extension
  "Replace the file extension with another one."
  [filename new-extension]
  (-> filename
      FilenameUtils/removeExtension
      (str "." new-extension)
      io/file))

(defn is-less-file?
  "Check if the file is a Less CSS file by looking at its extension."
  [file]
  (let [file (io/file file)]       
    (and
      (.isFile file)
      (-> file .getName
          (FilenameUtils/isExtension less-file-extensions)))))

(defn list-less-files
  "Recursively inspect the path to discover Less CSS files."
  [file]
  (->> file
       io/file
       file-seq
       (filter is-less-file?)))

(defn canonical-dir-path
  "Returns canonical path of file if it is a directory otherwise that of its parent."
  [file]
  (let [file (io/file file)
        file (if (.isFile file) (.getParentFile file) file)]
    (path file)))

(defn rebase-path
  "Replace base with new-base in path."
  [file base new-base]
  (let [base (canonical-dir-path base)
        new-base (canonical-dir-path new-base)
        relative (-> file path
                     (clojure.string/replace base "."))]
    (path new-base relative)))

