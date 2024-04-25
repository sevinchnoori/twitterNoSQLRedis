// class to represent a follows object
public class follows {
    private int userID;
    private int followsID;

    // getters and setters for follows object
    public int fllwUID() {
        return this.userID;
    }

    public int fllwFID() {
        return this.followsID;
    }

    public void setFllwUID(int uid) {
        this.userID = uid;
    }

    public void setFllwFID(int fid) {
        this.followsID = fid;
    }
}
