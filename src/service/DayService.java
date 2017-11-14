package service;

public class DayService extends TimeService {
	private static final int START_TIME = 8;
	private static final int END_TIME = 17;
	private static final String SERVICE_CODE = "E1";
	private static final int BASIC_CHARGE = 200;

	@Override
	public boolean isServiceTime(int hour) {
		if (this.isJoined()) {
			return (START_TIME <= hour && hour <= END_TIME);
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
