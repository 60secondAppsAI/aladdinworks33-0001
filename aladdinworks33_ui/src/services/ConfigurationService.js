import http from "../http-common"; 

class ConfigurationService {
  getAllConfigurations(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/configuration/configurations`, searchDTO);
  }

  get(configurationId) {
    return this.getRequest(`/configuration/${configurationId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/configuration?field=${matchData}`, null);
  }

  addConfiguration(data) {
    return http.post("/configuration/addConfiguration", data);
  }

  update(data) {
  	return http.post("/configuration/updateConfiguration", data);
  }
  
  uploadImage(data,configurationId) {
  	return http.postForm("/configuration/uploadImage/"+configurationId, data);
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

export default new ConfigurationService();
