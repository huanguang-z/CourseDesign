package spider;
import org.jsoup.nodes.Document;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
//import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.io.*;
import java.util.Set;
//实现批量爬取，批量爬取的所有网页地址均在weblist中
public class Uni_crawler extends JFrame implements ActionListener{
    private static JTextArea txtInfo = new JTextArea(50, 50);
    private JButton btnOpen = new JButton("批量爬取");
    private JScrollPane sp = new JScrollPane(txtInfo);
    private JPanel btnPane = new JPanel();
    private Set<String> set=null;
    private String color="#FF0000";
    private String path = "C:\\URLweb\\URL_spider\\weblist.txt";
    public Uni_crawler(){
        super("网址列表");
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        btnPane.add(btnOpen);
        btnOpen.setActionCommand("spider");
        btnOpen.addActionListener(this);

        txtInfo.setFont(new Font("宋体", Font.PLAIN, 16));
        txtInfo.setBackground(Color.WHITE);
        txtInfo.setForeground(Color.black);
        this.add(btnPane, BorderLayout.SOUTH);
        this.add(sp, BorderLayout.CENTER);
        this.setPreferredSize(new Dimension(500, 400));
        this.setLocation(400, 200);
        this.setVisible(true);
        this.pack();
        txtInfo.setEditable(false);
        File file = new File(path);
        txtInfo.setText("");
        readInStrings(file);
    }
    @Override
    //开始批量爬取的监听
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "spider") {
            String path = "C:\\URLweb\\URL_spider\\weblist.txt";
            File file = new File(path);
            Reader reader = null;
            try {
                reader = new FileReader(file);
                BufferedReader buf = new BufferedReader(reader);

                int lines = 0;
                String line = " ";
                File filetemp=new File("C:\\URLweb\\URL_spider\\批量爬取.html");
                if(filetemp.exists()) {
                    filetemp.delete();//保证每次都是新的
                }
                writeFile("C:\\URLweb\\URL_spider\\批量爬取.html",
                        "<!DOCTYPE html>\r\n" +
                                "<html>\r\n" +
                                "<head>\r\n" +
                                "<title>文档标题</title>\r\n" +
                                "</head>\r\n" +
                                " \r\n" +
                                "<body>");
                while ((line = buf.readLine()) != null) {
                    String s = line;
                    webPageResource wpr = new webPageResource();
                    Document document = wpr.getPageSource(s);
                    String title = document.title();
                    String text = document.select("p").text(); // 取得字符串中的文本
                    String htmls = wpr.getPageSource(s).toString();
                    set=GUI.readSensitiveWordFile();
                    writeFile("C:\\URLweb\\URL_spider\\各网址敏感词汇总.txt", "网址为" + s + "的敏感词记录为：");
                    for(String ss:set) {
                        if(text.indexOf(ss)!=-1) {
                            writeFile("C:\\URLweb\\URL_spider\\各网址敏感词汇总.txt", ss+" " );//显示每个网页拥有的敏感词
                        }
                        text=text.replaceAll(ss,"<font color=\""+color+"\">"+ss+"</font>");
                    }
                    writeFile("C:\\URLweb\\URL_spider\\各网址敏感词汇总.txt", "\n" );
                  //  System.out.println(htmls);

                    writeFile("C:\\URLweb\\URL_spider\\批量爬取.html",
                            title+"\n"+text+"\n");//依次写入每个网页的文本内容

                    writeFile("C:\\URLweb\\URL_spider\\各网址敏感词汇总.txt","\n");
                    lines++;
                }
                JOptionPane.showMessageDialog(this, "爬虫完成 ");
                writeFile("C:\\URLweb\\URL_spider\\批量爬取.html",
                        "</body>\r\n" +
                                " \r\n" +
                                "</html>");
            } catch (IOException ex)
            {
            } catch (Exception ex) {
            } finally {
                try {
                    reader.close();
                } catch (IOException ex) {
                }
            }

        }
    }
    public static void  readInStrings(File file) {
        Reader reader = null;
        try {
            reader = new FileReader(file);
            BufferedReader buf = new BufferedReader(reader);
            String line = " ";
            while ((line= buf.readLine()) != null)
            {
                txtInfo.append(line+"\n");
            }
            buf.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
            }
        }
    }
    public void writeFile(String filename, String str){//在框中显示爬取的所有网址
        try{
            FileOutputStream fos = new FileOutputStream(filename, true);
            byte[] b = str.getBytes();
            fos.write(b);
            fos.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new Uni_crawler();
    }
}
