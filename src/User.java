public class User {
    private String trueUserName;
    private String truePassword;
    private String truePhoneNumber;
    private String trueIdCard;

    public User() {
    }

    public User(String trueUserName, String truePassword, String truePhoneNumber, String trueIdCard) {
        this.trueUserName = trueUserName;
        this.truePassword = truePassword;
        this.truePhoneNumber = truePhoneNumber;
        this.trueIdCard = trueIdCard;
    }

    public String getTrueUserName() {
        return trueUserName;
    }

    public void setTrueUserName(String trueUserName) {
        this.trueUserName = trueUserName;
    }

    public String getTruePassword() {
        return truePassword;
    }

    public void setTruePassword(String truePassword) {
        this.truePassword = truePassword;
    }

    public String getTruePhoneNumber() {
        return truePhoneNumber;
    }

    public void setTruePhoneNumber(String truePhoneNumber) {
        this.truePhoneNumber = truePhoneNumber;
    }

    public String getTrueIdCard() {
        return trueIdCard;
    }

    public void setTrueIdCard(String trueIdCard) {
        this.trueIdCard = trueIdCard;
    }
}
