import http from "../http-common"; 

class NetworkAlertService {
  getAllNetworkAlerts(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/networkAlert/networkAlerts`, searchDTO);
  }

  get(networkAlertId) {
    return this.getRequest(`/networkAlert/${networkAlertId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/networkAlert?field=${matchData}`, null);
  }

  addNetworkAlert(data) {
    return http.post("/networkAlert/addNetworkAlert", data);
  }

  update(data) {
  	return http.post("/networkAlert/updateNetworkAlert", data);
  }
  
  uploadImage(data,networkAlertId) {
  	return http.postForm("/networkAlert/uploadImage/"+networkAlertId, data);
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

export default new NetworkAlertService();
