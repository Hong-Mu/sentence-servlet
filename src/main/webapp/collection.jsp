<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="com.hongmu.sentence.dao.SentenceDao" %>
<%@ page import="com.hongmu.sentence.dao.CollectionDao" %>
<%@ page import="com.hongmu.sentence.model.Sentence" %>
<%@ page import="java.util.List" %>

<%
    int collectionId = Integer.parseInt(request.getParameter("id"));
    SentenceDao dao = new SentenceDao();
    CollectionDao collectionDao = new CollectionDao();
    String title = collectionDao.get(collectionId).getTitle();
    
    List<Sentence> list = dao.getByCollectionId(collectionId);
    List<Sentence> allList = dao.getAll();
%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="bg-gray-100 h-screen">
    <%@ include file="/include/header.jsp" %>
    <div class="h-full flex items-center justify-center py-16">
        <div class="bg-white p-8 rounded-lg shadow-lg max-w-3xl w-full h-full">
            <h1 class="text-2xl font-bold"><a href="index.jsp"><%= title %></a></h1>
            <div class="text-sm mb-8 text-red-500">${message}</div>
            <% for (int i = 0; i < list.size(); i++) { %>
            <div>  
                <div class="text-xl font-bold"><%= list.get(i).getTitle() %></div>
                <div class="text-sm text-gray-500"><%= list.get(i).getSubtitle() %></div>
            </div>
            <% } %>

            <!-- 이미 추가된 sentence들 중 하나를 선택하여 collection에 추가하는 폼-->

            <form class="mt-16" action="/Sentence/sc" method="post">
                <input type="hidden" name="collectionId" value="<%= collectionId %>">
                <select name="sentenceId" class="border border-gray-300 rounded-lg p-2 w-full mb-4 px-8">
                    <% for (int i = 0; i < allList.size(); i++) { %>
                    <option value="<%= allList.get(i).getId() %>"><%= allList.get(i).getTitle() %></option>
                    <% } %>
                </select>
                <button type="submit" class="bg-blue-500 text-white rounded-lg p-2 w-full">추가</button>
            </form>
        </div>
    </div>
</body>

</html>