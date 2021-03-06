package com.capgemini.dnd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.capgemini.dnd.customexceptions.ConnectionException;
import com.capgemini.dnd.customexceptions.DisplayException;
import com.capgemini.dnd.customexceptions.DistributorIDDoesNotExistException;
import com.capgemini.dnd.customexceptions.ExitDateException;
import com.capgemini.dnd.customexceptions.ProductIDDoesNotExistException;
import com.capgemini.dnd.customexceptions.ProductNameDoesNotExistException;
import com.capgemini.dnd.customexceptions.ProductOrderIDDoesNotExistException;
import com.capgemini.dnd.customexceptions.UpdateException;
import com.capgemini.dnd.customexceptions.WIdDoesNotExistException;
import com.capgemini.dnd.dto.ProductOrder;
import com.capgemini.dnd.dto.ProductStock;
import com.capgemini.dnd.util.DBUtil;

public class ProductDAOImpl implements ProductDAO {

	Logger logger = Logger.getRootLogger();

	public ProductDAOImpl() {
		// PropertyConfigurator.configure("resources//log4j.properties");

	}

	/*
	 * Product order delivery status update
	 * 
	 */
	
	public String updateStatusProductOrder(String oid, String newStatus) throws Exception {

		Connection connection = DBUtil.getInstance().getConnection();
		PreparedStatement preparedStatement = null;
		java.util.Date today_date = new Date();
		int queryResult = 0;
		if (newStatus.equalsIgnoreCase("RECEIVED")) {
			try {
				preparedStatement = connection.prepareStatement(QueryMapper.UPDATE_DELIVERY_STATUS);

				preparedStatement.setString(1, newStatus);
				preparedStatement.setDate(2, DBUtil.utilDatetoSQLDate(today_date));
				preparedStatement.setInt(3, Integer.parseInt(oid));
				queryResult = preparedStatement.executeUpdate();
				if (queryResult == 0) {
					logger.error(Constants.LOGGER_ERROR_MESSAGE_FAILED_UPDATION);
					throw new UpdateException(Constants.UPDATE_EXCEPTION_MESSAGE_FAILURE_DELIVERY);

				} else {
					logger.info(Constants.LOGGER_INFO_DISPLAY_SUCCESSFUL);
					return Constants.UPADTED_SUCCESSFULLY_MESSAGE;
				}

			} catch (SQLException sqlException) {
				logger.error(sqlException.getMessage());

				throw new UpdateException(Constants.UPDATE_EXCEPTION_MESSAGE_TECHNICAL_PROBLEM);
			} finally {
				try {

					preparedStatement.close();
					connection.close();
				} catch (SQLException sqlException) {
					logger.error(sqlException.getMessage());
					throw new UpdateException(Constants.UPDATE_EXCEPTION_MESSAGE_DBCONNECTION_ERROR);

				}
			}
		} else {
			try {
				preparedStatement = connection.prepareStatement(QueryMapper.UPDATE_DELIVERY_STATUS1);
				preparedStatement.setString(1, newStatus);
				preparedStatement.setInt(2, Integer.parseInt(oid));

				queryResult = preparedStatement.executeUpdate();
				if (queryResult == 0) {
					logger.error(Constants.LOGGER_ERROR_MESSAGE_FAILED_UPDATION);
					throw new UpdateException(Constants.UPDATE_EXCEPTION_MESSAGE_FAILURE_DELIVERY);

				} else {
					logger.info(Constants.LOGGER_INFO_DISPLAY_SUCCESSFUL);
					return Constants.UPADTED_SUCCESSFULLY_MESSAGE;
				}
			} catch (SQLException sqlException) {
				logger.error(sqlException.getMessage());
				throw new UpdateException(Constants.UPDATE_EXCEPTION_MESSAGE_TECHNICAL_PROBLEM);
			} finally {
				try {

					preparedStatement.close();
					connection.close();
				} catch (SQLException sqlException) {
					logger.error(sqlException.getMessage());
					throw new UpdateException(Constants.UPDATE_EXCEPTION_MESSAGE_TECHNICAL_PROBLEM);

				}
			}
		}

	}

