package ofbizdemo.services;
import java.util.Map;

import org.apache.ofbiz.base.util.Debug;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.service.DispatchContext;
import org.apache.ofbiz.service.ServiceUtil;

public class OfbizDemoServices {
    public static final String MODULE = OfbizDemoServices.class.getName();

    public static Map<String, Object> createOfbizDemo(DispatchContext dctx, Map<String, ? extends Object> context){
        Map<String, Object> result = ServiceUtil.returnSuccess();
        Delegator delegator = dctx.getDelegator();
        try{
            // Creating a generic value 
            GenericValue ofbizDemo = delegator.makeValue("OfbizDemo");

            // Generating a primary key
            ofbizDemo.setNextSeqId();

            // Setting up all non-primary keys from the context
            ofbizDemo.setNonPKFields(context);

            // Creating the record
            ofbizDemo = delegator.create(ofbizDemo);

            result.put("ofbizDemoId", ofbizDemo.getString("ofbizDemoId"));
            Debug.log("====This is my first Java Service implementation in Apache OFBiz. " +
            "OfbizDemo record created successfully with ofbizDemoId:" 
            + ofbizDemo.getString("ofbizDemoId"));
        } catch (GenericEntityException e){
            Debug.logError(e, MODULE);
            return ServiceUtil.returnError("Error in creating record in OfbizDemo entity ........" + MODULE);
        }
        return result;
    }
}
