#!/usr/bin/perl
use strict;

delete @ENV{ 'IFS', 'CDPATH', 'ENV', 'BASH_ENV' };

print "Content-type: text/xml\n\n";

exec "/home/spl/src/imaging/ctalign/ctalign";
