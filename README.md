game-of-life
============

Conway's Game of Life implemented in Scala.

SYNOPSIS
--------

    sbt "run --tui 80 24 23 3"
    sbt "run --gui"

DESCRIPTION
-----------

game-of-life has two modes:

- GUI
- TUI

GUI mode does not take any arguments for now.

TUI mode takes 4 arguments: width of the board, height of the board, the survival rules and the birth rules.

DEPENDENCIES
------------

- scala
- sbt

KNOWN ISSUES
------------

The GUI mode does not refresh correctly on Windows.

LICENSE
-------

Copyright (C) 2015 Dorota Celinska

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 3
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program. If not, see <http://www.gnu.org/licenses/>.
