import org.apache.ofbiz.entity.GenericEntityException;

def createOfbizDemo(){
    result = [:];
    try{
        ofbizDemo = delegator.makeValue("OfbizDemo");
        ofbizDemo.setNextSeqId();
        ofbizDemo.setNonPKFields(context);
        ofbizDemo = delegator.create(ofbizDemo);
        result.ofbizDemoId = ofbizDemo.ofbizDemoId;
        logInfo("==========This is my first Groovy Service implementation in Apache OFBiz. OfbizDemo record "
                  + "created successfully with ofbizDemoId: "+ofbizDemo.getString("ofbizDemoId"));
    } catch (GenericEntityException e) {
        logError(e.getMessage());
        return error("Error in creating record in OfbizDemo entity ........");
    }
    return result;
}