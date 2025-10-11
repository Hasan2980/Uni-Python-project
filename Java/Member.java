

public class Member {
    private String name;
    private int membershipID;
    private String contactInfo;

    public Member(String name, int membershipID, String contactInfo) throws InvalidMemberException {
        if (name != null && !name.trim().isEmpty()) {
            if (membershipID < 0) {
                throw new InvalidMemberException("Error membership ID cannot be negative/less than 0, please try again.");
            } else if (contactInfo != null && !contactInfo.trim().isEmpty()) {
                this.name = name;
                this.membershipID = membershipID;
                this.contactInfo = contactInfo;
            } else {
                throw new InvalidMemberException("Error contact information cannot be blank and/or empty, please try again.");
            }
        } else {
            throw new InvalidMemberException("Error name cannot be blank and/or empty, please try again.");
        }
    }

    public int getMembershipID() {
        return this.membershipID;
    }

    public String toString() {
        return "Member: " + this.name + " | ID: " + this.membershipID + " | Contact: " + this.contactInfo;
    }
}
