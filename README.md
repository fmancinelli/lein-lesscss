# Introduction

This plugin allows the user to compile [Less CSS](http://lesscss.org/) resources
to be used in the application.

## Usage

* Add `[lein-lesscss "1.2"]` to the  `:plugins` section in your `project.clj` or `~/.lein/profiles.clj` (the latter is Leiningen 2-specific).

* Use the `lesscss` task to perform the compilation.

The `lesscss` task supports two commands: ```auto``` and ```once```
The ```auto``` command will auto-compile the less directory when any changes
to the less occur. The ```once``` command will compile the less directory once.

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
