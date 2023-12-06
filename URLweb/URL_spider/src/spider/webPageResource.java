package spider;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.*;
import java.util.HashSet;
import java.util.Set;

//得到网页内容
public class webPageResource {
    public  Document getPageSource(String pageUrl) {
        boolean flag = false;
        Document document = null;
        do{
            try {
                document = Jsoup
                        .connect(pageUrl)
                        .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31")
                        .timeout(5000)
                        .get();
                flag = false;
            } catch (IOException e) {
                // TODO 自动生成的 catch 块
                e.printStackTrace();
                flag = true;
            }
        }while(flag);
        return document;
    }
}
