import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    private static Library library = new Library();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Sistem Manajemen Perpustakaan ===");

        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("Pilih menu: ");
            int choice = readInt();

            switch (choice) {
                case 1 -> tambahItem();
                case 2 -> tambahAnggota();
                case 3 -> pinjamItem();
                case 4 -> kembalikanItem();
                case 5 -> lihatStatusPerpustakaan();
                case 6 -> lihatLogAktivitas();
                case 7 -> lihatItemDipinjamAnggota();
                case 8 -> {
                    System.out.println("Terima kasih. Program selesai.");
                    running = false;
                }
                default -> System.out.println("[!] Pilihan tidak valid.");
            }
        }

        scanner.close();
    }

    static void printMenu() {
        System.out.println("\n=== Sistem Manajemen Perpustakaan ===");
        System.out.println("1. Tambah Item");
        System.out.println("2. Tambah Anggota");
        System.out.println("3. Pinjam Item");
        System.out.println("4. Kembalikan Item");
        System.out.println("5. Lihat Status Perpustakaan");
        System.out.println("6. Lihat Log Aktivitas");
        System.out.println("7. Lihat Item yang Dipinjam Anggota");
        System.out.println("8. Keluar");
    }

    static void tambahItem() {
        System.out.println("\n-- Tambah Item --");
        System.out.println("Jenis item:");
        System.out.println("1. Buku");
        System.out.println("2. DVD");
        System.out.print("Pilih jenis: ");
        int jenis = readInt();

        System.out.print("Masukkan ID item: ");
        int id = readInt();
        System.out.print("Masukkan judul: ");
        String judul = scanner.nextLine().trim();

        try {
            if (jenis == 1) {
                System.out.print("Masukkan nama penulis: ");
                String author = scanner.nextLine().trim();
                Book book = new Book(judul, id, author);
                System.out.println("[OK] " + library.addItem(book));
            } else if (jenis == 2) {
                System.out.print("Masukkan durasi (menit): ");
                int duration = readInt();
                DVD dvd = new DVD(judul, id, duration);
                System.out.println("[OK] " + library.addItem(dvd));
            } else {
                System.out.println("[!] Jenis tidak valid.");
            }
        } catch (Exception e) {
            System.out.println("[Error] " + e.getMessage());
        }
    }

    static void tambahAnggota() {
        System.out.println("\n-- Tambah Anggota --");
        System.out.print("Masukkan ID anggota: ");
        int id = readInt();
        System.out.print("Masukkan nama anggota: ");
        String nama = scanner.nextLine().trim();

        try {
            Member member = new Member(nama, id);
            System.out.println("[OK] " + library.addMember(member));
        } catch (IllegalArgumentException e) {
            System.out.println("[Error] " + e.getMessage());
        }
    }

    static void pinjamItem() {
        System.out.println("\n-- Pinjam Item --");
        System.out.print("Masukkan ID anggota: ");
        int memberId = readInt();
        System.out.print("Masukkan ID item: ");
        int itemId = readInt();
        System.out.print("Masukkan jumlah hari peminjaman: ");
        int days = readInt();

        try {
            String result = library.borrowItem(memberId, itemId, days);
            System.out.println("[OK] " + result);
        } catch (IllegalArgumentException | IllegalStateException | NoSuchElementException e) {
            System.out.println("[Error] " + e.getMessage());
        }
    }

    static void kembalikanItem() {
        System.out.println("\n-- Kembalikan Item --");
        System.out.print("Masukkan ID anggota: ");
        int memberId = readInt();
        System.out.print("Masukkan ID item: ");
        int itemId = readInt();
        System.out.print("Masukkan jumlah hari keterlambatan: ");
        int daysLate = readInt();

        try {
            String result = library.returnItem(memberId, itemId, daysLate);
            System.out.println("[OK] " + result);
        } catch (NoSuchElementException | IllegalArgumentException e) {
            System.out.println("[Error] " + e.getMessage());
        }
    }

    static void lihatStatusPerpustakaan() {
        System.out.println("\n-- Status Perpustakaan --");
        System.out.println(library.getLibraryStatus());
    }

    static void lihatLogAktivitas() {
        System.out.println("\n-- Log Aktivitas --");
        System.out.println(library.getAllLogs());
    }

    static void lihatItemDipinjamAnggota() {
        System.out.println("\n-- Item yang Dipinjam Anggota --");
        System.out.print("Masukkan ID anggota: ");
        int memberId = readInt();

        try {
            Member member = library.findMemberById(memberId);
            System.out.println("Anggota: " + member.getName());
            member.getBorrowedItems();
        } catch (NoSuchElementException e) {
            System.out.println("[Error] " + e.getMessage());
        }
    }

    
    static int readInt() {
        int val = 0;
        try {
            val = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("[!] Input tidak valid, menggunakan 0.");
        }
        return val;
    }
}
