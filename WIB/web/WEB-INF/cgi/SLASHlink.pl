#!/usr/bin/perl
use strict;

delete @ENV{ 'IFS', 'CDPATH', 'ENV', 'BASH_ENV' };

$ENV{'BASE'} = "/home/spl/src/java/WIB/build/web";

print "Content-type: text/xml\n\n";

exec "/usr/local/bin/SLASHlink";