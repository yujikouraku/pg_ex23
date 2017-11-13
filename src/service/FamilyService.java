package service;

import java.util.Arrays;

public class FamilyService implements Service{
	private static final int TEL_NUMBER = 2;
	private static final String SERVICE_CODE = "C1";
	private static final int BASIC_CHARGE = 100;
	private boolean joined = false;
	private String[] telNumbers = new String[TEL_NUMBER];
	private int telNumberCount = 0;

	public boolean isJoined() {
		return joined;
	}

	@Override
	public void checkService(Record record) {
		if (record.getServiceCode().equals(SERVICE_CODE)) {
			this.joined = true;
			this.appendFamilyTelNumber(record.getServiceOption());
		}
	}

	public void appendFamilyTelNumber(String telNumber) {
		this.telNumbers[telNumberCount] = telNumber;
		telNumberCount += 1;
	}

	public boolean isFamilyTelNumber(String telNumber) {
		if (!this.isJoined()) return false;
		return Arrays.asList(telNumbers).contains(telNumber);
	}

	@Override
	public int calcUnitPrice(Record record, int unitPrice) {
		if (this.isFamilyTelNumber(record.getCallNumber()) == true) {
			unitPrice /= 2;
		}
		return unitPrice;
	}

	@Override
	public int calcBasicCharge(int basicCharge) {
		if (this.isJoined() == true) {
			basicCharge += BASIC_CHARGE;
		}
		return basicCharge;
	}

	@Override
	public void clear() {
		this.joined = false;
		this.telNumberCount = 0;
	}

}
