import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;
import java.io.InputStream;

public class RetrieveRandomItemActivity {

    public static void main(String[] args) {

        PostMethod post = new PostMethod("mws.amazonservices.com");


        NameValuePair[] data = {
                new NameValuePair("AWSAccessKeyId", "AKIAEXAMPLEFWR4TJ7ZQ"),
                new NameValuePair("Action", "ListMatchingProducts"),
                new NameValuePair("MWSAuthToken", "amzn.mws.4ea38b7b-f563-7709-4bae-87aeaEXAMPLE"),
                new NameValuePair("MarketplaceId", "ATVPDKIKX0DER"),
                new NameValuePair("Query", "0439708184"),
                new NameValuePair("SellerId", "A1IMEXAMPLEWRC"),
                new NameValuePair("SignatureMethod", "HmacSHA256"),
                new NameValuePair("SignatureVersion", "2"),
                new NameValuePair("Timestamp", "2012-12-12T22%3A23%3A50Z"),
                new NameValuePair("Version", "2011-10-01"),
                new NameValuePair("Signature", "V%2BEXAMPLERT%2Baj%2Fxwqo7y3PIifMFHeqFlNYW0EXAMPLEA%3D")
        };

        post.setRequestBody(data);

        try {
            InputStream in = post.getResponseBodyAsStream();
            System.out.println(post.getResponseBodyAsString());
        } catch (IOException e){
           e.printStackTrace();
        }
    }
}
