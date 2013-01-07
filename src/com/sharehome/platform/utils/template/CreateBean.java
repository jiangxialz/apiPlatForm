package com.sharehome.platform.utils.template;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class CreateBean {
	static String url;   
	static String username ;
	static String password ;
	static String rt="\r\t";
	String SQLTables="show tables";
    
	static{
	try{
		Class.forName("com.mysql.jdbc.Driver");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@SuppressWarnings("static-access")
	public void setMysqlInfo(String url,String username,String password){
		   this.url=url;
		   this.username=username;
		   this.password=password;
	}
	public Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url, username, password);
	}
    public List<String> getTables() throws SQLException{
    	Connection con=this.getConnection();
    	PreparedStatement ps= con.prepareStatement(SQLTables);
    	ResultSet rs=ps.executeQuery();
    	List<String> list=new ArrayList<String>();
    	while(rs.next()){
    		String tableName=rs.getString(1);
    		list.add(tableName);
    	}
    	rs.close();
		ps.close();
		con.close();
		return list;
	}
    
    /**
     * 查询表的字段，封装成List
     * @param tableName
     * @return
     * @throws SQLException
     */
    public List<ColumnData> getColumnDatas(String tableName) throws SQLException{
    	String SQLColumns="SELECT distinct COLUMN_NAME, DATA_TYPE, COLUMN_COMMENT FROM information_schema.columns WHERE table_name =  '"+tableName+"' ";
        Connection con=this.getConnection();
    	PreparedStatement ps=con.prepareStatement(SQLColumns);
    	List<ColumnData>  columnList= new ArrayList<ColumnData>();
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
        	String name = rs.getString(1);
        	// 如果字段是以x_y形式命名的则要做相应的转换
        	String propName = conveColumnToProperty(name);
			String type = rs.getString(2);
			String comment = rs.getString(3);
			type=this.getType(type);
			
			ColumnData cd= new ColumnData();
			cd.setPropertyName(propName);
			cd.setColumnName(name);
			cd.setDataType(type);
			cd.setColumnComment(comment);
			columnList.add(cd);
        }
		rs.close();
		ps.close();
		con.close();
		return columnList;
    }
    
    /**
     * 生成实体Bean 的属性和set,get方法
     * @param tableName
     * @return
     * @throws SQLException
     */
    public String getBeanFeilds(String tableName) throws SQLException{
    	List<ColumnData> dataList = getColumnDatas(tableName);
    	StringBuffer str = new StringBuffer();
    	StringBuffer getset = new StringBuffer();
        for(ColumnData d : dataList){
        	String name = d.getColumnName();
        	// 如果字段是以x_y形式命名的则要做相应的转换
        	name = conveColumnToProperty(name);
			String type =  d.getDataType();
			String comment =  d.getColumnComment();
			String maxChar=name.substring(0, 1).toUpperCase();
			str.append("\r\t").append("private ").append(type+" ").append(name).append("; //  ").append(comment);
			String method=maxChar+name.substring(1, name.length());
			getset.append("\r\t").append("public ").append(type+" ").append("get"+method+"() {\r\t");
			getset.append("    return this.").append(name).append(";\r\t}");
			getset.append("\r\t").append("public void ").append("set"+method+"("+type+" "+name+") {\r\t");
			getset.append("    this."+name+"=").append(name).append(";\r\t}");
        }
        String argv=str.toString();
        String method=getset.toString();
		return argv+method;
    }
    
    /**
     * <将数据库字段名称转换为bean的属性名称>
     * <功能详细描述>
     * @param strValue
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public String conveColumnToProperty(String strValue){
    	if(StringUtils.isNotBlank(strValue) && strValue.indexOf("_")>-1){
    		String [] split=strValue.split("_");
    		StringBuffer sb=new StringBuffer();
    		sb.append(split[0]);
            for(int i=1;i<split.length;i++){
            	String splitValue=split[i].substring(0, 1).toUpperCase()+split[i].substring(1, split[i].length());
                sb.append(splitValue);
            }
            strValue = sb.toString();
    	}
    	return strValue;
    }
    
    /**
     * 
     * <br>
     * <b>功能：</b>详细的功能描述<br>
     * @param tableName
     * @return 
     * @throws SQLException
     */
