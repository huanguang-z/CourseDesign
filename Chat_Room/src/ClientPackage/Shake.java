package ClientPackage;

public class Shake extends Thread{
    Chat chatFrame;
    public Shake(Chat chatFrame)
    {
        this.chatFrame=chatFrame;
    }


    @Override
    public void run() {
        try {
            for (int i=0; i<5; i++)
            {
                chatFrame.setLocation(chatFrame.getX()+10, chatFrame.getY());
                Thread.sleep(10);
                chatFrame.setLocation(chatFrame.getX(), chatFrame.getY()+10);
                Thread.sleep(10);
                chatFrame.setLocation(chatFrame.getX()-10, chatFrame.getY());
                Thread.sleep(10);
                chatFrame.setLocation(chatFrame.getX(),chatFrame.getY()-10);
            }
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }
}
