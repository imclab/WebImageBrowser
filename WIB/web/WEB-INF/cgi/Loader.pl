#!/usr/bin/perl
use strict;

$ENV{'ATLAS_SERVER'} = 'http://image.wholebraincatalog.org:80';
$ENV{'JARPATH'} = '/usr/local/share/ImageManager/SessionIDToURI.jar';
$ENV{'JAVAPATH'} = '/usr/java/default/bin';
$ENV{'METADATA_PATH'} = 'http://ccdb.ucsd.edu/sand/metadata4wib';

$ENV{'ATLAS_PATH'} = "/usr/local/share/incf";
$ENV{'LOG_PATH'} = "/var/tmp/ImageManager.log";
$ENV{'PLUGIN_PATH'} = "/usr/local/share/ImageManager/plugins";
$ENV{'FIFO_PATH'} = "/tmp";
$ENV{'MMAP_PATH'} = "/tmp";
$ENV{'IRODS_CFG'} = "/var/www/html/WebImageBrowser/cgi-bin/irods.cfg";

$ENV{'OMERA_SERVICE'} = "http://ccdb-stage-portal.crbs.ucsd.edu:8081/CellImageLibService/getCellImageInfo";
$ENV{'OMERA_DATA_PATH'} = "irods:/telescience/home/CCDB_DATA_USER.portal/spl/OMERA/Pixels";

$ENV{'DEBUG'} = "False";

exec "/usr/local/bin/ImageManager";