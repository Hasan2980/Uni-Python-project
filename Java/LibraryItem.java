

abstract class LibraryItem {
    protected String title;
    protected String author;
    protected String publicationDate;
    protected String category;
    protected boolean isAvailable;

    public LibraryItem(String Book_title, String author, String publicationDate, String category) throws InvalidLibraryItemException {
        if (Book_title != null && !Book_title.isEmpty()) {
            if (author != null && !author.isEmpty()) {
                if (publicationDate != null && !publicationDate.isEmpty()) {
                    if (category != null && !category.isEmpty()) {
                        this.title = Book_title;
                        this.author = author;
                        this.publicationDate = publicationDate;
                        this.category = category;
                        this.isAvailable = true;
                    } else {
                        throw new InvalidLibraryItemException("Error the Category/genre section can't be blank, please try again");
                    }
                } else {
                    throw new InvalidLibraryItemException("Error the publication Date can't be blank, please try again.");
                }
            } else {
                throw new InvalidLibraryItemException("Error the provided Author space can't be blank, please try again.");
            }
        } else {
            throw new InvalidLibraryItemException("Error the Title of the book can't be blank, please try again.");
        }
    }

    public void setAvailability(boolean current_status) {
        this.isAvailable = current_status;
    }

    public boolean isAvailable() {
        return this.isAvailable;
    }

    public String getTitle() {
        return this.title;
    }

    public String toString() {
        return this.title + " by " + this.author + " (" + this.publicationDate + ") - " + this.category + " | Available: " + this.isAvailable;
    }
}
