import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class LibraryLogger {
    
    private static class LogEntry {
        String timestamp;
        String itemTitle;
        String itemType;
        String memberName;
        String returnTimestamp; // null jika belum dikembalikan

        LogEntry(String timestamp, String itemTitle, String itemType, String memberName) {
            this.timestamp = timestamp;
            this.itemTitle = itemTitle;
            this.itemType = itemType;
            this.memberName = memberName;
            this.returnTimestamp = null;
        }
    }

    private List<LogEntry> logs;
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public LibraryLogger() {
        this.logs = new ArrayList<>();
    }

    
    public String logActivity(String activity) {
        String timestamp = LocalDateTime.now().format(FORMATTER);
        
        String[] parts = activity.split("\\|");
        if (parts[0].equals("BORROW")) {
            LogEntry entry = new LogEntry(timestamp, parts[2], parts[1], parts[3]);
            logs.add(entry);
            return timestamp + " " + parts[1] + " '" + parts[2] + "' dipinjam oleh " + parts[3];
        } else if (parts[0].equals("RETURN")) {
            String returnTime = LocalDateTime.now().format(FORMATTER);
            
            for (LogEntry entry : logs) {
                if (entry.itemTitle.equals(parts[1]) && entry.memberName.equals(parts[2])
                        && entry.returnTimestamp == null) {
                    entry.returnTimestamp = returnTime;
                    return returnTime + " '" + parts[1] + "' dikembalikan oleh " + parts[2];
                }
            }
        }
        return timestamp + " " + activity;
    }

  
    public String getLogs() {
        if (logs.isEmpty()) {
            return "Tidak ada log aktivitas.";
        }

        int colDipinjam = 20;
        int colJudul = 35;
        int colMember = 15;
        int colDikembalikan = 20;

        String separator = "+-" + "-".repeat(colDipinjam) + "-+-" +
                "-".repeat(colJudul) + "-+-" +
                "-".repeat(colMember) + "-+-" +
                "-".repeat(colDikembalikan) + "-+";

        StringBuilder sb = new StringBuilder();
        sb.append(separator).append("\n");
        sb.append(String.format("| %-" + colDipinjam + "s | %-" + colJudul + "s | %-" + colMember + "s | %-" + colDikembalikan + "s |%n",
                "Dipinjam pada", "Judul", "Member", "Dikembalikan pada"));
        sb.append(separator).append("\n");

        for (LogEntry entry : logs) {
            String returnTime = (entry.returnTimestamp != null) ? entry.returnTimestamp : "-";
            sb.append(String.format("| %-" + colDipinjam + "s | %-" + colJudul + "s | %-" + colMember + "s | %-" + colDikembalikan + "s |%n",
                    entry.timestamp, entry.itemTitle, entry.memberName, returnTime));
        }
        sb.append(separator);
        return sb.toString();
    }

    public void clearLogs() {
        logs.clear();
    }
}
