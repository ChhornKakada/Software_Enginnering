public class Time {
    int hour, mn, second;

    static int timeToSecond(int h, int MN, int S) {
        int results;
        results = h*3600 + MN*60 + S;
        return results;
    }

    static Time secToTime(int sec) {
        Time time = new Time();
        time.hour = sec / 3600;
        time.mn = (sec % 3600) / 60;
        time.second = (sec % 3600) % 60;
        return time;
    }
}
