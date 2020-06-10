/*
 * Copyright(c)1994, 2013, Oracle and/or its affiliates.
 * All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL.
 * Use is subject to license terms
 */
package sample;

/**
 * The Boolean class wraps a value of the primitive type
 * {@code boolean} in an object. An object of type
 * 
 * @author eeb-tosh-16
 *
 */

public final class Boolean implements java.io.Serializable,
										  Comparable<Boolean>
{
	public static final Boolean TRUE = new Boolean(true);
	public static final Boolean FALSE = new Boolean(false);
	
}