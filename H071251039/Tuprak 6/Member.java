import java.util.ArrayList;
import java.util.List;

public class Member {
    private String name;
    private int memberId;
    private List<AbstractLibraryItem> borrowedItems;

    public Member(String name, int memberId) {
        this.name = name;
        this.memberId = memberId;
        this.borrowedItems = new ArrayList<>();
    }

    public String borrow(AbstractLibraryItem item, int days) {
        if (item.isBorrowed()) {
            throw new IllegalStateException("Item '" + item.getTitle() + "' tidak tersedia (sudah dipinjam).");
        }
        String result = item.borrowItem(days); 
        borrowedItems.add(item);
        return "Item " + item.getTitle() + " berhasil dipinjam selama " + days + " hari";
    }

    public String returnItem(AbstractLibraryItem item, int daysLate) {
        item.returnItem();
        borrowedItems.remove(item);
        double fine = item.calculateFine(daysLate);
        String fineStr = String.format("%,.0f", fine);
        return "Item " + item.getTitle() + " berhasil dikembalikan dengan denda: Rp " + fineStr;
    }

    public void getBorrowedItems() {
        if (borrowedItems.isEmpty()) {
            System.out.println("Tidak ada item yang dipinjam");
            return;
        }
        System.out.println("+-------+------------------------------+");
        System.out.printf("| %-5s | %-28s |%n", "ID", "Judul");
        System.out.println("+-------+------------------------------+");
        for (AbstractLibraryItem item : borrowedItems) {
            System.out.printf("| %-5d | %-28s |%n", item.getItemId(), item.getTitle());
        }
        System.out.println("+-------+------------------------------+");
    }

    public String getName() { return name; }
    public int getMemberId() { return memberId; }
    public List<AbstractLibraryItem> getBorrowedItemsList() { return borrowedItems; }
}
