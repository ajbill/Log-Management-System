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
