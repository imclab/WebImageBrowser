#!/usr/bin/perl
use strict;

$ENV{'SERVER'} = 'incf-dev-local.crbs.ucsd.edu';
$ENV{'PORT'} = '8080';
$ENV{'CONFIG_XML'} = '/usr/local/share/incf/hubs.xml';

chdir( "/usr/local/share/incf/HubAgent" );

exec "/usr/bin/java -cp . -jar HubAgent.jar"