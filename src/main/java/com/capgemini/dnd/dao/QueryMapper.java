package com.capgemini.dnd.dao;



public interface QueryMapper {

		
		public static final String ADDRMORDER = "INSERT into RawmaterialOrders (name,rmid,supplierid,quantityvalue,quantityunit,dateoforder,dateofdelivery,priceperunit,totalprice,deliverystatus,warehouseid)"+ 
				" VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		public static final String ADDPRODUCTORDER = "INSERT into ProductOrders (name,productid,distributorid,quantityvalue,quantityunit,dateoforder,dateofdelivery,priceperunit,totalprice,deliverystatus,warehouseid)"+ 
				" VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		public static final String UPDATERMSTOCK = "update RMStock set manufacturingDate = ?, expiryDate = ?, qualityCheck = ? where orderID = ?";
		
		public static final String UPDATEPRODUCTSTOCK = "update ProductStock set manufacturingDate = ?, expiryDate = ?, qualityCheck = ? where orderID = ?";
		
		public static final String UPDATEPROCESSDATE = "update RMStock set processDate = ? where orderID = ?";
		
		public static final String UPDATEEXITDATE = "update ProductStock set exitDate = ? where orderID = ?";
		
		public static final String TRACKRMORDER = "select processDate, deliveryDate, warehouseID from RMStock where orderID = ?";
		
		public static final String TRACKPRODUCTORDER = "select exitDate, manufacturingDate, warehouseID from ProductStock where orderID = ?";
		
		public static final String CHECKPROCESSDATE = "select deliveryDate, expiryDate from RMStock where orderID = ?";
		
		public static final String RETRIEVEPRODUCTORDERDETAILSFORPRODUCTSTOCK = "SELECT name, priceperunit, quantityValue, quantityUnit, totalprice, warehouseId, dateofdelivery  FROM ProductOrders where orderId = ? ";
		public static final String INSERTPRODUCTSTOCK = "INSERT INTO ProductStock(orderId, name, price_per_unit, quantutyValue, quantityUnit, price, warehouseId, deliveryDate) values (?, ?, ?, ?, ?, ?, ?, ?)"; 
		
		public static final String RETRIEVERMORDERDETAILSFORRMSTOCK = "SELECT name, priceperunit, quantityValue, quantityUnit, totalprice, warehouseId, dateofdelivery  FROM ProductOrders where orderId = ? ";
		public static final String INSERTRMSTOCK = "INSERT INTO ProductStock(name, price_per_unit, quantutyValue, quantityUnit, price, warehouseId, deliveryDate) values (?, ?, ?, ?, ?, ?, ?) where orderId = ? "; 
		
		
		public static final String CHECKEXITDATE = "select manufacturingDate, expiryDate from ProductStock where orderID = ?";
		public static final String UPDATE_DELIVERY_STATUS="UPDATE ProductOrders SET deliverystatus=?, dateofdelivery = ? WHERE orderid=?";
        public static final String UPDATE_DELIVERY_STATUS1="UPDATE ProductOrders SET deliverystatus=? WHERE orderid=?";
		public static final String DISPLAY_PRODUCT_ORDER = "SELECT * FROM ProductOrders";
		public static final String DISPLAY_PENDING_PRODUCT_ORDER="SELECT * FROM ProductOrders WHERE deliverystatus = 'pending'";
		public static final String DISPLAY_RECEIVED_PRODUCT_ORDER="SELECT * FROM ProductOrders WHERE deliverystatus = 'received'";
		public static final String DISPLAY_CANCELLED_PRODUCT_ORDER="SELECT * FROM ProductOrders WHERE deliverystatus = 'Cancelled'";
	    public static final String DISPLAY_DISPATCHED_PRODUCT_ORDER="SELECT * FROM ProductOrders WHERE deliverystatus = 'dispatched'";
	    public static final String DISPLAY_PRODUCT_ORDER_FROM_DISTRIBUTOR="SELECT * FROM ProductOrders WHERE distributorid = ? ";
	    public static final String DISPLAY_PRODUCT_ORDER_BW_DATES="SELECT * FROM ProductOrders WHERE (deliverystatus = 'received') AND (dateofdelivery BETWEEN ? and ?)";
	    public static final String DISPLAY_RAWMATERIAL_ORDER = "SELECT * FROM RawmaterialOrders";
		public static final String DISPLAY_PENDING_RAWMATERIAL_ORDER="SELECT * FROM RawmaterialOrders WHERE deliverystatus = \"pending\"";
		public static final String DISPLAY_RECEIVED_RAWMATERIAL_ORDER="SELECT * FROM RawmaterialOrders WHERE deliverystatus = 'received'";
		public static final String DISPLAY_CANCELLED_RAWMATERIAL_ORDER="SELECT * FROM RawmaterialOrders WHERE deliverystatus = 'Cancelled'";
	    public static final String DISPLAY_DISPATCHED_RAWMATERIAL_ORDER="SELECT * FROM RawmaterialOrders WHERE deliverystatus = 'dispatched'";
	    public static final String DISPLAY_RAWMATERIAL_ORDER_FROM_SUPPLIER="SELECT * FROM RawmaterialOrders WHERE supplierid = ? ";
	    public static final String DISPLAY_RAWMATERIAL_ORDER_BW_DATES="SELECT * FROM RawmaterialOrders WHERE (deliverystatus = 'received') AND (dateofdelivery BETWEEN ? and ?)";
	    public static final String PLACE_PRODUCT_ORDER="INSERT into ProductOrders VALUES(?,?,?,?,?,?,SYSDATE,?,?,?,?,?)";
	    public static final String UPDATE_RM_DELIVERY_STATUS="UPDATE RawmaterialOrders SET deliverystatus=?, dateofdelivery = ? WHERE orderid=?";
        public static final String UPDATE_RM_DELIVERY_STATUS1="UPDATE RawmaterialOrders SET deliverystatus=? WHERE orderid=?";


