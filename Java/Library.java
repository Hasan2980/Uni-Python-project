import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

class Library {
    private List<LibraryItem> catalogue = new ArrayList();
    private List<Member> members = new ArrayList();
    private Map<Integer, BorrowedItem> borrowedItems = new HashMap();
    private static final double LATE_FEE_PER_DAY = (double)1.0F;

    Library() {
    }

    public void addItem(LibraryItem item) {
        this.catalogue.add(item);
    }

    public void removeItem(String title) {
        this.catalogue.removeIf((item) -> item.getTitle().equalsIgnoreCase(title));
    }

    public void addMember(Member member) {
        this.members.add(member);
    }

    public void borrowItem(int memberID, String title) throws MemberNotFoundException, ItemNotFoundException, ItemNotAvailableException {
        Member member = this.findMember(memberID);
        if (member == null) {
            throw new MemberNotFoundException("Error the Member with the designated ID " + memberID + " has not been located.");
        } else {
            LibraryItem item = this.findItem(title);
            if (item == null) {
                throw new ItemNotFoundException(" Error the Item with given title '" + title + "' has not been located.");
            } else if (!item.isAvailable()) {
                throw new ItemNotAvailableException("Error the Item with the given title '" + title + "' is not available during this moment in time.");
            } else {
                item.setAvailability(false);
                this.borrowedItems.put(memberID, new BorrowedItem(item, new Date()));
                System.out.println("Success! The item has successfully been borrowed.");
            }
        }
    }

    public void returnItem(int memberID, String title) throws BorrowRecordNotFoundException {
        BorrowedItem borrowed = (BorrowedItem)this.borrowedItems.get(memberID);
        if (borrowed != null && borrowed.item.getTitle().equalsIgnoreCase(title)) {
            borrowed.item.setAvailability(true);
            this.borrowedItems.remove(memberID);
            this.calculateLateFee(borrowed);
            System.out.println("Success! The item has been returned successfully.");
        } else {
            throw new BorrowRecordNotFoundException("Error there is no record of the borrowed item: '" + title + "'.");
        }
    }

    private void calculateLateFee(BorrowedItem borrowed) {
        long diff = (new Date()).getTime() - borrowed.getBorrowDate().getTime();
        int overdueDays = (int)(diff / 86400000L) - 7;
        if (overdueDays > 0) {
            System.out.println("The fee for your late return is: $" + (double)overdueDays * (double)1.0F);
        }

    }

    private Member findMember(int id) {
        return (Member)this.members.stream().filter((m) -> m.getMembershipID() == id).findFirst().orElse((Object)null);
    }

    private LibraryItem findItem(String title) {
        return (LibraryItem)this.catalogue.stream().filter((item) -> item.getTitle().equalsIgnoreCase(title)).findFirst().orElse((Object)null);
    }

    public void displayCatalogue() {
        List var10000 = this.catalogue;
        PrintStream var10001 = System.out;
        Objects.requireNonNull(var10001);
        var10000.forEach(var10001::println);
    }

    public void displayMembers() {
        List var10000 = this.members;
        PrintStream var10001 = System.out;
        Objects.requireNonNull(var10001);
        var10000.forEach(var10001::println);
    }
}
