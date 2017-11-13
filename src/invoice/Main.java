package invoice;

import service.Record;
import service.Service;
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

			Record record = reader.read();
			while (record != null) {
				char recordCode = record.getRecordCode();

				switch(recordCode){
				case RC_OWNER_INFO:
					invoice.setOwnerTelNumber(record.getOwnerTelNumber());
				case RC_SERVICE_INFO:
					service(service, record);
				case RC_CALL_LOG:
					call(invoice, service, record);
				case RC_SEPARATOR:
					separate(invoice, service, writer);

				}
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void service(Service service, Record record) {
		service.checkService(record);
	}

	private static void call(Invoice invoice, Service service, Record record) {
		int callDuration = record.getCallMinutes(); // 通話時間
		int unitPrice = service.calcUnitPrice(record, INITIAL_CALL_UNIT_PRICE); //通話単価
		int callCharge = unitPrice * callDuration; //通話料金
		invoice.addCallCharge(callCharge);
	}

	private static void separate(Invoice invoice, Service service, InvoiceWriter writer) {
		int basicCharge = service.calcBasicCharge(INITIAL_BASIC_CHARGE);
		invoice.setBasicCharge(basicCharge);
		writer.write(invoice);
		invoice.clear();
		service.clear();
	}

}
