package service;

import static org.junit.Assert.*;

import org.junit.Test;

public class NightServiceTest {

	@Test
	public void testCheckService() {
		NightService nightService = new NightService();

		// インスタンスを作成した直後は、加入フラグはfalse
		assertFalse(nightService.isJoined());

		// 昼トク割引意外のレコードの場合、加入フラグはfalseのまま
		nightService.checkService(new Record("2 C1 090-1234-0001"));
		assertFalse(nightService.isJoined());

		// 昼トク割引のレコードの場合、加入フラグがtrueになる
		nightService.checkService(new Record("2 E2"));
		assertTrue(nightService.isJoined());

		// clear()メソッドを呼び出すと、加入フラグがfalseになる
		nightService.clear();
		assertFalse(nightService.isJoined());
	}

	@Test
	public void testIsServiceTime() {
		NightService nightService = new NightService();

		// 昼トク割引に加入していない場合は、どの時間も昼トク割引の対象にならない
		assertFalse(nightService.isServiceTime(7));
		assertFalse(nightService.isServiceTime(8));
		assertFalse(nightService.isServiceTime(17));
		assertFalse(nightService.isServiceTime(18));

		// 昼トク割引に介入している場合は、8:00時から17:59分までに開始された通話は割引対象になる
		nightService.checkService(new Record("2 E2"));
		assertFalse(nightService.isServiceTime(7));
		assertTrue(nightService.isServiceTime(2));
		assertTrue(nightService.isServiceTime(23));
		assertFalse(nightService.isServiceTime(18));
	}

	@Test
	public void testCalcUnitPrice() {
		NightService nightService = new NightService();

		nightService.checkService(new Record("2 E2"));

		// 昼トク割引の対象外の時間の場合、単価は変わらない
		int unitPrice = nightService.calcUnitPrice(new Record("5 2004/06/05 18:00 010 090-1234-0002"), 20);
		assertEquals(20, unitPrice);

		// 昼トク割引の対象の時間の場合、単価は5円引き
		unitPrice = nightService.calcUnitPrice(new Record("5 2004/06/05 23:59 010 090-1234-0002"), 20);
		assertEquals(15, unitPrice);
	}

	@Test
	public void testCalcBasicCharge() {
		NightService nightService = new NightService();

		// 昼トク割引に加入していない場合、基本料金は変わらない
		int basicCharge = nightService.calcBasicCharge(1000);
		assertEquals(1000, basicCharge);

		// 昼トク割引に加入している場合、基本料金は500円増し
		nightService.checkService(new Record("2 E2"));
		basicCharge = nightService.calcBasicCharge(1000);
		assertEquals(1500, basicCharge);
	}

}
