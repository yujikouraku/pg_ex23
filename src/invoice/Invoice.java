package invoice;

public class Invoice {
	private String ownerTelNumber = null;
	private int basicCharge = 0;
	private int callCharge = 0;

	Invoice() {
		this.setOwnerTelNumber(ownerTelNumber);
		this.setBasicCharge(basicCharge);
		this.addCallCharge(callCharge);
	}

	public String getOwnerTelNumber() {
		return ownerTelNumber;
	}

	public int getBasicCharge() {
		return basicCharge;
	}

	public int getCallCharge() {
		return callCharge;
	}

	public void setOwnerTelNumber(String ownerTelNumber) {
		this.ownerTelNumber = ownerTelNumber;
	}

	public void setBasicCharge(int basicCharge) {
		this.basicCharge = basicCharge;
	}

	public void addCallCharge(int callCharge) {
		this.callCharge += callCharge;
	}

	public void clear() {
		this.ownerTelNumber = null;
		this.basicCharge = 0;
		this.callCharge = 0;
	}



}
