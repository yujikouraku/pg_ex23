package service;

abstract class TimeService implements Service {
	private boolean joined = false;

	public boolean isJoined() {
		return joined;
	}

	public void joined() {
		this.joined = true;
	}

	@Override
	public void clear() {
		this.joined = false;
	}

	public abstract boolean isServiceTime(int hour);

	@Override
	public void checkService(Record record) {
		if (record.getServiceCode().equals(this.getServiceCode())) {
			this.joined = true;
		}
	}

	public abstract String getServiceCode();

	@Override
	public int calcUnitPrice(Record record, int unitPrice) {
		int starthour = record.getStartHour(); // 通話開始時間取得
		if (this.isServiceTime(starthour)) {
			unitPrice -= this.getDiscount();
		}
		return unitPrice;
	}

	public abstract int getDiscount();

	@Override
	public int calcBasicCharge(int basicCharge) {
		if (this.isJoined()) {
			basicCharge += this.getBasicCharge();
		}
		return basicCharge;
	}

	public abstract int getBasicCharge();

}
