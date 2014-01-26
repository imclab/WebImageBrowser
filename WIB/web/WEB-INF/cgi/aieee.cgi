#!/bin/tcsh -f

echo "Content-type: text/html"
echo ""
echo "<HTML><HEAD>"
echo "<TITLE>Done!</TITLE>"
echo "</HEAD><BODY>"
echo "Thanks for the information!"
cat - | mailx -s "WIB 3.0 Error Log" spl@ncmir.ucsd.edu
echo "</BODY></HTML>"

exit 0
