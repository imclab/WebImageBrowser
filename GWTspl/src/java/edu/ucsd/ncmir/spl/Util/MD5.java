package edu.ucsd.ncmir.spl.Util;

/**<pre>
 *  Copyright (C) 2000 by Robert Hubley.                                      
 *  All rights reserved.                                                      
 *                                                                            
 *  This software is provided ``AS IS'' and any express or implied            
 *  warranties, including, but not limited to, the implied warranties of      
 *  merchantability and fitness for a particular purpose, are disclaimed.     
 *  In no event shall the authors be liable for any direct, indirect,         
 *  incidental, special, exemplary, or consequential damages (including, but  
 *  not limited to, procurement of substitute goods or services; loss of use, 
 *  data, or profits; or business interruption) however caused and on any     
 *  theory of liability, whether in contract, strict liability, or tort       
 *  (including negligence or otherwise) arising in any way out of the use of  
 *  this software, even if advised of the possibility of such damage.         
 *                                                                            
 *
 *
 * CLASS: MD5
 *
 * DESCRIPTION:
 *    This is a class which encapsulates a set of MD5 Message Digest functions.
 *    MD5 algorithm produces a 128 bit digital fingerprint (signature) from an
 *    dataset of arbitrary length.  For details see RFC 1321 (summarized below).
 *    This implementation is derived from the RSA Data Security, Inc. MD5 Message-Digest
 *    algorithm reference implementation (originally written in C)
 *
 * AUTHOR:
 *    Robert M. Hubley 1/2000
 * MODIFIED TO CONFORM TO PERSONAL CODING STYLE BY:
 *    Steve Lamont (spl@ucsd.edu) 2/12/2013
 *
 *
 * NOTES:
 *     Network Working Group                                    R. Rivest
 *     Request for Comments: 1321     MIT Laboratory for Computer Science
 *                                            and RSA Data Security, Inc.
 *                                                             April 1992
 *
 *
 *                          The MD5 Message-Digest Algorithm
 *
 *     Summary
 *
 *        This document describes the MD5 message-digest algorithm. The
 *        algorithm takes as input a message of arbitrary length and produces
 *        as output a 128-bit "fingerprint" or "message digest" of the input.
 *        It is conjectured that it is computationally infeasible to produce
 *        two messages having the same message digest, or to produce any
 *        message having a given prespecified target message digest. The MD5
 *        algorithm is intended for digital signature applications, where a
 *        large file must be "compressed" in a secure manner before being
 *        encrypted with a private (secret) key under a public-key cryptosystem
 *        such as RSA.
 *
 *        The MD5 algorithm is designed to be quite fast on 32-bit machines. In
 *        addition, the MD5 algorithm does not require any large substitution
 *        tables; the algorithm can be coded quite compactly.
 *
 *        The MD5 algorithm is an extension of the MD4 message-digest algorithm
 *        1,2]. MD5 is slightly slower than MD4, but is more "conservative" in
 *        design. MD5 was designed because it was felt that MD4 was perhaps
 *        being adopted for use more quickly than justified by the existing
 *        critical review; because MD4 was designed to be exceptionally fast,
 *        it is "at the edge" in terms of risking successful cryptanalytic
 *        attack. MD5 backs off a bit, giving up a little in speed for a much
 *        greater likelihood of ultimate security. It incorporates some
 *        suggestions made by various reviewers, and contains additional
 *        optimizations. The MD5 algorithm is being placed in the public domain
 *        for review and possible adoption as a standard.
 *
 *        RFC Author:
 *        Ronald L.Rivest
 *        Massachusetts Institute of Technology
 *        Laboratory for Computer Science
 *        NE43 -324545    Technology Square
 *        Cambridge, MA  02139-1986
 *        Phone: (617) 253-5880
 *        EMail:    Rivest@ theory.lcs.mit.edu
 *
 *
 *
 * CHANGE HISTORY:
 *
 *    0.1.0  RMH    1999/12/29      Original version
 *</pre>
 */

public final class MD5

{

    private static final long S11 = 7L;
    private static final long S12 = 12L;
    private static final long S13 = 17L;
    private static final long S14 = 22L;
    private static final long S21 = 5L;
    private static final long S22 = 9L;
    private static final long S23 = 14L;
    private static final long S24 = 20L;
    private static final long S31 = 4L;
    private static final long S32 = 11L;
    private static final long S33 = 16L;
    private static final long S34 = 23L;
    private static final long S41 = 6L;
    private static final long S42 = 10L;
    private static final long S43 = 15L;
    private static final long S44 = 21L;
    
    private static final char[] pad = new char[] {
	128, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	0, 0, 0, 0, 0, 0, 0, 0, 0
    };
    
    private boolean _closed = false;

    private char _byte_buffer[] = new char[64];
    private long _state[] = new long[4];
    private long _byte_count;
    
