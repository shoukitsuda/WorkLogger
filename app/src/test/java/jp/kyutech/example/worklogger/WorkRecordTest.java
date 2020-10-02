package jp.kyutech.example.worklogger;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * WorkRecordTest class to run unit tests on the development machine
 * (host).
 *
 * @author Masanobu UMEDA
 * @version $Revision$
 */

public class WorkRecordTest {

    @Test
    public void isToday() {
        WorkRecord record = new WorkRecord();
        assertEquals(record.isToday(), true);
    }

    @Test
    public void isYesterday() {
        WorkRecord record = new WorkRecord();
        assertEquals(record.isYesterday(), false);
    }

    @Test
    public void checkinNow() {
        WorkRecord record = new WorkRecord();
        assertEquals(record.checkinNow(), true);
        // Checkin twice.
        assertEquals(record.checkinNow(), false);
    }

    @Test
    public void checkoutNow() {
        WorkRecord record = new WorkRecord();
        assertEquals(record.checkinNow(), true);
        assertEquals(record.checkoutNow(), true);
        // Checkout twice.
        assertEquals(record.checkoutNow(), false);

        // Checkout now without checkin now.
        WorkRecord record2 = new WorkRecord();
        assertEquals(record2.checkoutNow(), false);
        assertEquals(record2.checkoutNow(), false);
    }
}
