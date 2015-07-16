package com.tangdj.hometest;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.format.Time;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

public class Functions {

    public static final String providers[] = {
            "content://com.btb.sec.mms.provider/message", "content://com.sec.mms.provider/message", "content://com.lge.messageprovider/msg/inbox", "content://com.kt.mmsclient/messages", "content://com.btb.ums.provider.MessageProvider/sms", "content://sms/inbox"
    };

    public static final String fields[][] = {
            {"RegTime", "Title", "MDN1st", "MainType", "SubType"},
            {"RegTime", "Title", "MDN1st", "MainType", "SubType"},
            {"date", "body", "sender"},
            {"TimeStamp", "Content", "Callback"},
            {"DeliveryTime", "Title", "MDN1st"},
            {"date", "body", "address"}
    };

    public static final String wheres[] = {
            " MainType = 0 AND SubType = 0 ", " MainType = 0 AND SubType = 0 ", " 1 ", " 1 ", " 1 ", " 1 "
    };

    public static final String orders[] = {
            " RegTime DESC ", " RegTime DESC ", " date DESC ", " TimeStamp DESC ", " DeliveryTime DESC ", " date DESC "
    };
	
	public static void Log(String msg){
		Log.v("HOME", msg);
	}

    public static String weekText(int week){
        String str = "";
        switch (week)
        {
            case 1 : str = "일"; break;
            case 2 : str = "월"; break;
            case 3 : str = "화"; break;
            case 4 : str = "수"; break;
            case 5 : str = "목"; break;
            case 6 : str = "금"; break;
            case 7 : str = "토"; break;
        }

        return str;
    }

    public static String addZero(int n){

        String szRet = "";
        if(n<10) szRet = "0" + n;
        else szRet = "" + n;

        return szRet;
    }

    public static int getSmsProvider(Context context)
    {
        for (int i=0;i<providers.length;i++)
        {
            Uri localUri = Uri.parse(providers[i]);
            try
            {
                Cursor cursor = context.getContentResolver().query(localUri, null, null, null, null);
                if (cursor != null) {
                    return i;
                }
            }
            catch (Exception localException)
            {
                localException.printStackTrace();
            }
        }

        return providers.length;
    }

    public static int getTodayJulian() {
        return getJulianFromDate((new Date()).getTime());
    }

    public static int getJulianFromDate(long lDate)
    {
        Time time = new Time();
        time.set(lDate);
        return Time.getJulianDay(lDate, time.gmtoff);
    }

    public static long getDateFromJulian(int paramInt) {
        Time localTime = new Time();
        localTime.setJulianDay(paramInt);
        return localTime.toMillis(false);
    }

    public static String dateLog(Iterable iterable, Date date, Date date1)
    {
        String s = (new StringBuilder()).append("minDate: ").append(date).append("\nmaxDate: ").append(date1).toString();
        String s2;
        if(iterable == null)
        {
            s2 = (new StringBuilder()).append(s).append("\nselectedDates: null").toString();
        } else
        {
            String s1 = (new StringBuilder()).append(s).append("\nselectedDates: ").toString();
            Iterator iterator = iterable.iterator();
            s2 = s1;
            while(iterator.hasNext())
            {
                Date date2 = (Date)iterator.next();
                s2 = (new StringBuilder()).append(s2).append(date2).append("; ").toString();
            }
        }
        return s2;
    }

    public static void setMidNight(Calendar calendar){
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    public static boolean sameDate(Calendar cal1, Calendar cal2) {
        return (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)) &&
                (cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)) &&
                (cal1.get(Calendar.DATE) == cal2.get(Calendar.DATE));
    }
}