    //
    // Constructor
    //

    /**
     * Constructs the <code>MD5</code> object and initializes the
     * state.
     */
    public MD5()

    {

	this._byte_count = 0;
  	this._state[0] = 0x67452301L;
  	this._state[1] = 0xefcdab89L;
  	this._state[2] = 0x98badcfeL;
  	this._state[3] = 0x10325476L;
 
    }
    
    /**
     * Adds a <code>String</code> to the sum.
     * @param s The <code>String</code>
     * @throws Exception Thrown if the sum is already closed.
     */
    public void update( String s ) 
        throws Exception

    {

	char[] data = s.toCharArray();
	
	this.update( data, data.length );

    }

    /**
     * Adds a collection of bytes to the sum.
     * @param byte_input The 8 bit bytes, encoded as <code>char</code>
     * because Java doesn't give us an <code>unsigned byte</code> type
     * as any sane language would.
     * @param length The number of bytes to be added to the sum.
     * @throws Exception Thrown if we try to update an already closed
     * sum.
     */
    public void update( char[] byte_input, long length )
        throws Exception

    {

        if ( this._closed )
            throw new Exception( "Unable to update closed MD5 sum." );
        
	this._byte_count += length;
        
	int index = ( int ) ( this._byte_count % 64 );
	int part_length = 64 - index;
	
	int i;

	if ( length >= part_length ) {
	    
	    for ( int j = 0; j < part_length; ++j ) 
		this._byte_buffer[j + index] = byte_input[j]; 
	    MD5.transform( this._state, this._byte_buffer );
	    
	    for ( i = part_length; i + 63 < length; i += 64 ) {
		
		for ( int j = 0; j < 64; ++j ) 
		    this._byte_buffer[j] = byte_input[j+i]; 
		MD5.transform( this._state, this._byte_buffer );
		
	    }
	    index = 0;
	    
	} else
	    i = 0;
	
	for ( int j = 0; j < length - i; ++j ) 
	    this._byte_buffer[index + j] = byte_input[i + j];
	
    }

    /**
     * Converts the <code>MD5</code> sum to a hexadecimal string,
     * closing the sum if necessary.
     * @return The hex <code>String</code>
     * @throws Exception Should theoretically never happen.
     */
    public String toHexString()
        throws Exception

    {

        if ( !this._closed )
            this.close();
        
	String s = "";

	for ( int j = 0; j < 4; ++j )
	    for ( int i = 0; i < 32; i += 8 ) {
		
		long b = ( this._state[j] >>> i ) & 0xFFL;
                
		if ( b < 16 )
		    s += "0" + Long.toHexString( b );
		else
		    s += Long.toHexString( b ); 

	    }

	return s;

    }
    
    /**
     * Closes the <code>MD5</code> sum.
     * @throws Exception Thrown if the sum is already closed.
     */
    public void close() 
        throws Exception

    {
        
	char[] chars = new char[8];
	long bits = this._byte_count * 8;
	
	chars[0] = ( char ) ( bits & 0xffL );
	chars[1] = ( char ) ( ( bits >>> 8 ) & 0xffL );
	chars[2] = ( char ) ( ( bits >>> 16 ) & 0xffL );
	chars[3] = ( char ) ( ( bits >>> 24 ) & 0xffL );
	chars[4] = ( char ) ( ( bits >>> 32 ) & 0xffL );
	chars[5] = ( char ) ( ( bits >>> 40 ) & 0xffL );
	chars[6] = ( char ) ( ( bits >>> 48 ) & 0xffL );
	chars[7] = ( char ) ( ( bits >>> 56 ) & 0xffL );
	
	int index = ( int ) this._byte_count%64;

	int pad_length = ( index < 56 ) ? ( 56 - index ) : ( 120 - index );

	this.update( pad, pad_length );
	this.update( chars, 8 );	   
        
        this._closed = true;
	
    }
    
    //
    // Private Methods
    //

    private static void transform( long[] state, char[] byte_block )
        
