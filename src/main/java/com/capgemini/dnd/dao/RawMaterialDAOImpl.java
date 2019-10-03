package com.capgemini.dnd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.capgemini.dnd.customexceptions.ConnectionException;
import com.capgemini.dnd.customexceptions.DisplayException;
import com.capgemini.dnd.customexceptions.ProcessDateException;
import com.capgemini.dnd.customexceptions.RMIDDoesNotExistException;
import com.capgemini.dnd.customexceptions.RMNameDoesNotExistException;
import com.capgemini.dnd.customexceptions.RMOrderIDDoesNotExistException;
import com.capgemini.dnd.customexceptions.RowNotAddedException;
import com.capgemini.dnd.customexceptions.SupplierIDDoesNotExistException;
import com.capgemini.dnd.customexceptions.UpdateException;
import com.capgemini.dnd.customexceptions.WIdDoesNotExistException;
import com.capgemini.dnd.dto.RawMaterialOrder;
import com.capgemini.dnd.dto.RawMaterialStock;
import com.capgemini.dnd.util.DBUtil;

public class RawMaterialDAOImpl implements RawMaterialDAO {

	Logger logger = Logger.getRootLogger();

	public RawMaterialDAOImpl() {
		//PropertyConfigurator.configure("resources//log4j.properties");

	}
	/*
	 * Raw Material Status Update
	 */

	public String updateStatusRawMaterialOrder(String oid, String newStatus) throws Exception {
		Connection con = DBUtil.getInstance().getConnection();
		PreparedStatement preparedStatement = null;
		int queryResult = 0;
		java.util.Date today_date = new Date();
		if (newStatus.equalsIgnoreCase("RECEIVED")) {
			try {
				preparedStatement = con.prepareStatement(QueryMapper.UPDATE_RM_DELIVERY_STATUS);
				preparedStatement.setString(1, newStatus);
				preparedStatement.setDate(2, DBUtil.stringtoDate(today_date));
				preparedStatement.setInt(3, Integer.parseInt(oid));
				queryResult = preparedStatement.executeUpdate();
				if (queryResult == 0) {
					logger.error(Constants.LOGGER_ERROR_MESSAGE_FAILED_UPDATION);
					throw new UpdateException(Constants.UPDATE_EXCEPTION_MESSAGE_FAILURE_DELIVERY);

				} else {
					logger.info(Constants.LOGGER_INFO_MESSAGE_DELIVERY_SUCCESSFUL);
					return Constants.UPADTED_SUCCESSFULLY_MESSAGE;
				}

			} catch (SQLException sqlException) {
				logger.error(sqlException.getMessage());
				throw new UpdateException(Constants.UPDATE_EXCEPTION_MESSAGE_TECHNICAL_PROBLEM);
			} finally {
				try {
					preparedStatement.close();
					con.close();
				} catch (SQLException sqlException) {
					logger.error(sqlException.getMessage());
					throw new UpdateException(Constants.UPDATE_EXCEPTION_MESSAGE_DBCONNECTION_ERROR);

				}
			}
		} else {
			try {
				preparedStatement = con.prepareStatement(QueryMapper.UPDATE_RM_DELIVERY_STATUS1);

				preparedStatement.setString(1, newStatus);
				preparedStatement.setInt(2, Integer.parseInt(oid));

				queryResult = preparedStatement.executeUpdate();
				if (queryResult == 0) {
					logger.error(Constants.LOGGER_ERROR_MESSAGE_FAILED_UPDATION);
					throw new UpdateException(Constants.UPDATE_EXCEPTION_MESSAGE_FAILURE_DELIVERY);

				} else {
					logger.info(Constants.LOGGER_INFO_MESSAGE_DELIVERY_SUCCESSFUL);
					return Constants.UPADTED_SUCCESSFULLY_MESSAGE;
				}
			} catch (SQLException sqlException) {
				logger.error(sqlException.getMessage());
				throw new UpdateException(Constants.UPDATE_EXCEPTION_MESSAGE_TECHNICAL_PROBLEM);
			} finally {
				try {
					preparedStatement.close();
					con.close();
				} catch (SQLException sqlException) {
					logger.error(sqlException.getMessage());
					throw new UpdateException(Constants.UPDATE_EXCEPTION_MESSAGE_DBCONNECTION_ERROR);

				}
			}
		}
	}

