<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
 
 <jsp:include page="header.jsp"/>
 <h1>Add New Course</h1>  
<form:form method="post" action="save">    
 <table >    
  <tr>    
   <td>Name : </td>   
   <td><form:input path="name"  /></td>  
  </tr>    
  <tr>    
   <td>Description :</td>    
   <td><form:input path="description" /></td>  
  </tr>   
  <tr>    
   <td>Status :</td>    
   <td><input path="status" /></td>  
  </tr>   
    <tr>    
   <td>Contact :</td>    
   <td><input path="contact" /></td>  
  </tr> 
  <tr>    
   <td> </td>    
   <td><input type="submit" value="Save" /></td>    
  </tr>    
 </table>    
</form:form>  