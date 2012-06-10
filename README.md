# Introduction

This plugin allows the user to compile [Less CSS](http://lesscss.org/) resources
to be used in the application.

## Usage

* Add a `:plugins` dependency to `[lein-lesscss "1.0-SNAPSHOT"]` to activate the task.

* Use the `lesscss` task to perform the compilation.

You can specify in your `project.clj` the `:lesscss-paths` attribute as a list
of directories where Less CSS files are stored. By default this parameter is set
to `less`.  You can also specify the output path using `:lesscss-output-path`.

For example:

    ...
    :lesscss-paths ["less" "path/to/other/location"]
    :lesscss-output-path "resource/public/css"
    ...

## Contributors

* Fabio Mancinelli <fabio@mancinelli.me>
* John Szakmeister <john@szakmeister.net>

## License

Copyright © 2012 Fabio Mancinelli <fabio@mancinelli.me>

Distributed under the [LGPLv3 license](http://www.gnu.org/licenses/lgpl-3.0.en.html)
