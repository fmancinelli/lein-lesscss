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

(def less-file-extensions
  "The default extensions for identifying Less CSS files."
  #{"less"})

(defn replace-extension
  "Replace the file extension with another one."
  [filename new-extension]
  (-> filename
      FilenameUtils/removeExtension
      (str "." new-extension)))

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
  [path]
  (->> path
       io/file
       file-seq
       (filter is-less-file?)))

