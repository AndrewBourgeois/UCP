package be.demmel.protocol.ucp;

public class NegativeResponse implements UCPOperation {
	private String ec;// 2 num. char
	private String sm;
	
	public NegativeResponse(String ec, String sm) {
		this.ec = ec;
		this.sm = sm;
	}
	
	public String getEc() {
		return ec;
	}
	
	public String getSm() {
		return sm;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("NegativeResponse [ec=");
		builder.append(ec);
		builder.append(", sm=");
		builder.append(sm);
		builder.append("]");
		return builder.toString();
	}
}
