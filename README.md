# Introduction

[![Build Status](https://travis-ci.org/mtnygard/lein-lesscss.png)](https://travis-ci.org/mtnygard/lein-lesscss)

This plugin allows the user to compile [Less CSS](http://lesscss.org/) resources
to be used in the application.

## Usage

* This is a fork of a fork.
* See the original one at http://github.com/fmancinelli/lein-lesscss
  or use at your own risk.
* The original was forked by Sergey Shiskin at
  https://github.com/shishkin/lein-lesscss with some nice
  features. (Collected config into a single map, allows a single
  starting file. Good for Bootstrap!)

* Add `[com.michaelnygard/lein-lesscss "1.5.1"]` to the  `:plugins` section in your `project.clj`.

* Use the `lesscss` task to perform the compilation.

You can specify in your `project.clj` a `:lesscss` key for the
configuration. The `:paths` key is a list of files or directories
where Less CSS files are stored. By default this parameter is set to
`less`. You can also specify the output path using
`:output-path`. LESS compression can be turned on with `:compress
true`. Compression is off by default.

For example:

    ...
    :lesscss {:paths       ["less" "path/to/other/location/style.less"]
              :output-path "resource/public/css"
              :compress    true}
    ...

## Contributors

* Fabio Mancinelli <fabio@mancinelli.me>
* John Szakmeister <john@szakmeister.net>
* Sergey Shishkin <sergei.shishkin@gmail.com>
* Michael Nygard <mtnygard@gmail.com>

## License

Copyright © 2012 Fabio Mancinelli <fabio@mancinelli.me>

Distributed under the [LGPLv3 license](http://www.gnu.org/licenses/lgpl-3.0.en.html)
