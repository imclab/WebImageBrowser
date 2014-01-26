#!/usr/bin/perl
use strict;

$ENV{'LOG_PATH'} = "/var/tmp/db_manager.log";
$ENV{'DB_PATH'} = "/usr/local/share/wibdb/annotation";

exec "/usr/local/bin/wib_ontology_db_manager";