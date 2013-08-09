package be.demmel.protocol.ucp.serialization;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.demmel.protocol.ucp.NegativeResponse;
import be.demmel.protocol.ucp.PositiveResponse;
import be.demmel.protocol.ucp.PositiveResponseWithMvp;
import be.demmel.protocol.ucp.UCPOperation;
import be.demmel.protocol.ucp.UCPOperationHeader;
import be.demmel.protocol.ucp.UCPOperationType;
import be.demmel.protocol.ucp.UCPPacket;

public class UCPPacketSerializerImpl {
	private static final Logger LOG = LoggerFactory.getLogger(UCPPacketSerializerImpl.class);
	private static final char DELIMITER = '/'; // 0x2F
	private final Map<UCPOperationType, UCPOperationSerializer> operationSerializers;

	public UCPPacketSerializerImpl(Map<UCPOperationType, UCPOperationSerializer> operationSerializers) {
		this.operationSerializers = operationSerializers;
	}

	public ByteBuf serialize(UCPPacket ucpPacket) {
		ByteBuf bb = Unpooled.buffer();
		bb.writeByte(0x02 /* STX */);

		// START - Header
		UCPOperationHeader header = ucpPacket.getHeader();

		int trn = header.getTrn();
		String trnString = String.format("%02d", trn);
		bb.writeByte(trnString.charAt(0));
		bb.writeByte(trnString.charAt(1));
		bb.writeByte(DELIMITER);

		bb.writerIndex(9); // skip the length for now
		bb.writeByte(DELIMITER);

		UCPOperationHeader.Type type = header.getType();
		bb.writeByte(type == UCPOperationHeader.Type.OPERATION ? 'O' : 'R');
		bb.writeByte(DELIMITER);

		UCPOperationType operation = header.getOperation();
		byte operationValue = operation.getValue();
		String operationValueString = String.format("%02d", operationValue);
		bb.writeByte(operationValueString.charAt(0));
		bb.writeByte(operationValueString.charAt(1));
		bb.writeByte(DELIMITER);
		// END - Header

		if (UCPOperationHeader.Type.OPERATION == type) {
			UCPOperationSerializer serializer = this.operationSerializers.get(operation);

			if (serializer == null) {
				throw new UnsupportedOperationException("UCPOperationType \"" + operation + "\" unsupported (operation)");
			}

			ByteBuf serializedData = serializer.serialize(ucpPacket.getData());
			bb.writeBytes(serializedData);
		} else {// result
			UCPOperation data = ucpPacket.getData();

			if (data instanceof NegativeResponse) {
				bb.writeByte('N');
				bb.writeByte('/');

				NegativeResponse negativeResponse = (NegativeResponse) data;
				String ec = negativeResponse.getEc();

				if (ec != null) {
					for (char c : ec.toCharArray()) {
						bb.writeByte(c);
					}
				}
				bb.writeByte('/');

				String sm = negativeResponse.getSm();

				if (sm != null) {
					for (char c : sm.toCharArray()) {
						bb.writeByte(c);
					}
				}
				bb.writeByte('/');
			} else {// positive response
				bb.writeByte('A');
				bb.writeByte('/');
				
				PositiveResponse positiveResponse = (PositiveResponse) data;
				
				if (positiveResponse instanceof PositiveResponseWithMvp) {
					PositiveResponseWithMvp positiveResponseWithMvp = (PositiveResponseWithMvp) positiveResponse;
					
					String mvp = positiveResponseWithMvp.getMvp();
					
					if (mvp != null) {
						for (char c : mvp.toCharArray()) {
							bb.writeByte(c);
						}
					}
					bb.writeByte('/');
				}
				
				String sm = positiveResponse.getSm();
				
				if (sm != null) {
					for (char c : sm.toCharArray()) {
						bb.writeByte(c);
					}
				}
				bb.writeByte('/');
			}
		}

		int writerindexBck = bb.writerIndex();
		int len = bb.readableBytes() - 1/* STX */+ 2 /* checksum */;
		String lenString = String.format("%05d", len);
		bb.writerIndex(4);
		bb.writeByte(lenString.charAt(0));
		bb.writeByte(lenString.charAt(1));
		bb.writeByte(lenString.charAt(2));
		bb.writeByte(lenString.charAt(3));
		bb.writeByte(lenString.charAt(4));
		bb.writerIndex(writerindexBck);

		// START - checksum
		int sum = 0;

		bb.readerIndex(1);// The STX character doesn't count
		while (bb.isReadable()) {
			sum += bb.readByte() & 0xFF;
		}

		sum &= 0xFF; // only keep the 8 LSBs (Least Significant Bits)

		String checksumString = String.format("%02X", sum);
		LOG.debug("Checksum: {}", checksumString);
		bb.writeByte(checksumString.charAt(0));
		bb.writeByte(checksumString.charAt(1));
		bb.readerIndex(0);// reset the readerIndex
		// END - checksum

		bb.writeByte(0x03 /* ETX */);
		return bb;
	}
}
