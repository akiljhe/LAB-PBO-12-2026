import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Library {
    private List<AbstractLibraryItem> items;
    private List<Member> members;
    private LibraryLogger logger;

    public Library() {
        this.items = new ArrayList<>();
        this.members = new ArrayList<>();
        this.logger = new LibraryLogger();
    }

    public String addItem(AbstractLibraryItem item) {
    for (AbstractLibraryItem existing : items) {
        if (existing.getItemId() == item.getItemId()) {
            throw new IllegalArgumentException(
                "Item dengan ID " + item.getItemId() + " sudah ada.");
        }
    }
    items.add(item);
    return item.getTitle() + " berhasil ditambahkan";
}

    public String addMember(Member member) {
    for (Member existing : members) {
        if (existing.getMemberId() == member.getMemberId()) {
            throw new IllegalArgumentException(
                "Anggota dengan ID " + member.getMemberId() + " sudah ada.");
        }
    }
    members.add(member);
    return "Anggota " + member.getName() + " berhasil ditambahkan";
}

    public AbstractLibraryItem findItemById(int itemId) {
        for (AbstractLibraryItem item : items) {
            if (item.getItemId() == itemId) {
                return item;
            }
        }
        throw new NoSuchElementException("Item dengan ID " + itemId + " tidak ditemukan.");
    }

    public Member findMemberById(int memberId) {
        for (Member member : members) {
            if (member.getMemberId() == memberId) {
                return member;
            }
        }
        throw new NoSuchElementException("Anggota dengan ID " + memberId + " tidak ditemukan.");
    }

    public String borrowItem(int memberId, int itemId, int days) {
        Member member = findMemberById(memberId);
        AbstractLibraryItem item = findItemById(itemId);

        String result = member.borrow(item, days);

        
        String itemType = (item instanceof Book) ? "Buku" : "DVD";
        logger.logActivity("BORROW|" + itemType + "|" + item.getTitle() + "|" + member.getName());

        return result;
    }

    public String returnItem(int memberId, int itemId, int daysLate) {
        Member member = findMemberById(memberId);
        AbstractLibraryItem item = findItemById(itemId);

        String result = member.returnItem(item, daysLate);

        logger.logActivity("RETURN|" + item.getTitle() + "|" + member.getName());

        return result;
    }

    public String getLibraryStatus() {
        if (items.isEmpty()) {
            return "Tidak ada item di perpustakaan.";
        }

        int colId = 5;
        int colJudul = 35;
        int colStatus = 10;

        String separator = "+-" + "-".repeat(colId) + "-+-" +
                "-".repeat(colJudul) + "-+-" +
                "-".repeat(colStatus) + "-+";

        StringBuilder sb = new StringBuilder();
        sb.append(separator).append("\n");
        sb.append(String.format("| %-" + colId + "s | %-" + colJudul + "s | %-" + colStatus + "s |%n",
                "ID", "Judul", "Status"));
        sb.append(separator).append("\n");

        for (AbstractLibraryItem item : items) {
            String status = item.isBorrowed() ? "Dipinjam" : "Tersedia";
            sb.append(String.format("| %-" + colId + "d | %-" + colJudul + "s | %-" + colStatus + "s |%n",
                    item.getItemId(), item.getTitle(), status));
        }
        sb.append(separator);
        return sb.toString();
    }

    public String getAllLogs() {
        return logger.getLogs();
    }

    public List<AbstractLibraryItem> getItems() { return items; }
    public List<Member> getMembers() { return members; }
}
