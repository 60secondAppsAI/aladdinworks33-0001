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
            <maintenanceTask-table
            v-if="maintenanceTasks && maintenanceTasks.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:maintenanceTasks="maintenanceTasks"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-maintenance-tasks="getAllMaintenanceTasks"
             >

            </maintenanceTask-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import MaintenanceTaskTable from "@/components/MaintenanceTaskTable";
import MaintenanceTaskService from "../services/MaintenanceTaskService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    MaintenanceTaskTable,
  },
  data() {
    return {
      maintenanceTasks: [],
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
    async getAllMaintenanceTasks(sortBy='maintenanceTaskId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await MaintenanceTaskService.getAllMaintenanceTasks(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.maintenanceTasks.length) {
					this.maintenanceTasks = response.data.maintenanceTasks;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching maintenanceTasks:", error);
        }
        
      } catch (error) {
        console.error("Error fetching maintenanceTask details:", error);
      }
    },
  },
  mounted() {
    this.getAllMaintenanceTasks();
  },
  created() {
    this.$root.$on('searchQueryForMaintenanceTasksChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllMaintenanceTasks();
    })
  }
};
</script>
<style></style>
