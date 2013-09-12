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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PositiveResponseWithMvp [mvp=");
		builder.append(mvp);
		builder.append("]");
		return builder.toString();
	}
}
