#!/usr/bin/perl
use strict;

$ENV{ 'DATAPATH' } = '/usr/local/share/incf/atlas';

delete @ENV{ 'IFS', 'CDPATH', 'ENV', 'BASH_ENV' };

exec "/usr/local/bin/niicontour";


