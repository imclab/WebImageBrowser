#!/usr/bin/perl
use strict;

delete @ENV{ 'IFS', 'CDPATH', 'ENV', 'BASH_ENV' };
$ENV{ 'ATLAS_PATH' } = '/usr/local/share/incf/atlas';

exec "/usr/local/bin/atlas_info";