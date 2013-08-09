package be.demmel.protocol.ucp.serialization;

import io.netty.buffer.ByteBuf;

import java.util.EnumSet;
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
import be.demmel.protocol.ucp.util.Serialization;

public class UCPPacketDeserializerImpl {
	private static final Logger LOG = LoggerFactory.getLogger(UCPPacketDeserializerImpl.class);
	private final Map<UCPOperationType, UCPOperationDeserializer> operationDeserializers;
	private final EnumSet<UCPOperationType> positiveResponsesThatUseMvp;// as of this moment: 50 series + 30. So not 60 series + 31 + 01 + 02 + 03
	private static final EnumSet<UCPOperationType> DEFAULT_POSITIVE_RESPONSE_WITH_MVP = EnumSet.of(UCPOperationType.SMS_MESSAGE_TRANSFER,
			UCPOperationType.SUBMIT_SHORT_MESSAGE, UCPOperationType.MODIFY_MESSAGE, UCPOperationType.INQUIRY_MESSAGE, UCPOperationType.DELETE_MESSAGE,
			UCPOperationType.DELIVER_SHORT_MESSAGE, UCPOperationType.DELIVER_NOTIFICATION, UCPOperationType.RESPONSE_INQUIRY_MESSAGE,
			UCPOperationType.RESPONSE_DELETE_MESSAGE);

	public UCPPacketDeserializerImpl(Map<UCPOperationType, UCPOperationDeserializer> operationDeserializers, EnumSet<UCPOperationType> positiveResponsesThatUseMvp) {
		this.operationDeserializers = operationDeserializers;
		this.positiveResponsesThatUseMvp = positiveResponsesThatUseMvp;
	}
	
	public UCPPacketDeserializerImpl(Map<UCPOperationType, UCPOperationDeserializer> operationDeserializers) {
		this(operationDeserializers, DEFAULT_POSITIVE_RESPONSE_WITH_MVP);
	}

	public UCPPacket deserialize(ByteBuf ucpPacket) {
		byte stx = ucpPacket.readByte();
		
		if(0x02 != stx) {
			throw new IllegalArgumentException("Packet should start with the STX character but starts with: " + stx);
		}
		
		int trn = Integer.parseInt("" + ((char) ucpPacket.readByte()) + ((char) ucpPacket.readByte()));
		Serialization.checkSeparator(ucpPacket, "TRN");
		int len = Integer.parseInt("" + ((char) ucpPacket.readByte()) + ((char) ucpPacket.readByte()) + ((char) ucpPacket.readByte())
				+ ((char) ucpPacket.readByte()) + ((char) ucpPacket.readByte()));
		//TODO: do something with the length
		Serialization.checkSeparator(ucpPacket, "LEN");
		char orChar = (char) ucpPacket.readByte();
		UCPOperationHeader.Type type = orChar == 'O' ? UCPOperationHeader.Type.OPERATION : UCPOperationHeader.Type.RESULT;
		Serialization.checkSeparator(ucpPacket, "O/R");
		UCPOperationType operationType = UCPOperationType.fromByte(Byte.parseByte("" + ((char) ucpPacket.readByte()) + ((char) ucpPacket.readByte())));
		Serialization.checkSeparator(ucpPacket, "Operation");
		
		UCPOperationHeader header = new UCPOperationHeader(trn, type, operationType);
		
		UCPOperation operation = null;

		if (header.getType() == UCPOperationHeader.Type.OPERATION) {
			// parse the body, based on the operation type
			UCPOperationDeserializer deserializer = this.operationDeserializers.get(operationType);

			if (deserializer == null) {
				throw new UnsupportedOperationException("UCPOperationType \"" + operation + "\" unsupported (operation)");
			}

			operation = deserializer.deserialize(ucpPacket);
		} else { // it's a result
			char ackOrNack = (char) ucpPacket.readByte(); // read the first field to find out if it's a positive or a negative result
			Serialization.checkSeparator(ucpPacket, "(N)Ack");

			if (ackOrNack == 'A') {
				if (this.positiveResponsesThatUseMvp.contains(header.getOperation())) { // 50 series + 30: MVP + SM
					String mvp = Serialization.getString(ucpPacket);
					String sm = Serialization.getString(ucpPacket);

					operation = new PositiveResponseWithMvp(sm, mvp);
				} else { // 60 series + 31 + 01 + 02 + 03: SM
					String sm = Serialization.getString(ucpPacket);
					operation = new PositiveResponse(sm);
				}
			} else if (ackOrNack == 'N') { // the negative responses have a common template
				String ec = "" + ((char) ucpPacket.readByte()) + ((char) ucpPacket.readByte());
				String sm = Serialization.getString(ucpPacket);

				operation = new NegativeResponse(ec, sm);
			} else {
				throw new IllegalStateException("The (N)Ack field of the result can only be 'A' or 'N' but was: " + ackOrNack);
			}
		}
		
		int checksum = Integer.parseInt("" + ((char) ucpPacket.readByte()) + ((char) ucpPacket.readByte()), 16);
		//TODO: verify checksum
		
		byte etx = ucpPacket.readByte();
		
		if(0x03 != etx) {
			throw new IllegalArgumentException("Packet should start with the ETX character but starts with: " + stx);
		}
		
		return new UCPPacket(header, operation);
	}
}