	/*****************************************************************
	 * - Method Name: displayProductOrderDetails() - Input Parameters : - Throws :
	 * Exception - Creation Date : 25/09/2019 - Description : Returns list of all
	 * products
	 *******************************************************************/
		public List<ProductOrder> displayProductOrderDetails() throws Exception {
		List<ProductOrder> poList1 = new ArrayList<ProductOrder>();
		Connection connection = DBUtil.getInstance().getConnection();


		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			preparedStatement = connection.prepareStatement(QueryMapper.DISPLAY_PRODUCT_ORDER);
			rs = preparedStatement.executeQuery();

			int isFetched = 0;
			while (rs.next()) {
				isFetched = 1;
				int index = 1;
	

				String orderId = Integer.valueOf(rs.getInt(index++)).toString();
				String name = rs.getString(index++);
				String productId = rs.getString(index++);
				String distributorId = rs.getString(index++);
				double quantityValue = rs.getDouble(index++);
				String quantityUnit = rs.getString(index++);
				Date dateOfOrder = rs.getDate(index++);
				Date dateofDelivery = rs.getDate(index++);
				double pricePerUnit = rs.getDouble(index++);
				double totalPrice = rs.getDouble(index++);
				String deliveryStatus = rs.getString(index++);
				String warehouseId = rs.getString(index++);
				poList1.add(new ProductOrder(orderId, name, productId, distributorId, quantityValue, quantityUnit,
						dateOfOrder, dateofDelivery, pricePerUnit, totalPrice, deliveryStatus, warehouseId));
			}

			if (isFetched == 0) {
				logger.error(Constants.LOGGER_ERROR_FETCH_FAILED);
				throw new DisplayException(Constants.DISPLAY_EXCEPTION_FETCH_FAILED);

			} else {
				logger.info(Constants.LOGGER_INFO_DISPLAY_SUCCESSFUL);

			}

		} catch (SQLException sqlException) {
			logger.error(sqlException.getMessage());
			throw new DisplayException(Constants.DISPLAY_EXCEPTION_MESSAGE_TECHNICAL_PROBLEM);
		} finally {
			try {

				rs.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException sqlException) {
				logger.error(sqlException.getMessage());
				throw new DisplayException(Constants.DISPLAY_EXCEPTION_MESSAGE_DBCONNECTION_ERROR);

			}
		}
		return poList1;

	}

	/*****************************************************************
	 * - Method Name: displayProductOrderbetweenDetails - Input Parameters : -
	 * Throws : Exception - Creation Date : 25/09/2019 - Description : Returns list
	 * of all received products between tow dates entered by user
	 * @return 
	 *******************************************************************/

	public List<ProductOrder> displayProductOrderbetweenDetails(java.util.Date dt1, java.util.Date dt2) throws Exception {
		List<ProductOrder> poList1 = new ArrayList<ProductOrder>();
		Connection connection = DBUtil.getInstance().getConnection();

		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			preparedStatement = connection.prepareStatement(QueryMapper.DISPLAY_PRODUCT_ORDER_BW_DATES);

			preparedStatement.setDate(1, DBUtil.utilDatetoSQLDate(dt1));
			preparedStatement.setDate(2, DBUtil.utilDatetoSQLDate(dt2));
			rs = preparedStatement.executeQuery();

			int isFetched = 0;
			while (rs.next()) {
				isFetched = 1;
				int index = 1;


				String orderId = Integer.valueOf(rs.getInt(index++)).toString();
				String name = rs.getString(index++);
				String productId = rs.getString(index++);
				String distributorId = rs.getString(index++);
				double quantityValue = rs.getDouble(index++);
				String quantityUnit = rs.getString(index++);
				Date dateOfOrder = rs.getDate(index++);
				Date dateofDelivery = rs.getDate(index++);
				double pricePerUnit = rs.getDouble(index++);
				double totalPrice = rs.getDouble(index++);
				String deliveryStatus = rs.getString(index++);
				String warehouseId = rs.getString(index++);
				poList1.add(new ProductOrder(orderId, name, productId, distributorId, quantityValue, quantityUnit,
						dateOfOrder, dateofDelivery, pricePerUnit, totalPrice, deliveryStatus, warehouseId));
			}

			if (isFetched == 0) {
				logger.error(Constants.LOGGER_ERROR_FETCH_FAILED);
				throw new DisplayException(Constants.DISPLAY_EXCEPTION_NO_RECORDS_FOUND);

			} else {
				logger.info(Constants.LOGGER_INFO_DISPLAY_SUCCESSFUL);

			}

		} catch (SQLException sqlException) {
			logger.error(sqlException.getMessage());
			throw new DisplayException(Constants.DISPLAY_EXCEPTION_MESSAGE_TECHNICAL_PROBLEM);
		} finally {
			try {

				rs.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException sqlException) {
				logger.error(sqlException.getMessage());
				throw new DisplayException(Constants.DISPLAY_EXCEPTION_MESSAGE_DBCONNECTION_ERROR);

			}
		}
		return poList1;

	}

	/*****************************************************************
	 * - Method Name: displayOrdersFromDistributor - Input Parameters : - Throws :
	 * Exception - Creation Date : 25/09/2019 - Description : Returns list of all
	 * received products from a particular distributor
	 *******************************************************************/

	public List<ProductOrder> displayOrdersFromDistributor(String distId) throws Exception {
		List<ProductOrder> poList1 = new ArrayList<ProductOrder>();
		Connection connection = DBUtil.getInstance().getConnection();
		PreparedStatement preparedStatement = null;

		ResultSet rs = null;
		try {

			preparedStatement = connection.prepareStatement(QueryMapper.DISPLAY_PRODUCT_ORDER_FROM_DISTRIBUTOR);
			preparedStatement.setString(1, distId);
			rs = preparedStatement.executeQuery();
			int isFetched = 0;
			while (rs.next()) {
				isFetched = 1;
				int index = 1;


				String orderId = Integer.valueOf(rs.getInt(index++)).toString();
				String name = rs.getString(index++);
				String productId = rs.getString(index++);
				String distributorId = rs.getString(index++);
				double quantityValue = rs.getDouble(index++);
				String quantityUnit = rs.getString(index++);
				Date dateOfOrder = rs.getDate(index++);
				Date dateofDelivery = rs.getDate(index++);
				double pricePerUnit = rs.getDouble(index++);
				double totalPrice = rs.getDouble(index++);
				String deliveryStatus = rs.getString(index++);
				String warehouseId = rs.getString(index++);
				poList1.add(new ProductOrder(orderId, name, productId, distributorId, quantityValue, quantityUnit,
						dateOfOrder, dateofDelivery, pricePerUnit, totalPrice, deliveryStatus, warehouseId));


			}

			if (isFetched == 0) {
				logger.error(Constants.LOGGER_ERROR_FETCH_FAILED);
				throw new DisplayException(Constants.DISPLAY_EXCEPTION_FETCH_FAILED);

			} else {
				logger.info(Constants.LOGGER_INFO_MESSAGE_DELIVERY_SUCCESSFUL);

			}

		} catch (SQLException sqlException) {
			logger.error(sqlException.getMessage());
			throw new DisplayException(Constants.DISPLAY_EXCEPTION_MESSAGE_TECHNICAL_PROBLEM);
		} finally {
			try {
				rs.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException sqlException) {
				logger.error(sqlException.getMessage());
				throw new DisplayException(Constants.DISPLAY_EXCEPTION_MESSAGE_DBCONNECTION_ERROR);

			}
		}
		return poList1;

	}

	/*****************************************************************
	 * - Method Name: displayPendingProductOrderDetails - Input Parameters : -
	 * Throws : Exception - Creation Date : 25/09/2019 - Description : Returns list
	 * of all pending products from a particular distributor
	 *******************************************************************/

	public List<ProductOrder> displayPendingProductOrderDetails() throws Exception {
		List<ProductOrder> poList1 = new ArrayList<ProductOrder>();
		Connection connection = DBUtil.getInstance().getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			preparedStatement = connection.prepareStatement(QueryMapper.DISPLAY_PENDING_PRODUCT_ORDER);
			rs = preparedStatement.executeQuery();

			int isFetched = 0;
			while (rs.next()) {
				isFetched = 1;
				int index = 1;


				String orderId = Integer.valueOf(rs.getInt(index++)).toString();
				String name = rs.getString(index++);
				String productId = rs.getString(index++);
				String distributorId = rs.getString(index++);
				double quantityValue = rs.getDouble(index++);
				String quantityUnit = rs.getString(index++);
				Date dateOfOrder = rs.getDate(index++);
				Date dateofDelivery = rs.getDate(index++);
				double pricePerUnit = rs.getDouble(index++);
				double totalPrice = rs.getDouble(index++);
				String deliveryStatus = rs.getString(index++);
				String warehouseId = rs.getString(index++);
				poList1.add(new ProductOrder(orderId, name, productId, distributorId, quantityValue, quantityUnit,
						dateOfOrder, dateofDelivery, pricePerUnit, totalPrice, deliveryStatus, warehouseId));


			}
			if (isFetched == 0) {
				logger.error(Constants.LOGGER_ERROR_FETCH_FAILED);
				throw new DisplayException(Constants.DISPLAY_EXCEPTION_FETCH_FAILED);

			} else {
				logger.info(Constants.LOGGER_INFO_DISPLAY_SUCCESSFUL);

			}

		} catch (SQLException sqlException) {
			logger.error(sqlException.getMessage());
			throw new DisplayException(Constants.DISPLAY_EXCEPTION_MESSAGE_TECHNICAL_PROBLEM);
		} finally {
			try {
				rs.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException sqlException) {
				logger.error(sqlException.getMessage());
				throw new DisplayException(Constants.DISPLAY_EXCEPTION_MESSAGE_DBCONNECTION_ERROR);

			}
		}
		return poList1;
	}

	/*****************************************************************
	 * - Method Name: displayReceivedProductOrderDetails - Input Parameters : -
	 * Throws : Exception - Creation Date : 25/09/2019 - Description : Returns list
	 * of all received products from a particular distributor
	 ********************************************************************/

	public List<ProductOrder> displayReceivedProductOrderDetails() throws Exception {
		
			List<ProductOrder> poList1 = new ArrayList<ProductOrder>();

			Connection connection = DBUtil.getInstance().getConnection();
			PreparedStatement preparedStatement = null;
			ResultSet rs = null;
			try {
				preparedStatement = connection.prepareStatement(QueryMapper.DISPLAY_RECEIVED_PRODUCT_ORDER);
				rs = preparedStatement.executeQuery();

				int isFetched = 0;
				while (rs.next()) {
					isFetched = 1;
					int index = 1;


					String orderId = Integer.valueOf(rs.getInt(index++)).toString();
					String name = rs.getString(index++);
					String productId = rs.getString(index++);
					String distributorId = rs.getString(index++);
					double quantityValue = rs.getDouble(index++);
					String quantityUnit = rs.getString(index++);
					Date dateOfOrder = rs.getDate(index++);
					Date dateofDelivery = rs.getDate(index++);
					double pricePerUnit = rs.getDouble(index++);
					double totalPrice = rs.getDouble(index++);
					String deliveryStatus = rs.getString(index++);
					String warehouseId = rs.getString(index++);
					poList1.add(new ProductOrder(orderId, name, productId, distributorId, quantityValue, quantityUnit,
							dateOfOrder, dateofDelivery, pricePerUnit, totalPrice, deliveryStatus, warehouseId));



				}
				if (isFetched == 0) {
					logger.error(Constants.LOGGER_ERROR_FETCH_FAILED);
					throw new DisplayException(Constants.DISPLAY_EXCEPTION_FETCH_FAILED);

				} else {
					logger.info(Constants.LOGGER_INFO_DISPLAY_SUCCESSFUL);

				}

			} catch (SQLException sqlException) {
				logger.error(sqlException.getMessage());
				throw new DisplayException(Constants.DISPLAY_EXCEPTION_MESSAGE_TECHNICAL_PROBLEM);
			} finally {
				try {
					rs.close();
					preparedStatement.close();
					connection.close();
				} catch (SQLException sqlException) {
					logger.error(sqlException.getMessage());
					throw new DisplayException(Constants.DISPLAY_EXCEPTION_MESSAGE_DBCONNECTION_ERROR);

				}
			}
			return poList1;
		}
	
		
	

	/*****************************************************************
	 * - Method Name: displayDispatchedProductOrderDetails - Input Parameters : -
	 * Throws : Exception - Creation Date : 25/09/2019 - Description : Returns list
	 * of all dispatched products from a particular distributor
	 *******************************************************************/

	public List<ProductOrder> displayDispatchedProductOrderDetails() throws Exception {
		List<ProductOrder> poList1 = new ArrayList<ProductOrder>();
		
		Connection connection = DBUtil.getInstance().getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			preparedStatement = connection.prepareStatement(QueryMapper.DISPLAY_DISPATCHED_PRODUCT_ORDER);
			rs = preparedStatement.executeQuery();

			int isFetched = 0;
			while (rs.next()) {
				isFetched = 1;
				int index = 1;


				String orderId = Integer.valueOf(rs.getInt(index++)).toString();
				String name = rs.getString(index++);
				String productId = rs.getString(index++);
				String distributorId = rs.getString(index++);
				double quantityValue = rs.getDouble(index++);
				String quantityUnit = rs.getString(index++);
				Date dateOfOrder = rs.getDate(index++);
				Date dateofDelivery = rs.getDate(index++);
				double pricePerUnit = rs.getDouble(index++);
				double totalPrice = rs.getDouble(index++);
				String deliveryStatus = rs.getString(index++);
				String warehouseId = rs.getString(index++);
				poList1.add(new ProductOrder(orderId, name, productId, distributorId, quantityValue, quantityUnit,
						dateOfOrder, dateofDelivery, pricePerUnit, totalPrice, deliveryStatus, warehouseId));


			}
			if (isFetched == 0) {
				logger.error(Constants.LOGGER_ERROR_FETCH_FAILED);
				throw new DisplayException(Constants.DISPLAY_EXCEPTION_FETCH_FAILED);

			} else {
				logger.info(Constants.LOGGER_INFO_DISPLAY_SUCCESSFUL);

			}

		} catch (SQLException sqlException) {
			logger.error(sqlException.getMessage());
			throw new DisplayException(Constants.DISPLAY_EXCEPTION_MESSAGE_TECHNICAL_PROBLEM);
		} finally {
			try {
				rs.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException sqlException) {
				logger.error(sqlException.getMessage());
				throw new DisplayException(Constants.DISPLAY_EXCEPTION_MESSAGE_DBCONNECTION_ERROR);

			}
		}
		return poList1;

	}

	/*****************************************************************
	 * - Method Name: displayCancelledProductOrderDetails - Input Parameters : -
	 * Throws : Exception - Creation Date : 25/09/2019 - Description : Returns list
	 * of all cancelled products from a particular distributor
	 *******************************************************************/

	public List<ProductOrder> displayCancelledProductOrderDetails() throws Exception {
		List<ProductOrder> poList1 = new ArrayList<ProductOrder>();
		Connection connection = DBUtil.getInstance().getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			preparedStatement = connection.prepareStatement(QueryMapper.DISPLAY_CANCELLED_PRODUCT_ORDER);
			rs = preparedStatement.executeQuery();

			int isFetched = 0;
			while (rs.next()) {
				isFetched = 1;
				int index = 1;


				String orderId = Integer.valueOf(rs.getInt(index++)).toString();
				String name = rs.getString(index++);
				String productId = rs.getString(index++);
				String distributorId = rs.getString(index++);
				double quantityValue = rs.getDouble(index++);
				String quantityUnit = rs.getString(index++);
				Date dateOfOrder = rs.getDate(index++);
				Date dateofDelivery = rs.getDate(index++);
				double pricePerUnit = rs.getDouble(index++);
				double totalPrice = rs.getDouble(index++);
				String deliveryStatus = rs.getString(index++);
				String warehouseId = rs.getString(index++);
				poList1.add(new ProductOrder(orderId, name, productId, distributorId, quantityValue, quantityUnit,
						dateOfOrder, dateofDelivery, pricePerUnit, totalPrice, deliveryStatus, warehouseId));


			}
			if (isFetched == 0) {
				logger.error(Constants.LOGGER_ERROR_FETCH_FAILED);
				throw new DisplayException(Constants.DISPLAY_EXCEPTION_FETCH_FAILED);

			} else {
				logger.info(Constants.LOGGER_INFO_DISPLAY_SUCCESSFUL);

			}

		} catch (SQLException sqlException) {
			logger.error(sqlException.getMessage());
			throw new DisplayException(Constants.DISPLAY_EXCEPTION_MESSAGE_TECHNICAL_PROBLEM);
		} finally {
			try {
				rs.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException sqlException) {
				logger.error(sqlException.getMessage());
				throw new DisplayException(Constants.DISPLAY_EXCEPTION_MESSAGE_DBCONNECTION_ERROR);

			}
		}
		return poList1;

	}

	/*******************************************************************************************************
	 * - Function Name : add product order - Input Parameters : ProductOrder po -
	 * Return Type : String - Throws : Exception - Author : Capgemini - Creation
	 * Date : 25/09/2019 - Description : Product order is placed i.e. entry is added
	 * in database
	 ********************************************************************************************************/
	public String addProductOrder(ProductOrder newPO) throws Exception {

		Connection connection = DBUtil.getInstance().getConnection();
		PreparedStatement stmt = connection.prepareStatement(QueryMapper.ADDPRODUCTORDER);
		System.out.println(newPO.getName());
		stmt.setString(1, newPO.getName().toUpperCase());
		stmt.setString(2, newPO.getPid().toUpperCase());
		stmt.setString(3, newPO.getDistributorId().toLowerCase());
		stmt.setDouble(4, newPO.getQuantityValue());
		stmt.setString(5, newPO.getQuantityUnit().toLowerCase());
		stmt.setDate(6, DBUtil.utilDatetoSQLDate(newPO.getDateOfOrder()));
		stmt.setDate(7, DBUtil.utilDatetoSQLDate(newPO.getDateofDelivery()));
		stmt.setDouble(8, newPO.getPricePerUnit());
		stmt.setDouble(9, newPO.getTotalPrice());
		stmt.setString(10, newPO.getDeliveryStatus().toLowerCase());
		stmt.setString(11, newPO.getWarehouseId().toLowerCase());

		int noOfRows = stmt.executeUpdate();
		stmt.close();
		connection.close();
		if (noOfRows == 1)
			return "Product Order placed successfully";
		else
			return "0 rows updated";
	}

	public boolean doesProductOrderIdExist(String orderId)
			throws ProductOrderIDDoesNotExistException, ConnectionException, SQLException {

		boolean pOrderIdFound = false;
		Connection connection;
		try {

			connection = DBUtil.getInstance().getConnection();
		} catch (Exception e) {

			logger.error(e.getMessage());
			throw new ConnectionException(Constants.CONNECTION_EXCEPTION_MESSAGE_DBCONNECTION_ERROR);
		}

		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			preparedStatement = connection.prepareStatement(QueryMapper.SELECT_ALL_PRODUCT_ORDER);
			preparedStatement.setString(1, orderId);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				pOrderIdFound = true;
				break;
			}

			if (!pOrderIdFound) {
				logger.error(Constants.PRODUCT_ID_DOES_NOT_EXISTS_EXCEPTION);
				throw new ProductOrderIDDoesNotExistException(Constants.PRODUCT_ID_DOES_NOT_EXISTS_EXCEPTION);
			}

			return pOrderIdFound;
		} finally {
			rs.close();
			preparedStatement.close();
			connection.close();
		}
	}

	@Override
	public boolean doesProductIdExist(String prodId, String name)
			throws ProductIDDoesNotExistException, ConnectionException, SQLException {

		boolean pIdFound = false;
		Connection connection;
		try {

			connection = DBUtil.getInstance().getConnection();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new ConnectionException(Constants.CONNECTION_EXCEPTION_MESSAGE_DBCONNECTION_ERROR);
		}

		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			preparedStatement = connection.prepareStatement(QueryMapper.SELECT_PRODUCTID_ORDER);
			preparedStatement.setString(1, name.toUpperCase());
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String pId = rs.getString(1);
				if (pId.equalsIgnoreCase(prodId)) {
					pIdFound = true;
					break;
				}
			}
			if (pIdFound) {
				return pIdFound;
			}
			if (!pIdFound) {
				logger.error(Constants.PRODUCT_ID_DOES_NOT_EXISTS_EXCEPTION);
				throw new ProductIDDoesNotExistException(Constants.PRODUCT_ID_DOES_NOT_EXISTS_EXCEPTION);
			}

			return pIdFound;
		} finally {
			rs.close();
			preparedStatement.close();
			connection.close();
		}
	}

	@Override
	public boolean doesProductOrderIdExistInStock(String orderId)
			throws ProductOrderIDDoesNotExistException, ConnectionException, SQLException {

		boolean productOrderIdFound = false;
		int oid = -1;
		try {
			oid = Integer.parseInt(orderId);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return productOrderIdFound;
		}
		Connection connection;
		try {

			connection = DBUtil.getInstance().getConnection();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new ConnectionException(Constants.CONNECTION_EXCEPTION_MESSAGE_DBCONNECTION_ERROR);
		}

		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			preparedStatement = connection.prepareStatement(QueryMapper.SELECT_PRODUCT_STOCK);
			preparedStatement.setInt(1, oid);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int pId = rs.getInt(1);
				if (pId == oid) {
					productOrderIdFound = true;
					break;
				}
			}
			if (productOrderIdFound) {
				return productOrderIdFound;
			}
			if (!productOrderIdFound) {
				logger.error(Constants.PRODUCT_ID_DOES_NOT_EXIST_IN_STOCK_EXCEPTION);
				throw new ProductOrderIDDoesNotExistException(Constants.PRODUCT_ID_DOES_NOT_EXIST_IN_STOCK_EXCEPTION);
			}

			return productOrderIdFound;
		} finally {
			rs.close();
			preparedStatement.close();
			connection.close();
		}

	}

	public boolean doesDistributorIdExist(String distId)
			throws DistributorIDDoesNotExistException, ConnectionException, SQLException {

		boolean distIdFound = false;
		Connection connection;
		try {

			connection = DBUtil.getInstance().getConnection();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new ConnectionException(Constants.CONNECTION_EXCEPTION_MESSAGE_DBCONNECTION_ERROR);
		}

		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			preparedStatement = connection.prepareStatement(QueryMapper.CHECK_IF_DISTRIBUTOR_ID_EXIST);
			preparedStatement.setString(1, distId);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				distIdFound = true;
				break;
			}

			if (!distIdFound) {
				logger.error(Constants.DISTRIBUTOR_ID_DOES_NOT_EXISTS_EXCEPTION);
				throw new DistributorIDDoesNotExistException(Constants.DISTRIBUTOR_ID_DOES_NOT_EXISTS_EXCEPTION);
			}
			return distIdFound;
		} finally {
			rs.close();
			preparedStatement.close();
			connection.close();
		}

	}

	public boolean doesProductNameExist(String name)
			throws ProductNameDoesNotExistException, ConnectionException, SQLException {

		boolean productNameFound = false;
		Connection connection;
		try {

			connection = DBUtil.getInstance().getConnection();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new ConnectionException(Constants.CONNECTION_EXCEPTION_MESSAGE_DBCONNECTION_ERROR);
		}

		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			preparedStatement = connection.prepareStatement(QueryMapper.CHECK_IF_PRODUCT_NAME_EXIST);
			preparedStatement.setString(1, name);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				productNameFound = true;
				break;
			}

			if (!productNameFound) {
				logger.error(Constants.PRODUCTNAME_DOES_NOT_EXISTS_EXCEPTION);
				throw new ProductNameDoesNotExistException(Constants.PRODUCTNAME_DOES_NOT_EXISTS_EXCEPTION);
			}
			return productNameFound;
		} finally {
			rs.close();
			preparedStatement.close();
			connection.close();
		}

	}

	@Override
	public boolean doesWIdExist(String WId) throws WIdDoesNotExistException, ConnectionException, SQLException {
		boolean found = false;
		Connection connection;
		try {
			connection = DBUtil.getInstance().getConnection();
		} catch (Exception e) {
			throw new ConnectionException(Constants.CONNECTION_EXCEPTION_MESSAGE_DBCONNECTION_ERROR);
		}
		Statement statement = null;
		ResultSet rs = null;
		try {
			statement = connection.createStatement();
			String sql = "SELECT * FROM Warehouse WHERE WarehouseID='" + WId.toLowerCase() + "';";
			rs = statement.executeQuery(sql);

			while (rs.next()) {
				found = true;
				break;
			}
			if (!found)
				throw new WIdDoesNotExistException(Constants.WAREHOUSE_ID_DOES_NOT_EXISTS_EXCEPTION);
			return found;
		} finally {
			rs.close();
			statement.close();
			connection.close();
		}

	}

	/*******************************************************************************************************
	 * - Function Name : track product order - Input Parameters : String orderid -
	 * Return Type : String - Throws : No Exception - Author : Capgemini - Creation
	 * Date : 25/09/2019 - Description : Product order is tracked in the warehouse
	 * along with its shelf life
	 ********************************************************************************************************/

	@Override
	public String trackProductOrder(ProductStock productStock) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			connection = DBUtil.getInstance().getConnection();

			statement = connection.prepareStatement(QueryMapper.TRACKPRODUCTORDER);
			statement.setInt(1, Integer.parseInt(productStock.getOrderId()));
			rs = statement.executeQuery();

			String warehouseId = null;
			java.sql.Date exitDate = null;
			java.sql.Date manDate = null;

			while (rs.next()) {

				exitDate = rs.getDate(1);

				manDate = rs.getDate(2);

				warehouseId = rs.getString(3);

			}

			String message = "The order ID had been in the warehouse with warehouseID = " + warehouseId + " from "
					+ manDate.toString() + " to " + exitDate.toString() + "("
					+ DBUtil.diffBetweenDays(exitDate, manDate) + " days)";

			return message;

		} catch (SQLException e) {
			logger.error(Constants.LOGGER_ERROR_MESSAGE_QUERY_NOT_EXECUTED);
			return Constants.LOGGER_ERROR_MESSAGE_QUERY_NOT_EXECUTED;
		}

		catch (Exception e) {
			logger.error(Constants.LOGGER_ERROR_MESSAGE_DATABASE_NOT_CONNECTED);
			return Constants.LOGGER_ERROR_MESSAGE_DATABASE_NOT_CONNECTED;
		} finally {
			try {
				rs.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage());
			}

		}
	}

	/*******************************************************************************************************
	 * - Function Name : Exit Date Check - Input Parameters : String orderId, Date
	 * exit_date - Return Type : boolean - Throws : ExitDateException, SQLException,
	 * ConnectionException - Author : CAPGEMINI - Creation Date : 25/09/2019 -
	 * Description : checking that exit_date should be after manufacturing_date and
	 * before expiry_date.
	 ********************************************************************************************************/

	@Override
	public boolean exitDateCheck(ProductStock productStock)
			throws ExitDateException, SQLException, ConnectionException {
		Connection connection = null;
		boolean datecheck = false;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			connection = DBUtil.getInstance().getConnection();
			statement = connection.prepareStatement(QueryMapper.CHECKEXITDATE);
			statement.setInt(1, Integer.parseInt(productStock.getOrderId()));
			rs = statement.executeQuery();

			java.sql.Date manufacturingDate = null;
			java.sql.Date expiryDate = null;

			while (rs.next()) {

				manufacturingDate = rs.getDate(1);

				expiryDate = rs.getDate(2);

				if (productStock.getExitDate().after(manufacturingDate)
						&& productStock.getExitDate().before(expiryDate)) {
					datecheck = true;
					return datecheck;
				}
			}
			throw new ExitDateException(Constants.EXIT_DATE_EXCEPTION);

		} catch (SQLException e) {
			throw new SQLException(Constants.LOGGER_ERROR_MESSAGE_QUERY_NOT_EXECUTED);

		}

		catch (ExitDateException e) {
			throw e;

		}

		catch (Exception e) {
			throw new ConnectionException(Constants.LOGGER_ERROR_MESSAGE_DATABASE_NOT_CONNECTED);


		} finally {
			rs.close();
			statement.close();
			connection.close();

		}

	}

	/*******************************************************************************************************
	 * - Function Name : update ExitDate in Stock - Input Parameters : String
	 * orderId, Date Exit_date - Return Type : Void - Throws : SQL Exception,
	 * Exception - Author : CAPGEMINI - Creation Date : 25/09/2019 - Description :
	 * updating exit date for an orderId in the Product Stock table.
	 ********************************************************************************************************/

	@Override
	public String updateExitDateinStock(ProductStock productStock) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DBUtil.getInstance().getConnection();

			statement = connection.prepareStatement(QueryMapper.UPDATEEXITDATE);
			statement.setDate(1, DBUtil.utilDatetoSQLDate(productStock.getExitDate()));
			statement.setInt(2, Integer.parseInt(productStock.getOrderId()));
			statement.executeUpdate();
			return Constants.DATA_INSERTED_MESSAGE;
		}

		catch (SQLException e) {
			logger.error(Constants.LOGGER_ERROR_MESSAGE_QUERY_NOT_EXECUTED);
			return Constants.LOGGER_ERROR_MESSAGE_QUERY_NOT_EXECUTED;

		} catch (Exception e) {
			logger.error(Constants.LOGGER_ERROR_MESSAGE_DATABASE_NOT_CONNECTED);
			return Constants.LOGGER_ERROR_MESSAGE_DATABASE_NOT_CONNECTED;
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
			}
		}

	}

	/*******************************************************************************************************
	 * - Function Name : update product stock - Input Parameters : String orderid,
	 * Date manufacturing date, Date exit date, String quality Status - Return Type
	 * : Void - Throws : SQL Exception, Exception - Author : Capgemini - Creation
	 * Date : 25/09/2019 - Description : updating manufacturing date, exit date and
	 * quality status into product stock table.
	 ********************************************************************************************************/

	@Override
	public String updateProductStock(ProductStock productStock) {
		Connection connection = null;
		PreparedStatement statement1 = null;
		ResultSet rs = null;
		PreparedStatement statement = null;
		PreparedStatement statement2 = null;
		try {

			connection = DBUtil.getInstance().getConnection();

			boolean orderIdcheckInStock = false;
			orderIdcheckInStock = doesProductOrderIdExistInStock(productStock.getOrderId());
			if (orderIdcheckInStock == false) {
				statement1 = connection.prepareStatement(QueryMapper.RETRIEVEPRODUCTORDERDETAILSFORPRODUCTSTOCK);
				statement1.setInt(1, Integer.parseInt(productStock.getOrderId()));
				rs = statement1.executeQuery();
				String name = null;
				double priceperunit = 0;
				double quantityValue = 0;
				String quantityUnit = null;
				double totalprice = 0;
				String warehouseId = null;
				Date dateofdelivery = null;

				while (rs.next()) {
					name = rs.getString(1);
					priceperunit = rs.getDouble(2);
					quantityValue = rs.getDouble(3);
					quantityUnit = rs.getString(4);
					totalprice = rs.getDouble(5);
					warehouseId = rs.getString(6);
					dateofdelivery = rs.getDate(7);
				}

				statement2 = connection.prepareStatement(QueryMapper.INSERTPRODUCTSTOCK);
				statement2.setInt(1, Integer.parseInt(productStock.getOrderId()));
				statement2.setString(2, name);
				statement2.setDouble(3, priceperunit);
				statement2.setDouble(4, quantityValue);
				statement2.setString(5, quantityUnit);
				statement2.setDouble(6, totalprice);
				statement2.setString(7, warehouseId);
				statement2.setDate(8, DBUtil.utilDatetoSQLDate(dateofdelivery));

				statement2.executeUpdate();

			}

			statement = connection.prepareStatement(QueryMapper.UPDATEPRODUCTSTOCK);
			statement.setDate(1, DBUtil.utilDatetoSQLDate(productStock.getManufacturingDate()));
			statement.setDate(2, DBUtil.utilDatetoSQLDate(productStock.getExpiryDate()));
			statement.setString(3, productStock.getQualityCheck());
			statement.setInt(4, Integer.parseInt(productStock.getOrderId()));
			statement.executeUpdate();
			connection.close();

			return Constants.DATA_INSERTED_MESSAGE;

		}

		catch (SQLException e) {
			logger.error(Constants.LOGGER_ERROR_MESSAGE_QUERY_NOT_EXECUTED);
			return Constants.LOGGER_ERROR_MESSAGE_DATABASE_NOT_CONNECTED;

		}

		catch (Exception e) {
			logger.error(Constants.LOGGER_ERROR_MESSAGE_DATABASE_NOT_CONNECTED);
			return Constants.LOGGER_ERROR_MESSAGE_DATABASE_NOT_CONNECTED;
		} finally {
			try {
				rs.close();
				statement1.close();
				statement.close();
				statement2.close();
				connection.close();

			} catch (SQLException e) {

				logger.error(e.getMessage());
			}
		}

	}

}
