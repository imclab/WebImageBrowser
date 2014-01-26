#!/usr/bin/perl
use strict;

$ENV{'LOG_PATH'} = "/var/tmp/wibagent.log";
$ENV{'QUERY_SERVER'} = "image.wholebraincatalog.org";
$ENV{'SERVLET'} = "atlas-serverside/servlet/ImageAssemblyServlet";

exec "/usr/local/bin/wibagent";



