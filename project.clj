;; Leiningen Less CSS compile task.
;; Copyright 2012 Fabio Mancinelli <fabio@mancinelli.me>
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

(defproject com.michaelnygard/lein-lesscss "1.5.1-SNAPSHOT"
  :description "Leiningen plugin for compiling Less CSS files."
  :url "https://github.com/mtnygard/lein-lesscss"
  :license {:name "LGPLv3"
            :url "http://www.gnu.org/licenses/lgpl-3.0.en.html"}
  :scm {:name "git"
        :url "https://github.com/mtnygard/lein-lesscss"}
  :dependencies [[com.asual.lesscss/lesscss-engine "1.5.0"]]
  :eval-in-leiningen true)
