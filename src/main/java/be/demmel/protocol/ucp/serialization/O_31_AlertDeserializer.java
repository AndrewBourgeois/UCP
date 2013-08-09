package be.demmel.protocol.ucp.serialization;

import io.netty.buffer.ByteBuf;
import be.demmel.protocol.ucp.O_31_Alert;

public class O_31_AlertDeserializer implements UCPOperationDeserializer<O_31_Alert> {
	@Override
	public O_31_Alert deserialize(ByteBuf bb) {
		String adc = getString(bb);
		String pid = getString(bb);
		return new O_31_Alert(adc, pid);
	}

	private static String getString(ByteBuf packetPDU) {
		String value = "";
		byte nextChar = -1;
		while ((nextChar = packetPDU.readByte()) != 0x2F) { // read until a '/' is encountered
			value += ((char) nextChar);
		}
		return value;
	}
}