//    public List<Map<String,String>> getColumnsMap(String tableName) throws SQLException{
//    	String SQLColumns="SELECT distinct COLUMN_NAME, DATA_TYPE, COLUMN_COMMENT FROM information_schema.columns WHERE  table_schema = 'ssi' and table_name =  '"+tableName+"' ";
////    	String SQLColumns="desc "+tableName;
//    	Connection con=this.getConnection();
//    	PreparedStatement ps=con.prepareStatement(SQLColumns);
//    	List<Map<String,String>> listMap=new ArrayList<Map<String,String>>();
//        ResultSet rs=ps.executeQuery();
//        while(rs.next()){
//        	Map<String,String> columnsMap=new HashMap<String, String>();
////        	String name = rs.getString(1);
////			String type = rs.getString(2);
////			String comment = rs.getString(3);
//			
//			
//			String name = rs.getString(1);
//			String type = rs.getString(2);
//			String comment = rs.getString(3);
//			type=this.getType(type);
//			columnsMap.put("COLUMN_NAME", name);
//			columnsMap.put("DATA_TYPE", type);
//			columnsMap.put("COLUMN_COMMENT", comment);
//			listMap.add(columnsMap);
//        }
//		rs.close();
//		ps.close();
//		con.close();
//		return listMap;
//    }
    
    public String getType(String type){
    	type=type.toLowerCase();
    	if("char".equals(type) || "varchar".equals(type) || "text".equals(type)){
			return "String";
		}else if("int".equals(type) || "smallint".equals(type) || "tinyint".equals(type) || "bit".equals(type)){
			return "Integer";
		}else if("bigint".equals(type) || "mediumint".equals(type)){
			return "Long";
		}else if("float".equals(type)){
			return "Float";
		}else if ("double".equals(type)) {
			return "Double";
		}else if("timestamp".equals(type) || "date".equals(type)  || "datetime".equals(type)){
			return "java.sql.Timestamp";
		}
    	return null;
    }
    
    public void getPackage(int type,String createPath, String content,String packageName,String className,String extendsClassName,String ... importName) throws Exception{
    	if(null==packageName){
    		packageName="";
    	}
    	StringBuffer sb=new StringBuffer();
    	sb.append("package ").append(packageName).append(";\r");
    	sb.append("\r");
    	for(int i=0;i<importName.length;i++){
    		sb.append("import ").append(importName[i]).append(";\r");
    	}
    	sb.append("\r");
    	sb.append("/**\r *  entity. @author wolf Date:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"\r */");
    	sb.append("\r");
    	sb.append("\rpublic class ").append(className);
    	if(null!=extendsClassName){
    		sb.append(" extends ").append(extendsClassName);
    	}
    	if(type==1){ //bean
    	   sb.append(" ").append("implements java.io.Serializable {\r");
    	}else{
    		sb.append(" {\r");
    	}
    	sb.append("\r\t");
    	sb.append("private static final long serialVersionUID = 1L;\r\t");
    	String temp=className.substring(0, 1).toLowerCase();
    	temp+= className.substring(1, className.length());
    	if(type==1){
    	sb.append("private "+className+" "+temp+"; // entity ");
    	}
    	sb.append(content);
    	sb.append("\r}");
    	System.out.println(sb.toString());
    	this.createFile(createPath, "", sb.toString());
    }
   
    /**
     * 
     * <br>
     * <b>功能：</b>表名转换成类名 每_首字母大写<br>
     * @param tableName
     * @return
     */
    public String getTablesNameToClassName(String tableName){
    	String [] split=tableName.split("_");
    	if(split.length>1){
    		StringBuffer sb=new StringBuffer();
            for(int i=0;i<split.length;i++){
            	String tempTableName=split[i].substring(0, 1).toUpperCase()+split[i].substring(1, split[i].length());
                sb.append(tempTableName);
            }
            System.out.println(sb.toString());
            return sb.toString();
    	}else{
    		String tempTables=split[0].substring(0, 1).toUpperCase()+split[0].substring(1, split[0].length());
    		return tempTables;
    	}
    }
    /**
     * 
     * <br>
     * <b>功能：</b>创建文件<br>
     * @param path
     * @param fileName
     * @param str
     * @throws IOException
     */
   public void createFile(String path,String fileName,String str) throws IOException{
    	FileWriter writer=new FileWriter(new File(path+fileName));
    	writer.write(new String(str.getBytes("utf-8")));
    	writer.flush();
    	writer.close();
    }
   /**
    * 
    * <br>
    * <b>功能：</b>生成sql语句<br>
    * @param tableName
    * @throws Exception
    */
    static String selectStr="select ";
    static String from=" from ";
    
    public Map<String,Object> getAutoCreateSql(String tableName) throws Exception{
   	 	 Map<String,Object> sqlMap=new HashMap<String, Object>();
   	 	 List<ColumnData> columnDatas = getColumnDatas(tableName);
	     String columnName=this.getColumnNameSplit(columnDatas);
	     String propName = this.getPropNameSplit(columnDatas);
	     String[] columnList =  getListByStrValue(columnName);  //表所有字段
	     String[] propList =  getListByStrValue(propName);  //表所有字段对应的bean中属性名称
	     String columnFields =  getColumnFields(columnName); //表所有字段 按","隔开
	     String insert="insert into "+tableName+"("+columnName.replaceAll("\\|", ",")+")\n values(#{"+propName.replaceAll("\\|", "},#{")+"})";
	     String update= getUpdateSql(tableName, columnList , propList);
	     String updateSelective = getUpdateSelectiveSql(tableName, columnDatas);
	     String selectById = getSelectByIdSql(tableName, columnDatas);
	     String delete = getDeleteSql(tableName, columnDatas);
//	     sqlMap.put("columnList",columnList);
	     sqlMap.put("columnFields",columnFields);
	     sqlMap.put("insert", insert);
	     sqlMap.put("update", update);
	     sqlMap.put("delete", delete);
	     sqlMap.put("updateSelective", updateSelective);
	     sqlMap.put("selectById", selectById);
	     return sqlMap;
   }
    
    /**
     * delete 
     * @param tableName
     * @param columnsList
     * @return
     * @throws SQLException
     */
    public String getDeleteSql( String tableName,List<ColumnData> columnsList)throws SQLException{
	   	 StringBuffer sb=new StringBuffer();
	   	 sb.append("delete");
	     sb.append("\t from ").append(tableName).append(" where ");
	     sb.append(columnsList.get(0).getColumnName()).append(" = #{").append(columnsList.get(0).getPropertyName()).append("}");
	     return sb.toString();
   }
    
    /**
     * 根据id查询
     * @param tableName
     * @param columnsList
     * @return
     * @throws SQLException
     */
    public String getSelectByIdSql( String tableName,List<ColumnData> columnsList)throws SQLException{
    	 StringBuffer sb=new StringBuffer();
    	 sb.append("select <include refid=\"Base_Column_List\" /> \n");
 	     sb.append("\t from ").append(tableName).append(" where ");
 	     sb.append(columnsList.get(0).getColumnName()).append(" = #{").append(columnsList.get(0).getPropertyName()).append("}");
    	return sb.toString();
    }
    
    /**
     * 获取所有的字段，并按","分割
     * @param columns
     * @return
     * @throws SQLException
     */
    public String getColumnFields(String columns)throws SQLException{
    	String fields = columns;
    	if(fields != null && !"".equals(fields)){
    		fields = fields.replaceAll("[|]", ",");
    	}
    	return fields;
    }
    
    /**
     * 
     * @param columns
     * @return
     * @throws SQLException
     */
    public String[] getListByStrValue(String strValue)throws SQLException{
    	 String[] strValueList=strValue.split("[|]");
	     return strValueList;
    }
    
    /**
     * 修改记录
     * @param tableName
     * @param columnsList
     * @return
     * @throws SQLException
     */
    public String getUpdateSql(String tableName,String[] columnsList ,String[] propList)throws SQLException{
    	 StringBuffer sb=new StringBuffer();
    	 
	     for(int i=1;i<columnsList.length;i++){
	    	  sb.append(columnsList[i]+"=#{"+propList[i]+"}");
	    	  //最后一个字段不需要","
	    	  if((i+1) < columnsList.length){
	    		  sb.append(",");
	    	  }
	     }
    	 String update = "update "+tableName+" set "+sb.toString()+" where "+columnsList[0]+"=#{"+propList[0]+"}";
	     return update;
    }
    
    public String getUpdateSelectiveSql(String tableName,List<ColumnData> columnList)throws SQLException{
   	 	StringBuffer sb=new StringBuffer();
   	    ColumnData cd = columnList.get(0); //获取第一条记录，主键
   	 	sb.append("\t<trim  suffixOverrides=\",\" >\n");
   	 	 for(int i=1;i<columnList.size();i++){
   	 		 ColumnData data = columnList.get(i);
   	 		 sb.append("\t<if test=\"").append(data.getPropertyName()).append(" != null");
   	 		 //String 还要判断是否为空
   	 		 if("String" == data.getDataType()){
   	 			sb.append(" and ").append(data.getPropertyName()).append(" != ''");
   	 		 }
   	 		 sb.append("\">\n\t\t");
   	 		 sb.append(data.getColumnName()+"=#{"+data.getPropertyName()+"},\n");
   	 		 sb.append("\t</if>\n");
	     }
	     sb.append("\t</trim>");
	     String update = "update "+tableName+" set \n"+sb.toString()+" where "+cd.getColumnName()+"=#{"+cd.getPropertyName()+"}";
	     return update;
   }
   
    /**
     * 
     * <br>
     * <b>功能：</b>获取所有列名字<br>
     * @param tableName
     * @return
     * @throws SQLException
     */
    public String getColumnNameSplit(List<ColumnData> columnList) throws SQLException{
 	     StringBuffer commonColumnName=new StringBuffer();
 	     for(ColumnData data : columnList){
 	    	commonColumnName.append(data.getColumnName()+"|");
 	     }
 	     return commonColumnName.delete(commonColumnName.length()-1, commonColumnName.length()).toString();
    }
    
    public String getPropNameSplit(List<ColumnData> columnList) throws SQLException{
	     StringBuffer commonPropName=new StringBuffer();
	     for(ColumnData data : columnList){
	    	 commonPropName.append(data.getPropertyName()+"|");
	     }
	     return commonPropName.delete(commonPropName.length()-1, commonPropName.length()).toString();
   }
    
}
