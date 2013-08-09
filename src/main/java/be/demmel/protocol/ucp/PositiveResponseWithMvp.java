package be.demmel.protocol.ucp;

public class PositiveResponseWithMvp extends PositiveResponse {
	private String mvp;
	
	public PositiveResponseWithMvp(String sm, String mvp) {
		super(sm);
		this.mvp = mvp;
	}

	public String getMvp() {
		return mvp;
	}
}
