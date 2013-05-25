# Introduction

This plugin allows the user to compile [Less CSS](http://lesscss.org/) resources
to be used in the application.

## Usage

* This is my "private" fork of the project. See the original one at http://github.com/fmancinelli/lein-lesscss or use at your own risk.

* Add `[org.clojars.shishkin/lein-lesscss "1.3.3"]` to the  `:plugins` section in your `project.clj` or `~/.lein/profiles.clj` (the latter is Leiningen 2-specific).

* Use the `lesscss` task to perform the compilation.

You can specify in your `project.clj` the `:lesscss-paths` attribute as a list
of files or directories where Less CSS files are stored. By default this parameter is set
to `less`. You can also specify the output path using `:lesscss-output-path`. LESS compression
can be turned on with `:lesscss-compress true`. Compression is turned off by default.

For example:

    ...
    :lesscss-paths ["less" "path/to/other/location/style.less"]
    :lesscss-output-path "resource/public/css"
    :lesscss-compress true
    ...

## Contributors

* Fabio Mancinelli <fabio@mancinelli.me>
* John Szakmeister <john@szakmeister.net>
* Sergey Shishkin <sergei.shishkin@gmail.com>

## License

Copyright © 2012 Fabio Mancinelli <fabio@mancinelli.me>

Distributed under the [LGPLv3 license](http://www.gnu.org/licenses/lgpl-3.0.en.html)
