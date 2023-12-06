package Server.Entity;


//消息类型枚举

public enum ChatStatus {

    LOGIN(1,"登录消息"),
    NOTICE(2,"系统消息"),
    CHAT(3,"聊天消息"),
    SHAKE(4,"抖动消息"),
    ULIST(5,"在线用户列表"),
    QUIT(6,"退出登录"),
    REMOVE(7,"踢出");
    private int status;
    private String desc;
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private ChatStatus(int status,String desc)
    {
        this.status=status;
        this.desc=desc;
    }

}
