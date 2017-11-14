package service;

public class NightService extends TimeService {
	private static final int START_TIME = 23;
	private static final int END_TIME = 5;
	private static final String SERVICE_CODE = "E2";
	private static final int BASIC_CHARGE = 500;

	public boolean isServiceTime(int hour) {
		if (this.isJoined()) {
			return (START_TIME <= hour || hour <= END_TIME);
		}
		return false;
	}

	@Override
	public String getServiceCode() {
		return SERVICE_CODE;
	}

	@Override
	public int getDiscount() {
		return 5;
	}

	@Override
	public int getBasicCharge() {
		return BASIC_CHARGE;
	}

}
