public class Book extends AbstractLibraryItem {
    private String author;
    private static final int MAX_BORROW_DAYS = 14;
    private static final double FINE_PER_DAY = 10000;

    public Book(String title, int itemId, String author) {
        super(title, itemId);
        this.author = author;
    }

    @Override
    public String getDescription() {
        return "Buku: " + title + " oleh " + author + ", ID: " + itemId;
    }

    @Override
    public String borrowItem(int days) {
        if (isBorrowed) {
            throw new IllegalArgumentException("Item sudah dipinjam dan belum dikembalikan.");
        }
        if (days > MAX_BORROW_DAYS) {
            throw new IllegalArgumentException("Buku hanya dapat dipinjam maksimal " + MAX_BORROW_DAYS + " hari.");
        }
        this.isBorrowed = true;
        return "Item " + title + " berhasil dipinjam selama " + days + " hari";
    }

    @Override
    public double calculateFine(int daysLate) {
        if (daysLate <= 0) return 0;
        return daysLate * FINE_PER_DAY;
    }

    public void printBooks() {
        System.out.println(getDescription());
    }

    public String getAuthor() { return author; }
}
