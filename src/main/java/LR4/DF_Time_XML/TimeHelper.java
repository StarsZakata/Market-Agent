package LR4.DF_Time_XML;

public class TimeHelper {
    private static long initialTime = System.currentTimeMillis();
    public static int hourDurationMs = 75000;
    public static int getCurrentHour() {
        return (int)(System.currentTimeMillis() - initialTime)/hourDurationMs %24; }
    public static int getMsBeforeNextHour() {
        return hourDurationMs - ((int)(System.currentTimeMillis() - initialTime) %hourDurationMs);
    }
}
