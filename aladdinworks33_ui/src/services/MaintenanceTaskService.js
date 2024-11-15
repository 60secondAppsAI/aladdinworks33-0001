import http from "../http-common"; 

class MaintenanceTaskService {
  getAllMaintenanceTasks(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/maintenanceTask/maintenanceTasks`, searchDTO);
  }

  get(maintenanceTaskId) {
    return this.getRequest(`/maintenanceTask/${maintenanceTaskId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/maintenanceTask?field=${matchData}`, null);
  }

  addMaintenanceTask(data) {
    return http.post("/maintenanceTask/addMaintenanceTask", data);
  }

  update(data) {
  	return http.post("/maintenanceTask/updateMaintenanceTask", data);
  }
  
  uploadImage(data,maintenanceTaskId) {
  	return http.postForm("/maintenanceTask/uploadImage/"+maintenanceTaskId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new MaintenanceTaskService();
