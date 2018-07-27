import java.util.HashMap;
import java.util.Map;

public class RetrieveRandomItemActivity {

    private static SignedRequestsHelper signedRequestsHelper;

    public void orderRandomItem() {

        signedRequestsHelper = new SignedRequestsHelper();
        Map<String, String> parameters = new HashMap<>();

        parameters.put("SearchIndex", "All");
        parameters.put("Service", "AWSECommerceService");
        parameters.put("Operation", "ItemSearch");
        parameters.put("Keywords", "Dildo");
        parameters.put("AssociateTag", "mobilea0d38bf-20");

        String url = signedRequestsHelper.sign(parameters);

        System.out.println(url);

    }
}
