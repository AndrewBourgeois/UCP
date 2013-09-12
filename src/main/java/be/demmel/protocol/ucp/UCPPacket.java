package be.demmel.protocol.ucp;

/**
 * The structure of an UCP packet is as follows:
 * <stx><header>/<data>/<checksum><etx>
 * 
 * <stx> = the STX (Start Of Text) character (0x02)
 * <header> = a structure with the following mandatory fields:
 * - TRN (Transaction Reference Number): 2 numeric characters
 * - LEN: 5 numerical characters. The amount of characters between the <stx> and <etx> characters.
 * - O/R: The 'O' (0x4F) or 'R' (0x52) character, which respectively stands for Operation and Result.
 * - OT: 2 numerical characters. The Operation Type.
 * <data> = a structure where the fields depend on the "OT" parameter of the <header>
 * <checksum> = the 8 LSB (Least Significant Bits) of the sum of all bytes of a packet
 * <etx> = the ETX (End Of Text) character (0x03)
 * 
 * A "/" (0x2F) is used to separate every field (including the fields inside the <header> and <data>).
 * 
 * Numeric characters are encoded as in IRA (International Reference Alphabet)
 * Alphanumeric characters are encoded as two numeric IRA characters (the higher 3 bits (0...7) first, the lower 4 bits (0...F) last). 
 */
public class UCPPacket {
	private UCPOperationHeader header;
	private UCPOperation data;
	
	public UCPPacket(UCPOperationHeader header, UCPOperation data) {
		this.header = header;
		this.data = data;
	}
	
	public UCPOperationHeader getHeader() {
		return header;
	}
	
	public UCPOperation getData() {
		return data;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("UCPPacket [header=");
		builder.append(header);
		builder.append(", data=");
		builder.append(data);
		builder.append("]");
		return builder.toString();
	}
}
