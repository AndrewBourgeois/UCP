package be.demmel.protocol.ucp;

public class PositiveResponse implements UCPOperation {
	private String sm;
	
	public PositiveResponse(String sm) {
		this.sm = sm;
	}

	public String getSm() {
		return sm;
	}
}
