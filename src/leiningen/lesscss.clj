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

(ns leiningen.lesscss
  (:use [leiningen.file-utils]
        [clojure.string :only [join]])
  (:require [leiningen.core.main :as main]
            [clojure.java.io :as io])
  (:import [org.apache.commons.io FilenameUtils]))

;; Create an instance of the Less CSS compiler.
(def lesscss-compiler (delay (new org.lesscss.LessCompiler)))

;; Return a list containing a single path where Less files are stored.
(defn default-lesscss-paths [project]
  (cons (FilenameUtils/normalize (str (:root project) "/less")) nil))

(defn get-output-file
  "Get the file where to store the compiled output. Its path will depend on the
  relative path in the source tree. For example, if the path where less files
  are stored is '/.../projectdir/less/', the current less file is
  '/.../projectdir/less/foo/bar.less' and the output path is
  '/.../projectdir/target/classes' then the output path will be
  '/.../projectdir/target/classes/foo/bar.less'"
  [base-path file output-path]
  (let [output-file (rebase-path file base-path output-path)]
    (replace-extension output-file "css")))

;; Compile the Less CSS file to the specified output file.
(defn lesscss-compile [project prefix less-file output-path]
  (let [output-file (io/file (get-output-file prefix less-file output-path))]
    (try
      (when (or (not (.exists output-file))
                (> (.lastModified less-file) (.lastModified output-file)))
        (.compile @lesscss-compiler less-file output-file))
      (catch org.lesscss.LessException e
        (str "ERROR: compiling " less-file ": " (.getMessage e))))))


;; Leiningen task. 
(defn lesscss "Compile Less CSS resources." [project & args]
  (let [lesscss-paths (:lesscss-paths project (default-lesscss-paths project)) 
        lesscss-output-path (or (:lesscss-output-path project)
                                (:compile-path project))
        ]
    ;; Iterate over all the Less CSS files and compile them
    (doseq [less-path lesscss-paths]
      (let [less-files (list-less-files less-path)
            errors (doall
                     (filter identity
                             (for [less-file less-files]
                             (lesscss-compile project less-path less-file lesscss-output-path))))]
        (if (not-empty errors)
          (main/abort (join "\n" errors)))))))
