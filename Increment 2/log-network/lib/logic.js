/**
 * @param {org1.andrew.lognetwork.AddNewLog} tx
 * @transaction
 */

async function AddNewLog(tx) {
 
    var newDeviceLog;
    
    getAssetRegistry("org1.andrew.lognetwork.LoggingDevice")
    .then(function(deviceAssetRegistry) {
    return deviceAssetRegistry.get(tx.deviceId); 
    })
    .then(function(updateDeviceDetails) {
        newDeviceLog = updateDeviceDetails;
      newDeviceLog.log = tx.newLog;
      return getAssetRegistry("org1.andrew.lognetwork.LoggingDevice")
    })
    .then(function(updateAssetRegistry) {
      return updateAssetRegistry.update(newDeviceLog);
    })
  }
  
  /**
   * Read-only transaction
   * @param {org1.andrew.lognetwork.DeviceLogHistory} tx
   * @returns {org1.andrew.lognetwork.LoggingDevice[]} All trxns  
   * @transaction
   */
  
  
  async function DeviceLogHistory(tx) {
  
      const deviceId = tx.deviceId;
      const nativeSupport = tx.nativeSupport;
      const nativeKey = getNativeAPI().createCompositeKey('Asset:org1.andrew.lognetwork.LoggingDevice', [deviceId]);
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
      return newArray; 
  }