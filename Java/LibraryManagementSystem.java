

import java.util.Scanner;

public class LibraryManagementSystem {
    public LibraryManagementSystem() {
    }

    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("\n--- Library Management System ---");
            System.out.println("1. Add a Book");
            System.out.println("2. Register new Member");
            System.out.println("3. Borrow an Item");
            System.out.println("4. Return an Item");
            System.out.println("5. Display the Catalogue");
            System.out.println("6. Display all Members");
            System.out.println("7. Exit management system");
            System.out.print("Please select an option: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException var19) {
                System.out.println("Please input a viable number/option.");
                continue;
            }

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter the book title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter the author of the book: ");
                        String author = scanner.nextLine();
                        System.out.print("Enter book publication date: ");
                        String pubDate = scanner.nextLine();
                        System.out.print("Enter category/genre: ");
                        String category = scanner.nextLine();
                        Book book = new Book(title, author, pubDate, category);
                        library.addItem(book);
                        System.out.println("Success! Book has been added!");
                        break;
                    case 2:
                        System.out.print("Please enter member name: ");
                        String name = scanner.nextLine();
                        System.out.print("Please enter the membership ID: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        System.out.print("Please enter contact details: ");
                        String contact = scanner.nextLine();
                        Member member = new Member(name, id, contact);
                        library.addMember(member);
                        System.out.println("Success! Member has been registered.");
                        break;
                    case 3:
                        System.out.print("Enter ID of selected member: ");
                        int borrowID = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter title of chosen item: ");
                        String borrowTitle = scanner.nextLine();
                        library.borrowItem(borrowID, borrowTitle);
                        break;
                    case 4:
                        System.out.print("Enter the member(s) ID: ");
                        int returnID = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter title of the item: ");
                        String returnTitle = scanner.nextLine();
                        library.returnItem(returnID, returnTitle);
                        break;
                    case 5:
                        library.displayCatalogue();
                        break;
                    case 6:
                        library.displayMembers();
                        break;
                    case 7:
                        System.out.println("Ending simulation...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Error Invalid choice, please select an option numbered 1-7.");
                }
            } catch (InvalidMemberException | MemberNotFoundException | ItemNotFoundException | ItemNotAvailableException | BorrowRecordNotFoundException | InvalidLibraryItemException e) {
                System.err.println("Error: " + ((Exception)e).getMessage());
            } catch (NumberFormatException var18) {
                System.err.println("Please enter a valid number/option.");
            }
        }
    }
}
