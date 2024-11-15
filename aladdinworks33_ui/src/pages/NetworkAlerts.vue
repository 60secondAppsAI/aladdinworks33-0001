<template>
  <div class="content">
    <div class="row">
      <div class="col-12">
        <card class="card-plain">
          <!-- <template slot="header">
            <h4 class="card-title">Table on Plain Background</h4>
            <p class="category">Here is a subtitle for this table</p>
          </template>-->
          <div class="table-full-width text-left">
            <networkAlert-table
            v-if="networkAlerts && networkAlerts.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:networkAlerts="networkAlerts"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-network-alerts="getAllNetworkAlerts"
             >

            </networkAlert-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import NetworkAlertTable from "@/components/NetworkAlertTable";
import NetworkAlertService from "../services/NetworkAlertService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    NetworkAlertTable,
  },
  data() {
    return {
      networkAlerts: [],
	  totalElements: 0,
      page: 0,
      searchQuery: '',     
      table1: {
        title: "Simple Table",
        columns: [...tableColumns],
        data: [...tableData],
      },
    };
  },
  methods: {
    async getAllNetworkAlerts(sortBy='networkAlertId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await NetworkAlertService.getAllNetworkAlerts(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.networkAlerts.length) {
					this.networkAlerts = response.data.networkAlerts;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching networkAlerts:", error);
        }
        
      } catch (error) {
        console.error("Error fetching networkAlert details:", error);
      }
    },
  },
  mounted() {
    this.getAllNetworkAlerts();
  },
  created() {
    this.$root.$on('searchQueryForNetworkAlertsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllNetworkAlerts();
    })
  }
};
</script>
<style></style>
