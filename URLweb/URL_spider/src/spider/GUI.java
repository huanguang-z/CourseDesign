package spider;
import org.jsoup.nodes.Document;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.net.URL;

public class GUI {
    public String url;
    private static String ENCODING = "utf-8";    //字符编码
    private static JButton SaveButton=new JButton("添加网址");//可以将指定的网址写入weblist
    private static JButton SpiderButton=new JButton("单网址爬虫");
    private JButton ckfile=new JButton("查看文件");
    private JTextField jft=new JTextField();
    private JFrame frame = new JFrame("Java爬虫");
    private Set<String> set=null;
    private String color="#FF0000";
    private JButton piliangButton=new JButton("批量爬取");

    private static void saveHTML(String html, String fileName) throws IOException {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
            out.print(html);
        }
    }

    private void saveSensitiveWord(String sensitiveWord) {
        try {
            File file = new File("sensitive_words.txt");
            FileWriter writer = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(sensitiveWord);
            bufferedWriter.newLine();
            bufferedWriter.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showSensitiveWords() {
        JFrame sensitiveWordFrame = new JFrame();
        sensitiveWordFrame.setTitle("展示敏感词");
        sensitiveWordFrame.setSize(300, 200);
        sensitiveWordFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        sensitiveWordFrame.setResizable(false);
        sensitiveWordFrame.setLayout(null);

        // 创建文本区域用于展示敏感词
        JTextArea txtAreaSensitiveWords = new JTextArea();
        txtAreaSensitiveWords.setBounds(20, 20, 250, 120);
        txtAreaSensitiveWords.setEditable(false);
        sensitiveWordFrame.add(txtAreaSensitiveWords);

        // 读取敏感词文件，并将内容显示在文本区域中
        try (BufferedReader reader = new BufferedReader(new FileReader("sensitive_words.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                txtAreaSensitiveWords.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        sensitiveWordFrame.setVisible(true);
    }

    private void openAddSensitiveWordPage() {
        JFrame addSensitiveWordFrame = new JFrame();
        addSensitiveWordFrame.setTitle("添加敏感词");
        addSensitiveWordFrame.setSize(300, 200);
        addSensitiveWordFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addSensitiveWordFrame.setResizable(false);
        addSensitiveWordFrame.setLayout(null);

        // 创建文本框用于输入敏感词
        JTextField txtSensitiveWord = new JTextField();
        txtSensitiveWord.setBounds(20, 20, 260, 30);
        addSensitiveWordFrame.getContentPane().add(txtSensitiveWord);

        // 创建按钮用于保存敏感词
        JButton btnSave = new JButton("保存");
        btnSave.setBounds(20, 60, 100, 30);
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sensitiveWord = txtSensitiveWord.getText();
                saveSensitiveWord(sensitiveWord);
                txtSensitiveWord.setText("");
            }
        });
        addSensitiveWordFrame.getContentPane().add(btnSave);

        addSensitiveWordFrame.setVisible(true);
    }


    //
    //
    private int kindOfImg(String url){
        int stratIndex=url.lastIndexOf(".");
        char kind=url.substring(stratIndex).charAt(1);
        switch (kind){
            case 'j':return 0;
            case 'g':return 1;
            case 'p':return 2;
            default: return 3;
        }
    }

    private void openShowSensitiveWordPage() {
        JFrame showSensitiveWordFrame = new JFrame();
        showSensitiveWordFrame.setTitle("展示敏感词");
        showSensitiveWordFrame.setSize(300, 200);
        showSensitiveWordFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        showSensitiveWordFrame.setResizable(false);
        showSensitiveWordFrame.setLayout(null);

        // 创建文本区域用于展示敏感词
        JTextArea txtAreaSensitiveWord = new JTextArea();
        txtAreaSensitiveWord.setEditable(false);
        txtAreaSensitiveWord.setBounds(20, 20, 260, 120);
        showSensitiveWordFrame.getContentPane().add(txtAreaSensitiveWord);

        // 读取敏感词文件内容并展示
        try {
            File file = new File("sensitive_words.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                txtAreaSensitiveWord.append(line + "\n");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        showSensitiveWordFrame.setVisible(true);
    }



    public <BackgroundPanel> GUI() {
        jft.setBounds(20, 24, 450, 41);
        frame.getContentPane().add(jft);
        piliangButton.setBounds(100, 80, 300, 50);
        frame.getContentPane().add(piliangButton);
        SaveButton.setSize(300, 50);
        SaveButton.setLocation(100, 135);
        frame.getContentPane().add(SaveButton);
        SpiderButton.setBounds(100, 190, 300, 50);
        frame.getContentPane().add(SpiderButton);
        frame.getContentPane().setLayout(null);
        frame.setLocation(400,300);
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        ckfile.setBounds(100, 245, 300, 50);
        frame.getContentPane().add(ckfile);
      //  bgp.setBounds(0,0,660,437);
      //  frame.getContentPane().add(bgp);
        //查看文件按钮
        ckfile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CheckFile("打开文件");
            }
        });
        //批量爬取
        piliangButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Uni_crawler();
            }
        });
        JButton btnAddSensitiveWord = new JButton("添加敏感词");
        btnAddSensitiveWord.setBounds(20, 330, 150, 30);
        btnAddSensitiveWord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAddSensitiveWordPage();
            }
        });
        frame.getContentPane().add(btnAddSensitiveWord);

        JButton btnShowSensitiveWord = new JButton("展示敏感词");
        btnShowSensitiveWord.setBounds(280, 330, 150, 30);
        btnShowSensitiveWord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openShowSensitiveWordPage();
            }
        });
        frame.getContentPane().add(btnShowSensitiveWord);


        //开始爬虫，对输入text中的连接进行爬取
        SpiderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    String s1=jft.getText();//网页地址
                    set=readSensitiveWordFile();//set保证不出现重复的敏感词
                    System.out.println("set:"+set);
                    webPageResource wpr=new webPageResource();
                    Document document=wpr.getPageSource(s1);//得到网页的源码
                    String []strs =document.html().split("<");
                    ArrayList<String> ImgUrls=new ArrayList<String>();
                    for(String str:strs){
                        str=str.replaceAll("\\s*|\t|\r|\n","");//去除空格
                        if(str.startsWith("img"))
                        {
                            int startIndex=str.indexOf("src=");
                            int endIndex;
                            int kind=kindOfImg(str);
                            switch (kind){
                                case 0://.jpg|.jpeg类格式
                                    endIndex=str.indexOf(".j");
                                    str=str.substring(startIndex,endIndex)+".jpg";
                                    break;
                                case 1://.gif类格式
                                    endIndex=str.indexOf(".g");
                                    str=str.substring(startIndex,endIndex)+".gif";
                                    break;
                                case 2://.png类格式
                                    endIndex=str.indexOf(".p");
                                    str=str.substring(startIndex,endIndex)+".png";
                                    break;
                                case 3:
                                    continue;
                            }
                            str=str.replace("src=","").replace("\"","");
                            ImgUrls.add(str);
                        }
                        else if(str.contains("background:url(")){
                            int startIndex=str.indexOf("background:url(");
                            int endIndex=str.indexOf(")");
                            str=str.substring(startIndex,endIndex);
                            str=str.replace("background:url(","");
                            ImgUrls.add(str);
                        }
                    }
                    int i=0;
                    //创建文件夹
                    String filesPath="C:\\URLweb\\URL_spider\\"+"web";//文件目录
                    File files=new File(filesPath);
                    if(!files.exists()){
                        files.mkdirs();
                    }
                    for(String imgUrl:ImgUrls){
                        try{
                            if(imgUrl.startsWith("//")){
                                imgUrl="https:"+imgUrl;
                            }else if(imgUrl.startsWith("http")){

                            }else{
                                imgUrl="https://"+imgUrl;
                            }
                            HttpURLConnection httpURLConnection=(HttpURLConnection) new URL(imgUrl).openConnection();
                            httpURLConnection.setRequestMethod("GET");//请求方式
                            httpURLConnection.setConnectTimeout(15000);
                            httpURLConnection.setReadTimeout(20000);
                            InputStream inputStream=httpURLConnection.getInputStream();
                            String suffix;
                            int kind=kindOfImg(imgUrl);
                            switch (kind){
                                case 0:
                                    suffix=".jpg";
                                    break;
                                case 1:
                                    suffix=".gif";
                                    break;
                                case 2:
                                    suffix=".png";
                                    break;
                                default:
                                    suffix=".txt";
                                    break;
                            }
                            String fileName=Integer.toString(i)+suffix;//文件名
                            i++;
                            File file=new File(filesPath+"\\"+fileName);
                            FileOutputStream out=new FileOutputStream(file);
                            int data=0;
                            while((data=inputStream.read())!=-1){
                                out.write(data);
                            }
                            inputStream.close();
                            out.close();
                        }catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                    saveHTML(document.html(),"C:\\URLweb\\URL_spider\\保存源码.html");
                    String title = document.title();
                    String text = document.select("p").text(); // 获取网页p标签的信息，避免正则表达式
                    writeFile("C:\\URLweb\\URL_spider\\各网址敏感词汇总.txt", "网址为" + s1 + "的敏感词记录为：");
                    File filetemp=new File("C:\\URLweb\\URL_spider\\爬取文本.html");
                    if(filetemp.exists()) {
                        filetemp.delete();
                    }
                    for(String s:set) {
                        if(text.indexOf(s)!=-1) { //如果网页中出现了敏感词则需要写入
                            writeFile("C:\\URLweb\\URL_spider\\各网址敏感词汇总.txt", s+" " );
                        }
                        text=text.replaceAll(s,"<font color=\""+color+"\">"+s+"</font>");//找到敏感词包围敏感词，做高亮处理
                    }
                    writeFile("C:\\URLweb\\URL_spider\\各网址敏感词汇总.txt", "\n" );
                   // System.out.println(htmls);

                    //将修改过敏感词网页文本信息保存到爬取文本.html（这个文件每次都会更新，覆盖上次的内容）中
                    writeFile("C:\\URLweb\\URL_spider\\爬取文本.html",
                            "<!DOCTYPE html>\r\n" +
                                    "<html>\r\n" +
                                    "<head>\r\n" +
                                    " <meta http-equiv=\"Content-Type\" charset=\"utf-8\">"+
                                    "<title>文档标题</title>\r\n" +
                                    "</head>\r\n" +
                                    " \r\n" +
                                    "<body>"+title+"\n"+text+"\n"+
                                    "</body>\r\n" +
                                    " \r\n" +
                                    "</html>");
                    JOptionPane.showMessageDialog(
                            frame, "爬虫完成 ");
                }catch(Exception ee) {
                    System.out.println(ee.getMessage());
                }
            }
        });

        //保存网址，将该网址保存到weblist中，这样下次批量处理包含此网址
        SaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s2=jft.getText();
                writeFile("C:\\URLweb\\URL_spider\\weblist.txt",s2+"\n");
            }
        });
    }

    public static void main(String[] args) {
        new GUI();
    }

    public static  Set<String> readSensitiveWordFile() throws Exception{
        //读取敏感词库 功能：将敏感词库中的词 不重复的存放在set中
        Set<String> set = null;
        File file = new File("C:\\URLweb\\URL_spider\\sensitive words.txt");    //文件敏感词库
        InputStreamReader read = new InputStreamReader(new FileInputStream(file),ENCODING);
        try {
            if(file.isFile() && file.exists()){      //文件流是否存在
                set = new HashSet<String>();
                BufferedReader bufferedReader = new BufferedReader(read);
                String txt = null;
                while((txt = bufferedReader.readLine()) != null){    //读取文件，将文件内容放入到set中
                    set.add(txt);
                }
            }
            else{         //不存在抛出异常信息
                throw new Exception("敏感词库文件不存在");
            }
        } catch (Exception e) {
            throw e;
        }finally{
            read.close();     //关闭文件流
        }
        return set;
    }


    //往文件中写
    public void writeFile(String filename, String str){
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
}
