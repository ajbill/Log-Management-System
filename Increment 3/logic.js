/**
 * @param {org1.andrew.lognetwork.AddNewLog} addNewLog
 * @transaction
 */

async function AddNewLog(addNewLog) {
  
  	var factory = getFactory();
  	var newlog;

  	return getAssetRegistry("org1.andrew.lognetwork.Log")
  	.then(function(postLogRegistry) {
      
    newLog = factory.newResource("org1.andrew.lognetwork", "Log", addNewLog.logId);
    newLog.data = addNewLog.data;
    
    return postLogRegistry.add(newLog);
})
}

/**
 * Sample read-only transaction
 * @param {org1.andrew.lognetwork.deviceLogHistory} tx
 * @returns {org1.andrew.lognetwork.Log[]} All trxns  
 * @transaction
 */


async function deviceLogHistory(tx) {

    const logId = tx.logId;
    const nativeSupport = tx.nativeSupport;
    // const partRegistry = await getParticipantRegistry('org.example.trading.Trader')

    const nativeKey = getNativeAPI().createCompositeKey('Asset:org1.andrew.lognetwork.Log', [logId]);
    const iterator = await getNativeAPI().getHistoryForKey(nativeKey);
    let results = [];
    let res = {done : false};
    while (!res.done) {
        res = await iterator.next();

        if (res && res.value && res.value.value) {
            let val = res.value.value.toString('utf8');
            if (val.length > 0) {
               console.log("@debug val is  " + val );
               results.push(JSON.parse(val));
            }
        }
        if (res && res.done) {
            try {
                iterator.close();
            }
            catch (err) {
            }
        }
    }
    var newArray = [];
    for (const item of results) {
            newArray.push(getSerializer().fromJSON(item));
    }
    console.log("@debug the results to be returned are as follows: ");

    return newArray; // returns something to my NodeJS client (called via REST API)
}
