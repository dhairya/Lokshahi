<%@ include file="/Templates/initial.jsp" %>
<%@ include file="/Templates/headerW.jsp" %>

    <!-- B.2 MAIN CONTENT -->
    <div class="main-content">
      <div id="translation"></div>
      <div id="article">
        <!-- Pagetitle -->
        <h1 class="pagetitle">Post a Problem</h1>
        <!-- Content unit - One column -->
        <div class="column1-unit"><br />
          <form name="addgrev" method="post" action="/CMS/Grievance_add?consti=<%=consti%>&ward=<%=ward%>">
            Title
            <input type="text" name="grev_title" />
            <br />
            <br />
            <p>Description <br />
              <textarea rows="5" cols="40" name="grev"></textarea>
            </p>
            <br />
            <br />
            <%ResultSet rs= (ResultSet)request.getAttribute("cats");%>
            <p> Category:
              <select name="dept">
              <% while(rs.next()){%>
                <option value="<%=rs.getString("CAT_ID")%>"><%=rs.getString("CATEGORY")%></option>
              <%} %>
              </select>
              <br />
              <br />
            </p>
            <input type="submit" name="Submit" />
          </form>
        </div>
        <hr class="clear-contentunit" />
        <!-- Content unit - One column -->
      </div>
    </div>

    
    <%@ include file="/Templates/footer.jsp" %>        
    