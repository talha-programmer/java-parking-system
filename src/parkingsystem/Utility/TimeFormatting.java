package parkingsystem.Utility;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TimeFormatting {

    /**
     * Return the date and time as a String
     * Return Format: hh:mm a dd-MM-yyyy
    * */
    public static String getDateTimeString(Timestamp timestamp){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a dd-MM-yyyy");
        LocalDateTime localTime = timestamp.toLocalDateTime();
        return localTime.format(formatter);
    }

    public static String getDateString(Timestamp timestamp){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime localTime = timestamp.toLocalDateTime();
        return localTime.format(formatter);
    }


    public static float getHoursDifference(Timestamp smaller, Timestamp greater){
        //in milliseconds
        long difference = greater.getTime() - smaller.getTime();

        long diffSeconds = difference / 1000 % 60;
        long diffMinutes = difference / (60 * 1000) % 60;
        long diffHours = difference / (60 * 60 * 1000) % 24;
        long diffDays = difference / (24 * 60 * 60 * 1000);

        return (float) ((diffDays * 24.0 + diffHours) + diffMinutes/60.0);
    }

    /**
     * Get timestamp from date provided in string
     * Date format provided should be 'dd-MM-yyyy'
     * */
    public static Timestamp getTimestampFromDate(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String dateStr = formatter.format(date);
        try {
            Date localDate = formatter.parse(dateStr);
            return new Timestamp(localDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
