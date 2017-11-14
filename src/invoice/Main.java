package invoice;

import service.Record;
import service.ServiceCollection;

public class Main {
	// 変数宣言
	private static final int INITIAL_BASIC_CHARGE = 1000;
	private static final int INITIAL_CALL_UNIT_PRICE = 20;
	private static final char RC_OWNER_INFO = '1';
	private static final char RC_SERVICE_INFO = '2';
	private static final char RC_CALL_LOG = '5';
	private static final char RC_SEPARATOR = '9';

	public static void main(String[] args) {

		// 処理開始
		ServiceCollection service = new ServiceCollection();

		try {
			RecordReader reader = new RecordReader();

			InvoiceWriter writer = new InvoiceWriter();

			Invoice invoice = new Invoice();

			for (Record record = reader.read(); record != null; record = reader.read()) {
				char recordCode = record.getRecordCode();
				switch (recordCode) {
				case RC_OWNER_INFO:
					invoice.setOwnerTelNumber(record.getOwnerTelNumber());
					break;
				case RC_SERVICE_INFO:
					service.checkService(record);
					break;
				case RC_CALL_LOG:
					invoice.addCallCharge(
							record.getCallMinutes() * service.calcUnitPrice(record, INITIAL_CALL_UNIT_PRICE));
					break;
				case RC_SEPARATOR:
					invoice.setBasicCharge(service.calcBasicCharge(INITIAL_BASIC_CHARGE));
					writer.write(invoice);
					invoice.clear();
					service.clear();
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
