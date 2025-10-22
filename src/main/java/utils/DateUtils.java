package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtils {
    private static final Logger logger = LogManager.getLogger(DateUtils.class);

    public static Date getCurrentDate() {
        logger.debug("Getting current date");
        Date date = new Date();
        logger.debug("Current date: " + date.toString());
        return date;
    }

    public static long getCurrentTimestamp() {
        logger.debug("Getting current timestamp");
        long timestamp = System.currentTimeMillis();
        logger.debug("Current timestamp: " + timestamp);
        return timestamp;
    }

    public static String formatDate(Date date, String format) {
        if (format == null || format.isEmpty()) format = "yyyy-MM-dd";
        logger.debug("Formatting date: " + date + " with format: " + format);

        SimpleDateFormat sdf = new SimpleDateFormat(convertFormat(format));
        String formattedDate = sdf.format(date);

        logger.debug("Formatted date: " + formattedDate);
        return formattedDate;
    }

    // Converts custom format syntax to Java SimpleDateFormat
    private static String convertFormat(String format) {
        return format.replace("YYYY", "yyyy")
                .replace("DD", "dd")
                .replace("HH", "HH")
                .replace("mm", "mm")
                .replace("ss", "ss")
                .replace("SSS", "SSS");
    }

    public static Date parseDate(String dateString, String format) {
        logger.debug("Parsing date from string: " + dateString);
        try {
            Date date;
            if (format != null && !format.isEmpty()) {
                SimpleDateFormat sdf = new SimpleDateFormat(convertFormat(format));
                date = sdf.parse(dateString);
            } else {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
            }

            logger.debug("Parsed date: " + date);
            return date;
        } catch (ParseException e) {
            logger.error("Date parsing failed: " + e.getMessage());
            throw new IllegalArgumentException("Invalid date string: " + dateString, e);
        }
    }

    public static Date addDays(Date date, int days) {
        logger.debug("Adding " + days + " days to date: " + date);
        Date result = new Date(date.getTime() + TimeUnit.DAYS.toMillis(days));
        logger.debug("New date: " + result);
        return result;
    }

    public static Date addHours(Date date, int hours) {
        logger.debug("Adding " + hours + " hours to date: " + date);
        Date result = new Date(date.getTime() + TimeUnit.HOURS.toMillis(hours));
        logger.debug("New date: " + result);
        return result;
    }

    public static Date subtractDays(Date date, int days) {
        logger.debug("Subtracting " + days + " days from date: " + date);
        Date result = new Date(date.getTime() - TimeUnit.DAYS.toMillis(days));
        logger.debug("New date: " + result);
        return result;
    }

    public static long getDaysDifference(Date date1, Date date2) {
        logger.debug("Getting days difference between " + date1 + " and " + date2);
        long timeDiff = Math.abs(date2.getTime() - date1.getTime());
        long daysDiff = TimeUnit.MILLISECONDS.toDays(timeDiff);
        logger.debug("Days difference: " + daysDiff);
        return daysDiff;
    }

    public static String generateTimestampForFileName() {
        logger.debug("Generating timestamp for file name");
        String timestamp = formatDate(new Date(), "yyyy-MM-dd_HH-mm-ss-SSS");
        logger.info("Timestamp for file name: " + timestamp);
        return timestamp;
    }
}