	    public static final String SELECT_ONE_EMPLOYEE_LOGIN_CREDENTIAL = "SELECT * FROM EmployeeCredentials WHERE Username = ? ";
	    public static final String INSERT_ONE_EMPLOYEE = "INSERT INTO Employees VALUES (?,?,?,?,?,?,?)";
	    public static final String INSERT_ONE_EMPLOYEE_LOGIN_CREDENTIAL = "INSERT INTO EmployeeCredentials VALUES (?,?,?,?,?,?)";
	    public static final String SELECT_ALL_EMPLOYEES = "SELECT * FROM Employees";
	    public static final String SELECT_ALL_EMPLOYEE_LOGIN_CREDENTIALS = "SELECT * FROM EmployeeCredentials";
	    public static final String UPDATE_LOGGED_IN = "UPDATE EmployeeCredentials SET LoggedIn = true WHERE Username = ? AND Password = ?";
	    public static final String UPDATE_LOGGED_OUT = "UPDATE EmployeeCredentials SET LoggedIn = false WHERE Username = ?";
	    public static final String UPDATE_PASSWORD = "UPDATE EmployeeCredentials SET Password = ? WHERE Username = ? ";
	    public static final String UPDATE_ALL_LOGGED_OUT = "UPDATE EmployeeCredentials SET LoggedIn = false";
		public static final String PRODUCT_ID_EXIST = "SELECT pId FROM ProductSpecs WHERE pId=?";
		public static final String SELECT_ALL_RM_ORDER = "SELECT * FROM RawmaterialOrders WHERE orderid=?";
		public static final String SELECT_RMSID_ORDER = "SELECT rmsId FROM RMSpecs WHERE name=?";
		public static final String CHECK_IF_SUPPLIERID_EXIST = "SELECT * FROM Supplier WHERE supplierid=?";
		public static final String CHECK_IF_RNAMEID_EXIST = "SELECT * FROM RawmaterialOrders WHERE name=?";
		public static final String CHECK_IF_WID_EXIST = "SELECT * FROM Warehouse WHERE WarehouseID=?";
		public static final String SELECT_RM_STOCK = "SELECT * FROM RMStock WHERE orderid=?";
		public static final String SELECT_ALL_PRODUCT_ORDER = "SELECT * FROM ProductOrders WHERE orderid=?";
		public static final String SELECT_PRODUCTID_ORDER = "SELECT pId FROM ProductSpecs WHERE pId=?";
		public static final String SELECT_PRODUCT_STOCK = "SELECT * FROM ProductStock WHERE orderid=?";
		public static final String CHECK_IF_DISTRIBUTOR_ID_EXIST = "SELECT * FROM Distributor WHERE distributorId=?";
		public static final String CHECK_IF_PRODUCT_NAME_EXIST = "SELECT * FROM ProductSpecs WHERE name=?";
		
	    
}


