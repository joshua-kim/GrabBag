import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import sun.net.www.http.HttpClient;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class RetrieveRandomItemActivity {

    private static SignedRequestsHelper signedRequestsHelper;

    private String choose(File f) throws FileNotFoundException
    {
        String result = null;
        Random rand = new Random();
        int n = 0;
        for(Scanner sc = new Scanner(f); sc.hasNext(); )
        {
            ++n;
            String line = sc.nextLine();
            if(rand.nextInt(n) == 0)
                result = line;
        }

        return result;
    }

    private String getOrderURL() {

        String product = "";

        try {
            product = choose(new File("src/java/dictionary.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        signedRequestsHelper = new SignedRequestsHelper();
        Map<String, String> parameters = new HashMap<>();

        parameters.put("SearchIndex", "All");
        parameters.put("Service", "AWSECommerceService");
        parameters.put("Operation", "ItemSearch");
        parameters.put("Keywords", product);
        parameters.put("AssociateTag", "mobilea0d38bf-20");

        String url = signedRequestsHelper.sign(parameters);

        return url;

    }

  public String getRandomItemName() {
        String url = getOrderURL();

        CloseableHttpResponse response;

        String asin = "";
        String itemName = "";

        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            response = httpclient.execute(httpGet);

            String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");

            System.out.println(responseString);

            int asinIndex = responseString.indexOf("ASIN=") + 5;


            int titleStartIndex = responseString.indexOf("<Title>") + 7;
            int titleEndIndex = responseString.indexOf("</Title>");

            asin = responseString.substring(asinIndex, asinIndex + 10);
            itemName = responseString.substring(titleStartIndex, titleEndIndex);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return itemName;
    }
}
