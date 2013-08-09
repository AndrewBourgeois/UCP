package be.demmel.protocol.ucp;

/**
 * "len"and "operation" are missing because these can be gathered by other means
 */
public class UCPOperationHeader {
	public enum Type {
		OPERATION, RESULT;
	}
	
	private final int trn;
	private final Type type;
	private final UCPOperationType operation;
	
	public UCPOperationHeader(int trn, Type type, UCPOperationType operation) {
		this.trn = trn;
		this.type = type;
		this.operation = operation;
	}
	
	public int getTrn() {
		return trn;
	}
	
	public Type getType() {
		return type;
	}
	
	public UCPOperationType getOperation() {
		return operation;
	}
}
