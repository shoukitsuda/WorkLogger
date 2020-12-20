// WorkRecord for representing work hours
//
// Copyright (C) 2018-2020  Masanobu UMEDA (umerin@ci.kyutech.ac.jp)
//
// $Id$

package jp.kyutech.example.worklogger;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * WorkRecord class representing work hours.
 *
 * @author Masanobu UMEDA
 * @version $Revision$
 */

public class WorkRecord
{
  private static SimpleDateFormat time_format = new SimpleDateFormat("HH:mm");
  private long		id = 0;		 // Record ID
  private String user = null;	 // UNUSED
  private Date date = null;	 // Date of a record
  private Time checkin = null;	 // Checkin time
  private Time checkout = null; // Checkout time

  public WorkRecord()
  {
    Calendar cal = new GregorianCalendar();
    cal.setTimeInMillis(System.currentTimeMillis());
    cal.set(Calendar.HOUR_OF_DAY, 0); // 00:00 AM of today
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);
    this.date = new Date(cal.getTimeInMillis());
    this.user = "worker";
  }

  public WorkRecord(String user)
  {
    this();
    this.user = user;
  }

  /*
   * Returns a time plus a offset time from GMT time zone.
   * For example, 9 hours are added to a check-in time in JST.  This
   * method is defined for time representation of MS Excel.
   *
   * @return a java.sql.Time
   * @see getTimeWithTimeZoneOffset
   */
  public static Time getTimeWithTimeZoneOffset(Time time)
  {
    Calendar gmt_cal = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
    Calendar loc_cal = new GregorianCalendar();
    gmt_cal.setTimeInMillis(time.getTime());
    loc_cal.setTimeInMillis(time.getTime());
    int hours =
      loc_cal.get(Calendar.HOUR_OF_DAY) - gmt_cal.get(Calendar.HOUR_OF_DAY);
    int minutes =
      loc_cal.get(Calendar.MINUTE) - gmt_cal.get(Calendar.MINUTE);
    gmt_cal.add(Calendar.HOUR_OF_DAY, hours);
    gmt_cal.add(Calendar.MINUTE, minutes);

    return new Time(gmt_cal.getTimeInMillis());
  }

  /*
   * Return true if startTime and endTime constitute a valid time rage.
   *
   * @param startTime
   * @param endTime
   * @return a boolean
   */
  public static boolean isValidTimeRange(Time startTime, Time endTime)
  {
    if(endTime == null){
      return true;
    }
    if(startTime == null){
      return false;
    }
    Calendar startCal = GregorianCalendar.getInstance();
    startCal.setTime(startTime);
    Calendar endCal = GregorianCalendar.getInstance();
    endCal.setTime(endTime);
    // NOTE: We have to take care of hour and minute only.
    if((startCal.get(Calendar.HOUR_OF_DAY) > endCal.get(Calendar.HOUR_OF_DAY)) ||
       (startCal.get(Calendar.HOUR_OF_DAY) == endCal.get(Calendar.HOUR_OF_DAY) &&
	startCal.get(Calendar.MINUTE) > endCal.get(Calendar.MINUTE))){
      return false;
    }
    return true;
  }

  /*
   * Return true if time1 =< time2 in hours, minutes, and seconds.
   *
   * @param time1
   * @param time2
   * @return a boolean
   */
  public static boolean isTimeBeforeTime(Time time1, Time time2)
  {
    Calendar cal1 = new GregorianCalendar();
    cal1.setTime(time1);
    cal1.set(Calendar.YEAR, 2000);
    cal1.set(Calendar.MONTH, 1);
    cal1.set(Calendar.DAY_OF_MONTH, 1);
    Calendar cal2 = new GregorianCalendar();
    cal2.setTime(time2);
    cal2.set(Calendar.YEAR, 2000);
    cal2.set(Calendar.MONTH, 1);
    cal2.set(Calendar.DAY_OF_MONTH, 1);

    return cal1.getTimeInMillis() <= cal2.getTimeInMillis();
  }

  /*
   * Return true if the date of this record is today.
   */
  public boolean isToday()
  {
    Calendar cal = new GregorianCalendar();
    cal.setTimeInMillis(System.currentTimeMillis());
    cal.set(Calendar.HOUR_OF_DAY, 0); // 00:00 AM of today
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);
    return date.getTime() == cal.getTimeInMillis();
  }

  /*
   * Return true if the date of this record is yesterday.
   */
  public boolean isYesterday()
  {
    Calendar cal = new GregorianCalendar();
    cal.setTimeInMillis(System.currentTimeMillis());
    cal.set(Calendar.HOUR_OF_DAY, 0); // 00:00 AM of today
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);
    cal.add(Calendar.DATE, -1);
    return date.getTime() == cal.getTimeInMillis();
  }

  public void setId(long id)
  {
    this.id = id;
  }

  public long getId()
  {
    return id;
  }

  public void setUser(String user)
  {
    this.user = user;
  }

  public String getUser()
  {
    return user;
  }

  public void setDate(Date date)
  {
    this.date = date;
  }

  public Date getDate()
  {
    return date;
  }

  public String getDateAsString()
  {
    if(date == null){
      return null;
    }
    return date.toString();
  }

  public void setCheckinTime(Time time)
  {
    this.checkin = time;
  }

  public Time getCheckinTime()
  {
    return checkin;
  }

  public long getCheckinTimeAslong(){return checkin.getTime();}
  public long getCheckoutTimeAslong(){return checkout.getTime();}


  /*
   * Returns a check-in time plus a offset time from GMT time zone.
   * For example, 9 hours are added to a check-in time in JST.  This
   * method is defined for time representation of MS Excel.
   *
   * @return a java.sql.Time
   * @see getCheckoutTimeWithTimeZoneOffset
   */
  public Time getCheckinTimeWithTimeZoneOffset()
  {
    return getTimeWithTimeZoneOffset(checkin);
  }

  public String getCheckinTimeAsString()
  {
    if(checkin == null){
      return null;
    }
    return checkin.toString();
  }

  public String getCheckinTimeAsString(String default_value)
  {
    if(checkin == null){
      return default_value;
    }
    return checkin.toString();
  }

  public String getCheckinTimeAsHHMMString()
  {
    if(checkin == null){
      return null;
    }
    return time_format.format(checkin);
  }

  public void setCheckoutTime(Time time)
  {
    this.checkout = time;
  }

  public Time getCheckoutTime()
  {
    return checkout;
  }

  /*
   * Returns a check-out time plus a offset time from GMT time zone.
   * For example, 9 hours are added to a check-out time in JST.  This
   * method is defined for time representation of MS Excel.
   *
   * @return a java.sql.Time
   * @see getCheckoutTimeWithTimeZoneOffset
   */
  public Time getCheckoutTimeWithTimeZoneOffset()
  {
    return getTimeWithTimeZoneOffset(checkout);
  }

  public String getCheckoutTimeAsString()
  {
    if(checkout == null){
      return null;
    }
    return checkout.toString();
  }

  public String getCheckoutTimeAsString(String default_value)
  {
    if(checkout == null){
      return default_value;
    }
    return checkout.toString();
  }

  public String getCheckoutTimeAsHHMMString()
  {
    if(checkout == null){
      return null;
    }
    return time_format.format(checkout);
  }

  /*
   * Remember a checkin time.  Return true if a record is updated.
   *
   * @return a boolean
   */
  public boolean checkinNow()
  {
    boolean updated_p = false;

    // Record a checkin time unless we have never checked.
    if(checkin == null){
      checkin = new Time(System.currentTimeMillis());
      updated_p = true;
    }
    if(checkout != null){
      checkout = null;
      updated_p = true;
    }
    return updated_p;
  }

  /*
   * Remember a checkout time.  Return true if a record is updated.
   *
   * @return a boolean
   */
  public boolean checkoutNow()
  {
    boolean updated_p = false;

    // Record a checkout time if we have checked in and have not
    // checked out.
    if((checkin != null) &&
       (checkout == null)){
      checkout = new Time(System.currentTimeMillis());
      updated_p = true;
    }
    return updated_p;
  }

  public String toString()
  {
    return String.format("[%d] %s %s=>%s (%s)",
			 id,
			 (date==null)?"":date.toString(),
			 (checkin==null)?"":checkin.toString(),
			 (checkout==null)?"":checkout.toString(),
			 user);
  }
}