	/*****************************************************************
	 * - Method Name: displayRawMaterialOrderDetails() - Input Parameters : - Throws
	 * : Exception - Creation Date : 25/09/2019 - Description : Returns list of all
	 * raw materials
	 *******************************************************************/
	public void displayRawMaterialOrderDetails() throws Exception {
		Connection con = DBUtil.getInstance().getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(QueryMapper.DISPLAY_RAWMATERIAL_ORDER);
			ResultSet rs = pst.executeQuery();

			int isFetched = 0;
			while (rs.next()) {
				isFetched = 1;

				int index = 1;
				System.out.println(rs.getInt(index++) + "\t" + rs.getString(index++) + "\t" + rs.getString(index++) + "\t"
						+ rs.getString(index++) + "\t" + rs.getDouble(index++) + "\t" + rs.getString(index++) + "\t"
						+ rs.getDate(index++) + "\t" + rs.getDate(index++) + "\t" + rs.getDouble(index++) + "\t" + rs.getDouble(index++)
						+ "\t" + rs.getString(index++) + "\t" + rs.getString(index++));

			}
			if (isFetched == 0) {
				logger.error(Constants.LOGGER_ERROR_FETCH_FAILED);
				throw new DisplayException(Constants.DISPLAY_EXCEPTION_INALID_INPUT);

			} else {
				logger.info(Constants.LOGGER_INFO_DISPLAY_SUCCESSFUL);

			}

		} catch (SQLException sqlException) {
			logger.error(sqlException.getMessage());
			throw new DisplayException(Constants.DISPLAY_EXCEPTION_MESSAGE_TECHNICAL_PROBLEM);
		} finally {
			try {
				// resultSet.close();
				pst.close();
				con.close();
			} catch (SQLException sqlException) {
				logger.error(sqlException.getMessage());
				throw new DisplayException(Constants.DISPLAY_EXCEPTION_MESSAGE_DBCONNECTION_ERROR);

			}
		}

	}

	/*****************************************************************
	 * - Method Name: displayPendingRawMaterialOrderDetails() - Input Parameters : -
	 * Throws : Exception - Creation Date : 25/09/2019 - Description : Returns list
	 * of pending raw materials
	 *******************************************************************/
	public void displayPendingRawMaterialOrderDetails() throws Exception {

		Connection con = DBUtil.getInstance().getConnection();

		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(QueryMapper.DISPLAY_PENDING_RAWMATERIAL_ORDER);
			ResultSet rs = pst.executeQuery();

			int isFetched = 0;
			while (rs.next()) {
				isFetched = 1;

				int index = 1;
				System.out.println(rs.getInt(index++) + "\t" + rs.getString(index++) + "\t" + rs.getString(index++)
						+ "\t" + rs.getString(index++) + "\t" + rs.getDouble(index++) + "\t" + rs.getString(index++)
						+ "\t" + rs.getDate(index++) + "\t" + rs.getDate(index++) + "\t" + rs.getDouble(index++) + "\t"
						+ rs.getDouble(index++) + "\t" + rs.getString(index++) + "\t" + rs.getString(index++));

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
				pst.close();
				con.close();
			} catch (SQLException sqlException) {
				logger.error(sqlException.getMessage());
				throw new DisplayException(Constants.DISPLAY_EXCEPTION_MESSAGE_DBCONNECTION_ERROR);

			}
		}

	}

	/*****************************************************************
	 * - Method Name: displayReceivedRawMaterialOrderDetails() - Input Parameters :
	 * - Throws : Exception - Creation Date : 25/09/2019 - Description : Returns
	 * list of received raw materials
	 *******************************************************************/
	public void displayReceivedRawMaterialOrderDetails() throws Exception {

		Connection con = DBUtil.getInstance().getConnection();

		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(QueryMapper.DISPLAY_RECEIVED_RAWMATERIAL_ORDER);
			ResultSet rs = pst.executeQuery();

			int isFetched = 0;
			while (rs.next()) {
				isFetched = 1;

				int index = 1;
				System.out.println(rs.getInt(index++) + "\t" + rs.getString(index++) + "\t" + rs.getString(index++)
						+ "\t" + rs.getString(index++) + "\t" + rs.getDouble(index++) + "\t" + rs.getString(index++)
						+ "\t" + rs.getDate(index++) + "\t" + rs.getDate(index++) + "\t" + rs.getDouble(index++) + "\t"
						+ rs.getDouble(index++) + "\t" + rs.getString(index++) + "\t" + rs.getString(index++));

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
				pst.close();
				con.close();
			} catch (SQLException sqlException) {
				logger.error(sqlException.getMessage());
				throw new DisplayException(Constants.DISPLAY_EXCEPTION_MESSAGE_DBCONNECTION_ERROR);

			}
		}

	}

	/*****************************************************************
	 * - Method Name: displayCancelledRawMaterialOrderDetails() - Input Parameters :
	 * - Throws : Exception - Creation Date : 25/09/2019 - Description : Returns
	 * list of cancelled raw materials
	 *******************************************************************/
	public void displayCancelledRawMaterialOrderDetails() throws Exception {

		Connection con = DBUtil.getInstance().getConnection();

		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(QueryMapper.DISPLAY_CANCELLED_RAWMATERIAL_ORDER);
			ResultSet rs = pst.executeQuery();

			int isFetched = 0;
			while (rs.next()) {
				isFetched = 1;

				int index = 1;
				System.out.println(rs.getInt(index++) + "\t" + rs.getString(index++) + "\t" + rs.getString(index++)
						+ "\t" + rs.getString(index++) + "\t" + rs.getDouble(index++) + "\t" + rs.getString(index++)
						+ "\t" + rs.getDate(index++) + "\t" + rs.getDate(index++) + "\t" + rs.getDouble(index++) + "\t"
						+ rs.getDouble(index++) + "\t" + rs.getString(index++) + "\t" + rs.getString(index++));

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
				pst.close();
				con.close();
			} catch (SQLException sqlException) {
				logger.error(sqlException.getMessage());
				throw new DisplayException(Constants.DISPLAY_EXCEPTION_MESSAGE_DBCONNECTION_ERROR);

			}
		}

	}

	/*****************************************************************
	 * - Method Name: displayDispatchedRawMaterialOrderDetails() - Input Parameters
	 * : - Throws : Exception - Creation Date : 25/09/2019 - Description : Returns
	 * list of dispatched raw materials
	 *******************************************************************/
	public void displayDispatchedRawMaterialOrderDetails() throws Exception {

		Connection con = DBUtil.getInstance().getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(QueryMapper.DISPLAY_DISPATCHED_RAWMATERIAL_ORDER);
			ResultSet rs = pst.executeQuery();

			int isFetched = 0;
			while (rs.next()) {
				isFetched = 1;

				int index = 1;
				System.out.println(rs.getInt(index++) + "\t" + rs.getString(index++) + "\t" + rs.getString(index++)
						+ "\t" + rs.getString(index++) + "\t" + rs.getDouble(index++) + "\t" + rs.getString(index++)
						+ "\t" + rs.getDate(index++) + "\t" + rs.getDate(index++) + "\t" + rs.getDouble(index++) + "\t"
						+ rs.getDouble(index++) + "\t" + rs.getString(index++) + "\t" + rs.getString(index++));

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
				pst.close();
				con.close();
			} catch (SQLException sqlException) {
				logger.error(sqlException.getMessage());
				throw new DisplayException(Constants.DISPLAY_EXCEPTION_MESSAGE_DBCONNECTION_ERROR);

			}
		}

	}

	/*****************************************************************
	 * - Method Name: displayRawmaterialOrdersbetweenDetails() - Input Parameters :
	 * - Throws : Exception - Creation Date : 25/09/2019 - Description : Returns
	 * list of raw materials between two dates given by user
	 *******************************************************************/

	public void displayRawmaterialOrdersbetweenDetails(java.util.Date dt1, java.util.Date dt2) throws Exception {
		Connection con = DBUtil.getInstance().getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(QueryMapper.DISPLAY_RAWMATERIAL_ORDER_BW_DATES);
			pst.setDate(1, DBUtil.stringtoDate(dt1));
			pst.setDate(2, DBUtil.stringtoDate(dt2));
			ResultSet rs = pst.executeQuery();

			int isFetched = 0;
			while (rs.next()) {
				isFetched = 1;

				int index = 1;
				System.out.println(rs.getInt(index++) + "\t" + rs.getString(index++) + "\t" + rs.getString(index++)
						+ "\t" + rs.getString(index++) + "\t" + rs.getDouble(index++) + "\t" + rs.getString(index++)
						+ "\t" + rs.getDate(index++) + "\t" + rs.getDate(index++) + "\t" + rs.getDouble(index++) + "\t"
						+ rs.getDouble(index++) + "\t" + rs.getString(index++) + "\t" + rs.getString(index++));

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
				pst.close();
				con.close();
			} catch (SQLException sqlException) {
				logger.error(sqlException.getMessage());
				throw new DisplayException(Constants.DISPLAY_EXCEPTION_MESSAGE_DBCONNECTION_ERROR);

			}
		}

	}

	/*****************************************************************
	 * - Method Name: displayOrdersFromSupplier - Input Parameters : - Throws :
	 * Exception - Creation Date : 25/09/2019 - Description : Returns list of raw
	 * materials by a particular supplier
	 *******************************************************************/
	public void displayOrdersFromSupplier(String supid) throws Exception {
		Connection con = DBUtil.getInstance().getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(QueryMapper.DISPLAY_RAWMATERIAL_ORDER_FROM_SUPPLIER);
			pst.setString(1, supid);
			ResultSet rs = pst.executeQuery();
			int isFetched = 0;
			while (rs.next()) {
				isFetched = 1;

				int index = 1;
				System.out.println(rs.getInt(index++) + "\t" + rs.getString(index++) + "\t" + rs.getString(index++)
						+ "\t" + rs.getString(index++) + "\t" + rs.getDouble(index++) + "\t" + rs.getString(index++)
						+ "\t" + rs.getDate(index++) + "\t" + rs.getDate(index++) + "\t" + rs.getDouble(index++) + "\t"
						+ rs.getDouble(index++) + "\t" + rs.getString(index++) + "\t" + rs.getString(index++));

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
				pst.close();
				con.close();
			} catch (SQLException sqlException) {
				logger.error(sqlException.getMessage());
				throw new DisplayException(Constants.DISPLAY_EXCEPTION_MESSAGE_DBCONNECTION_ERROR);

			}
		}
	}

	/*******************************************************************************************************
	 * - Function Name : add raw material order 
	 * - Input Parameters :RawmaterialOrder newRMO 
	 * - Return Type : String 
	 * - Throws : Exception 
	 * - Author : Capgemini 
	 * - Creation Date : 25/09/2019 
	 * - Description : Raw Material orders is placed i.e. entry is added in database
	 ********************************************************************************************************/
	public boolean addRawMaterialOrder(RawMaterialOrder newRMO) throws Exception {
		boolean added = false;
		Connection con = DBUtil.getInstance().getConnection();
		PreparedStatement stmt = con.prepareStatement(QueryMapper.ADDRMORDER);
		stmt.setString(1, newRMO.getName().toUpperCase());
		stmt.setString(2, newRMO.getRmId().toUpperCase());
		stmt.setString(3, newRMO.getSupplierId().toUpperCase());
		stmt.setDouble(4, newRMO.getQuantityValue());
		stmt.setString(5, newRMO.getQuantityUnit().toLowerCase());
		stmt.setDate(6, DBUtil.stringtoDate(newRMO.getDateOfOrder()));
		stmt.setDate(7, DBUtil.stringtoDate(newRMO.getDateOfDelivery()));
		stmt.setDouble(8, newRMO.getPricePerUnit());
		stmt.setDouble(9, newRMO.getTotalPrice());
		stmt.setString(10, newRMO.getDeliveryStatus().toUpperCase());
		stmt.setString(11, newRMO.getWarehouseId().toLowerCase());

		int noOfRows = stmt.executeUpdate();
		con.close();
		if (noOfRows == 1)
			added = true;
		if (!added) {
			logger.error(Constants.ROW_NOT_ADDED_MESSAGE);
			throw new RowNotAddedException(Constants.ROW_NOT_ADDED_MESSAGE);
		}
		return added;
	}

	public boolean doesRawMaterialOrderIdExist(String orderId)
			throws RMOrderIDDoesNotExistException, ConnectionException, SQLException {
		boolean rmIdFound = false;
		Connection con;
		try {

			con = DBUtil.getInstance().getConnection();
		} catch (Exception e) {

			logger.error(Constants.CONNECTION_EXCEPTION_MESSAGE_DBCONNECTION_ERROR);
			throw new ConnectionException(Constants.CONNECTION_EXCEPTION_MESSAGE_DBCONNECTION_ERROR);
		}

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		preparedStatement = con.prepareStatement(QueryMapper.SELECT_ALL_RM_ORDER);
		preparedStatement.setString(1, orderId);
		resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			rmIdFound = true;
			break;
		}

		if (!rmIdFound) {
			logger.error(Constants.RAWMATERIAL_ID_DOES_NOT_EXISTS_EXCEPTION);
			throw new RMOrderIDDoesNotExistException(Constants.RAWMATERIAL_ID_DOES_NOT_EXISTS_EXCEPTION);
		}

		con.close();
		return rmIdFound;
	}

	@Override
	public boolean doesRawMaterialIdExist(String rmId, String name)
			throws RMIDDoesNotExistException, ConnectionException, SQLException {
		boolean rmIdFound = false;
		Connection con;
		try {

			con = DBUtil.getInstance().getConnection();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new ConnectionException(Constants.CONNECTION_EXCEPTION_MESSAGE_DBCONNECTION_ERROR);
		}
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		preparedStatement = con.prepareStatement(QueryMapper.SELECT_RMSID_ORDER);
		preparedStatement.setString(1, name.toUpperCase());
		resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			String rmsId = resultSet.getString(1);
			if (rmsId.equalsIgnoreCase(rmId)) {
				rmIdFound = true;
				break;
			}
		}

		con.close();
		if (rmIdFound) {
			return rmIdFound;
		}
		if (!rmIdFound) {
			logger.error(Constants.RAWMATERIAL_ID_DOES_NOT_EXISTS_EXCEPTION);
			throw new RMIDDoesNotExistException(Constants.RAWMATERIAL_ID_DOES_NOT_EXISTS_EXCEPTION);
		}

		return rmIdFound;
	}

	@Override
	public boolean doesRawMaterialOrderIdExistInStock(String orderId)
			throws RMOrderIDDoesNotExistException, ConnectionException, SQLException {

		boolean rmOrderIdFound = false;
		int oid = -1;
		try {
		oid = Integer.parseInt(orderId);
		} catch(Exception e) {
			
		}
		Connection con;
		try {

			con = DBUtil.getInstance().getConnection();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new ConnectionException(Constants.CONNECTION_EXCEPTION_MESSAGE_DBCONNECTION_ERROR);
		}
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		preparedStatement = con.prepareStatement(QueryMapper.SELECT_RM_STOCK);
		preparedStatement.setInt(1, oid);
		resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			int rmsId = resultSet.getInt(1);
			if (rmsId == oid) {
				rmOrderIdFound = true;
				break;
			}
		}

		con.close();
		if (rmOrderIdFound) {
			return rmOrderIdFound;
		}
		if (!rmOrderIdFound) {
			logger.error(Constants.RAWMATERIAL_ID_DOES_NOT_EXIST_IN_STOCK_EXCEPTION);
			throw new RMOrderIDDoesNotExistException(Constants.RAWMATERIAL_ID_DOES_NOT_EXIST_IN_STOCK_EXCEPTION);
		}

		return rmOrderIdFound;
		
	}

	public boolean doesSupplierIdExist(String suppId)
			throws SupplierIDDoesNotExistException, ConnectionException, SQLException {
		
		boolean suppIdFound = false;
		Connection connection;
		try {

			connection = DBUtil.getInstance().getConnection();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new ConnectionException(Constants.CONNECTION_EXCEPTION_MESSAGE_DBCONNECTION_ERROR);
		}

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		preparedStatement = connection.prepareStatement(QueryMapper.CHECK_IF_SUPPLIERID_EXIST);
		preparedStatement.setString(1, suppId);
		resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			suppIdFound = true;
			break;
		}

		if (!suppIdFound) {
			logger.error(Constants.SUPPLIER_ID_DOES_NOT_EXISTS_EXCEPTION);
			throw new SupplierIDDoesNotExistException(Constants.SUPPLIER_ID_DOES_NOT_EXISTS_EXCEPTION);
		}

		connection.close();
		return suppIdFound;
	}

	public boolean doesRMNameExist(String name) throws RMNameDoesNotExistException, ConnectionException, SQLException {
		boolean rmNameFound = false;
		Connection connection;
		try {

			connection = DBUtil.getInstance().getConnection();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new ConnectionException(Constants.CONNECTION_EXCEPTION_MESSAGE_DBCONNECTION_ERROR);
		}

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		preparedStatement = connection.prepareStatement(QueryMapper.CHECK_IF_RNAMEID_EXIST);
		preparedStatement.setString(1, name);
		resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			rmNameFound = true;
			break;
		}

		if (!rmNameFound) {
			logger.error(Constants.RMNAME_DOES_NOT_EXISTS_EXCEPTION);
			throw new RMNameDoesNotExistException(Constants.RMNAME_DOES_NOT_EXISTS_EXCEPTION);
		}

		connection.close();
		return rmNameFound;
	}

	public boolean doesWIdExist(String wId) throws WIdDoesNotExistException, ConnectionException, SQLException {
		
		boolean wIdFound = false;
		Connection connection;
		try {

			connection = DBUtil.getInstance().getConnection();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new ConnectionException(Constants.CONNECTION_EXCEPTION_MESSAGE_DBCONNECTION_ERROR);
		}

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		preparedStatement = connection.prepareStatement(QueryMapper.CHECK_IF_WID_EXIST);
		preparedStatement.setString(1, wId);
		resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			wIdFound = true;
			break;
		}

		if (!wIdFound) {
			logger.error(Constants.WID_DOES_NOT_EXISTS_EXCEPTION);
			throw new WIdDoesNotExistException(Constants.WID_DOES_NOT_EXISTS_EXCEPTION);
		}

		connection.close();
		return wIdFound;
	}

	/*******************************************************************************************************
	 * - Function Name : update raw material stock 
	 * - Input Parameters : String OrderId, Date manufacturing Date, process Date, string quality status 
	 * - Return Type : Void 
	 * - Throws : SQL Exception, Connection Exception 
	 * - Author : CAPGEMINI 
	 * - Creation Date : 25/09/2019 
	 * - Description : updating manufacturing date, process date and quality status into raw material stock table.
	 ********************************************************************************************************/
	@Override
	public String updateRMStock(RawMaterialStock rawMaterialStock) throws SQLException, ConnectionException {
		Connection connection = null;
		boolean rmOrderinStock = false;
		try {

			connection = DBUtil.getInstance().getConnection();

			rmOrderinStock = doesRawMaterialOrderIdExistInStock(rawMaterialStock.getOrderId());
			if (rmOrderinStock == false) {

				PreparedStatement statementt1 = connection.prepareStatement(QueryMapper.RETRIEVERMORDERDETAILSFORRMSTOCK);
				statementt1.setInt(1, Integer.parseInt(rawMaterialStock.getOrderId()));
				ResultSet rs = statementt1.executeQuery();
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

				PreparedStatement statementt2 = connection.prepareStatement(QueryMapper.INSERTRMSTOCK);
				statementt2.setInt(1, Integer.parseInt(rawMaterialStock.getOrderId()));
				statementt2.setString(2, name);
				statementt2.setDouble(3, priceperunit);
				statementt2.setDouble(4, quantityValue);
				statementt2.setString(5, quantityUnit);
				statementt2.setDouble(6, totalprice);
				statementt2.setString(7, warehouseId);
				statementt2.setDate(8, DBUtil.stringtoDate(dateofdelivery));

				statementt2.executeUpdate();
			}

			

			PreparedStatement statementt = connection.prepareStatement(QueryMapper.UPDATERMSTOCK);
			statementt.setDate(1, DBUtil.stringtoDate(rawMaterialStock.getManufacturingDate()));
			statementt.setDate(2, DBUtil.stringtoDate(rawMaterialStock.getExpiryDate()));
			statementt.setString(3, rawMaterialStock.getQualityCheck());
			statementt.setInt(4, Integer.parseInt(rawMaterialStock.getOrderId()));
			statementt.executeUpdate();
			

			connection.close();
			return Constants.DATA_INSERTED_MESSAGE;

		}

		catch (SQLException e) {
			logger.error(Constants.LOGGER_ERROR_MESSAGE_QUERY_NOT_EXECUTED);
			throw new SQLException(Constants.LOGGER_ERROR_MESSAGE_QUERY_NOT_EXECUTED);

		}

		catch (Exception e) {
			logger.error(Constants.LOGGER_ERROR_MESSAGE_DATABASE_NOT_CONNECTED);
			throw new ConnectionException(Constants.LOGGER_ERROR_MESSAGE_DATABASE_NOT_CONNECTED);
		}
	

	}

	/*******************************************************************************************************
	 * - Function Name : process date check 
	 * - Input Parameters : String orderId, Date process date 
	 * - Return Type : boolean 
	 * - Throws : SQLException, ConnectionException, ProcessDateException 
	 * - Author : CAPGEMINI 
	 * - Creation Date : 25/09/2019 
	 * - Description : checking that process_date should be after delivery_date and before expiry_date.
	 ********************************************************************************************************/

	@Override
	public boolean processDateCheck(RawMaterialStock rawMaterialStock)
			throws SQLException, ConnectionException, ProcessDateException {
		Connection connection = null;
		boolean datecheck = false;

		try {
			try {
				connection = DBUtil.getInstance().getConnection();
			} catch (Exception e) {
				throw new ConnectionException(Constants.CONNECTION_EXCEPTION_MESSAGE_DBCONNECTION_ERROR);
			}
			PreparedStatement stmt = connection.prepareStatement(QueryMapper.CHECKPROCESSDATE);
			stmt.setInt(1, Integer.parseInt(rawMaterialStock.getOrderId()));

			ResultSet rs = stmt.executeQuery();
			

			java.sql.Date deliveryDate = null;
			java.sql.Date expiryDate = null;

			while (rs.next()) {

				deliveryDate = rs.getDate(1);
				
				expiryDate = rs.getDate(2);

				if (rawMaterialStock.getProcessDate().after(deliveryDate)
						&& rawMaterialStock.getProcessDate().before(expiryDate)) {
					datecheck = true;
					return datecheck;
				}
					
				else
					throw new ProcessDateException(Constants.PROCESS_DATE_EXCEPTION_MESSAGE);

			}

		} catch (SQLException e) {
			logger.error(Constants.LOGGER_ERROR_MESSAGE_QUERY_NOT_EXECUTED);
			throw new SQLException(Constants.LOGGER_ERROR_MESSAGE_QUERY_NOT_EXECUTED);

		}

		catch (ProcessDateException e) {
			logger.error(Constants.PROCESS_DATE_EXCEPTION_MESSAGE);
			throw e;

		}

		return datecheck;

	}

	/*******************************************************************************************************
	 * - Function Name : update process_date in Stock 
	 * - Input Parameters : String orderId, Date Process_date 
	 * - Return Type : void 
	 * - Throws : No Exception 
	 * - Author : CAPGEMINI 
	 * - Creation Date : 25/09/2019 
	 * - Description : updating process date for an orderId in the Raw Material Stock table.
	 ********************************************************************************************************/

	@Override
	public String updateProcessDateinStock(RawMaterialStock rawMaterialStock) {
		Connection connection = null;
		try {

			connection = DBUtil.getInstance().getConnection();

			PreparedStatement stmt = connection.prepareStatement(QueryMapper.UPDATEPROCESSDATE);
			stmt.setDate(1, DBUtil.stringtoDate(rawMaterialStock.getProcessDate()));
			stmt.setInt(2, Integer.parseInt(rawMaterialStock.getOrderId()));
			stmt.executeUpdate();

			connection.close();
			return Constants.DATA_INSERTED_MESSAGE;

		}

		catch (SQLException e) {
			logger.info(Constants.LOGGER_ERROR_MESSAGE_QUERY_NOT_EXECUTED);
			return Constants.LOGGER_ERROR_MESSAGE_QUERY_NOT_EXECUTED;

		} catch (Exception e) {
			logger.info(Constants.LOGGER_ERROR_MESSAGE_DATABASE_NOT_CONNECTED);
			return Constants.LOGGER_ERROR_MESSAGE_DATABASE_NOT_CONNECTED;
		}

	}

	/*******************************************************************************************************
	 * - Function Name : Track raw material order 
	 * - Input Parameters : String orderId 
	 * - Return Type : String 
	 * - Throws : No Exception 
	 * - Author : CAPGEMINI 
	 * - Creation Date : 23/09/2019 
	 * - Description : Raw Material order is tracked in the warehouse along with its shelf life
	 ********************************************************************************************************/

	@Override
	public String trackRawMaterialOrder(RawMaterialStock rawMaterialStock) {
		Connection connection = null;
		try {

			connection = DBUtil.getInstance().getConnection();

			PreparedStatement stmt = connection.prepareStatement(QueryMapper.TRACKRMORDER);
			stmt.setInt(1, Integer.parseInt(rawMaterialStock.getOrderId()));
			ResultSet rs = stmt.executeQuery();
			

			String warehouseId = null;
			java.sql.Date processDate = null;
			java.sql.Date deliveryDate = null;

			while (rs.next()) {

				processDate = rs.getDate(1);
				
				deliveryDate = rs.getDate(2);
				
				warehouseId = rs.getString(3);
				
			}

	
			String message = "The order ID had been in the warehouse with warehouseID = " + warehouseId + " from "
					+ deliveryDate.toString() + " to " + processDate.toString() + "("
					+ DBUtil.diffBetweenDays(processDate, deliveryDate) + " days)";

			return message;

		} catch (SQLException e) {
			logger.error(Constants.LOGGER_ERROR_MESSAGE_QUERY_NOT_EXECUTED);
			return Constants.LOGGER_ERROR_MESSAGE_QUERY_NOT_EXECUTED;
		} catch (Exception e) {
			logger.error(Constants.LOGGER_ERROR_MESSAGE_DATABASE_NOT_CONNECTED);
			return Constants.LOGGER_ERROR_MESSAGE_DATABASE_NOT_CONNECTED;
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
			}
		}
	}

}
