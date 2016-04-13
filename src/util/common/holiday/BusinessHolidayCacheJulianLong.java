package util.common.holiday;

import java.util.Collection;

import util.common.CDate;

public class BusinessHolidayCacheJulianLong implements BusinessHolidayCache {

	protected final static CDate cacheStartDate = CDate.valueOf(2000, 1, 1);

	private final static long cacheStartDateJulian = CDate.valueOf(2000, 1, 1)
			.getJulian();
	private final static int numWordsInCache = 6 * 50;
	private final static CDate cacheEndDate = cacheStartDate.addMonths(
			2 * numWordsInCache).addDays(-1);

	private long[] holidayMonth = new long[numWordsInCache];
	private byte[] countInWord = new byte[numWordsInCache];

	private final static long[] countMask = {
			0x0L,// 1 exclude enddate
			0x1L,// 2
			0x3L,// 3
			0x7L,// 4
			0xFL,
			0x1FL,
			0x3FL,
			0x7FL,
			0xFFL,
			0x1FFL,
			0x3FFL,// 10
			0x7FFL,
			0xFFFL,
			0x1FFFL,
			0x3FFFL,
			0xFFFFL,
			0x1FFFFL,
			0x1FFFFL,
			0x3FFFFL,
			0x7FFFFL,
			0xFFFFFL,// 20
			0x1FFFFFL,
			0x3FFFFFL,
			0x7FFFFFL,
			0xFFFFFFL,
			0x1FFFFFFL,
			0x3FFFFFFL,
			0x7FFFFFFL,
			0xFFFFFFFL,
			0x1FFFFFFFL,
			0x3FFFFFFFL,// 30
			0x7FFFFFFFL,// 31
			0xFFFFFFFFL, 0x1FFFFFFFFL,
			0x3FFFFFFFFL,
			0x7FFFFFFFFL,
			0xFFFFFFFFFL,
			0x1FFFFFFFFFL,
			0x3FFFFFFFFFL,
			0x7FFFFFFFFFL,
			0xFFFFFFFFFFL,// 40
			0x1FFFFFFFFFFL, 0x3FFFFFFFFFFL, 0x7FFFFFFFFFFL, 0xFFFFFFFFFFFL,
			0x1FFFFFFFFFFFL, 0x3FFFFFFFFFFFL,
			0x7FFFFFFFFFFFL,
			0xFFFFFFFFFFFFL,
			0x1FFFFFFFFFFFFL,
			0x3FFFFFFFFFFFFL,// 50
			0x7FFFFFFFFFFFFL, 0xFFFFFFFFFFFFFL, 0x1FFFFFFFFFFFFFL,
			0x3FFFFFFFFFFFFFL, 0x7FFFFFFFFFFFFFL, 0xFFFFFFFFFFFFFFL,
			0x1FFFFFFFFFFFFFFL, 0x3FFFFFFFFFFFFFFL, 0x7FFFFFFFFFFFFFFL,
			0xFFFFFFFFFFFFFFFL,// 60
			0x1FFFFFFFFFFFFFFFL, 0x3FFFFFFFFFFFFFFFL, 0x7FFFFFFFFFFFFFFFL

	};

	public void addBusinessDays(BusinessHolidayCache chc) {
		if (chc instanceof BusinessHolidayCacheJulianLong) {

			BusinessHolidayCacheJulianLong c = (BusinessHolidayCacheJulianLong) chc;

			for (int i = 0; i < holidayMonth.length; ++i) {
				holidayMonth[i] |= c.holidayMonth[i];
				countInWord[i] = (byte) Long.bitCount(holidayMonth[i]);
			}

		} else {

			CDate d = cacheStartDate;
			while (d.lte(cacheEndDate)) {
				boolean isBusinessDay = chc.isGoodBusinessDay(d);
				if (isBusinessDay) {
					this.setGoodBusinessDay(d);
				}
				d = d.addDays(1);
			}
		}
	}

	public int calcBusinessDaysBetween(CDate x, CDate y) {

		PositionIndex xIdx = new PositionIndex();
		PositionIndex yIdx = new PositionIndex();

		getBitIndex(x, xIdx);
		getBitIndex(y, yIdx);

		int count = 0;
		if (xIdx.idx == yIdx.idx) {
			long mask = countMask[yIdx.bitPos] & ~countMask[xIdx.bitPos];
			count = Long.bitCount(holidayMonth[xIdx.idx] & mask);
		} else {

			// days to end of month
			count = Long.bitCount(this.holidayMonth[xIdx.idx]
					& ~countMask[xIdx.bitPos]);
			// sum in between months
			for (int i = xIdx.idx + 1; i < yIdx.idx; ++i) {
				// if this is too slow count years;
				count += this.countInWord[i];
			}
			// day from start of month
			count += Long.bitCount(this.holidayMonth[yIdx.idx]
					& countMask[yIdx.bitPos]);
		}
		return count;
	}

	public void clear() {
		for (int i = 0; i < holidayMonth.length; ++i) {
			holidayMonth[i] = 0;
			countInWord[i] = 0;
		}
	}

	public CDate getFirstDateInCacheRange() {
		return cacheStartDate;
	}

	public CDate getLastDateInCacheRange() {
		return cacheEndDate;
	}

	public boolean isGoodBusinessDay(CDate d) {
		PositionIndex index = new PositionIndex();
		getBitIndex(d, index);
		long mask = 1L << index.bitPos;
		if ((holidayMonth[index.idx] & mask) != 0)
			return true;
		return false;
	}

	public void setGoodBusinessDay(CDate d) {

		PositionIndex index = new PositionIndex();
		getBitIndex(d, index);
		long mask = 1L << index.bitPos;
		holidayMonth[index.idx] |= mask;
		countInWord[index.idx] = (byte) Long.bitCount(holidayMonth[index.idx]);
	}

	private void getBitIndex(CDate d, PositionIndex index) {
		int days = (int) (d.getJulian() - cacheStartDateJulian);
		index.idx = days >>> 6; // 64;
		index.bitPos = days % 64;
	}

	static class PositionIndex {
		public int idx, bitPos;
	}

	private String longToBinaryString(long l) {
		char[] x = new char[64];
		long mask = 1L << 63;
		int j = 63;
		while (j >= 0) {
			mask = 1L << j;
			if ((l & mask) != 0)
				x[j] = '1';
			else
				x[j] = '0';
			--j;
		}
		return new String(x);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CacheStartDate: " + cacheStartDate + " CacheEndDate: "
				+ cacheEndDate + "\n");
		String counter = ("0123456789012345678901234567890123456789012345678901234567890123");

		builder.append("Idx" + "\tDays" + "\tFirstDate \t" + counter
				+ "\tBusinessDaysWord\n");

		for (int i = 0; i < numWordsInCache; ++i) {
			CDate firstDate = cacheStartDate.addDays(i * 64);
			int days = (int) (firstDate.getJulian() - cacheStartDateJulian);
			builder.append(i + "\t" + days + "\t" + firstDate + "\t"
					+ longToBinaryString(holidayMonth[i]) + "\t"
					+ this.countInWord[i] + "\n");
		}

		return builder.toString();
	}

}