    {

	long A = state[0];
	long B = state[1];
	long C = state[2];
	long D = state[3];

	long[] x = MD5.decode( byte_block );
	
	/* Round 1 */
	A = MD5.ff( A, B, C, D, x[ 0], MD5.S11, 0xd76aa478L ); /*  1 */
	D = MD5.ff( D, A, B, C, x[ 1], MD5.S12, 0xe8c7b756L ); /*  2 */
	C = MD5.ff( C, D, A, B, x[ 2], MD5.S13, 0x242070dbL ); /*  3 */
	B = MD5.ff( B, C, D, A, x[ 3], MD5.S14, 0xc1bdceeeL ); /*  4 */
	A = MD5.ff( A, B, C, D, x[ 4], MD5.S11, 0xf57c0fafL ); /*  5 */
	D = MD5.ff( D, A, B, C, x[ 5], MD5.S12, 0x4787c62aL ); /*  6 */
	C = MD5.ff( C, D, A, B, x[ 6], MD5.S13, 0xa8304613L ); /*  7 */
	B = MD5.ff( B, C, D, A, x[ 7], MD5.S14, 0xfd469501L ); /*  8 */
	A = MD5.ff( A, B, C, D, x[ 8], MD5.S11, 0x698098d8L ); /*  9 */
	D = MD5.ff( D, A, B, C, x[ 9], MD5.S12, 0x8b44f7afL ); /* 10 */
	C = MD5.ff( C, D, A, B, x[10], MD5.S13, 0xffff5bb1L ); /* 11 */
	B = MD5.ff( B, C, D, A, x[11], MD5.S14, 0x895cd7beL ); /* 12 */
	A = MD5.ff( A, B, C, D, x[12], MD5.S11, 0x6b901122L ); /* 13 */
	D = MD5.ff( D, A, B, C, x[13], MD5.S12, 0xfd987193L ); /* 14 */
	C = MD5.ff( C, D, A, B, x[14], MD5.S13, 0xa679438eL ); /* 15 */
	B = MD5.ff( B, C, D, A, x[15], MD5.S14, 0x49b40821L ); /* 16 */
	
	/* Round 2 */
	A = MD5.gg( A, B, C, D, x[ 1], MD5.S21, 0xf61e2562L ); /* 17 */
	D = MD5.gg( D, A, B, C, x[ 6], MD5.S22, 0xc040b340L ); /* 18 */
	C = MD5.gg( C, D, A, B, x[11], MD5.S23, 0x265e5a51L ); /* 19 */
	B = MD5.gg( B, C, D, A, x[ 0], MD5.S24, 0xe9b6c7aaL ); /* 20 */
	A = MD5.gg( A, B, C, D, x[ 5], MD5.S21, 0xd62f105dL ); /* 21 */
	D = MD5.gg( D, A, B, C, x[10], MD5.S22, 0x02441453L ); /* 22 */
	C = MD5.gg( C, D, A, B, x[15], MD5.S23, 0xd8a1e681L ); /* 23 */
	B = MD5.gg( B, C, D, A, x[ 4], MD5.S24, 0xe7d3fbc8L ); /* 24 */
	A = MD5.gg( A, B, C, D, x[ 9], MD5.S21, 0x21e1cde6L ); /* 25 */
	D = MD5.gg( D, A, B, C, x[14], MD5.S22, 0xc33707d6L ); /* 26 */
	C = MD5.gg( C, D, A, B, x[ 3], MD5.S23, 0xf4d50d87L ); /* 27 */
	B = MD5.gg( B, C, D, A, x[ 8], MD5.S24, 0x455a14edL ); /* 28 */
	A = MD5.gg( A, B, C, D, x[13], MD5.S21, 0xa9e3e905L ); /* 29 */
	D = MD5.gg( D, A, B, C, x[ 2], MD5.S22, 0xfcefa3f8L ); /* 30 */
	C = MD5.gg( C, D, A, B, x[ 7], MD5.S23, 0x676f02d9L ); /* 31 */
	B = MD5.gg( B, C, D, A, x[12], MD5.S24, 0x8d2a4c8aL ); /* 32 */
	
	/* Round 3 */
	A = MD5.hh( A, B, C, D, x[ 5], MD5.S31, 0xfffa3942L ); /* 33 */
	D = MD5.hh( D, A, B, C, x[ 8], MD5.S32, 0x8771f681L ); /* 34 */
	C = MD5.hh( C, D, A, B, x[11], MD5.S33, 0x6d9d6122L ); /* 35 */
	B = MD5.hh( B, C, D, A, x[14], MD5.S34, 0xfde5380cL ); /* 36 */
	A = MD5.hh( A, B, C, D, x[ 1], MD5.S31, 0xa4beea44L ); /* 37 */
	D = MD5.hh( D, A, B, C, x[ 4], MD5.S32, 0x4bdecfa9L ); /* 38 */
	C = MD5.hh( C, D, A, B, x[ 7], MD5.S33, 0xf6bb4b60L ); /* 39 */
	B = MD5.hh( B, C, D, A, x[10], MD5.S34, 0xbebfbc70L ); /* 40 */
	A = MD5.hh( A, B, C, D, x[13], MD5.S31, 0x289b7ec6L ); /* 41 */
	D = MD5.hh( D, A, B, C, x[ 0], MD5.S32, 0xeaa127faL ); /* 42 */
	C = MD5.hh( C, D, A, B, x[ 3], MD5.S33, 0xd4ef3085L ); /* 43 */
	B = MD5.hh( B, C, D, A, x[ 6], MD5.S34, 0x04881d05L ); /* 44 */
	A = MD5.hh( A, B, C, D, x[ 9], MD5.S31, 0xd9d4d039L ); /* 45 */
	D = MD5.hh( D, A, B, C, x[12], MD5.S32, 0xe6db99e5L ); /* 46 */
	C = MD5.hh( C, D, A, B, x[15], MD5.S33, 0x1fa27cf8L ); /* 47 */
	B = MD5.hh( B, C, D, A, x[ 2], MD5.S34, 0xc4ac5665L ); /* 48 */
	
	/* Round 4 */
	A = MD5.ii( A, B, C, D, x[ 0], MD5.S41, 0xf4292244L ); /* 49 */
	D = MD5.ii( D, A, B, C, x[ 7], MD5.S42, 0x432aff97L ); /* 50 */
	C = MD5.ii( C, D, A, B, x[14], MD5.S43, 0xab9423a7L ); /* 51 */
	B = MD5.ii( B, C, D, A, x[ 5], MD5.S44, 0xfc93a039L ); /* 52 */
	A = MD5.ii( A, B, C, D, x[12], MD5.S41, 0x655b59c3L ); /* 53 */
	D = MD5.ii( D, A, B, C, x[ 3], MD5.S42, 0x8f0ccc92L ); /* 54 */
	C = MD5.ii( C, D, A, B, x[10], MD5.S43, 0xffeff47dL ); /* 55 */
	B = MD5.ii( B, C, D, A, x[ 1], MD5.S44, 0x85845dd1L ); /* 56 */
	A = MD5.ii( A, B, C, D, x[ 8], MD5.S41, 0x6fa87e4fL ); /* 57 */
	D = MD5.ii( D, A, B, C, x[15], MD5.S42, 0xfe2ce6e0L ); /* 58 */
	C = MD5.ii( C, D, A, B, x[ 6], MD5.S43, 0xa3014314L ); /* 59 */
	B = MD5.ii( B, C, D, A, x[13], MD5.S44, 0x4e0811a1L ); /* 60 */
	A = MD5.ii( A, B, C, D, x[ 4], MD5.S41, 0xf7537e82L ); /* 61 */
	D = MD5.ii( D, A, B, C, x[11], MD5.S42, 0xbd3af235L ); /* 62 */
	C = MD5.ii( C, D, A, B, x[ 2], MD5.S43, 0x2ad7d2bbL ); /* 63 */
	B = MD5.ii( B, C, D, A, x[ 9], MD5.S44, 0xeb86d391L ); /* 64 */
	
	state[0] = ( state[0] + A ) & 0xFFFFFFFFL;
	state[1] = ( state[1] + B ) & 0xFFFFFFFFL;
	state[2] = ( state[2] + C ) & 0xFFFFFFFFL;
	state[3] = ( state[3] + D ) & 0xFFFFFFFFL;
	
    }
    
