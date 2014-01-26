#!/usr/bin/perl
use strict;

delete @ENV{ 'IFS', 'CDPATH', 'ENV', 'BASH_ENV' };
$ENV{ 'PATH' } = '/usr/local/share/incf/atlas:/bin:/usr/bin';
$ENV{ 'TIMEOUT' } = '60';

print "Content-type: text/xml\n";
print "Content-Encoding: gzip\n";
print "Cache-Control: no-cache\n\n";

exec "/usr/local/bin/SLASHdb";
