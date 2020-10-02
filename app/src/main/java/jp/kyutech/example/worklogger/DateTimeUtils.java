// Date and time utilities
//
// Copyright (C) 2018-2020  Masanobu UMEDA (umerin@ci.kyutech.ac.jp)
//
// $Id$

package jp.kyutech.example.worklogger;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.GregorianCalendar;

import android.widget.Button;

public class DateTimeUtils
{
  public static Date getToday()
  {
    return new Date(System.currentTimeMillis());
  }

  public static Date getYesterday()
  {
    Calendar cal = new GregorianCalendar();
    cal.setTimeInMillis(System.currentTimeMillis());
    cal.set(Calendar.HOUR_OF_DAY, 0); // 00:00 AM of today
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);
    cal.add(Calendar.DATE, -1);
    return new Date(cal.getTimeInMillis());
  }

  public static Date getFirstDayOfLastMonth()
  {
    Calendar cal = new GregorianCalendar();
    cal.setTimeInMillis(System.currentTimeMillis());
    cal.add(Calendar.MONTH, -1);
    cal.set(Calendar.DATE, 1);
    cal.set(Calendar.HOUR_OF_DAY, 0); // 00:00 AM of today
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);
    return new Date(cal.getTimeInMillis());
  }

  public static Date getLastDayOfLastMonth()
  {
    Calendar cal = new GregorianCalendar();
    cal.setTimeInMillis(System.currentTimeMillis());
    cal.set(Calendar.DATE, 1);
    cal.set(Calendar.HOUR_OF_DAY, 0); // 00:00 AM of today
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);
    cal.add(Calendar.SECOND, -1);
    return new Date(cal.getTimeInMillis());
  }

  public static Date getFirstDayOfThisMonth()
  {
    return getFirstDayOf(System.currentTimeMillis());
  }

  public static Date getLastDayOfThisMonth()
  {
    return getLastDayOf(System.currentTimeMillis());
  }

  public static Date getFirstDayOf(long time)
  {
    Calendar cal = new GregorianCalendar();
    cal.setTimeInMillis(time);
    cal.set(Calendar.DATE, 1);
    cal.set(Calendar.HOUR_OF_DAY, 0); // 00:00 AM of today
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);
    return new Date(cal.getTimeInMillis());
  }

  public static Date getLastDayOf(long time)
  {
    Calendar cal = new GregorianCalendar();
    cal.setTimeInMillis(time);
    cal.add(Calendar.MONTH, 1);
    cal.set(Calendar.DATE, 1);
    cal.set(Calendar.HOUR_OF_DAY, 0); // 00:00 AM of today
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);
    cal.add(Calendar.SECOND, -1);
    return new Date(cal.getTimeInMillis());
  }
}
