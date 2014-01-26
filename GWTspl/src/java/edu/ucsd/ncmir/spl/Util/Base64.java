package edu.ucsd.ncmir.spl.Util;

//By saher hassan 
public class Base64

{
    
    private final static String _etab = 
	"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
    
    // Found at
    // http://sahers.wordpress.com/2011/08/04/gwt-base64-decoding-into-binary-supports-unsigned-byte-data/

    //By saher hassan 

    public static byte[] decode( String s ) 

    {
	
	// remove/ignore any characters not in the base64 characters list
	// or the pad character -- particularly newlines
	s = s.replaceAll( "[^" + Base64._etab + "=]", "" );
	
	// replace any incoming padding with a zero pad ( the 'A' character is
	// zero )

	String p = 
	    ( s.charAt( s.length() - 1 ) == '=' ? 
	      ( s.charAt( s.length() - 2 ) == '=' ? "AA" : "A" ) : "" );
	
	s = s.substring( 0, s.length() - p.length() ) + p;

	int res_length = ( int ) Math.ceil( ( s.length() / 4.0 ) * 3.0 );
        
	byte[] out = new byte[res_length];
	int out_i = 0;
	
	// increment over the length of this encrypted string, four characters
	// at a time

	for ( int c = 0; c < s.length(); c += 4 ) {
	    
	    // each of these four characters represents a 6-bit index in the
	    // base64 characters list which, when concatenated, will give the
	    // 24-bit number for the original 3 characters
	    int n = ( Base64._etab.indexOf( s.charAt( c ) ) << 18 )
		+ ( Base64._etab.indexOf( s.charAt( c + 1 ) ) << 12 )
		+ ( Base64._etab.indexOf( s.charAt( c + 2 ) ) << 6 )
		+ Base64._etab.indexOf( s.charAt( c + 3 ) );
	    
	    // split the 24-bit number into the original three 8-bit ( ASCII )
	    // characters
	    
	    char c1 = ( char ) ( ( n >>> 16 ) & 0xFF );
	    char c2 = ( char ) ( ( n >>>8 ) & 0xFF );
	    char c3 = ( char ) ( n & 0xFF );
	    
	    out[out_i++] = ( byte ) c1;
	    out[out_i++] = ( byte ) c2;
	    out[out_i++] = ( byte ) c3;
	    
	}
	
	return out;
        
    }

    // Found at https://snipt.net/tweakt/gwt-base64/

    public static String encode( byte[] data ) 

    {

        StringBuilder out = new StringBuilder();
        
        int i = 0;
        int r = data.length;
        while ( r > 0 ) {

            byte d0 = data[i++]; --r;
            byte e0 = ( byte ) ( d0 >>> 2 );
            byte e1 = ( byte ) ( ( d0 & 0x03 ) << 4 );
            byte e2;
            byte e3;
            
            if ( r > 0 ) {

                byte d1 = data[i++]; --r;
                e1 += ( d1 >>> 4 );
                e2 = ( byte ) ( ( d1 & 0x0f ) << 2 );

            } else
                e2 = 64;
            
            if ( r > 0 ) {

                byte d2 = data[i++]; --r;
                e2 += ( d2 >>> 6 );
                e3 = ( byte ) ( d2 & 0x3f );

            } else
                e3 = 64;

            out.append( Base64._etab.charAt( e0 ) );
            out.append( Base64._etab.charAt( e1 ) );
            out.append( Base64._etab.charAt( e2 ) );
            out.append( Base64._etab.charAt( e3 ) );

        }
	
        return out.toString();

    }

}