    private static long[] decode( char[] byte_block )

    {
        
	long[] block = new long[16];

	int j = 0;

	for ( int i = 0; i < byte_block.length; i += 4 )
	    block[j++] =
		byte_block[i] +
		byte_block[i+1] * 256L + 
		byte_block[i+2] * 65536L + 
		byte_block[i+3] * 16777216L;

	return block;
        
    }
    
    private static long ff( long A, long B, long C, long D,
			    long X, long S, long AC )

    {

	A = ( A + ( B & C | ( ~B ) & D ) + X + AC ) & 0xFFFFFFFFL;
	A = ( ( A << S ) | ( A >>> ( 32L-S ) ) ) & 0xFFFFFFFFL;
	A = ( A + B ) & 0xFFFFFFFFL;

	return A;

    }
    
    private static long gg( long A, long B, long C, long D,
			    long X, long S, long AC )

    {

	A = ( A + ( B & D | C & ~D ) + X + AC ) & 0xFFFFFFFFL;
	A = ( ( A << S ) | ( A >>> ( 32L-S ) ) ) & 0xFFFFFFFFL;
	A = ( A + B ) & 0xFFFFFFFFL;

	return A;

    }
    
    private static long hh( long A, long B, long C, long D,
			    long X, long S, long AC )

    {
	
	A = ( A + ( B ^ C ^ D ) + X + AC ) & 0xFFFFFFFFL;
	A = ( ( A << S ) | ( A >>> ( 32L-S ) ) ) & 0xFFFFFFFFL;
	A = ( A + B ) & 0xFFFFFFFFL;

	return A;
	
    }
    
    private static long ii( long A, long B, long C, long D,
			    long X, long S, long AC )

    {

	A = ( A + ( C ^ ( B | ~D ) ) + X + AC ) & 0xFFFFFFFFL;
	A = ( ( A << S ) | ( A >>> ( 32L-S ) ) ) & 0xFFFFFFFFL;
	A = ( A + B ) & 0xFFFFFFFFL;

	return A;

    }
        
}
