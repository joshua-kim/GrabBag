import java.util.HashMap;
import java.util.Map;

public class RetrieveRandomItemActivity {

    private static SignedRequestsHelper signedRequestsHelper;

    public static void main(String[] args) {

        signedRequestsHelper = new SignedRequestsHelper();
        Map<String, String> parameters = new HashMap<>();

        parameters.put("Service", "AWSECommerceService");
        parameters.put("Operation", "ItemLookup");
        parameters.put("ItemId", "0679722769");

        String url = signedRequestsHelper.sign(parameters);

        System.out.println(url);

    }
}
