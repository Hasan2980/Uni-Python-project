import java.util.Date;

public class BorrowedItem {
    public LibraryItem item;
    public Date borrowDate;

    public BorrowedItem(LibraryItem item, Date borrowDate) {
        this.item = item;
        this.borrowDate = borrowDate;
    }

    public LibraryItem getItem() {
        return this.item;
    }

    public Date getBorrowDate() {
        return this.borrowDate;
    }
}
