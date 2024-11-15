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
            <configuration-table
            v-if="configurations && configurations.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:configurations="configurations"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-configurations="getAllConfigurations"
             >

            </configuration-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import ConfigurationTable from "@/components/ConfigurationTable";
import ConfigurationService from "../services/ConfigurationService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    ConfigurationTable,
  },
  data() {
    return {
      configurations: [],
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
    async getAllConfigurations(sortBy='configurationId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await ConfigurationService.getAllConfigurations(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.configurations.length) {
					this.configurations = response.data.configurations;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching configurations:", error);
        }
        
      } catch (error) {
        console.error("Error fetching configuration details:", error);
      }
    },
  },
  mounted() {
    this.getAllConfigurations();
  },
  created() {
    this.$root.$on('searchQueryForConfigurationsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllConfigurations();
    })
  }
};
</script>
<style></style>
