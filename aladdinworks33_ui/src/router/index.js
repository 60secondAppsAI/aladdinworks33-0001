import Vue from "vue";
import VueRouter from "vue-router";
import DefaultLayout from "@/layouts/DefaultLayout.vue";
import DataCenters from  '@/pages/DataCenters.vue';
import DataCenterDetail from  '@/pages/DataCenterDetail.vue';
import MonitoringPoints from  '@/pages/MonitoringPoints.vue';
import MonitoringPointDetail from  '@/pages/MonitoringPointDetail.vue';
import Racks from  '@/pages/Racks.vue';
import RackDetail from  '@/pages/RackDetail.vue';
import NetworkSwitchs from  '@/pages/NetworkSwitchs.vue';
import NetworkSwitchDetail from  '@/pages/NetworkSwitchDetail.vue';
import PowerSupplys from  '@/pages/PowerSupplys.vue';
import PowerSupplyDetail from  '@/pages/PowerSupplyDetail.vue';
import PowerStrips from  '@/pages/PowerStrips.vue';
import PowerStripDetail from  '@/pages/PowerStripDetail.vue';
import CoolingUnits from  '@/pages/CoolingUnits.vue';
import CoolingUnitDetail from  '@/pages/CoolingUnitDetail.vue';
import Generators from  '@/pages/Generators.vue';
import GeneratorDetail from  '@/pages/GeneratorDetail.vue';
import TemperatureSensors from  '@/pages/TemperatureSensors.vue';
import TemperatureSensorDetail from  '@/pages/TemperatureSensorDetail.vue';
import CurrentSensors from  '@/pages/CurrentSensors.vue';
import CurrentSensorDetail from  '@/pages/CurrentSensorDetail.vue';
import Users from  '@/pages/Users.vue';
import UserDetail from  '@/pages/UserDetail.vue';
import Alerts from  '@/pages/Alerts.vue';
import AlertDetail from  '@/pages/AlertDetail.vue';
import MaintenanceTasks from  '@/pages/MaintenanceTasks.vue';
import MaintenanceTaskDetail from  '@/pages/MaintenanceTaskDetail.vue';
import PowerAlerts from  '@/pages/PowerAlerts.vue';
import PowerAlertDetail from  '@/pages/PowerAlertDetail.vue';
import CapacityAlerts from  '@/pages/CapacityAlerts.vue';
import CapacityAlertDetail from  '@/pages/CapacityAlertDetail.vue';
import CoolingAlerts from  '@/pages/CoolingAlerts.vue';
import CoolingAlertDetail from  '@/pages/CoolingAlertDetail.vue';
import NetworkAlerts from  '@/pages/NetworkAlerts.vue';
import NetworkAlertDetail from  '@/pages/NetworkAlertDetail.vue';
import Configurations from  '@/pages/Configurations.vue';
import ConfigurationDetail from  '@/pages/ConfigurationDetail.vue';
import Reports from  '@/pages/Reports.vue';
import ReportDetail from  '@/pages/ReportDetail.vue';

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: () => import("../views/HomeView.vue"),
			redirect: '/dataCenters',
																			  },
  {
    path: "/pricing",
    name: "PricingView",
    component: () => import("../views/PricingView.vue"),
  },
  {
    path: "/arts-gallery",
    name: "ArtsGalleryView",
    component: () => import("../views/ArtsGalleryView.vue"),
  },
  {
    path: "/checkout/:id",
    name: "CheckoutView",
    component: () => import("../views/CheckoutView.vue"),
  },
  {
    path: "/stripe-checkout",
    name: "StripeCheckoutView",
    component: () => import("../views/StripeCheckoutView.vue"),
  },
	{
		path: '/dataCenters',
		name: 'DataCenters',
		layout: DefaultLayout,
		component: DataCenters,
	},
	{
	    path: '/dataCenter/:dataCenterId', 
	    name: 'DataCenterDetail',
		layout: DefaultLayout,
	    component: DataCenterDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/monitoringPoints',
		name: 'MonitoringPoints',
		layout: DefaultLayout,
		component: MonitoringPoints,
	},
	{
	    path: '/monitoringPoint/:monitoringPointId', 
	    name: 'MonitoringPointDetail',
		layout: DefaultLayout,
	    component: MonitoringPointDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/racks',
		name: 'Racks',
		layout: DefaultLayout,
		component: Racks,
	},
	{
	    path: '/rack/:rackId', 
	    name: 'RackDetail',
		layout: DefaultLayout,
	    component: RackDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/networkSwitchs',
		name: 'NetworkSwitchs',
		layout: DefaultLayout,
		component: NetworkSwitchs,
	},
	{
	    path: '/networkSwitch/:networkSwitchId', 
	    name: 'NetworkSwitchDetail',
		layout: DefaultLayout,
	    component: NetworkSwitchDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/powerSupplys',
		name: 'PowerSupplys',
		layout: DefaultLayout,
		component: PowerSupplys,
	},
	{
	    path: '/powerSupply/:powerSupplyId', 
	    name: 'PowerSupplyDetail',
		layout: DefaultLayout,
	    component: PowerSupplyDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/powerStrips',
		name: 'PowerStrips',
		layout: DefaultLayout,
		component: PowerStrips,
	},
	{
	    path: '/powerStrip/:powerStripId', 
	    name: 'PowerStripDetail',
		layout: DefaultLayout,
	    component: PowerStripDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/coolingUnits',
		name: 'CoolingUnits',
		layout: DefaultLayout,
		component: CoolingUnits,
	},
	{
	    path: '/coolingUnit/:coolingUnitId', 
	    name: 'CoolingUnitDetail',
		layout: DefaultLayout,
	    component: CoolingUnitDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/generators',
		name: 'Generators',
		layout: DefaultLayout,
		component: Generators,
	},
	{
	    path: '/generator/:generatorId', 
	    name: 'GeneratorDetail',
		layout: DefaultLayout,
	    component: GeneratorDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/temperatureSensors',
		name: 'TemperatureSensors',
		layout: DefaultLayout,
		component: TemperatureSensors,
	},
	{
	    path: '/temperatureSensor/:temperatureSensorId', 
	    name: 'TemperatureSensorDetail',
		layout: DefaultLayout,
	    component: TemperatureSensorDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/currentSensors',
		name: 'CurrentSensors',
		layout: DefaultLayout,
		component: CurrentSensors,
	},
	{
	    path: '/currentSensor/:currentSensorId', 
	    name: 'CurrentSensorDetail',
		layout: DefaultLayout,
	    component: CurrentSensorDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/users',
		name: 'Users',
		layout: DefaultLayout,
		component: Users,
	},
	{
	    path: '/user/:userId', 
	    name: 'UserDetail',
		layout: DefaultLayout,
	    component: UserDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/alerts',
		name: 'Alerts',
		layout: DefaultLayout,
		component: Alerts,
	},
	{
	    path: '/alert/:alertId', 
	    name: 'AlertDetail',
		layout: DefaultLayout,
	    component: AlertDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/maintenanceTasks',
		name: 'MaintenanceTasks',
		layout: DefaultLayout,
		component: MaintenanceTasks,
	},
	{
	    path: '/maintenanceTask/:maintenanceTaskId', 
	    name: 'MaintenanceTaskDetail',
		layout: DefaultLayout,
	    component: MaintenanceTaskDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/powerAlerts',
		name: 'PowerAlerts',
		layout: DefaultLayout,
		component: PowerAlerts,
	},
	{
	    path: '/powerAlert/:powerAlertId', 
	    name: 'PowerAlertDetail',
		layout: DefaultLayout,
	    component: PowerAlertDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/capacityAlerts',
		name: 'CapacityAlerts',
		layout: DefaultLayout,
		component: CapacityAlerts,
	},
	{
	    path: '/capacityAlert/:capacityAlertId', 
	    name: 'CapacityAlertDetail',
		layout: DefaultLayout,
	    component: CapacityAlertDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/coolingAlerts',
		name: 'CoolingAlerts',
		layout: DefaultLayout,
		component: CoolingAlerts,
	},
	{
	    path: '/coolingAlert/:coolingAlertId', 
	    name: 'CoolingAlertDetail',
		layout: DefaultLayout,
	    component: CoolingAlertDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/networkAlerts',
		name: 'NetworkAlerts',
		layout: DefaultLayout,
		component: NetworkAlerts,
	},
	{
	    path: '/networkAlert/:networkAlertId', 
	    name: 'NetworkAlertDetail',
		layout: DefaultLayout,
	    component: NetworkAlertDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/configurations',
		name: 'Configurations',
		layout: DefaultLayout,
		component: Configurations,
	},
	{
	    path: '/configuration/:configurationId', 
	    name: 'ConfigurationDetail',
		layout: DefaultLayout,
	    component: ConfigurationDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/reports',
		name: 'Reports',
		layout: DefaultLayout,
		component: Reports,
	},
	{
	    path: '/report/:reportId', 
	    name: 'ReportDetail',
		layout: DefaultLayout,
	    component: ReportDetail,
	    props: true // Pass route params as props to the component
  	},
];

const router = new VueRouter({
  mode: "hash",
  base: process.env.BASE_URL,
  routes,
});

export default router;
