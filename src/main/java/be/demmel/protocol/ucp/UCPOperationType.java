package be.demmel.protocol.ucp;

import java.util.HashMap;
import java.util.Map;

public enum UCPOperationType {
	// SMT (Short Message Terminal) ==> SMSC (Short Message System Center)
	ALERT(31),
	SUBMIT_SHORT_MESSAGE(51),
	MODIFY_MESSAGE(54),
	INQUIRY_MESSAGE(55),
	DELETE_MESSAGE(56),
	SESSION_MANAGEMENT(60),
	LIST_MANAGEMENT(61),
	// SMSC ==> SMT
	DELIVER_SHORT_MESSAGE(52),
	DELIVER_NOTIFICATION(53),
	RESPONSE_INQUIRY_MESSAGE(57),
	RESPONSE_DELETE_MESSAGE(58),
	// SMT ==> SMSC (legacy)
	MULTIPLE_ADDRESS_CALL_INPUT(02),
	CALL_INPUT_WITH_MULTIPLE_SUPPLEMENTARY_SERVICES(03),
	SMS_MESSAGE_TRANSFER(30),
	// SMSC ==> SMT (legacy) & SMT ==> SMSC (legacy)
	CALL_INPUT(01);
	
	private static final Map<Byte, UCPOperationType> map = new HashMap<>();
	
	static {
	    for (UCPOperationType type : UCPOperationType.values()) {
	    	map.put(type.value, type);
	    }
	}
	
	private final byte value;
	
	private UCPOperationType(final int value) {
		this.value = (byte) value;
	}

	public byte getValue() {
		return value;
	}
	
	public static UCPOperationType fromByte(byte b) {
		return map.get(b);
	}
}
