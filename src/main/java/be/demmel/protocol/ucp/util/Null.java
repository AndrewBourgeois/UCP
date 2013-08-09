package be.demmel.protocol.ucp.util;

public class Null {
	public static String check(String value, String name) {
		if(value == null) {
			throw new NullPointerException(name + " cannot be null");
		}
		return value;
	}
}
