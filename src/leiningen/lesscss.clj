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
  (:import [org.lesscss LessCompiler]))

(defn less-settings
  "Returns a map of LESS project settings."
  [project]
  {:paths (get project :lesscss-paths (.getCanonicalPath (io/file (:root project) "less")))
   :output-path (get project :lesscss-output-path (:compile-path project))})

(defn get-output-file
  "Get the file where to store the compiled output. Its path will depend on the
  relative path in the source tree. For example, if the path where less files
  are stored is '/.../projectdir/less/', the current less file is
  '/.../projectdir/less/foo/bar.less' and the output path is
  '/.../projectdir/target/classes' then the output path will be
  '/.../projectdir/target/classes/foo/bar.less'"
  [{file :file
    base-path :base-path
    output-path :output-path}]
  (let [output-file (rebase-path file base-path output-path)]
    (replace-extension output-file "css")))

(defn should-compile?
  "True if target doesn't exist or is older than source."
  [source target]
  (let [source (io/file source)
        target (io/file target)]
    (or (-> target .exists not)
        (> (.lastModified source) (.lastModified target)))))

(defn lesscss-compile
  "Compile the source file to the output file as specified in task."
  [compiler {file :file output-file :output-file}]
    (when (should-compile? file output-file)
      (try (.compile compiler (io/file file) (io/file output-file))
        (catch org.lesscss.LessException e
          (str "ERROR: compiling " file ": " (.getMessage e))))))

(defn compiler
  "Returns the compiler function."
  [settings]
  (let [compiler (new LessCompiler)]
    (partial lesscss-compile compiler)))

(defn compiler-tasks
  "Returns a sequence of maps, each representing single file compilation call."
  [{paths :paths :as settings}]
  (for [path paths
        file (list-less-files path)
        :let [task (assoc settings :file file :base-path path)]]
    (assoc task :output-file (get-output-file task))))

(defn lesscss
  "Compile Less CSS resources."
  [project & args]
  (let [settings (less-settings project)
        compiler (compiler settings)
        tasks (compiler-tasks settings)
        errors (map compiler tasks)
        errors (->> errors
                    (filter identity)
                    (join "\n"))]
    (when (not-empty errors)
      (main/abort errors))))

