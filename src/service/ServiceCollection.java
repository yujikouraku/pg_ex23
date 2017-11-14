package service;

public class ServiceCollection implements Service {

	// Serviceインスタンス生成
	Service[] services = { new DayService(), new NightService(), new FamilyService() };

	@Override
	public void clear() {
		for (int i = 0; i < services.length; i++) {
			services[i].clear();
		}
	}

	@Override
	public void checkService(Record record) {
		for (int i = 0; i < services.length; i++) {
			services[i].checkService(record);
		}
	}

	@Override
	public int calcUnitPrice(Record record, int unitPrice) {
		for (int i = 0; i < services.length; i++) {
			unitPrice = services[i].calcUnitPrice(record, unitPrice);
		}
		return unitPrice;
	}

	@Override
	public int calcBasicCharge(int basicCharge) {
		for (int i = 0; i < services.length; i++) {
			basicCharge = services[i].calcBasicCharge(basicCharge);
		}
		return basicCharge;
	}

}
