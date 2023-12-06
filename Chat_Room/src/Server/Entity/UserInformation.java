package Server.Entity;

public class UserInformation {
    private String Username;
    private String uiconPath;
    private String motto;

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getUiconPath() {
        return uiconPath;
    }

    public void setUiconPath(String uiconPath) {
        this.uiconPath = uiconPath;
    }
}
