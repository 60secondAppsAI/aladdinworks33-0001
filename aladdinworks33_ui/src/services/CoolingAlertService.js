import http from "../http-common"; 

class CoolingAlertService {
  getAllCoolingAlerts(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/coolingAlert/coolingAlerts`, searchDTO);
  }

  get(coolingAlertId) {
    return this.getRequest(`/coolingAlert/${coolingAlertId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/coolingAlert?field=${matchData}`, null);
  }

  addCoolingAlert(data) {
    return http.post("/coolingAlert/addCoolingAlert", data);
  }

  update(data) {
  	return http.post("/coolingAlert/updateCoolingAlert", data);
  }
  
  uploadImage(data,coolingAlertId) {
  	return http.postForm("/coolingAlert/uploadImage/"+coolingAlertId, data);
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

export default new CoolingAlertService();
