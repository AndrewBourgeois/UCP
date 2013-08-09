package be.demmel.protocol.ucp.util;

import io.netty.buffer.ByteBuf;

public class Serialization {
	private static final char SEPARATOR = '/'; // 0x2F

	public static Character getCharacter(ByteBuf packetPDU) {
		byte value = packetPDU.readByte();

		if (0x2F == value) {
			return null;
		}// else read the separator and return the value

		if (0x2F != packetPDU.readByte()) {
			throw new IllegalArgumentException("0x2F expected after a character field");
		}

		return (char) value;
	}

	public static String getString(ByteBuf packetPDU) {
		String value = "";
		byte nextChar = -1;
		while ((nextChar = packetPDU.readByte()) != SEPARATOR) {
			value += ((char) nextChar);
		}
		return value.isEmpty() ? null : value;
	}

	public static void serializeString(ByteBuf bb, String value) {
		if (value != null) {
			for (char c : value.toCharArray()) {
				bb.writeByte(c);
			}
		}
		bb.writeByte(SEPARATOR);
	}

	public static void serializeCharacter(ByteBuf bb, Character value) {
		if (value != null) {
			bb.writeByte(value);
		}
		bb.writeByte(SEPARATOR);
	}

	// validates that the next byte is a separator, as expected
	public static void checkSeparator(ByteBuf ucpPacket, String afterFieldName) {
		if (ucpPacket.readByte() != 0x2F) { // the separator is '/' (0x2F)
			throw new IllegalArgumentException("Separator expected after " + afterFieldName);
		}
	}
}
