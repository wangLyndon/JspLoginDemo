package Model;

public class User {
    private int userId;
    private String userName;
    private String userPws;
    private int userAge;

    public User() {
    }

    public User(String userName, String userPws, int userAge) {
        this.userName = userName;
        this.userPws = userPws;
        this.userAge = userAge;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPws() {
        return userPws;
    }

    public void setUserPws(String userPws) {
        this.userPws = userPws;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }
}
